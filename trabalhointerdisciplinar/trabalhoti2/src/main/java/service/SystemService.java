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
	
	
	
}
