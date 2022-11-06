package service;

import java.util.Scanner;
import spark.Request;
import spark.Response;
import java.io.File;

/**
 * Mostra a página de contato.
 * @author Pedro R
 */
public class ContatoService {
    private String form;

    public ContatoService() {
        makeForm();
    }

    /**
     * Carrega o arquivo html da página de contato
     */
    public void makeForm() {
        String nomeArquivo = "src/main/resources/public/front-end/EntreEmContato/contato.html";
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
     * Exibe a página de contato
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object getContato(Request request, Response response) {
        makeForm();
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
		return form;
    }
}

