package service;

import java.util.Scanner;
import spark.Request;
import spark.Response;
import java.io.File;
import dao.TutorialDAO;
import dao.ComentarioDAO;
import dao.UsuarioDAO;
import model.Tutorial;
import model.Comentario;
import model.Usuario;
import java.util.List;

/**
 * Mostra um tutorial e seus comentários.
 * @author Pedro R
 */
public class PagTutorialService {
    private TutorialDAO tutorialDAO = new TutorialDAO();
    private ComentarioDAO comentarioDAO = new ComentarioDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private String form;

    public PagTutorialService() {
        makeForm();
    }

    public void makeForm() {
        makeForm(0, -1);
    }

    /**
     * Monta a página a ser mostrada, e chama o TutorialDAO, UsuarioDAO e ComentarioDAO
     * para fazer as requisições SQL.
     * @param id id do tutorial a ser exibido
     * @param idUsuario id do usuário que está logado
     */
    public void makeForm(int id, int idUsuario) {
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

        List<Comentario> comentarios = comentarioDAO.getTutorial(id);
        String insertComentarios = "";

        insertComentarios += "<table class=\"table table-striped\" style=\"text-align:justify\">"
                           + "<thead>"
                           + "    <tr>"
                           + "        <th scope=\"col\">Nome</th>"
                           + "        <th scope=\"col\">Comentário</th>"
                           + "        <th scope=\"col\"></th>"
                           + "    </tr>"
                           + "</thead>"
                           + "<tbody>";

        for(Comentario p : comentarios) {
            Usuario autor = usuarioDAO.get(p.getAutor());
            insertComentarios += "<tr>"
                               + "<th scope=\"row\">" + autor.getNome() + "</th>"
                               + "<td>" + p.getDescricao() + "</td>";
            if(autor.getId() == idUsuario) {
                insertComentarios += "<td><a href=\"/editarcomentario/" + p.getId() + "\">Editar</a> | <a href=\"/deletarcomentario/" + p.getId() + " \">Deletar</a></td>";
            } else {
                insertComentarios += "<td></td>";
            }
            insertComentarios += "</tr>";
        }

        insertComentarios += "</tbody>"
                           + "</table>";
        form = form.replaceFirst("<COMENTARIOS>", insertComentarios);

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
        int idUsuario = -1;
        if(request.session().attribute("logado") != null) {
            idUsuario = request.session().attribute("id");
        } else {
            idUsuario = -1;
        }
        

        makeForm(id, idUsuario);

        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
        form = form.replaceFirst("/addcomentario", "/addcomentario/" + id);
        return form;
    }
}
