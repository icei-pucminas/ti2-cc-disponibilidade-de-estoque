package service;
import model.User;
import model.Funcionario;
//import model.Fornecedor;
import java.sql.Date;
import java.text.ParseException;

import bd.userActions;
import spark.Request;
import spark.Response;
public class FornecedorService {
	private userActions con;
	public FornecedorService() {
		con = new userActions(); //criar um novo objeto do tipo userActions 
	}
	public Object addFornecedor(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String nome = request.queryParams("nome");       
		String endereco = request.queryParams("endereco");
		String email = request.queryParams("email");
		System.out.println(nome);
		System.out.println(endereco);
		System.out.println(email);
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.insertFornecedor(nome,endereco,email))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	public Object acharTodosFornecedor(Request request, Response response) throws ParseException { 
		 response.header("Content-Type", "application/json");
	     response.header("Content-Encoding", "UTF-8");
	     con.connect(); //enviar requisi��o

		 String answer = con.findAllFornecedor();
		 System.out.println(answer);
		 if(answer != null) {
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		 }else {
			response.status(404);
		 }//caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return answer;
	}
	
	
	public Object alterFornecedorNome(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String nome = request.queryParams("nome");       
		int id= Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarFornecedorNome(id,nome))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	public Object alterFornecedorEndereco(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String endereco = request.queryParams("endereco");       
		int id= Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarFornecedorEndereco(id,endereco))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	public Object alterFornecedorEmail(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String email = request.queryParams("email");       
		int id= Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarFornecedorEmail(id,email))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	public Object deletarFornecedor(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP ;       
		int id_item = Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.deleteFornecedor(id_item))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	
	
}
