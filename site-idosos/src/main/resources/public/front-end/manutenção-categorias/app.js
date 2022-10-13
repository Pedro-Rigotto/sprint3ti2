
// declara um conjunto inicial de categorias
var db_categorias_inicial = {
    "categorias": [
        {
            "id": 1,
            "nome": "Aplicativos de banco"
        },

        {
            "id": 2,
            "nome": "Aplicativos de redes sociais"
        },
        {
            "id": 3,
            "nome": "Aplicativos de filmes e séries"
        },
        {
            "id": 4,
            "nome": "Uso do celular"
        },
        {
            "id": 5,
            "nome": "Aplicativos de música"
        },
        {
            "id": 6,
            "nome": "Aplicativos de telefonia"
        },
        {
            "id": 7,
            "nome": "Gratuito"
        }
    ]
}

// Caso os dados já estejam no Local Storage, caso contrário, carrega os dados iniciais
var db = JSON.parse(localStorage.getItem('db_categoria'));
if (!db) {
    db = db_categorias_inicial
};


// Exibe mensagem em um elemento de ID msg
function displayMessage(msg) {
    $('#msg').html('<div class="alert alert-warning">' + msg + '</div>');
}

function insertCategoria(categoria) {
  // Calcula novo Id a partir do último código existente no array (PODE GERAR ERRO SE A BASE ESTIVER VAZIA)
    let novoId = 1;
    if (db.categorias.length != 0) 
      novoId = db.categorias[db.categorias.length - 1].id + 1;
    let novaCategoria = {
        "id": novoId,
        "nome": categoria.nome
    };
  
    let testeCategoria = novaCategoria.nome;
    let teste = db.categorias.map(obj => obj.nome).indexOf(testeCategoria)==-1?0:1;
    
    if(!teste){
    // Insere o novo objeto no array
    db.categorias.push(novaCategoria);
    displayMessage("Categoria inserida com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_categoria', JSON.stringify(db));
    }
    else {
      displayMessage("Categoria já existe");
    }
}

function updateCategoria(id, categoria) {
    // Localiza o indice do objeto a ser alterado no array a partir do seu ID
    let index = db.categorias.map(obj => obj.id).indexOf(id);

    // Altera os dados do objeto no array
    db.categorias[index].nome = categoria.nome;

    displayMessage("Categoria alterada com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_categoria', JSON.stringify(db));
}

function deleteCategoria(id) {    
    // Filtra o array removendo o elemento com o nome passado
    db.categorias = db.categorias.filter(function (element) { return element.id != id });

    displayMessage("Categoria removida com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_categoria', JSON.stringify(db));
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

//testarLoginAdm();
colocarSair();