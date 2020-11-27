package service;

import model.User;

import java.sql.Date;
import java.text.ParseException;

import bd.userActions;
import spark.Request;
import spark.Response;

public class FuncionarioService {
	
	private userActions con;
	public FuncionarioService() {
		con = new userActions(); //criar um novo objeto do tipo userActions 
	}
	
	public Object addFuncionario(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String nome = request.queryParams("nome");       
		String cargo = request.queryParams("cargo");
		double salario= Double.parseDouble(request.queryParams("salario"));
		System.out.println(nome);
		System.out.println(cargo);
		System.out.println(salario);
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.insertEmployee(nome,cargo,salario))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	public Object acharTodosFuncionario(Request request, Response response) throws ParseException { 
		 response.header("Content-Type", "application/json");
	     response.header("Content-Encoding", "UTF-8");
	     con.connect(); //enviar requisi��o

		 String answer = con.findAllFuncionario();
		 System.out.println(answer);
		 if(answer != null) {
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		 }else {
			response.status(404);
		 }//caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return answer;
	}
	
	public Object alterFuncionarioNome(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String nome = request.queryParams("nome");       
		int id= Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarFuncionarioNome(id,nome))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	
	
	public Object alterFuncionarioCargo(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String cargo = request.queryParams("cargo");       
		int id= Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarFuncionarioCargo(id,cargo))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	public Object alterFuncionarioSalario(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		double salario =Double.parseDouble( request.queryParams("salario"));       
		int id= Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarFuncionarioSalario(id,salario))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	public Object deletarFuncionario(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP ;       
		int id_item = Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.deleteFuncionario(id_item))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	
}