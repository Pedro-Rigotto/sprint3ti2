package model;

/**
 * Classe de usuarios e seus metodos. Contem nome de usuario, email, senha, tipo de usuario, telefone, e id.
 * @author Pedro Rigotto
 *
 */
public class Usuario {
    private String username;
    private String nome;
    private String email;
    private String password;
    private int tipoUsuario;
    private String telefone;
    private int id;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Usuario(String username, String nome, String email, String password, int tipoUsuario, String telefone, int id) {
        super();
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.telefone = telefone;
        this.id = id;
    }
    public Usuario() {
        super();
        this.username = "";
        this.nome = "";
        this.email = "";
        this.password = "";
        this.tipoUsuario = 0;
        this.telefone = "";
        this.id = -1;
    }
    
    @Override
    public boolean equals(Object obj) {
        return (this.getId() == ((Usuario) obj).getId());
    }
}
