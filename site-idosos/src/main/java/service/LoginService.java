package service;

import static spark.Spark.*;
import java.util.Scanner;
import model.Usuario;
import dao.UsuarioDAO;
import spark.Request;
import spark.Response;
import java.io.File;
//import org.json;

public class LoginService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private String form;
    private final int FORM_DETAIL = 2;
    private final int FORM_ORDERBY_USERNAME = 2;

    public LoginService() {
        makeForm();
    }

    public void makeForm() {
        makeForm(new Usuario());
    }

    public void makeForm(Usuario usuario) {
        String nomeArquivo = "src/main/resources/public/front-end/inicio-login/login.html";
        form = "";
        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));

            while (entrada.hasNext()) {
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param request
     * @param response
     * @return
     */
    /*public Object get(Request request, Response response) {
        String id = (request.params(":id"));
        Usuario usuario = usuarioDAO.get(Integer.parseInt(id));
        if (usuario != null) {
            response.status(200); // success
            request.session(true);

        } else {
            response.status(404); // 404 Not found
            String resp = "Usuario " + usuario + " não encontrado.";
            makeForm();
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">",
                    "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
        }

        return form;
    }*/

    /**
     * @param request
     * @param response
     * @return
     */
    public Object loga(Request request, Response response) {
        String nomeUsuario = (request.params(":username"));
        Usuario usuario = usuarioDAO.getUsuario(nomeUsuario);
        if (!usuario.equals(null)) {
            if (request.params(":password") == usuario.getPassword()) {
                response.status(200);
                request.session().attribute("usuarioCorrente");
                request.session().attribute("username", usuario.getUsername());
                request.session().attribute("tipoUsuario", usuario.getTipoUsuario());
            } else {
                response.status(404);
                String resp = "Senha incorreta!";
                form.replaceFirst("<input type=\"hidden\" id=\"msg\" "
                        + "name=\"msg\" value=\"\">",
                        "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
            }
        } else {
            response.status(404);
            String resp = "Usuario " + usuario.getUsername() + " não encontrado";
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" "
                    + "name=\"msg\" value=\"\">",
                    "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");

        }
        return form;
    }
    


    public Object getCriar(Request request, Response response) {
        makeForm();
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        return form;
    }
}
