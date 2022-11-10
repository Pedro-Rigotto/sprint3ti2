package service;

import java.util.Scanner;
import model.Usuario;
import dao.UsuarioDAO;
import spark.Request;
import spark.Response;
import java.io.File;
import dao.DAO;

/**
 * Efetua o Login no site
 * @author Pedro R ,Andre M, Henrique
 */
public class LoginService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private String form;

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
     * Faz o login do usuário 
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object loga(Request request, Response response) {
        String nomeUsuario = (request.queryParams("username"));
        Usuario usuario = usuarioDAO.getUsuario(nomeUsuario);
        if (!usuario.equals(new Usuario())) {
            try {
                if (DAO.toMD5(request.queryParams("password")).equals(usuario.getPassword())) {
                    request.session(true);
                    response.status(200);
                    /*String strJson = "{\"login\":\"" + usuario.getUsername() + "\",\"nome\":\"" 
                        + usuario.getNome() +  "\",\"id\":\"" + usuario.getId() +  "\",\"telefone\":\"" 
                        + usuario.getTelefone() + "\",\"tipo\":\"user\"}";
                    request.session().attribute("usuarioCorrente", strJson);*/
                    request.session().attribute("usuario", usuario.getUsername());
                    request.session().attribute("nome", usuario.getNome());
                    request.session().attribute("id", usuario.getId());
                    request.session().attribute("telefone", usuario.getTelefone());
                    request.session().attribute("logado", true);
                    request.session().attribute("tipo", usuario.getTipoUsuario());
                    //System.out.println(request.session().attribute("usuarioCorrente").toString());
                    //System.out.println(strJson);

                    //System.out.println(request.session().attribute("usuarioCorrente").toString());
                    if(usuario.getTipoUsuario() == 0) {
                        response.redirect("/listacategorias");
                    } else if(usuario.getTipoUsuario() == 1) {
                        response.redirect("/admin");
                    } else {
                        response.redirect("/logout");
                    }
                } else {
                    //System.out.println("Senha incorreta!");
                    //System.out.println(request.queryParams("password") + " " + usuario.getPassword());
                    response.status(404);
                    String resp = "Senha incorreta!";
                    form.replaceFirst("<input type=\"hidden\" id=\"msg\" "
                            + "name=\"msg\" value=\"\">",
                            "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            //System.out.println("Usuário não foi encontrado!");
            response.status(404);
            String resp = "Usuario " + usuario.getUsername() + " não encontrado";
            form.replaceFirst("<input type=\"hidden\" id=\"msg\" "
                    + "name=\"msg\" value=\"\">",
                    "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"" + resp + "\">");

        }
        return form;
    }
    

    /**
     * Mostra a página de login do usuário
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object getCriar(Request request, Response response) {
        makeForm();
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
        return form;
    }
}
