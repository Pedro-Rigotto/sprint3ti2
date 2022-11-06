package service;

import java.util.Scanner;
import model.Tutorial;
import dao.TutorialDAO;
import spark.Request;
import spark.Response;
import java.io.File;
import java.util.List;
import java.time.LocalDate;

/**
 * Mostra a página de cadastro de tutorial e faz a conexão com o TutorialDAO.
 * 
 * @author Pedro R, André M
 */
public class TutorialService {
    private TutorialDAO tutorialDAO = new TutorialDAO();
    private String form;
    private final int FORM_INSERT = 1;
    private final int FORM_UPDATE = 2;
    private final int FORM_DELETE = 3;

    public TutorialService() {
        makeform();
    }

    public void makeform() {
        makeform(FORM_INSERT);
    }

    /**
     * Metodo que faz a preparação da pagina para o sistema atualizar o front-end,
     * de acordo com a requisição passada por parametro;
     * 
     * @param tipo Parametro passando qual vai ser o formulario que será inserido na
     *             tela;
     * @throws Exception Caso ocorra um problema generalizado na hora da inserção do
     *                   arquivo;
     */
    public void makeform(int tipo) {
        String nomeArquivo = "src/main/resources/public/front-end/cadastro-tutorial/index.html";
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

        String formInsert = "";

        if (tipo == FORM_INSERT) {
            formInsert += "<h1>Cadastro de tutorial</h1>\n";
            formInsert += "<form id=\"form-contato\" method=\"post\" action=\"/criartutorial/insert/do\">\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-2\">\n";
            formInsert += "            <label for=\"inputId\">Id</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputId\" placeholder=\"ID\" name=\"id\" disabled>\n";
            formInsert += "        </div>\n";
            formInsert += "        <div class=\"col-sm-10\">\n";
            formInsert += "            <label for=\"inputTitulo\">Título (*)</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputTitulo\" required name=\"titulo\" placeholder=\"Informe o nome do título\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-12\">\n";
            formInsert += "            <label for=\"inputURL\">URL do Vídeo (*)</label>\n";
            formInsert += "            <input type=\"url\" class=\"form-control\" id=\"inputURL\" required name=\"link\" placeholder=\"Informe a URL do vídeo\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-6\">\n";
            formInsert += "            <label for=\"inputAutor\">Autor (*)</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputAutor\" required name=\"autor\" placeholder=\"Informe a ID do autor do tutorial\">\n";
            formInsert += "        </div>\n";
            formInsert += "        <div class=\"col-sm-6\">\n";
            formInsert += "            <label for=\"inputPublicado\">Publicado (*)</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputPublicado\" required name=\"publicado\" placeholder=\"Informe se o tutorial foi publicado\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-12\">\n";
            formInsert += "            <label for=\"inputTutorial\">Texto do Tutorial</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputTutorial\" name=\"texto\" placeholder=\"Informe o texto do tutorial\" >\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-12\">\n";
            formInsert += "            <label for=\"inputCategoria\">Categoria (*)</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputCategoria\" name=\"categoria\" placeholder=\"Informe a ID da categoria\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-4\">\n";
            formInsert += "            <small>(*) Campos obrigatórios</small>\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-12\">\n";
            formInsert += "            <input type=\"submit\" class=\"btn btn-13\" id=\"btnInserir\" value=\"Inserir\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "</form>\n";
        } else if (tipo == FORM_UPDATE) {
            formInsert += "<h1>Atualização de tutorial</h1>\n";
            formInsert += "<form id=\"form-contato\" method=\"post\" action=\"/criartutorial/update/do\">\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-2\">\n";
            formInsert += "            <label for=\"inputId\">Id</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputId\" placeholder=\"ID\" name=\"id\">\n";
            formInsert += "        </div>\n";
            formInsert += "        <div class=\"col-sm-10\">\n";
            formInsert += "            <label for=\"inputTitulo\">Título (*)</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputTitulo\" required name=\"titulo\" placeholder=\"Informe o nome do título\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-12\">\n";
            formInsert += "            <label for=\"inputURL\">URL do Vídeo (*)</label>\n";
            formInsert += "            <input type=\"url\" class=\"form-control\" id=\"inputURL\" required name=\"link\" placeholder=\"Informe a URL do vídeo\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-6\">\n";
            formInsert += "            <label for=\"inputAutor\">Autor (*)</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputAutor\" required name=\"autor\" placeholder=\"Informe a ID do autor do tutorial\">\n";
            formInsert += "        </div>\n";
            formInsert += "        <div class=\"col-sm-6\">\n";
            formInsert += "            <label for=\"inputPublicado\">Publicado (*)</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputPublicado\" required name=\"publicado\" placeholder=\"Informe se o tutorial foi publicado\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-12\">\n";
            formInsert += "            <label for=\"inputTutorial\">Texto do Tutorial</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputTutorial\" name=\"texto\" placeholder=\"Informe o texto do tutorial\" >\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-12\">\n";
            formInsert += "            <label for=\"inputCategoria\">Categoria (*)</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputCategoria\" name=\"categoria\" placeholder=\"Informe a ID da categoria\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-4\">\n";
            formInsert += "            <small>(*) Campos obrigatórios</small>\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-12\">\n";
            formInsert += "            <input type=\"submit\" class=\"btn btn-13\" id=\"btnAtualizar\" value=\"Alterar\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "</form>\n";
        } else if (tipo == FORM_DELETE) {
            formInsert += "<h1>Remoção de tutorial</h1>\n";
            formInsert += "<form id=\"form-contato\" method=\"post\" action=\"/criartutorial/delete/do\">\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-2\">\n";
            formInsert += "            <label for=\"inputId\">Id</label>\n";
            formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputId\" placeholder=\"ID\" name=\"id\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "    <div class=\"form-group row\">\n";
            formInsert += "        <div class=\"col-sm-12\">\n";
            formInsert += "            <input type=\"submit\" class=\"btn btn-13\" id=\"btnExcluir\" value=\"Excluir\">\n";
            formInsert += "        </div>\n";
            formInsert += "    </div>\n";
            formInsert += "</form>\n";
        }

        form = form.replaceFirst("<FORMULARIO>", formInsert);

        List<Tutorial> tutoriais = tutorialDAO.get("1");
        String formTabela = "";
        for (Tutorial p : tutoriais) {
            formTabela += "<tr>";
            formTabela += "<td scope=\"row\">" + p.getId() + "</td>";
            formTabela += "<td>" + p.getTitulo() + "</td>";
            formTabela += "<td>" + p.getVideo() + "</td>";
            formTabela += "<td>" + p.getAutor() + "</td>";
            formTabela += "<td>" + p.getDataCriacao() + "</td>";
            formTabela += "<td>" + p.getPublicado() + "</td>";
            formTabela += "<td>" + p.getTexto() + "</td>";
            formTabela += "<td>" + p.getCategoria() + "</td>";
        }

        form = form.replaceFirst("<TABELA>", formTabela);
    }

