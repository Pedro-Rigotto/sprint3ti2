package service;

import java.util.Scanner;
import model.Categoria;
import dao.CategoriaDAO;
import spark.Request;
import spark.Response;
import java.io.File;
import java.util.List;

/**
 * Mostra a página de cadastro de categoria e faz a conexão com o CategoriaDAO.
 * 
 * @author Pedro R, André M
 */
public class CategoriaService {
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private String form;
    private final int FORM_INSERT = 1;
    private final int FORM_UPDATE = 2;
    private final int FORM_DELETE = 3;

    public CategoriaService() {
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
        String nomeArquivo = "src/main/resources/public/front-end/manutenção-categorias/index.html";
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
            formInsert += "\t\t\t<form method=\"post\" action=\"/criarcategoria/insert/do\" id=\"form-categoria\">\n";
            formInsert += "\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<p><h3>Criar categorias</h3></p>\n";
            formInsert += "\t\t\t\t\t  \n";
            formInsert += "\t\t\t\t\t  <div class=\"row\">\n";
            formInsert += "\t\t\t\t\t\t<div class=\"col-sm-2\">\n";
            formInsert += "\t\t\t\t\t\t  <label for=\"inputId\">Id</label>\n";
            formInsert += "\t\t\t\t\t\t  <input type=\"text\" class=\"form-control \" id=\"inputId\" required name=\"id\"\n";
            formInsert += "\t\t\t\t\t\t\t  placeholder=\"Id\" autocomplete=\"off\" disabled>\n";
            formInsert += "\t\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t\t\t<div class=\"col-sm-5\">\n";
            formInsert += "\t\t\t\t\t\t  <label for=\"inputNome\">Nome (*)</label>\n";
            formInsert += "\t\t\t\t\t\t  <input type=\"text\" class=\"form-control \" id=\"inputNome\" required name=\"nome\"\n";
            formInsert += "\t\t\t\t\t\t\t  placeholder=\"Informe o nome da categoria\" autocomplete=\"off\">\n";
            formInsert += "\t\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t\t\t<div class=\"col-sm-5\">\n";
            formInsert += "\t\t\t\t\t\t  <label for=\"inputSupercat\">Supercategoria (*)</label>\n";
            formInsert += "\t\t\t\t\t\t  <input type=\"text\" class=\"form-control \" id=\"inputSupercat\" required name=\"supercategoria\"\n";
            formInsert += "\t\t\t\t\t\t\t  placeholder=\"Informe o nome da supercategoria\" autocomplete=\"off\">\n";
            formInsert += "\t\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t\t  </div>\n";
            formInsert += "\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<small>(*) Campos obrigatórios</small>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-13\" id=\"btnInserir\" value=\"Inserir\">\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t</form>\n";
        } else if (tipo == FORM_UPDATE) {
            formInsert += "\t\t\t<form method=\"post\" action=\"/criarcategoria/update/do\" id=\"form-categoria\">\n";
            formInsert += "\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<p><h3>Manutenção de categorias</h3></p>\n";
            formInsert += "\t\t\t\t\t  \n";
            formInsert += "\t\t\t\t\t  <div class=\"row\">\n";
            formInsert += "\t\t\t\t\t\t<div class=\"col-sm-2\">\n";
            formInsert += "\t\t\t\t\t\t  <label for=\"inputId\">Id</label>\n";
            formInsert += "\t\t\t\t\t\t  <input type=\"text\" class=\"form-control \" id=\"inputId\" required name=\"id\"\n";
            formInsert += "\t\t\t\t\t\t\t  placeholder=\"Id\" autocomplete=\"off\">\n";
            formInsert += "\t\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t\t\t<div class=\"col-sm-5\">\n";
            formInsert += "\t\t\t\t\t\t  <label for=\"inputNome\">Nome (*)</label>\n";
            formInsert += "\t\t\t\t\t\t  <input type=\"text\" class=\"form-control \" id=\"inputNome\" required name=\"nome\"\n";
            formInsert += "\t\t\t\t\t\t\t  placeholder=\"Informe o nome da categoria\" autocomplete=\"off\">\n";
            formInsert += "\t\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t\t\t<div class=\"col-sm-5\">\n";
            formInsert += "\t\t\t\t\t\t  <label for=\"inputSupercat\">Supercategoria (*)</label>\n";
            formInsert += "\t\t\t\t\t\t  <input type=\"text\" class=\"form-control \" id=\"inputSupercat\" required name=\"supercategoria\"\n";
            formInsert += "\t\t\t\t\t\t\t  placeholder=\"Informe o nome da supercategoria\" autocomplete=\"off\">\n";
            formInsert += "\t\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t\t  </div>\n";
            formInsert += "\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<small>(*) Campos obrigatórios</small>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-13\" id=\"btnAtualizar\" value=\"Alterar\">\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t</form>\n";
        } else if (tipo == FORM_DELETE) {
            formInsert += "\t\t\t<form method=\"post\" action=\"/criarcategoria/delete/do\" id=\"form-categoria\">\n";
            formInsert += "\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<p><h3>Remover categorias</h3></p>\n";
            formInsert += "\t\t\t\t\t  \n";
            formInsert += "\t\t\t\t\t  <div class=\"row\">\n";
            formInsert += "\t\t\t\t\t\t<div class=\"col-sm-3\">\n";
            formInsert += "\t\t\t\t\t\t  <label for=\"inputId\">Id</label>\n";
            formInsert += "\t\t\t\t\t\t  <input type=\"text\" class=\"form-control \" id=\"inputId\" required name=\"id\"\n";
            formInsert += "\t\t\t\t\t\t\t  placeholder=\"Id\" autocomplete=\"off\">\n";
            formInsert += "\t\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t\t\t<div class=\"col-sm-9\">\n";
            formInsert += "\t\t\t\t\t\t  <label for=\"inputNome\">Nome (*)</label>\n";
            formInsert += "\t\t\t\t\t\t  <input type=\"text\" class=\"form-control \" id=\"inputNome\" required name=\"nome\"\n";
            formInsert += "\t\t\t\t\t\t\t  placeholder=\"Informe o nome da categoria\" autocomplete=\"off\">\n";
            formInsert += "\t\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t\t  </div>\n";
            formInsert += "\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<small>(*) Campos obrigatórios</small>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-13\" id=\"btnRemover\" value=\"Excluir\">\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t</form>\n";
        }

        form = form.replaceFirst("<FORMULARIO>", formInsert);
        List<Categoria> categorias = categoriaDAO.get("1");
        String formTabela = "";
        for (Categoria p : categorias) {
            formTabela += "<tr>";
            formTabela += "<td scope=\"row\">" + p.getId() + "</td>";
            formTabela += "<td>" + p.getNome() + "</td>";
            formTabela += "<td>" + p.getSupercategoria() + "</td>";
            formTabela += "</tr>";
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
        String nome = request.queryParams("nome");
        String supercategoria = request.queryParams("supercategoria");
        Categoria categoria = new Categoria(-1, nome, supercategoria);
        if (categoriaDAO.insert(categoria) == true) {
            response.status(201); // 201 Created
        } else {
            response.status(404); // 404 Not found
        }

        makeform();
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
        String nome = request.queryParams("nome");
        int id = Integer.parseInt(request.queryParams("id"));
        String supercategoria = request.queryParams("supercategoria");
        Categoria categoria = new Categoria(id, nome, supercategoria);
        if (categoriaDAO.update(categoria) == true) {
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
        if (categoriaDAO.delete(id) == true) {
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
