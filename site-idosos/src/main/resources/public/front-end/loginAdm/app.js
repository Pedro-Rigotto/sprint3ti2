
// Página inicial de Login
const LOGIN_URL = "LoginAdm.html";

// Objeto para o banco de dados de usuários baseado em JSON
var db_usuarios = {};

// Objeto para o usuário corrente
var usuarioCorrente = {};

// Dados de usuários para serem utilizados como carga inicial
const dadosIniciais = {
    data: [
        {  "usuario": "admin",    "senha" : "admin"},
        {  "usuario": "usuario1", "senha": "123"   },
    ]
};


// Inicializa o usuarioCorrente e banco de dados de usuários da aplicação de Login
function initLoginApp () {
    // PARTE 1 - INICIALIZA USUARIOCORRENTE A PARTIR DE DADOS NO LOCAL STORAGE, CASO EXISTA
    usuarioCorrenteJSON = sessionStorage.getItem('usuarioCorrente');
    if (usuarioCorrenteJSON) {
        usuarioCorrente = JSON.parse (usuarioCorrenteJSON);
        if(usuarioCorrente.tipo == "user")
        {
            usuarioCorrente = {};
            sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));
        }
        if(usuarioCorrente.tipo == "adm")
        {
            window.location.href = '/src/main/resources/front-end/manutenção-administradores/index.html';
        }
    }
    
    // PARTE 2 - INICIALIZA BANCO DE DADOS DE USUÁRIOS
    // Obtem a string JSON com os dados de usuários a partir do localStorage
    var usuariosJSON = localStorage.getItem('db_admin');

    // Verifica se existem dados já armazenados no localStorage
    if (!usuariosJSON) {  // Se NÃO há dados no localStorage
        
        // Informa sobre localStorage vazio e e que serão carregados os dados iniciais
        alert('Dados de usuários não encontrados no localStorage. \n -----> Fazendo carga inicial.');

        // Copia os dados iniciais para o banco de dados 
        db_usuarios = dadosIniciais;

        // Salva os dados iniciais no local Storage convertendo-os para string antes
        localStorage.setItem('db_admin', JSON.stringify (dadosIniciais));
    }
    else  {  // Se há dados no localStorage
        
        // Converte a string JSON em objeto colocando no banco de dados baseado em JSON
        db_usuarios = JSON.parse(usuariosJSON);    
    }
};


// Verifica se o login do usuário está ok e, se positivo, direciona para a página inicial
function loginUser (login, senha) {

    // Verifica todos os itens do banco de dados de usuarios 
    // para localizar o usuário informado no formulario de login
    for (var i = 0; i < db_usuarios.data.length; i++) {
        var usuario = db_usuarios.data[i];
        
        // Se encontrou login, carrega usuário corrente e salva no Session Storage
        if (login == usuario.usuario && senha == usuario.senha) {
            usuarioCorrente.usuario = usuario.usuario;
            usuarioCorrente.senha = usuario.senha;
            usuarioCorrente.tipo = "adm";
            
            // Salva os dados do usuário corrente no Session Storage, mas antes converte para string
            sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));

            // Retorna true para usuário encontrado
            return true;
        }
    }

    // Se chegou até aqui é por que não encontrou o usuário e retorna falso
    return false;
}

// Apaga os dados do usuário corrente no sessionStorage
function logoutUser () {
    usuarioCorrente = {};
    sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));
    window.location = LOGIN_URL;
}

function setUserPass () {

}


// Inicializa as estruturas utilizadas pelo LoginApp
initLoginApp ();