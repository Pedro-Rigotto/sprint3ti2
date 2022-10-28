package app;

import static spark.Spark.*;

import service.*;

public class Aplicacao {
    private static ContaService contaService = new ContaService();
    private static AdminService adminService = new AdminService();
    private static LoginService loginService = new LoginService();
    private static TutorialService tutorialService = new TutorialService();
    private static CategoriaService categoriaService = new CategoriaService();
    private static PagInicialService pagInicialService = new PagInicialService();
    private static ContatoService contatoService = new ContatoService();
    private static ListaCategoriaService listaCategoriaService = new ListaCategoriaService();
    private static ListaTutorialService listaTutorialService = new ListaTutorialService();
    private static PagTutorialService pagTutorialService = new PagTutorialService();
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

        get("/admin", (request, response) -> adminService.getMenu(request, response));

        get("/admin/insert", (request, response) -> adminService.getAdmins(request, response));

        get("/admin/update", (request, response) -> adminService.getAdminsUpdate(request, response));

        get("/admin/delete", (request, response) -> adminService.getAdminsDelete(request, response));

        post("/admin/insert/do", (request, response) -> adminService.insert(request, response));

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

        get("/", (request, response) -> pagInicialService.getIniciar(request, response));

        get("/contato", (request, response) -> contatoService.getContato(request, response));

        get("/listacategorias/:supercategoria", (request, response) -> listaCategoriaService.getCategorias(request, response));

        get("/listacategorias", (request, response) -> listaCategoriaService.getSuperCategorias(request, response));

        get("/listatutoriais/:id", (request, response) -> listaTutorialService.getTutoriais(request, response));

        get("/tutorial/:id", (request, response) -> pagTutorialService.getTutorial(request, response));
    }
}
