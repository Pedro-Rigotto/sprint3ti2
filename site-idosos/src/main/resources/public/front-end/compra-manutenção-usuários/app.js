// declara um conjunto inicial de administradores
var db_usu_inicial = {
    "data": [
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
}

// Caso os dados já estejam no Local Storage, caso contrário, carrega os dados iniciais
var db = JSON.parse(localStorage.getItem('db_usu'));
if (!db) {
    db = db_usu_inicial
};

// Exibe mensagem em um elemento de ID msg
function displayMessage(msg) {
    $('#msg').html('<div class="alert alert-warning">' + msg + '</div>');
}

function insertUsuario(usu) {
    
    let novoUsuario = {
        "nome": usu.nome,
        "email": usu.email,
        "telefone": usu.telefone,
        "senha": usu.senha,
        "endereco": usu.endereco,
        "numend": usu.numend,
        "compend": usu.compend,
        "cidade": usu.cidade,
        "estado": usu.estado,
        "cep": usu.cep
    };

    // Testa se o usuário já existe
    let testeUsuario = novoUsuario.email;
    let teste = db.data.map(obj => obj.email).indexOf(testeUsuario)==-1?0:1;
    
    if(!teste){
        // Insere o novo objeto no array
        db.data.push(novoUsuario);
        displayMessage("Conta criada com sucesso");

        // Atualiza os dados no Local Storage
        localStorage.setItem('db_usu', JSON.stringify(db));
    }
    else {
        displayMessage("Já existe uma conta com este e-mail");
    }
}

function updateUsuario(email, usu) {
    // Localiza o indice do objeto a ser alterado no array a partir do seu ID
    let index = db.data.map(obj => obj.email).indexOf(email);

    // Altera os dados do objeto no array
    db.data[index].nome = usu.nome;
    db.data[index].email = usu.email;
    db.data[index].telefone = usu.telefone;
    db.data[index].senha = usu.senha;
    db.data[index].endereco = usu.endereco;
    db.data[index].numend = usu.numend;
    db.data[index].compend = usu.compend;
    db.data[index].cidade = usu.cidade;
    db.data[index].estado = usu.estado;
    db.data[index].cep = usu.cep;

    displayMessage("Usuário alterado com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_usu', JSON.stringify(db));
}

function deleteUsuario(email) {    
    // Filtra o array removendo o elemento com o id passado
    db.data = db.data.filter(function (element) { return element.email != email });

    displayMessage("Usuário removido com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_usu', JSON.stringify(db));
}

function logoutUser () {
    usuarioCorrente = {};
    sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));
    window.location = LOGIN_URL;
}

colocarSair();

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
    
    textoNome = `<p>${nomeUsu} | <a onclick="logoutUser()" href="/src/main/resources/front-end/inicio-login/login.html">Sair</a></p>`;

    document.querySelector('#insertSair').innerHTML = textoNome;
}
