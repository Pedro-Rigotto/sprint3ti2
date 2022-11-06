package service;

import java.util.Scanner;
import spark.Request;
import spark.Response;
import java.io.File;
import dao.CategoriaDAO;
import model.Categoria;
import java.util.List;

/**
 * Lista as categorias de tutoriais.
 * @author Pedro R
 */
public class ListaCategoriaService {
    private String form;
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private int FORM_CATEGORIA = 1;
    private int FORM_SUPERCATEGORIA = 2;

    public ListaCategoriaService() {
        makeForm();
    }

    public void makeForm() {
        makeForm(FORM_SUPERCATEGORIA, "");
    }

    /**
     * Carrega o arquivo html da página de categorias
     * @param tipo Informa se a lista será de categorias ou subcategorias
     * @param supercategoria A supercategoria desejada, pode ser string vazia caso 
     *                      seja desejada a lista de supercategorias
     */
    public void makeForm(int tipo, String supercategoria) {
        String nomeArquivo = "src/main/resources/public/front-end/cat-premium/index.html";
        form = "";

        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
    
            while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
            entrada.close();
        } catch (Exception e) { System.out.println(e.getMessage()); }

        String inserir = "";
        if(tipo == FORM_CATEGORIA) {
            List<Categoria> categorias = categoriaDAO.getSupercategoria(supercategoria, "nome");
            for(Categoria p : categorias) {
                inserir += "<div class=\"col-12 col-xl-6\">"
                        + "<a href=\"/listatutoriais/" + p.getId() + "\">" 
                        + "<button class=\"custom-btn btn-13\">" + p.getNome()
                        + "</button></a></div>";
            }
        } else if (tipo == FORM_SUPERCATEGORIA) {
            List<String> supercategorias = categoriaDAO.getListaSupercategoria("supercategoria");
            for(String p : supercategorias) {
                inserir += "<div class=\"col-12 col-xl-6\">"
                        + "<a href=\"/listacategorias/" + p + "\">" 
                        + "<button class=\"custom-btn btn-13\">" + p
                        + "</button></a></div>";
            }
        }

        form = form.replaceFirst("<BOTOES>", inserir);
    }

    /**
     * Exibe a página de categorias
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object getCategorias(Request request, Response response) {
        String supercategoria = request.params(":supercategoria");
        makeForm(FORM_CATEGORIA, supercategoria);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
		return form;
    }

    /**
     * Exibe a página de supercategorias
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object getSuperCategorias(Request request, Response response) {
        makeForm(FORM_SUPERCATEGORIA, "");
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
		return form;
    }
}
