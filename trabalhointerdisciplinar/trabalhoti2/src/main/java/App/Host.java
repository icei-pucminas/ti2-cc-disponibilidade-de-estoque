package App;

import static spark.Spark.*;



import java.util.*;
import bd.userActions;
import model.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import model.User;
import bd.userActions;
import service.UserService;
import service.SystemService;
import service.FuncionarioService;
//importar classes e bibliotecas necess�rias para o c�digo funcionar 
public class Host {
	
	private static UserService userService = new UserService();           
	private static SystemService systemService = new SystemService();
	private static FuncionarioService funcionarioService = new FuncionarioService();
	
	
	public static void main(String[] arg){
		
		
		staticFiles.location("/");
        
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                new ModelAndView(model, "index.html")
            );
        });
        
        
        post("/usuarios/search", (request, response) -> userService.searchUser(request, response));        
        post("/site/add_lote", (request, response) -> systemService.addLote(request, response));        
        post("/usuarios/add_user",(request, response) -> userService.addUser(request, response));
        post("/funcionario/add_funcionario",(request, response) -> funcionarioService.addFuncionario(request, response));
     
	}
}