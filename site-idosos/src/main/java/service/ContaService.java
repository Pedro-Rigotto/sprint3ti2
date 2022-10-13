package service;

import java.util.Scanner;
import model.Usuario;
import dao.UsuarioDAO;
import spark.Request;
import spark.Response;
import java.io.File;

/**
 * Mostra a página compra.html e faz a conexão dela com o UsuarioDAO.
 * @author Pedro R, André M
 */
public class ContaService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private String form;


    public ContaService() {
        makeForm();
    }

    public void makeForm() {
        makeForm(new Usuario());
    }

    public void makeForm(Usuario usuario) { 
        String nomeArquivo = "src/main/resources/public/front-end/compra-manutenção-usuários/compra.html";///src/main/resources/public/compra.html src/main/resources/public/compra.html
        form = "";
        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
    
            while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
            entrada.close();
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

    public Object insert(Request request, Response response) {
        String username = request.queryParams("username");
        String nome = request.queryParams("nome");
        String email = request.queryParams("email");
        String password = request.queryParams("password");
        int tipoUsuario = Integer.parseInt(request.queryParams("tipo_usuario"));
        String telefone = request.queryParams("telefone");

        String resp = "";

        Usuario usuario = new Usuario(username, nome, email, password, tipoUsuario, telefone, -1);

        if(usuarioDAO.insert(usuario) == true) {
            resp = "Usuario (" + username + ") inserido!";
            response.status(201); // 201 Created
        } else {
            resp = "Erro! Usuario (" + username + ") não inserido!";
            response.status(404); // 404 Not found
        }

        makeForm();
        return form.replaceFirst("<div display=\"none\" id=\"mensagemDeAviso\"></div>", "<div class=\"alert alert-warning\">" + resp + "</div>");
    }

    public Object getCriar(Request request, Response response) {
        makeForm();
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
    }
}
