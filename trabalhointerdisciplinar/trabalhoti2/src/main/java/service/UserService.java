package service;

import model.User;

import java.sql.Date;

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
	
	
}
