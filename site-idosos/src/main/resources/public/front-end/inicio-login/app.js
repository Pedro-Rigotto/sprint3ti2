
// Página inicial de Login
const LOGIN_URL = "login.html";

// Objeto para o banco de dados de usuários baseado em JSON
var db_usuarios = {};

// Objeto para o usuário corrente
var usuarioCorrente = {};

// função para gerar códigos randômicos a serem utilizados como código de usuário
// Fonte: https://stackoverflow.com/questions/105034/how-to-create-guid-uuid


// Dados de usuários para serem utilizados como carga inicial
const dadosIniciais = {
    data: [
        {
            "nome": "Maria dos Santos",
            "email": "mariasantos@hotmail.com",
            "telefone": "3133221100",
            "senha": "123456",
            "endereco": "Rua São João",
            "numend": "34",
            "compend": "ap 201",
            "cidade": "Belo Horizonte",
            "estado": "MG",
            "cep": "33222-000"
        },
        {
            "nome": "João Silva",
            "email": "joaosilva@msn.com.br",
            "telefone": "11999887766",
            "senha": "abcdef",
            "endereco": "Avenida do Contorno",
            "numend": "1005",
            "compend": "",
            "cidade": "São Paulo",
            "estado": "SP",
            "cep": "34152-321"
        },
        {
            "nome": "Carlos Pereira",
            "email": "carlosp@facebook.com",
            "telefone": "31998564555",
            "senha": "asdfqwer",
            "endereco": "Rua Ouro Preto",
            "numend": "362",
            "compend": "ap 801 bloco 2",
            "cidade": "Manhuaçu",
            "estado": "MG",
            "cep": "36642-165"
        }
    ]
};


// Inicializa o usuarioCorrente e banco de dados de usuários da aplicação de Login
function initLoginApp () {
    // PARTE 1 - INICIALIZA USUARIOCORRENTE A PARTIR DE DADOS NO LOCAL STORAGE, CASO EXISTA
    usuarioCorrenteJSON = sessionStorage.getItem('usuarioCorrente');
    if (usuarioCorrenteJSON) {
        usuarioCorrente = JSON.parse (usuarioCorrenteJSON);
        usuarioCorrente = JSON.parse (usuarioCorrenteJSON);
        if(usuarioCorrente.tipo == "adm")
        {
            usuarioCorrente = {};
            sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));
        }
        if(usuarioCorrente.tipo == "user")
        {
            window.location.href = '/src/main/resources/front-end/cat-premium/index.html';
        }
    }
    
    // PARTE 2 - INICIALIZA BANCO DE DADOS DE USUÁRIOS
    // Obtem a string JSON com os dados de usuários a partir do localStorage
    var usuariosJSON = localStorage.getItem('db_usu');

    // Verifica se existem dados já armazenados no localStorage
    if (!usuariosJSON) {  // Se NÃO há dados no localStorage
        
        // Informa sobre localStorage vazio e e que serão carregados os dados iniciais
        alert('Dados de usuários não encontrados no localStorage. \n -----> Fazendo carga inicial.');

        // Copia os dados iniciais para o banco de dados 
        db_usuarios = dadosIniciais;

        // Salva os dados iniciais no local Storage convertendo-os para string antes
        localStorage.setItem('db_usu', JSON.stringify (dadosIniciais));
    }
    else  {  // Se há dados no localStorage
        
        // Converte a string JSON em objeto colocando no banco de dados baseado em JSON
        db_usuarios = JSON.parse(usuariosJSON);    
    }
};


// Verifica se o login do usuário está ok e, se positivo, direciona para a página inicial
function loginUser (email, senha) {
    
    // Verifica todos os itens do banco de dados de usuarios 
    // para localizar o usuário informado no formulario de login
    for (var i = 0; i < db_usuarios.data.length; i++) {
        var usuario = db_usuarios.data[i];
        
        // Se encontrou login, carrega usuário corrente e salva no Session Storage
        if (email == usuario.email && senha == usuario.senha) {
            usuarioCorrente.login = usuario.login;
            usuarioCorrente.nome = usuario.nome;
            usuarioCorrente.cidade = usuario.telefone;
            usuarioCorrente.compend = usuario.compend;
            usuarioCorrente.numend = usuario.numend;
            usuarioCorrente.endereco = usuario.endereco;
            usuarioCorrente.estado = usuario.estado;
            usuarioCorrente.cep = usuario.cep;
            usuarioCorrente.tipo = "user";

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


