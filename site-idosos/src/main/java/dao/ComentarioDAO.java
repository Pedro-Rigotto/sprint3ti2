package dao;

import model.Comentario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que faz os comandos SQL da classe Comentario
 * 
 * @author Pedro R, Henrique, André M
 *
 */
public class ComentarioDAO extends DAO {
	public ComentarioDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	/**
	 * Faz a query para inserção de um Comentario no banco, e logo após abre a
	 * conexão
	 * e manda para o banco;
	 * 
	 * @param Comentario Object:Comentario que será inserido;
	 * @return TRUE: caso seja concluido com sucesso; FALSE: caso seja não seja
	 *         concluido;
	 * @throws RuntimeException Exceção caso o sistema não consiga completar essa
	 *                          requisição;
	 */
	public boolean insert(Comentario comentario) {
		boolean status = false;
		try {
			String sql = "INSERT INTO mydb.comentario (descricao, publicado, autor, tutorial, dataPublicacao) "
					+ "VALUES ('" + comentario.getDescricao() + "', "
					+ comentario.getPublicado() + ", " + comentario.getAutor() + "," + comentario.getTutorial();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setDate(2, Date.valueOf(comentario.getDataPublicacao()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	/**
	 * retorna todos os comentarios do banco;
	 *
	 * @param id chave de busca
	 * @return Comentario pesquisado do tipo Object:Comentario
	 * @throws Exception Erro generalizado de acordo com requisição.
	 */
	public Comentario get(int id) {
		Comentario comentario = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM mydb.comentario WHERE id=" + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				comentario = new Comentario(rs.getInt("id"), rs.getString("descricao"),
						rs.getDate("datapublicacao").toLocalDate(), rs.getInt("publicado"),
						rs.getInt("autor"), rs.getInt("tutorial"));
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return comentario;
	}

	/**
	 * Metodo que traz a lista de comentarios no banco, requisição via SQL
	 * 
	 * @param orderBy refencia qual é a ordem que deve retornar a lista de
	 *                comentarios.
	 * @return List<Comentario> comentarios
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Comentario> get(String orderBy) {
		List<Comentario> comentarios = new ArrayList<Comentario>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM mydb.comentario"
					+ ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Comentario p = new Comentario(rs.getInt("id"), rs.getString("descricao"),
						rs.getDate("dataPublicacao").toLocalDate(), rs.getInt("publicado"),
						rs.getInt("autor"), rs.getInt("tutorial"));
				comentarios.add(p);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return comentarios;
	}

	/**
	 * Metodo que traz a lista de comentarios no banco sem ordenação , requisição
	 * via SQL
	 * 
	 * @return List<Comentario> comentarios
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Comentario> get() {
		return get("");
	}

	/**
	 * Metodo que traz a lista de comentarios no banco ordernado por ID , requisição
	 * via SQL
	 * 
	 * @return List<Comentario> comentarios
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Comentario> getOrderByID() {
		return get("id");
	}

	/**
	 * Metodo que traz a lista de comentarios no banco ordernado por descrição ,
	 * requisição via SQL
	 * 
	 * @return List<Comentario> comentarios
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Comentario> getOrderByDescricao() {
		return get("descricao");
	}

	/**
	 * Metodo que traz a lista de comentarios no banco ordernado por autor ,
	 * requisição via SQL
	 * 
	 * @return List<Comentario> comentarios
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Comentario> getOrderByAutor() {
		return get("autor");
	}

	/**
	 * Metodo que faz a atualização do comentario no banco;
	 * 
	 * @param usuario Object:Comentario que será atualizado no banco;
	 * @return TRUE: Caso seja atualizado com sucesso; FALSE: Ocorra durante a
	 *         requisição com o banco de dados;
	 * @throws RuntimeException Caso ocorra um problema durante a requisição no
	 *                          banco de dados, será lançada a exceção;
	 */
	public boolean update(Comentario comentario) {
		boolean status = false;
		try {
			String sql = "UPDATE comentario SET descricao = '" + comentario.getDescricao() + "', "
					+ "datapublicacao = ?, "
					+ "publicado = " + comentario.getPublicado() + ", "
					+ "autor = " + comentario.getAutor() + ","
					+ "tutorial = " + comentario.getTutorial()
					+ " WHERE comentario_id = " + comentario.getId();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setDate(2, Date.valueOf(comentario.getDataPublicacao()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	/**
	 * Metodo que faz a exclusão do comentario no banco;
	 * 
	 * @param id identificador do comentario que será excluido;
	 * @return TRUE: Caso seja deletado com sucesso; FALSE: Ocorra durante a
	 *         requisição com o banco de dados;
	 * @throws SQLException Caso ocorra um problema durante a requisição no banco de
	 *                      dados, será lançada a exceção;
	 */
	public boolean delete(int id) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM mydb.comentario WHERE comentario_id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
}