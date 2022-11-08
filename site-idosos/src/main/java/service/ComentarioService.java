package service;

import java.util.Scanner;
import model.Comentario;
import dao.ComentarioDAO;
import spark.Request;
import spark.Response;
import java.io.File;
import java.time.LocalDate;

/**
 * Cria, edita e deleta comentários.
 * @author Pedro R
 */
public class ComentarioService {
    ComentarioDAO comentarioDAO = new ComentarioDAO();
    private String form;

    public ComentarioService() {
        makeForm();
    }

    public void makeForm() {
        form = "";
    }

    /**
     * Carrega a página de inserir comentário
     * @param idTutorial id do tutorial em que estamos adicionando o comentário
     */
    public void makeFormInsert(int idTutorial) {
        String nomeArquivo = "src/main/resources/public/front-end/tutorial/addcomentario.html";
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
        String formInsert = "<form id=\"formAddCom\" action=\"/inserircomentario\" method=\"post\" autocomplete=\"off\">"
                          + "<h1>Adicionar comentário</h1>"
                          + "<br>"
                          + "<textarea type=\"text\" class=\"form-control\" name=\"inputCom\" id=\"inputCom\" required placeholder=\"Escreva aqui o seu comentário...\"></textarea>"
                          + "<input type=\"hidden\" value=\"" + idTutorial + "\" name=\"idTutorial\" id=\"idTutorial\">"
                          + "<div style=\"text-align:right\">"
                          + "    <input type=\"submit\" class=\"btn-13 custom-btn\" id=\"btnAddCom\" value=\"Salvar\">"
                          + "</div> "
                          + "</form>";
        form = form.replaceFirst("<FORMULARIO>", formInsert);
    }

    /**
     * Carrega a página de atualizar comentário
     * @param id id do comentário que estamos editando
     */
    public void makeFormUpdate(int id) {
        String nomeArquivo = "src/main/resources/public/front-end/tutorial/addcomentario.html";
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
        String textoComentario = comentarioDAO.get(id).getDescricao();
        String formInsert = "<form id=\"formAddCom\" action=\"/mandareditcomentario/" + id + "\" method=\"post\" autocomplete=\"off\">"
                          + "<h1>Editar comentário</h1>"
                          + "<br>"
                          + "<textarea type=\"text\" class=\"form-control\" name=\"inputCom\" id=\"inputCom\" required placeholder=\"" + textoComentario + "\">" + textoComentario + "</textarea>"
                          + "<input type=\"hidden\" value=\"" + comentarioDAO.get(id).getTutorial() + "\" name=\"idTutorial\" id=\"idTutorial\">"
                          + "<div style=\"text-align:right\">"
                          + "    <input type=\"submit\" class=\"btn-13 custom-btn\" id=\"btnAddCom\" value=\"Salvar\">"
                          + "</div> "
                          + "</form>";
        form = form.replaceFirst("<FORMULARIO>", formInsert);
    }

    /**
     * Exibe a página de criar um comentário.
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object criaComentario (Request request, Response response) {
        int idTutorial = Integer.parseInt(request.params("idtutorial"));
        makeFormInsert(idTutorial);
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        } else {
            response.redirect("/loginusuario");
            return "";
        }
        return form;
    }

    /**
     * Exibe a página de editar um comentário.
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object editaComentario (Request request, Response response) {
        int id = Integer.parseInt(request.params("id"));
        makeFormUpdate(id);
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        } else {
            response.redirect("/loginusuario");
            return "";
        }
        return form;
    }

    /**
     * Faz a inserção de um comentário.
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object insereComentario (Request request, Response response) {
        int idTutorial = Integer.parseInt(request.queryParams("idTutorial"));
        String textoComentario = request.queryParams("inputCom");
        int autor = request.session().attribute("id");
        LocalDate localDate = LocalDate.now();
        Comentario comentario = new Comentario(-1, textoComentario, localDate, 1, autor, idTutorial);
        if (comentarioDAO.insert(comentario) == true) {
            response.status(201); // 201 Created
            response.redirect("/tutorial/" + idTutorial);
            makeFormInsert(idTutorial);
            return form;
        } else {
            response.status(404); // 404 Not found
            makeFormInsert(idTutorial);
            return form;
        }
    }

    /**
     * Faz a alteração de um comentário.
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object mandaEditarComentario (Request request, Response response) {
        int idTutorial = Integer.parseInt(request.queryParams("idTutorial"));
        int id = Integer.parseInt(request.params(":id"));
        String textoComentario = request.queryParams("inputCom");
        int autor = request.session().attribute("id");
        LocalDate localDate = comentarioDAO.get(id).getDataPublicacao();
        Comentario comentario = new Comentario(id, textoComentario, localDate, 1, autor, idTutorial);
        Comentario comentario2 = comentarioDAO.get(id);
        if(request.session().attribute("logado") != null) {
            int idLogada = request.session().attribute("id");
            if (idLogada == comentario2.getAutor()) {
                if (comentarioDAO.update(comentario) == true) {
                    response.status(201); // 201 Created
                    response.redirect("/tutorial/" + idTutorial);
                    makeFormUpdate(idTutorial);
                    return form;
                } else {
                    response.status(404); // 404 Not found
                    makeFormUpdate(idTutorial);
                    return form;
                }
            } else {
                response.status(404); // 404 Not found
                response.redirect("/tutorial/" + idTutorial);
                return "";
            }
        } else {
            response.redirect("/loginusuario");
            return "";
        }
    }

    /**
     * Faz a remoção de um comentário.
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object mandaDeletarComentario (Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Comentario comentario = comentarioDAO.get(id);
        int idTutorial = comentario.getTutorial();
        if(request.session().attribute("logado") != null) {
            int idLogada = request.session().attribute("id");
            if (idLogada == comentario.getAutor()) {
                if (comentarioDAO.delete(id) == true) {
                    response.status(201); // 201 Created
                    response.redirect("/tutorial/" + idTutorial);
                    return "";
                } else {
                    response.status(404); // 404 Not found
                    response.redirect("/tutorial/" + idTutorial);
                    return "";
                }
            } else {
                response.status(404); // 404 Not found
                response.redirect("/tutorial/" + idTutorial);
                return "";
            }
        } else {
            response.redirect("/loginusuario");
            return "";
        }
    }
}
