package bd;

import java.math.BigInteger;


import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.User;


public class userActions {
	
	private Connection conexao;   
	
	
	public userActions() {
		conexao = null;
	}
	//fazer a conexão com o postgreSQL 
	public boolean connect() {            
		boolean status = false;
		System.out.println("oioi");
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/controle_estoque","ti2cc", "root"); 
			status = (conexao == null);
			System.out.println("Conexao efetuada com o postgres!"); //conexão efetuada com sucesso 
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexao NAO efetuada com o postgres -- Driver nao encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexao NAO efetuada com o postgres -- " + e.getMessage());  //conexão não efetuada com Postgres
		}

		return status;
	}
	
	public boolean insertUser(User usuario) {  //inserir um usuário no banco de dados 
		boolean status = false;
		try {
			Statement st = conexao.createStatement();  //objeto para realização de um comando SQL, podendo este ser UPDATE, INSERT ou DELETE
			st.executeUpdate("INSERT INTO sacolao VALUES ("
						   + usuario.getId() + ", '" + usuario.getNome() + "', '"+ usuario.getEmail() 
					       + "', '" + usuario.getSenha() + "', '" + usuario.getEmpresa() + "', '" + usuario.getCnpj() + "');"); //inserir um usuário que se refere a um sacolao na tabela de usário com seus respectivos atributos: id, nome, senha, empresa
			st.close(); 
			status = true;
		} catch (SQLException u) {   //retornar erro caso não tenha adicionado o usuário com sucesso 
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean deleteUser(int id) { //método para exlcluir um usuário
		boolean status = false;
		try {  
			Statement st = conexao.createStatement(); 
			st.executeUpdate("DELETE FROM sacolao WHERE id = " + id); 
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean searchUser(String email, String senha) { //procurar por um usuário existente
		boolean status = false;
		int count = 0;
		try {  
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM sacolao WHERE email = '" + email + "' AND senha = '" + senha + "'");  //selecionar um usuário da tabela 
			while (rs.next()) {
			    count++;
			}
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return (count == 1) ? true : false; //1= sucesso 0=erro 
	}
	
	public boolean addLote(String desc, String data_compra, String data_validade, String categoria, int id_item, int quantidade, double valor_unitario, double valor_compra) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "INSERT INTO lote VALUES (DEFAULT,'" + data_compra + "', " + valor_compra + ", " + quantidade + "," + valor_unitario + ", '" + categoria + "', '" + desc + "' , '" + data_validade + "', " + id_item + ", '57853485000183');";  //String que possui o comando SQL para ser utilizada na função execute update o lote com seus respectivos atributos na tabela 
			//System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}

	
}