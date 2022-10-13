function goBack() {
    window.history.back()
}

function logoutUser () {
    usuarioCorrente = {};
    sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));
    window.location = LOGIN_URL;
}

colocarSair();

//apenas para pagina que podem ser acessadas com ou sem login
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

    if( nomeUsu )
    {
        document.querySelector('#insertSair').innerHTML = textoNome;
    }

}