package dao;

import model.Categoria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que faz os comandos SQL da classe Categoria
 * 
 * @author Pedro R, André M
 *
 */
public class CategoriaDAO extends DAO {
	public CategoriaDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}

	/**
	 * Faz a query para inserção de um Categoria no banco, e logo após abre a
	 * conexão
	 * e manda para o banco;
	 * 
	 * @param categoria Object:Categoria que será inserido;
	 * @return TRUE: caso seja concluido com sucesso; FALSE: caso seja não seja
	 *         concluido;
	 * @throws RuntimeException Exceção caso o sistema não consiga completar essa
	 *                          requisição;
	 */
	public boolean insert(Categoria categoria) {
		boolean status = false;
		try {
			String sql = "INSERT INTO mydb.categoria (nome, supercategoria) "
					+ "VALUES ('"
					+ categoria.getNome() + "', '" 
					+ categoria.getSupercategoria() + "');";
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
	 * retorna todos as Categorias do banco;
	 *
	 * @param id chave de busca
	 * @return Categoria pesquisado do tipo Object:Categoria
	 * @throws Exception Erro generalizado de acordo com requisição.
	 */
	public Categoria get(int id) {
		Categoria categoria = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM mydb.categoria WHERE id_categoria=" + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				categoria = new Categoria(rs.getInt("id_categoria"), rs.getString("nome"), rs.getString("supercategoria"));
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return categoria;
	}

	/**
	 * Metodo que traz a lista de categorias no banco, requisição via SQL
	 * 
	 * @param orderBy refencia qual é a ordem que deve retornar a lista de
	 *                categorias.
	 * @return List<Categoria> categorias
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Categoria> get(String orderBy) {
		List<Categoria> categoria = new ArrayList<Categoria>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM mydb.categoria"
					+ ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Categoria p = new Categoria(rs.getInt("id_categoria"), rs.getString("nome"), rs.getString("supercategoria"));
				categoria.add(p);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return categoria;
	}

	/**
	 * Metodo que traz a lista de categorias no banco sem ordenação , requisição via
	 * SQL
	 * 
	 * @return List<Categoria> categorias
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Categoria> get() {
		return get("");
	}

	/**
	 * Metodo que traz a lista de categorias no banco ordernado por ID , requisição
	 * via SQL
	 * 
	 * @return List<Categoria> categorias
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Categoria> getOrderByID() {
		return get("id_categoria");
	}

	/**
	 * Metodo que traz a lista de categorias no banco ordernado por nome ,
	 * requisição via SQL
	 * 
	 * @return List<Categoria> categorias
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Categoria> getOrderByNome() {
		return get("nome");
	}

	/**
	 * Metodo que traz a lista de categorias no banco ordernado por supercategoria ,
	 * requisição via SQL
	 * 
	 * @return List<Categoria> categorias
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Categoria> getOrderBySupercategoria() {
		return get("supercategoria");
	}

	/**
	 * Metodo que traz a lista de categorias no banco de uma certa supercategoria, requisição via SQL
	 * 
	 * @param supercategoria referencia qual é a supercategoria desejada
	 * @param orderBy refencia qual é a ordem que deve retornar a lista de
	 *                categorias.
	 * @return List<Categoria> categorias
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<Categoria> getSupercategoria(String supercategoria, String orderBy) {
		List<Categoria> categoria = new ArrayList<Categoria>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM mydb.categoria"
					+ " WHERE supercategoria='" + supercategoria + "'"
					+ ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Categoria p = new Categoria(rs.getInt("id_categoria"), rs.getString("nome"), rs.getString("supercategoria"));
				categoria.add(p);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return categoria;
	}

	/**
	 * Metodo que traz a lista de supercategorias no banco, requisição via SQL
	 * 
	 * @param orderBy refencia qual é a ordem que deve retornar a lista de
	 *                categorias.
	 * @return List<String> supercategorias
	 * @throws Erro generalizado de acordo com requisição.
	 */
	public List<String> getListaSupercategoria(String orderBy) {
		List<String> supercategorias = new ArrayList<String>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT supercategoria FROM mydb.categoria GROUP BY supercategoria"
					+ ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String p = rs.getString("supercategoria");
				supercategorias.add(p);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return supercategorias;
	}

	/**
	 * Metodo que faz a atualização da Categoria no banco;
	 * 
	 * @param categoria Object:Categoria que será atualizado no banco;
	 * @return TRUE: Caso seja atualizado com sucesso; FALSE: Ocorra durante a
	 *         requisição com o banco de dados;
	 * @throws RuntimeException Caso ocorra um problema durante a requisição no
	 *                          banco de dados, será lançada a exceção;
	 */
	public boolean update(Categoria categoria) {
		boolean status = false;
		try {
			String sql = "UPDATE mydb.categoria SET nome = '" + categoria.getNome()
					+ "', supercategoria = '" + categoria.getSupercategoria()
					+ "' WHERE id_categoria = " + categoria.getId();
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
	 * Metodo que faz a exclusão do categoria no banco;
	 * 
	 * @param id identificador do categoria que será excluido;
	 * @return TRUE: Caso seja deletado com sucesso; FALSE: Ocorra durante a
	 *         requisição com o banco de dados;
	 * @throws SQLException Caso ocorra um problema durante a requisição no banco de
	 *                      dados, será lançada a exceção;
	 */
	public boolean delete(int id) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM mydb.categoria WHERE id_categoria = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
}