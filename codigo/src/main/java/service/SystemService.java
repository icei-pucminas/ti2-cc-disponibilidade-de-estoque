package service;

import model.User;

import java.sql.Date;
import java.text.ParseException;

import bd.userActions;
import spark.Request;
import spark.Response;

public class SystemService {
	
	private userActions con;  
	public SystemService() {
		con = new userActions();    //criar um objeto do tipo userAction por meio do construtor de SystemService
	}
	

	public Object addLote(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String desc = request.queryParams("desc");       
		String data_compra = request.queryParams("data_compra");
		String data_validade = request.queryParams("data_validade");
		String categoria = request.queryParams("categoria");
		int id_item = Integer.parseInt(request.queryParams("id"));
		int quantidade = Integer.parseInt(request.queryParams("qtd"));
		double valor_unitario = Double.parseDouble(request.queryParams("valor_unitario"));
		double valor_compra = Double.parseDouble(request.queryParams("valor_compra"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.addLote(desc,data_compra,data_validade,categoria,id_item,quantidade,valor_unitario,valor_compra))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	
	public Object alterLote(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String nome = request.queryParams("nome");       
		int id_item = Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con. alterarLote(id_item,nome))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	public Object alterLoteEstoque(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		int qt_estoque = Integer.parseInt(request.queryParams("quant_estoque"));       
		int id_item = Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarLoteEstoque(id_item,qt_estoque))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	
	
	
	public Object alterLoteValidade(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String validade = request.queryParams("validade");       
		int id_item = Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarLoteValidade(id_item,validade))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	public Object alterLoteEntrada(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String entrada = request.queryParams("entrada");       
		int id_item = Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarLoteEntrada(id_item,entrada))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	
	
	public Object alterLoteCategoria(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		String categoria = request.queryParams("categoria");       
		int id_item = Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarLoteCategoria(id_item,categoria))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	public Object alterLotePrecoUn(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		double preco_un =Double.parseDouble(request.queryParams("precoUn"));       
		int id_item = Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarLotePrecoUn(id_item,preco_un))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	
	public Object alterLotePreco(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP 
		double preco =Double.parseDouble(request.queryParams("preco"));       
		int id_item = Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.alterarLotePreco(id_item,preco))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Object deletarLote(Request request, Response response) throws ParseException { //pegar par�metros do lote por meio de uma requisi��o HTTP ;       
		int id_item = Integer.parseInt(request.queryParams("id"));
		//pegar os par�metros que foram passados pelo formul�rio
		con.connect(); //enviar requisi��o
		if(con.deleteLote(id_item))
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		else
			response.status(404); //caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Object acharTodosLote(Request request, Response response) throws ParseException { 
		 response.header("Content-Type", "application/json");
	     response.header("Content-Encoding", "UTF-8");
	     con.connect(); //enviar requisi��o

		 String answer = con.findAllLote();
		 System.out.println(answer);
		 if(answer != null) {
			response.status(200);  //caso todos os par�metros forem devolvidos, houve sucesso (status de resposta 200)
		 }else {
			response.status(404);
		 }//caso contr�rio, haver� erro cometido pelo usu�rio (404 not found)
		
		return answer;
	}
	
}