package service;

import java.util.Scanner;
import spark.Request;
import spark.Response;
import java.io.File;
import dao.TutorialDAO;
import model.Tutorial;
import java.util.List;

/**
 * Lista os tutoriais de uma certa categoria.
 * @author Pedro R
 */
public class ListaTutorialService {
    private String form;
    private TutorialDAO tutorialDAO = new TutorialDAO();

    public ListaTutorialService() {
        makeForm();
    }

    public void makeForm() {
        makeForm(0);
    }

    /**
     * Carrega o arquivo html da página de tutoriais
     * @param id id da categoria a ser listada
     */
    public void makeForm(int id) {
        String nomeArquivo = "src/main/resources/public/front-end/tipos-para-cada-categoria/index.html";
        form = "";

        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
    
            while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
            entrada.close();
        } catch (Exception e) { System.out.println(e.getMessage()); }

        String inserir = "";
        List<Tutorial> tutoriais = tutorialDAO.getCategoria(id);
        for(Tutorial p : tutoriais) {
            inserir += "<div class=\"col-12 col-xl-6\">"
                     + "<a href=\"/tutorial/" + p.getId() + "\">" 
                     + "<button class=\"custom-btn btn-13\">" + p.getTitulo()
                     + "</button></a></div>";
        }

        form = form.replaceFirst("<BOTOES>", inserir);
    }

    /**
     * Exibe a página de tutoriais de uma certa categoria
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object getTutoriais(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        makeForm(id);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
		return form;
    }
}
