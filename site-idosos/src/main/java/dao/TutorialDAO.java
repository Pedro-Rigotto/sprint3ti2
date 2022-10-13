package dao;

import model.Tutorial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que faz os comandos SQL da classe Tutorial
 * 
 * @author Pedro R,André M, Henrique
 *
 */
public class TutorialDAO extends DAO {
	public TutorialDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	/**
	 * Faz a query para inserção de um Tutorial no banco, e logo após abre a conexão
	 * e manda para o banco;
	 * 
	 * @param tutorial Object:Tutorial que será inserido;
	 * @return TRUE: caso seja concluido com sucesso; FALSE: caso seja não seja
	 *         concluido;
	 * @throws RuntimeException Exceção caso o sistema não consiga completar essa
	 *                          requisição;
	 */
	public boolean insert(Tutorial tutorial) {
		boolean status = false;
		try {
			String sql = "INSERT INTO mydb.tutorial (texto, titulo, autor, data_criacao, publicado, link_youtube, id_categoria) "
					+ "VALUES ('" + tutorial.getTexto() + "', '"
					+ tutorial.getTitulo() + "', " + tutorial.getAutor() + ", ?, "
					+ tutorial.getPublicado() + ", '" + tutorial.getVideo() + "', "
					+ tutorial.getCategoria() + ") ;";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setDate(1, Date.valueOf(tutorial.getDataCriacao()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	/**
	 * retorna todos os tutoriais do banco;
	 *
	 * @param id_tutorial chave de busca
	 * @return tutorial pesquisado do tipo Object:Tutorial
	 * @throws Exception Erro generalizado de acordo com requisição.
	 */
	public Tutorial get(int id_tutorial) {
		Tutorial tutorial = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM mydb.tutorial WHERE id_tutorial=" + id_tutorial;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				tutorial = new Tutorial(rs.getInt("id_tutorial"), rs.getString("texto"), rs.getString("titulo"),
						rs.getInt("autor"), rs.getDate("data_criacao").toLocalDate(),
						rs.getInt("publicado"), rs.getString("link_youtube"), rs.getInt("id_categoria"));

			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return tutorial;
	}

	/**
	 * Metodo que traz a lista de tutoriais no banco, requisição via SQL
	 * 
	 * @param orderBy refencia qual é a ordem que deve retornar a lista de
	 *                tutoriais.
	 * @return List<Tutorial> tutoriais
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Tutorial> get(String orderBy) {
		List<Tutorial> tutoriais = new ArrayList<Tutorial>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM mydb.tutorial"
					+ ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Tutorial p = new Tutorial(rs.getInt("id_tutorial"), rs.getString("texto"), rs.getString("titulo"),
						rs.getInt("autor"), rs.getDate("data_criacao").toLocalDate(), rs.getInt("publicado"),
						rs.getString("link_youtube"), rs.getInt("id_categoria"));
				tutoriais.add(p);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return tutoriais;
	}

	/**
	 * Metodo que traz a lista de tutoriais no banco sem ordenação , requisição via
	 * SQL
	 * 
	 * @return List<Tutorial> tutoriais
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Tutorial> get() {
		return get("");
	}

	/**
	 * Metodo que traz a lista de tutoriais no banco ordernado por ID , requisição
	 * via SQL
	 * 
	 * @return List<Tutorial> tutoriais
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Tutorial> getOrderByID() {
		return get("id_tutorial");
	}

	/**
	 * Metodo que traz a lista de tutoriais no banco ordernado por Categoria ,
	 * requisição via SQL
	 * 
	 * @return List<Tutorial> tutoriais
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Tutorial> getOrderByCategoria() {
		return get("id_categoria");
	}

	/**
	 * Metodo que traz a lista de tutoriais no banco ordernado por autor ,
	 * requisição via SQL
	 * 
	 * @return List<Tutorial> tutoriais
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Tutorial> getOrderByAutor() {
		return get("autor");
	}

	/**
	 * Metodo que traz a lista de tutoriais no banco ordernado por data de criação ,
	 * requisição via SQL
	 * 
	 * @return List<Tutorial> tutoriais
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Tutorial> getOrderByDataCriacao() {
		return get("data_criacao");
	}

	/**
	 * Metodo que traz a lista de tutoriais no banco ordernado se está publicado ,
	 * requisição via SQL
	 * 
	 * @return List<Tutorial> tutoriais
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Tutorial> getOrderByPublicado() {
		return get("publicado");
	}

	/**
	 * Metodo que traz a lista de tutoriais no banco ordernado por ordem alfabetica
	 * do YouTube , requisição via SQL
	 * 
	 * @return List<Tutorial> tutoriais
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Tutorial> getOrderByLinkYoutube() {
		return get("link_youtube");
	}

	/**
	 * Metodo que traz a lista de tutoriais no banco ordernado por Data de criação ,
	 * requisição via SQL
	 * 
	 * @return List<Tutorial> tutoriais
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Tutorial> getOrderBy() {
		return get("data_criacao");
	}

	/**
	 * Metodo que faz a atualização do Tutorial no banco;
	 * 
	 * @param Tutorial Object:Tutorial que será atualizado no banco;
	 * @return TRUE: Caso seja atualizado com sucesso; FALSE: Ocorra durante a
	 *         requisição com o banco de dados;
	 * @throws RuntimeException Caso ocorra um problema durante a requisição no
	 *                          banco de dados, será lançada a exceção;
	 */
	public boolean update(Tutorial tutorial) {
		boolean status = false;
		try {
			String sql = "UPDATE mydb.tutorial SET texto = '" + tutorial.getTexto() + "', "
					+ "titulo = '" + tutorial.getTitulo() + "', "
					+ "autor = " + tutorial.getAutor() + ", "
					+ "data_criacao = ? ,"
					+ "publicado = " + tutorial.getPublicado() + ", "
					+ "link_youtube = '" + tutorial.getVideo() + "', "
					+ "id_categoria = " + tutorial.getCategoria()
					+ " WHERE id_tutorial = " + tutorial.getId();
			System.out.println(sql);
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setDate(1, Date.valueOf(tutorial.getDataCriacao()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	/**
	 * Metodo que faz a exclusão do tutorial no banco;
	 * 
	 * @param id identificador do usuario que será excluido;
	 * @return TRUE: Caso seja deletado com sucesso; FALSE: Ocorra durante a
	 *         requisição com o banco de dados;
	 * @throws SQLException Caso ocorra um problema durante a requisição no banco de
	 *                      dados, será lançada a exceção;
	 */
	public boolean delete(int id_tutorial) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM mydb.tutorial WHERE id_tutorial = " + id_tutorial);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
}