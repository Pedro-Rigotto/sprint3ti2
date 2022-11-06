package service;

import java.util.Scanner;
import spark.Request;
import spark.Response;
import java.io.File;
import dao.TutorialDAO;
import model.Tutorial;
import java.util.List;

/**
 * Mostra um tutorial.
 * @author Pedro R
 */
public class PagTutorialService {
    private TutorialDAO tutorialDAO = new TutorialDAO();
    private String form;

    public PagTutorialService() {
        makeForm();
    }

    public void makeForm() {
        makeForm(0);
    }

    /**
     * Monta a página a ser mostrada, e chama o TutorialDAO para fazer as requisições SQL.
     * @param id id do tutorial a ser exibido
     */
    public void makeForm(int id) {
        String nomeArquivo = "src/main/resources/public/front-end/tutorial/index.html";
        form = "";

        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
    
            while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
            entrada.close();

            
        } catch (Exception e) { System.out.println(e.getMessage()); }

        String dados = "";

        if(id>0) {
            Tutorial tutorial = tutorialDAO.get(id);
            
            dados += "    <div class=\"row\">"
                + "        <div class=\"col-12\">"
                + "            <h1 id=\"title\">Tutorial: " + tutorial.getTitulo() + "</h1>"
                + "        </div>"
                + "    </div>"
                + "    <div class=\"row\">"
                + "        <div class=\"col-12\" id=\"colTutorial\">"
                + "            <div id=\"divVideo\">"
                + "                <iframe"
                + "                    width=\"560\""
                + "                    height=\"315\""
                + "                    src=\"" + tutorial.getVideo() + "\""
                + "                    title=\"YouTube video player\""
                + "                    frameborder=\"0\""
                + "                    allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\""
                + "                    allowfullscreen"
                + "                    class=\"w-100\""
                + "                ></iframe>"
                + "            </div>"
                + "            <div id=\"textoTutorial\">"
                + "                <p>"
                + "                    " + tutorial.getTexto()
                + "                </p>"
                + "            </div>"
                + "        </div>"
                + "    </div>";
            form = form.replaceFirst("<TUTORIAL>", dados);
        }
    }
    
    /**
     * Exibe a página de um tutorial.
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object getTutorial(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        makeForm(id);

        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
        return form;
    }
}
