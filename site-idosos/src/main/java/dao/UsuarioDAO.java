package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que faz os comandos SQL da classe Usuario
 * 
 * @author Pedro R , André M,Henrique
 *
 */
public class UsuarioDAO extends DAO {
    public UsuarioDAO() {
        super();
        conectar();
    }

    /**
     * @return
     */
    public void finalize() {
        close();
    }

    /**
     * Faz a query para inserção de um usuario no banco, e logo após abre a conexão
     * e manda para o banco;
     * 
     * @param usuario Object:Usuario que será inserido;
     * @return TRUE: caso seja concluido com sucesso; FALSE: caso seja não seja
     *         concluido;
     * @throws RuntimeException Exceção caso o sistema não consiga completar essa
     *                          requisição;
     */
    public boolean insert(Usuario usuario) {
        boolean status = false;
        try {
            String sql = "INSERT INTO mydb.usuario (username, nome, email, password, tipo_usuario, telefone) "
                    + "VALUES ('" + usuario.getUsername() + "', '" + usuario.getNome() + "', '" + usuario.getEmail()
                    + "', '"
                    + usuario.getPassword() + "', " + usuario.getTipoUsuario()
                    + ", '" + usuario.getTelefone() + "');";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    /**
     * retorna todos os usuarios do banco;
     *
     * @param id chave de busca
     * @return Usuario pesquisado do tipo Object:Usuario
     * @throws Exception Erro generalizado de acordo com requisição.
     */
    public Usuario get(int id) {
        Usuario usuario = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM mydb.usuario WHERE id_hash=" + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                usuario = new Usuario(rs.getString("username"), rs.getString("nome"), rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("tipo_usuario"), rs.getString("telefone"), rs.getInt("id_hash"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuario;
    }

    /**
     * Metodo que traz a lista de usuarios no banco, requisição via SQL
     * 
     * @param orderBy refencia qual é a ordem que deve retornar a lista de usuarios.
     * @return List<Usuario> usuarios
     * @throws Erro generalizado de acordo com requisição.
     */
    public List<Usuario> get(String orderBy) {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM mydb.usuario"
                    + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuario p = new Usuario(rs.getString("username"), rs.getString("nome"), rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("tipo_usuario"), rs.getString("telefone"), rs.getInt("id_hash"));
                usuarios.add(p);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuarios;
    }

    /**
     * Metodo que traz a lista de usuarios no banco sem ordenação , requisição via
     * SQL
     * 
     * @return List<Usuario> usuarios
     * @throws Erro generalizado de acordo com requisição.
     */
    public List<Usuario> get() {
        return get("");
    }

    /**
     * Metodo que traz a lista de usuarios no banco ordernado por ID , requisição
     * via SQL
     * 
     * @return List<Usuario> usuarios
     * @throws Erro generalizado de acordo com requisição.
     */
    public List<Usuario> getOrderByID() {
        return get("id_hash");
    }

    /**
     * Metodo que traz a lista de usuarios no banco ordernado por username ,
     * requisição via SQL
     * 
     * @return List<Usuario> usuarios
     * @throws Erro generalizado de acordo com requisição.
     */
    public List<Usuario> getOrderByUsername() {
        return get("username");
    }

    /**
     * Metodo que traz a lista de usuarios no banco ordernado por nome , requisição
     * via SQL
     * 
     * @return List<Usuario> usuarios
     * @throws Erro generalizado de acordo com requisição.
     */
    public List<Usuario> getOrderByNome() {
        return get("nome");
    }

    /**
     * Metodo que traz a lista de usuarios no banco ordernado por email , requisição
     * via SQL
     * 
     * @return List<Usuario> usuarios
     * @throws Erro generalizado de acordo com requisição.
     */
    public List<Usuario> getOrderByEmail() {
        return get("email");
    }

    /**
     * Metodo que traz a lista de usuarios no banco ordernado por password ,
     * requisição via SQL
     * 
     * @return List<Usuario> usuarios
     * @throws Erro generalizado de acordo com requisição.
     */
    public List<Usuario> getOrderByPassword() {
        return get("password");
    }

    /**
     * Metodo que traz a lista de usuarios no banco ordernado por Tipo de usuario ,
     * requisição via SQL
     * 
     * @return List<Usuario> usuarios
     * @throws Erro generalizado de acordo com requisição.
     */
    public List<Usuario> getOrderByTipoUsuario() {
        return get("tipo_usuario");
    }

    /**
     * Metodo que traz a lista de usuarios no banco ordernado por Telefone ,
     * requisição via SQL
     * 
     * @return List<Usuario> usuarios
     * @throws Erro generalizado de acordo com requisição.
     */
    public List<Usuario> getOrderByTelefone() {
        return get("telefone");
    }

    /**
     * Metodo que faz a atualização do usuario no banco;
     * 
     * @param usuario Object:Usuario que será atualizado no banco;
     * @return TRUE: Caso seja atualizado com sucesso; FALSE: Ocorra durante a
     *         requisição com o banco de dados;
     * @throws RuntimeException Caso ocorra um problema durante a requisição no
     *                          banco de dados, será lançada a exceção;
     */
    public boolean update(Usuario usuario) {
        boolean status = false;
        try {
            String sql = "UPDATE mydb.usuario SET username = '" + usuario.getUsername() + "', "
                    + "nome = '" + usuario.getNome() + "', "
                    + "email = '" + usuario.getEmail() + "', "
                    + "password = '" + usuario.getPassword() + "',"
                    + "tipo_usuario = " + usuario.getTipoUsuario() + ","
                    + "telefone = '" + usuario.getTelefone() + "' WHERE id_hash = " + usuario.getId();
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    /**
     * Metodo que faz a exclusão do usuario no banco;
     * 
     * @param id identificador do usuario que será excluido;
     * @return TRUE: Caso seja deletado com sucesso; FALSE: Ocorra durante a
     *         requisição com o banco de dados;
     * @throws SQLException Caso ocorra um problema durante a requisição no banco de
     *                      dados, será lançada a exceção;
     */
    public boolean delete(int id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM mydb.usuario WHERE id_hash = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    /**
     * Metodo que faz a verificação do usuario e senha no banco;
     * 
     * @param nome  username que será verificado;
     * @param senha senha que será verificada;
     * @return id: Caso exista no banco de dados; -1 Caso não exista
     *       
     * @throws SQLException Caso ocorra um problema durante a requisição no banco de
     *                      dados, será lançada a exceção;
     */
    public int efetuarLogin(String nome, String senha) {
        try {
            // 1 passo criar o comando sql
            String cmdsql = "select * from mydb.usuario where username =? and password =?";
            // 2 passo- Organizar o cmdsql e executalo
            PreparedStatement stmt = conexao.prepareStatement(cmdsql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            // 3 passo- executa o comando
            ResultSet rs = stmt.executeQuery();
            // 4 passo- verificar Qual tipo foi retornado
            if (rs.first()) {
                return rs.getInt("id_hash");
            } else {
                return -1;
            }
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    /**
     * retorna um usuario buscando pelo nome de usuário;
     *
     * @param username chave de busca
     * @return Usuario pesquisado do tipo Object:Usuario
     * @throws Exception Erro generalizado de acordo com requisição.
     */
    public Usuario getUsuario(String username) {
        Usuario usuario = null;

        try {
            String sql = "SELECT * FROM mydb.usuario WHERE username=?";
            PreparedStatement st = conexao.prepareStatement(sql);
    		st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getString("username"), rs.getString("nome"), rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("tipo_usuario"), rs.getString("telefone"), rs.getInt("id_hash"));
            } else {
                usuario = new Usuario();
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuario;
    }
}