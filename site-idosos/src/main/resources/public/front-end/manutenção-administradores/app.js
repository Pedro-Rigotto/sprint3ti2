// declara um conjunto inicial de administradores
var db_adms_inicial = {
    "data": [
        {
            "usuario": "admin",
            "senha" : "admin"
        },
        {
            "usuario": "usuario1",
            "senha": "123"
        }
    ]
}

// Caso os dados já estejam no Local Storage, caso contrário, carrega os dados iniciais
var db = JSON.parse(localStorage.getItem('db_admin'));
if (!db) {
    db = db_adms_inicial
};

// Exibe mensagem em um elemento de ID msg
function displayMessage(msg) {
    $('#msg').html('<div class="alert alert-warning">' + msg + '</div>');
}

function insertAdmin(adm) {
    
    let novoAdmin = {
        "usuario": adm.usuario,
        "senha": adm.senha
    };

    // Testa se o usuário já existe
    let testeUsuario = novoAdmin.usuario;
    let teste = db.data.map(obj => obj.usuario).indexOf(testeUsuario)==-1?0:1;
    
    if(!teste){
        // Insere o novo objeto no array
        db.data.push(novoAdmin);
        displayMessage("Administrador inserido com sucesso");

        // Atualiza os dados no Local Storage
        localStorage.setItem('db_admin', JSON.stringify(db));
    }
    else {
        displayMessage("Usuário já existe");
    }
}

function updateAdmin(usuario, admin) {
    // Localiza o indice do objeto a ser alterado no array a partir do seu ID
    let index = db.data.map(obj => obj.usuario).indexOf(usuario);

    // Altera os dados do objeto no array
    db.data[index].senha = admin.senha;

    displayMessage("Administrador alterado com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_admin', JSON.stringify(db));
}

function deleteAdmin(usuario) {    
    // Filtra o array removendo o elemento com o id passado
    db.data = db.data.filter(function (element) { return element.usuario != usuario });

    displayMessage("Admin removido com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_admin', JSON.stringify(db));
}

function logoutUser () {
    usuarioCorrente = {};
    sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));
    window.location = LOGIN_URL;
}


function colocarSair(){
    let tipo = JSON.parse(sessionStorage.getItem('usuarioCorrente')).tipo;
    let nomeUsu = "";

    let textoNome;
    
    if (tipo == "user")
    {
        nomeUsu = JSON.parse(sessionStorage.getItem('usuarioCorrente')).nome;
    } else if (tipo == "adm")
    {
        nomeUsu = JSON.parse(sessionStorage.getItem('usuarioCorrente')).usuario;
    }
    
    textoNome = `<p>${nomeUsu} | <a onclick="logoutUser()" href="/src/main/resources/front-end/loginAdm/LoginAdm.html">Sair</a></p>`;

    document.querySelector('#insertSair').innerHTML = textoNome;
}


function testarLoginAdm ()
{
    let tipoUsuarioLogado = " ";

    if (sessionStorage.getItem('usuarioCorrente') != null )
    {
        tipoUsuarioLogado = JSON.parse(sessionStorage.getItem('usuarioCorrente')).tipo;
    }

    if (sessionStorage.getItem('usuarioCorrente') != null )
    {
        if (tipoUsuarioLogado != "adm" ){
            alert("Você não tem acesso a essa página.");
            window.location = "/src/main/resources/front-end/loginAdm/LoginAdm.html";
        }
    } else {
        alert("Você não tem acesso a essa página.");
        window.location = "/src/main/resources/front-end/loginAdm/LoginAdm.html";
    }
}

testarLoginAdm();
colocarSair();
