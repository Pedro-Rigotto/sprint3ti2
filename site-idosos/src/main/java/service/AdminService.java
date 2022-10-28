package service;

import java.util.Scanner;
import model.Usuario;
import dao.UsuarioDAO;
import spark.Request;
import spark.Response;
import java.io.File;
import java.util.List;

/**
 * Mostra as páginas de CRUD de usuários e faz a conexão delas com o UsuarioDAO.
 * 
 * @author Pedro R, André M
 */
public class AdminService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private String form;
    private final int FORM_INSERT = 1;
    private final int FORM_UPDATE = 2;
    private final int FORM_DELETE = 3;
    private final int FORM_SELECT = 4;

    public AdminService() {
        makeForm();
    }

    public void makeForm() {
        makeForm(FORM_INSERT);
    }

    /**
     * Metodo que faz a preparação da pagina para o sistema atualizar o front-end;
     * 
     * @param tipo Parametro passando qual vai ser o formulario que será inserido na
     *             tela;
     * @throws Exception Caso ocorra um problema generalizado na hora da inserção do
     *                   arquivo;
     */
    public void makeForm(int tipo) {
        String nomeArquivo = "src/main/resources/public/front-end/compra-manutenção-usuários/gerenciarusuario.html";/// src/main/resources/public/compra.html
                                                                                                                    /// src/main/resources/public/compra.html
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

        if (tipo == FORM_INSERT) {
            String formInsert = "";

            formInsert += "<div class=\"col-sm-12\" id=\"formulario\">\n";
            formInsert += "\t<form id=\"form-compra\" autocomplete=\"off\" action=\"/admin/insert/do\" method=\"post\">\n";
            formInsert += "\t\t<div class=\"row\">\n";
            formInsert += "\t\t\t<div class=\"col-12 col-md-3\"></div>\n";
            formInsert += "\t\t\t<div class=\"col-12 col-md-6\">\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputNome\">Nome: (*)</label>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"text\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputNome\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"nome\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"Nome\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputEmail\"\n";
            formInsert += "\t\t\t\t\t\t\t>E-mail: (*)</label\n";
            formInsert += "\t\t\t\t\t\t>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"email\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputEmail\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"email\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"E-mail\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputUsuario\"\n";
            formInsert += "\t\t\t\t\t\t\t>Nome de usuário: (*)</label\n";
            formInsert += "\t\t\t\t\t\t>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"text\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputUsuario\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"username\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"Nome de usuário\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputSenha\"\n";
            formInsert += "\t\t\t\t\t\t\t>Senha: (*)</label\n";
            formInsert += "\t\t\t\t\t\t>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"password\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputSenha\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"password\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"Senha\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputTelefone\"\n";
            formInsert += "\t\t\t\t\t\t\t>Telefone:</label\n";
            formInsert += "\t\t\t\t\t\t>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"text\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputTelefone\"\n";
            formInsert += "\t\t\t\t\t\t\tname=\"telefone\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"Seu telefone\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputTipo\"\n";
            formInsert += "\t\t\t\t\t\t\t>Tipo de usuário: (*)</label\n";
            formInsert += "\t\t\t\t\t\t>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"text\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputTipo\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"tipo_usuario\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"0 / 1\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<small>(*) Campos obrigatórios</small>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\" id=\"divBotaoPagar\">\n";
            formInsert += "\t\t\t\t\t\t<input type=\"submit\" class=\"btn btn-13\" id=\"btnInserir\"\n";
            formInsert += "\t\t\t\t\t\tvalue=\"Inserir\">\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t</div>\n";
            formInsert += "\t\t</div>\n";
            formInsert += "\t\t<div class=\"col-12 col-md-3\"></div>\n";
            formInsert += "\t</form>\n";
            formInsert += "</div>\n";

            form = form.replaceFirst("<FORMULARIO>", formInsert);
            form = form.replaceFirst("<TITULO>", "Inserir usuário");
        } else if (tipo == FORM_UPDATE) {
            String formInsert = "";

            formInsert += "<div class=\"col-sm-12\" id=\"formulario\">\n";
            formInsert += "\t<form id=\"form-compra\" autocomplete=\"off\" action=\"/admin/update/do\" method=\"post\">\n";
            formInsert += "\t\t<div class=\"row\">\n";
            formInsert += "\t\t\t<div class=\"col-12 col-md-3\"></div>\n";
            formInsert += "\t\t\t<div class=\"col-12 col-md-6\">\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputId\">ID: (*)</label>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"text\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputId\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"id\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"ID\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputNome\">Nome: (*)</label>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"text\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputNome\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"nome\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"Nome\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputEmail\"\n";
            formInsert += "\t\t\t\t\t\t\t>E-mail: (*)</label\n";
            formInsert += "\t\t\t\t\t\t>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"email\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputEmail\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"email\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"E-mail\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputUsuario\"\n";
            formInsert += "\t\t\t\t\t\t\t>Nome de usuário: (*)</label\n";
            formInsert += "\t\t\t\t\t\t>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"text\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputUsuario\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"username\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"Nome de usuário\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputSenha\"\n";
            formInsert += "\t\t\t\t\t\t\t>Senha: (*)</label\n";
            formInsert += "\t\t\t\t\t\t>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"password\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputSenha\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"password\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"Senha\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputTelefone\"\n";
            formInsert += "\t\t\t\t\t\t\t>Telefone:</label\n";
            formInsert += "\t\t\t\t\t\t>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"text\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputTelefone\"\n";
            formInsert += "\t\t\t\t\t\t\tname=\"telefone\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"Seu telefone\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputTipo\"\n";
            formInsert += "\t\t\t\t\t\t\t>Tipo de usuário: (*)</label\n";
            formInsert += "\t\t\t\t\t\t>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"text\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputTipo\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"tipo_usuario\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"0 / 1\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<small>(*) Campos obrigatórios</small>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\" id=\"divBotaoPagar\">\n";
            formInsert += "\t\t\t\t\t\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"submit\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"btn btn-13\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"btnAlterar\"\n";
            formInsert += "\t\t\t\t\t\t\tvalue=\"Alterar\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t\t\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t</div>\n";
            formInsert += "\t\t</div>\n";
            formInsert += "\t\t<div class=\"col-12 col-md-3\"></div>\n";
            formInsert += "\t</form>\n";
            formInsert += "</div>\n";

            form = form.replaceFirst("<FORMULARIO>", formInsert);
            form = form.replaceFirst("<TITULO>", "Editar usuário");
        } else if (tipo == FORM_DELETE) {
            String formInsert = "";

            formInsert += "<div class=\"col-sm-12\" id=\"formulario\">\n";
            formInsert += "\t<form id=\"form-compra\" autocomplete=\"off\" action=\"/admin/delete/do\" method=\"post\">\n";
            formInsert += "\t\t<div class=\"row\">\n";
            formInsert += "\t\t\t<div class=\"col-12 col-md-3\"></div>\n";
            formInsert += "\t\t\t<div class=\"col-12 col-md-6\">\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<label for=\"inputId\">ID: (*)</label>\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"text\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"form-control\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"inputId\"\n";
            formInsert += "\t\t\t\t\t\t\trequired\n";
            formInsert += "\t\t\t\t\t\t\tname=\"id\"\n";
            formInsert += "\t\t\t\t\t\t\tplaceholder=\"ID\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\">\n";
            formInsert += "\t\t\t\t\t\t<small>(*) Campos obrigatórios</small>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t<div class=\"form-group row\">\n";
            formInsert += "\t\t\t\t\t<div class=\"col-sm-12\" id=\"divBotaoPagar\">\n";
            formInsert += "\t\t\t\t\t\t\t\t\t\t\t\t\n";
            formInsert += "\t\t\t\t\t\t<input\n";
            formInsert += "\t\t\t\t\t\t\ttype=\"submit\"\n";
            formInsert += "\t\t\t\t\t\t\tclass=\"btn btn-13\"\n";
            formInsert += "\t\t\t\t\t\t\tid=\"btnDeletar\"\n";
            formInsert += "\t\t\t\t\t\t\tvalue=\"Excluir\"\n";
            formInsert += "\t\t\t\t\t\t/>\n";
            formInsert += "\t\t\t\t\t</div>\n";
            formInsert += "\t\t\t\t</div>\n";
            formInsert += "\t\t\t</div>\n";
            formInsert += "\t\t</div>\n";
            formInsert += "\t\t<div class=\"col-12 col-md-3\"></div>\n";
            formInsert += "\t</form>\n";
            formInsert += "</div>\n";

            form = form.replaceFirst("<FORMULARIO>", formInsert);
            form = form.replaceFirst("<TITULO>", "Excluir usuário");
        } else if (tipo == FORM_SELECT) {
            nomeArquivo = "src/main/resources/public/front-end/compra-manutenção-usuários/menu.html";
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
        }

        String formTabela = "";
        List<Usuario> usuarios = usuarioDAO.get("7");
        for (Usuario p : usuarios) {
            formTabela += "<tr>\n";
            formTabela += "\n<td scope=\"row\">" + p.getId() + "</td>\n";
            formTabela += "\n<td>" + p.getNome() + "</td>\n";
            formTabela += "\n<td>" + p.getEmail() + "</td>\n";
            formTabela += "\n<td>" + p.getUsername() + "</td>\n";
            formTabela += "\n<td>" + p.getPassword() + "</td>\n";
            formTabela += "\n<td>" + p.getTelefone() + "</td>\n";
            formTabela += "\n<td>" + p.getTipoUsuario() + "</td>\n";
            formTabela += "</tr>\n";
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
        String username = request.queryParams("username");
        String nome = request.queryParams("nome");
        String email = request.queryParams("email");
        String password = request.queryParams("password");
        int tipoUsuario = Integer.parseInt(request.queryParams("tipo_usuario"));
        String telefone = request.queryParams("telefone");

        String resp = "";

        Usuario usuario = new Usuario(username, nome, email, password, tipoUsuario, telefone, -1);

        if (usuarioDAO.insert(usuario) == true) {
            resp = "Usuario (" + username + ") inserido!";
            response.status(201); // 201 Created
        } else {
            resp = "Erro! Usuario (" + username + ") não inserido!";
            response.status(404); // 404 Not found
        }

        makeForm();
        return form.replaceFirst("<div display=\"none\" id=\"mensagemDeAviso\"></div>",
                "<div class=\"alert alert-warning\">" + resp + "</div>");
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
        String username = request.queryParams("username");
        String nome = request.queryParams("nome");
        String email = request.queryParams("email");
        String password = request.queryParams("password");
        int tipoUsuario = Integer.parseInt(request.queryParams("tipo_usuario"));
        String telefone = request.queryParams("telefone");

        String resp = "";

        Usuario usuario = new Usuario(username, nome, email, password, tipoUsuario, telefone, id);

        if (usuarioDAO.update(usuario) == true) {
            resp = "Usuario (" + username + ") atualizado!";
            response.status(201); // 201 Created
        } else {
            resp = "Erro! Usuario (" + username + ") não atualizado!";
            response.status(404); // 404 Not found
        }

        makeForm(FORM_UPDATE);
        return form.replaceFirst("<div display=\"none\" id=\"mensagemDeAviso\"></div>",
                "<div class=\"alert alert-warning\">" + resp + "</div>");
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

        String resp = "";

        if (usuarioDAO.delete(id) == true) {
            resp = "Usuario (" + id + ") excluído!";
            response.status(201); // 201 Created
        } else {
            resp = "Erro! Usuario (" + id + ") não excluído!";
            response.status(404); // 404 Not found
        }

        makeForm(FORM_DELETE);
        return form.replaceFirst("<div display=\"none\" id=\"mensagemDeAviso\"></div>",
                "<div class=\"alert alert-warning\">" + resp + "</div>");
    }

    /**
     * Metodo que recebe os dados preenchidos no front-end e prepara a visão de
     * administrador;
     * 
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object getAdmins(Request request, Response response) {
        makeForm(FORM_INSERT);
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        return form;
    }

    /**
     * Metodo que recebe os dados preenchidos no front-end e prepara para atualizar
     * usuarios de dominio administrador no DAO ;
     * 
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object getAdminsUpdate(Request request, Response response) {
        makeForm(FORM_UPDATE);
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        return form;
    }

    /**
     * Metodo que recebe os dados preenchidos no front-end e prepara para deletar
     * usuarios de dominio administrador no DAO ;
     * 
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object getAdminsDelete(Request request, Response response) {
        makeForm(FORM_DELETE);
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        return form;
    }

    /**
     * Metodo que exibe a página de menu de administradores;
     * 
     * @param request  parametros de requisição da pagina;
     * @param response parametros de resposta da pagina;
     * @return retorna a pagina pronta para atualizar no front-end e a mensagem do
     *         status;
     */
    public Object getMenu(Request request, Response response) {
        makeForm(FORM_SELECT);
        response.header("Content-Type", "text/html");
        response.header("Content-Encoding", "UTF-8");
        return form;
    }
}