    /**
     * Metodo que recebe os dados preenchidos no front-end e prepara pra ser
     * inserido no DAO;
     * 
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object insert(Request request, Response response) {
        String texto = request.queryParams("texto");
        String titulo = request.queryParams("titulo");
        int autor = Integer.parseInt(request.queryParams("autor"));
        LocalDate dataCriacao = LocalDate.now();
        int publicado = Integer.parseInt(request.queryParams("publicado"));
        String linkYoutube = request.queryParams("link");
        int categoria = Integer.parseInt(request.queryParams("categoria"));

        Tutorial tutorial = new Tutorial(-1, texto, titulo, autor, dataCriacao, publicado, linkYoutube, categoria);

        if (tutorialDAO.insert(tutorial) == true) {
            response.status(201); // 201 Created
        } else {
            response.status(404); // 404 Not found
        }

        makeform(FORM_INSERT);
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
        return form;
    }

    /**
     * Metodo que recebe os dados preenchidos no front-end e prepara pra ser
     * atualizado no DAO;
     * 
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.queryParams("id"));
        String texto = request.queryParams("texto");
        String titulo = request.queryParams("titulo");
        int autor = Integer.parseInt(request.queryParams("autor"));
        LocalDate dataCriacao = LocalDate.now();
        int publicado = Integer.parseInt(request.queryParams("publicado"));
        String linkYoutube = request.queryParams("link");
        int categoria = Integer.parseInt(request.queryParams("categoria"));

        Tutorial tutorial = new Tutorial(id, texto, titulo, autor, dataCriacao, publicado, linkYoutube, categoria);

        if (tutorialDAO.update(tutorial) == true) {
            response.status(201); // 201 Created
        } else {
            response.status(404); // 404 Not found
        }

        makeform(FORM_UPDATE);
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
        return form;
    }

    /**
     * Metodo que recebe os dados preenchidos no front-end e prepara pra ser
     * deletado no DAO;
     * 
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.queryParams("id"));

        if (tutorialDAO.delete(id) == true) {
            response.status(201); // 201 Created
        } else {
            response.status(404); // 404 Not found
        }

        makeform(FORM_DELETE);
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
        return form;
    }

    /**
     * Metodo que faz a preparação da pagina para o sistema atualizar o front-end,
     * para fazer a inserção;
     * 
     * @param tipo Parametro passando qual vai ser o formulario que será inserido na
     *             tela;
     * @throws Exception Caso ocorra um problema generalizado na hora da inserção do
     *                   arquivo;
     */
    public Object pagInsert(Request request, Response response) {

        makeform(FORM_INSERT);
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
        return form;
    }

    /**
     * Metodo que faz a preparação da pagina para o sistema atualizar o front-end,
     * para fazer a atualização;
     * 
     * @param tipo Parametro passando qual vai ser o formulario que será inserido na
     *             tela;
     * @throws Exception Caso ocorra um problema generalizado na hora da inserção do
     *                   arquivo;
     */
    public Object pagUpdate(Request request, Response response) {

        makeform(FORM_UPDATE);
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
        return form;
    }

    /**
     * Metodo que faz a preparação da pagina para o sistema atualizar o front-end,
     * para fazer a exclusão;
     * 
     * @param tipo Parametro passando qual vai ser o formulario que será inserido na
     *             tela;
     * @throws Exception Caso ocorra um problema generalizado na hora da inserção do
     *                   arquivo;
     */
    public Object pagDelete(Request request, Response response) {

        makeform(FORM_DELETE);
        if(request.session().attribute("logado") != null) {
            form = form.replaceFirst("<div id=\"insertSair\">", "<div id=\"insertSair\"><p> " + request.session().attribute("nome") + " | <a href=\"/logout\">Sair</a></p>");
        }
        return form;
    }

}
