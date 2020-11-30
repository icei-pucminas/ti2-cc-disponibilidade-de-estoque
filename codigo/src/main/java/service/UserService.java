package service;

import model.User;

import java.sql.Date;
import java.text.ParseException;

import bd.userActions;
import spark.Request;
import spark.Response;

public class UserService {
	
	private userActions con;
	public UserService() {
		con = new userActions(); //criar um novo objeto do tipo userActions 
	}
	
	public Object searchUser(Request request, Response response) { //pegar os dados passados pelo formul�rio por meio de uma requisi��o HTTP 
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");      
		User resposta = new User(0,"null",email,senha,"null","null");  //criar objeto usu�rio passando os par�metros do formul�rio
		
		con.connect();
		if(con.searchUser(email,senha))
			response.status(200);  //status de resposta 200= sucesso
		else
			response.status(404); //status 400 = erro do usu�rio 
		
		return email;
	}
	public Object addUser(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String nome = request.queryParams("nome");       
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");
		String empresa= request.queryParams("empresa");
		String cnpj = request.queryParams("cnpj");
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.insertUser(nome,email,senha,empresa,cnpj))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	public Object acharTodos(Request request, Response response) throws ParseException { 
		 response.header("Acess-Control-Allow-Origin", "*");
		 response.header("Content-Type", "application/json");
 	     response.header("Content-Encoding", "UTF-8");
 	     con.connect(); //enviar requisi��o
 
		 String answer = con.findAll();
		 System.out.println(answer);
		 if(answer != null) {
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		 }else {
			response.status(404);
		 }//caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return answer;
	}
	
	
}