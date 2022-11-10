package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

/**
 * Classe que faz a conexao com o servidor PostgreSQL. Deve ser criado um banco
 * de dados de nome "idosos".
 * 
 * @author Pedro R, André M
 *
 */
public class DAO {
    protected Connection conexao;

    public DAO() {
        conexao = null;
    }

    /**
     * Metodo que faz a conexão com o banco;
     * 
     * @return TRUE: Caso seja conectado com sucesso; FALSE: Ocorra durante a
     *         requisição com o banco de dados;
     * @throws ClassNotFoundException Caso ocorra um problema autenticação no banco
     *                                de dados;
     * @throws SQLException           Caso a requisição do banco esteja incorreta;
     */
    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "idosos";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "ti2cc";
        String password = "ti@cc";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao == null);
            System.out.println("Conexão efetuada com o postgres!");
        } catch (ClassNotFoundException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return status;
    }

    /**
     * Metodo que fecha a conexão com o banco;
     * 
     * @return TRUE: Caso seja desconectado com sucesso; FALSE: Ocorra durante a
     *         requisição com o banco de dados;
     * @throws SQLException Caso seja tenha fechado a conexão e/ou erro na conexão;
     */
    public boolean close() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    /**
     * Função que recebe a senha e transforma em MD5.
     * @param senha
     * @return A senha em MD5
     * @throws Exception
     */
    public static String toMD5(String senha) throws Exception {
		MessageDigest m=MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(),0, senha.length());
		return new BigInteger(1,m.digest()).toString(16);
	}

}

