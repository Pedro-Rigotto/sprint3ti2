package app;

import static spark.Spark.*;

import service.*;

public class Aplicacao {
    private static ContaService contaService = new ContaService();
    private static AdminService adminService = new AdminService();
    private static LoginService loginService = new LoginService();
    private static TutorialService tutorialService = new TutorialService();
    private static CategoriaService categoriaService = new CategoriaService();
    /**
     * Programa que roda o servidor do projeto, e chama as funções que mostram as páginas e fazem os requerimentos POST.
     *@author Pedro R, André M, Henrique
    */
    public static void main(String[] args) {
        port(6789);

        staticFiles.location("/public");
        
        get("/criarusuario", (request, response) -> contaService.getCriar(request, response));

        post("/criarusuario/insert", (request, response) -> contaService.insert(request, response));

        get("/loginUsuario",(request, response)-> loginService.getCriar(request, response));
        
        post("/loginAutentica",(request, response)-> loginService.loga(request, response));

        get("/admin", (request, response) -> adminService.getAdmins(request, response));

        get("/admin/update", (request, response) -> adminService.getAdminsUpdate(request, response));

        get("/admin/delete", (request, response) -> adminService.getAdminsDelete(request, response));

        post("/admin/insert", (request, response) -> adminService.insert(request, response));

        post("/admin/update/do", (request, response) -> adminService.update(request, response));

        post("/admin/delete/do", (request, response) -> adminService.delete(request, response));

        get("/criartutorial/insert", (request, response) -> tutorialService.pagInsert(request, response));

        get("/criartutorial/update", (request, response) -> tutorialService.pagUpdate(request, response));

        get("/criartutorial/delete", (request, response) -> tutorialService.pagDelete(request, response));

        post("/criartutorial/insert/do", (request, response) -> tutorialService.insert(request, response));

        post("/criartutorial/update/do", (request, response) -> tutorialService.update(request, response));

        post("/criartutorial/delete/do", (request, response) -> tutorialService.delete(request, response));

        get("/criarcategoria/insert", (request, response) -> categoriaService.pagInsert(request, response));

        get("/criarcategoria/update", (request, response) -> categoriaService.pagUpdate(request, response));

        get("/criarcategoria/delete", (request, response) -> categoriaService.pagDelete(request, response));

        post("/criarcategoria/insert/do", (request, response) -> categoriaService.insert(request, response));

        post("/criarcategoria/update/do", (request, response) -> categoriaService.update(request, response));

        post("/criarcategoria/delete/do", (request, response) -> categoriaService.delete(request, response));

    }
}
