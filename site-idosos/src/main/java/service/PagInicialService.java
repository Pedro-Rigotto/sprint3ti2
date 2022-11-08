package service;

import java.util.Scanner;
import spark.Request;
import spark.Response;
import java.io.File;

/**
 * Mostra a página inicio.html.
 * @author Pedro R
 */
public class PagInicialService {
    private String form;

    public PagInicialService() {
        makeForm();
    }

    /**
     * Carrega o arquivo html da página inicial
     */
    public void makeForm() {
        String nomeArquivo = "src/main/resources/public/front-end/inicio-login/inicio.html";
        form = "";

        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
    
            while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
            entrada.close();
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

    /**
     * Exibe a página inicial
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object getIniciar(Request request, Response response) {
        makeForm();
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
        String botoes = "";
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
            botoes = "<div class=\"col-md-12 col-12 coluna-botao\">"
                   + "<a href=\"/listacategorias\"><button class=\"custom-btn btn-13\"><h4>Acessar tutoriais</h4></button></a>"
                   + "</div>";
        } else {
            botoes = "<div class=\"col-md-4 col-12 coluna-botao\">"
                   + "<a href=\"/listacategorias\"><button class=\"custom-btn btn-13\"><h4>Acessar tutoriais</h4></button></a>"
                   + "</div>"
                   + "<div class=\"col-md-4 col-12 coluna-botao\">"
                   + "    <a href=\"/loginusuario\"><button class=\"custom-btn btn-13\"><h4>Entrar em conta</h4></button></a>"
                   + "</div>"
                   + "<div class=\"col-md-4 col-12 coluna-botao\">"
                   + "    <a href=\"/criarusuario\"><button class=\"custom-btn btn-13\"><h4>Criar conta</h4></button></a>"
                   + "</div>";
        }
        form = form.replaceFirst("<BOTOES>", botoes);
		return form;
    }

    /**
     * Faz o logout do usuario
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object desloga(Request request, Response response) {
        request.session().removeAttribute("logado");
        makeForm();
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
        String botoes = "";
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
            botoes = "<div class=\"col-md-12 col-12 coluna-botao\">"
                   + "<a href=\"/listacategorias\"><button class=\"custom-btn btn-13\"><h4>Acessar tutoriais</h4></button></a>"
                   + "</div>";
        } else {
            botoes = "<div class=\"col-md-4 col-12 coluna-botao\">"
                   + "<a href=\"/listacategorias\"><button class=\"custom-btn btn-13\"><h4>Acessar tutoriais</h4></button></a>"
                   + "</div>"
                   + "<div class=\"col-md-4 col-12 coluna-botao\">"
                   + "    <a href=\"/loginusuario\"><button class=\"custom-btn btn-13\"><h4>Entrar em conta</h4></button></a>"
                   + "</div>"
                   + "<div class=\"col-md-4 col-12 coluna-botao\">"
                   + "    <a href=\"/criarusuario\"><button class=\"custom-btn btn-13\"><h4>Criar conta</h4></button></a>"
                   + "</div>";
        }
        form = form.replaceFirst("<BOTOES>", botoes);
		return form;
    }

    public Object mostraChatbot (Request request, Response response) {
        String nomeArquivo = "src/main/resources/public/front-end/EntreEmContato/dificuldades.html";
        form = "";

        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
    
            while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
            entrada.close();
        } catch (Exception e) { System.out.println(e.getMessage()); }
        
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
		return form;
    }
}
