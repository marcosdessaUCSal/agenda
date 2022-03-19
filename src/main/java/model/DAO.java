package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** Módulo de conexão **/
	
	// Parâmetros de conexão
	private String driver = "org.mariadb.jdbc.Driver";
	private String url = "jdbc:mariadb://localhost:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";
	
	// Método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/** CRUD CREATE **/
	public void inserirContato(JavaBeans contato) {
		String create = "INSERT INTO contatos (nome, fone, email) values (?,?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			
			// preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			
			// substituir os parâmetros (?) pelo conteúdo das variáveis
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			
			// executar a query
			pst.executeUpdate();
			
			// encerrar a conexão com o banco
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD READ **/
	public ArrayList<JavaBeans> listarContatos() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "SELECT * FROM contatos ORDER BY nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/** CRUD UPDATE **/
	// selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read = "SELECT * FROM contatos WHERE idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, contato.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//setar as variáveis JavaBeans
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// editar o contato
	public void alterarContato(JavaBeans contato) {
		String create = "UPDATE contatos SET nome = ?, fone = ?, email = ? WHERE idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD DELETE **/
	public void deletarContato(JavaBeans contato) {
		String delete = "DELETE FROM contatos WHERE idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void reset() {
		String delete = "TRUNCATE TABLE contatos";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}






