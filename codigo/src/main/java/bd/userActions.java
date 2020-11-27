package bd;

import java.math.BigInteger;






import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.User;
import model.Lote;
import model.Funcionario;
import model.Fornecedor;

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
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/controle_estoque", "ti2cc", "root"); 
			status = (conexao == null);
			System.out.println("Conexao efetuada com o postgres!"); //conexão efetuada com sucesso 
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexao NAO efetuada com o postgres -- Driver nao encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexao NAO efetuada com o postgres -- " + e.getMessage());  //conexão não efetuada com Postgres
		}

		return status;
	}
	
	public boolean insertUser(String nome,String email,String senha,String empresa,String cnpj) {  //inserir um usuário no banco de dados 
		boolean status = false;

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "INSERT INTO sacolao VALUES (DEFAULT,'" + nome + "', '" + email + "', '" +senha + "','" +empresa + "', '" + cnpj + "');";  //String que possui o comando SQL para ser utilizada na função execute update o lote com seus respectivos atributos na tabela 
			//System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
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
	
	
 	public String findAll(){
		int contador = 0;
		String json = "[";
		String sql = " SELECT * FROM sacolao"; 
		String qtd_registros = "SELECT COUNT(*) FROM sacolao";
		PreparedStatement st= null;
		ResultSet rs=null;
		
		
		//Contar Registros
		int count = 0;
		PreparedStatement st_1= null;
		ResultSet rs_1=null;
		try {
		st_1 = conexao.prepareStatement(qtd_registros);
		rs_1 = st_1.executeQuery();
		 while(rs_1.next()){
			    count = rs_1.getInt("count");
		 }
		}
		catch(SQLException u) {
			throw new RuntimeException(u);
		}
			
		try {  
			 st = conexao.prepareStatement(sql);  //criar objeto para realização de comandos SQL
			 //String que possui o comando SQL para ser utilizada na função execute update o lote com seus respectivos atributos na tabela 
			//System.out.println(sql);  
			rs=st.executeQuery();
			while(rs.next()) {
				contador++;
				//System.out.println(rs.getString("nome")); 
				//System.out.println(rs.getString("empresa")); 
				//System.out.println(rs.getString("email")); 
				//System.out.println(rs.getString("cnpj")); 
				//System.out.println(rs.getString("id")); 
				Gson gson = new Gson();
				User user = new User(Integer.parseInt(rs.getString("id")),rs.getString("nome"),rs.getString("email"),rs.getString("senha"),rs.getString("empresa"),rs.getString("cnpj"));
				json += gson.toJson(user);
				if(contador==count)
					json += "";
					else
				json += ",";
               //public User(int id, String nome, String email, String senha, String empresa, String cnpj)
				
			}
			json += "]";
			st.close();		
			return json;
			
		   
		} catch (SQLException u) {  
			throw new RuntimeException(u);

		}
		
		
		
	}
 	
 	public String findAllLote(){
		int contador = 0;
		String json = "[";
		String sql = " SELECT * FROM lote"; 
		String qtd_registros = "SELECT COUNT(*) FROM lote";
		PreparedStatement st= null;
		ResultSet rs=null;
		
		
		//Contar Registros
		int count = 0;
		PreparedStatement st_1= null;
		ResultSet rs_1=null;
		try {
		st_1 = conexao.prepareStatement(qtd_registros);
		rs_1 = st_1.executeQuery();
		 while(rs_1.next()){
			    count = rs_1.getInt("count");
		 }
		}
		catch(SQLException u) {
			throw new RuntimeException(u);
		}
			
		try {  
			 st = conexao.prepareStatement(sql);  //criar objeto para realização de comandos SQL
			 //String que possui o comando SQL para ser utilizada na função execute update o lote com seus respectivos atributos na tabela 
			//System.out.println(sql);  
			rs=st.executeQuery();
			while(rs.next()) {
				contador++;
				//System.out.println(rs.getString("nome")); 
				//System.out.println(rs.getString("empresa")); 
				//System.out.println(rs.getString("email")); 
				//System.out.println(rs.getString("cnpj")); 
				//System.out.println(rs.getString("id")); 
				Gson gson = new Gson();
				Lote lote = new Lote(Integer.parseInt(rs.getString("codigo")),rs.getString("data_entrada"),Double.parseDouble(rs.getString("valor")),Integer.parseInt(rs.getString("quant_estoque")),Double.parseDouble(rs.getString("preco_unitario")),rs.getString("categoria"),rs.getString("descricao"), rs.getString("data_validade") );
				json += gson.toJson(lote);
				if(contador==count)
					json += "";
					else
				json += ",";
               
				
			}
			json += "]";
			st.close();		
			return json;
			
		   
		} catch (SQLException u) {  
			throw new RuntimeException(u);

		}
		
		
		
	}
 	
 	public String findAllFuncionario(){
		int contador = 0;
		String json = "[";
		String sql = " SELECT * FROM funcionario"; 
		String qtd_registros = "SELECT COUNT(*) FROM funcionario";
		PreparedStatement st= null;
		ResultSet rs=null;
		
		
		//Contar Registros
		int count = 0;
		PreparedStatement st_1= null;
		ResultSet rs_1=null;
		try {
		st_1 = conexao.prepareStatement(qtd_registros);
		rs_1 = st_1.executeQuery();
		 while(rs_1.next()){
			    count = rs_1.getInt("count");
		 }
		}
		catch(SQLException u) {
			throw new RuntimeException(u);
		}
			
		try {  
			 st = conexao.prepareStatement(sql);  //criar objeto para realização de comandos SQL
			 //String que possui o comando SQL para ser utilizada na função execute update o lote com seus respectivos atributos na tabela 
			//System.out.println(sql);  
			rs=st.executeQuery();
			while(rs.next()) {
				contador++;
				Gson gson = new Gson();
				Funcionario funcionario = new Funcionario(Integer.parseInt(rs.getString("codigo")),Double.parseDouble(rs.getString("salario")),rs.getString("cargo"),rs.getString("nome"));
				json += gson.toJson(funcionario);
				if(contador==count)
					json += "";
					else
				json += ",";
               
				
			}
			json += "]";
			st.close();		
			return json;
			
		   
		} catch (SQLException u) {  
			throw new RuntimeException(u);

		}
		
		
		
	}
	public String findAllFornecedor(){
		int contador = 0;
		String json = "[";
		String sql = " SELECT * FROM fornecedor"; 
		String qtd_registros = "SELECT COUNT(*) FROM fornecedor";
		PreparedStatement st= null;
		ResultSet rs=null;
		
		
		//Contar Registros
		int count = 0;
		PreparedStatement st_1= null;
		ResultSet rs_1=null;
		try {
		st_1 = conexao.prepareStatement(qtd_registros);
		rs_1 = st_1.executeQuery();
		 while(rs_1.next()){
			    count = rs_1.getInt("count");
		 }
		}
		catch(SQLException u) {
			throw new RuntimeException(u);
		}
			
		try {  
			 st = conexao.prepareStatement(sql);  //criar objeto para realização de comandos SQL
			 //String que possui o comando SQL para ser utilizada na função execute update o lote com seus respectivos atributos na tabela 
			//System.out.println(sql);  
			rs=st.executeQuery();
			while(rs.next()) {
				contador++;
				Gson gson = new Gson();
				Fornecedor fornecedor = new Fornecedor(Integer.parseInt(rs.getString("codigo")),rs.getString("nome"),rs.getString("endereco"),rs.getString("email"));
				json += gson.toJson(fornecedor);
				if(contador==count)
					json += "";
					else
				json += ",";
               
				
			}
			json += "]";
			st.close();		
			return json;
			
		   
		} catch (SQLException u) {  
			throw new RuntimeException(u);

		}
		
		
		
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
			String sql = "INSERT INTO lote VALUES (DEFAULT,'" + data_compra + "', " + valor_compra + ", " + quantidade + "," + valor_unitario + ", '" + categoria + "', '" + desc + "' , '" + data_validade + "', " + id_item + ", '38415290000107');";  //String que possui o comando SQL para ser utilizada na função execute update o lote com seus respectivos atributos na tabela 
			//System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	public boolean alterarLote(int id, String nome) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE lote SET descricao='" + nome +"' WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	
	
	public boolean alterarLoteEstoque(int id, int quantidade) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE lote SET quant_estoque=" + quantidade +" WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	//===================================================================================VALIDADE===============================================================================================================================================================================================
	public boolean alterarLoteValidade(int id, String validade) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE lote SET data_validade='" + validade +"' WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	//==============================================================================ENTRADA=============================================================================================================================================================================================
	public boolean alterarLoteEntrada(int id, String entrada) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		System.out.println("entrada="+entrada);
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE lote SET data_entrada='" + entrada +"' WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	
	
	
	
	//==============================================================================CATEGORIA=====================================================================================--=-======================================================================================
	public boolean alterarLoteCategoria(int id, String categoria) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE lote SET categoria='" + categoria +"' WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	
	//========================================================================================PRECO UNITARIO===========================================================================================================================================================================
	public boolean alterarLotePrecoUn(int id, double preco) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE lote SET preco_unitario=" + preco +" WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	//========================================================================================PRECO TOAL===========================================================================================================================================================================
	public boolean alterarLotePreco(int id, double preco) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE lote SET valor=" + preco +" WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	
	
	//--------------------------------------------------------------------------------------FUNCIONARIO-----------------------------------------------------------------------------------------------------------------------
	
	//===========================================================================NOME=============================================================================================================================================================================
	
	public boolean alterarFuncionarioNome(int id, String nome) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE funcionario SET nome='" + nome +"' WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	//=====================================================================CARGO============================================================================================================================================================================
	
	public boolean alterarFuncionarioCargo(int id, String cargo) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE funcionario SET cargo='" + cargo +"' WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	
	//===============================================================SALARIO==========================================================================================================================================================================
	
	public boolean alterarFuncionarioSalario(int id, double salario) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE funcionario SET salario='" + salario +"' WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	
	
	//--------------------------------------------------------------------------------------FORNECEDOR-----------------------------------------------------------------------------------------------------------------------
	
	//===========================================================================NOME=============================================================================================================================================================================
	
	public boolean alterarFornecedorNome(int id, String nome) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE fornecedor SET nome='" + nome +"' WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	//===========================================================================ENDERECO=============================================================================================================================================================================
	
	public boolean alterarFornecedorEndereco(int id, String endereco) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE fornecedor SET endereco='" + endereco +"' WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	//===========================================================================EMAIL=============================================================================================================================================================================
	
	public boolean alterarFornecedorEmail(int id, String email) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "UPDATE fornecedor SET email='" + email +"' WHERE codigo= "+ id + "";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	
	
	
	
	
	
	
	
	
	
	//==================================================================================DELETE===================================================================================================================================================================================================
	public boolean deleteLote(int id) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "DELETE FROM lote WHERE codigo=" + id +"";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	public boolean deleteFuncionario(int id) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "DELETE FROM funcionario WHERE codigo=" + id +"";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	
	public boolean deleteFornecedor(int id) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "DELETE FROM fornecedor WHERE codigo=" + id +"";
			System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	
	
	
	
	
	
	
	
	
	public boolean insertEmployee(String nome,String cargo, double salario) {  //inserir um usuário no banco de dados 
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "INSERT INTO funcionario VALUES (DEFAULT," + salario + ", '" + cargo + "', '" + nome+ "');";  //String que possui o comando SQL para ser utilizada na função execute update o lote com seus respectivos atributos na tabela 
			//System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	public boolean insertFornecedor(String nome, String endereco, String email) {  //inserir um usuário no banco de dados 
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "INSERT INTO fornecedor VALUES (DEFAULT,'" + nome + "', '" + endereco + "', '" + email+ "');";  //String que possui o comando SQL para ser utilizada na função execute update o lote com seus respectivos atributos na tabela 
			//System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
	
	
	
	
	
	
	
	
	
	
	
/*	
	
	public boolean addUser(String name, String email, String senha, String empresa, int cnpj) throws ParseException { //adicionar um lote com seus respectivos atributos: descrição, data da compra, data de validade, categoria, id, quantidade de produtos no lote e valor unitário do produto
		boolean status = false;
		

		try {  
			Statement st = conexao.createStatement();  //criar objeto para realização de comandos SQL
			String sql = "INSERT INTO sacolao VALUES (DEFAULT,'" + name + "', " + email + ", " +senha + "," +empresa + ", '" + cnpj + "');";  //String que possui o comando SQL para ser utilizada na função execute update o lote com seus respectivos atributos na tabela 
			//System.out.println(sql);  
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);  //erro para caso o lote não seja adicionado à tabela 
		}
		return status;
	}
*/
	
}