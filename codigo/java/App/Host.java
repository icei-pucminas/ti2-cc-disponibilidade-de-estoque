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
//importar classes e bibliotecas necess�rias para o c�digo funcionar 
public class Host {
	
	private static UserService userService = new UserService();           
	private static SystemService systemService = new SystemService();
	
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
     
	}
}
