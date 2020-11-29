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
import service.FornecedorService;
//importar classes e bibliotecas necess�rias para o c�digo funcionar 
public class Host {
	
	private static UserService userService = new UserService();           
	private static SystemService systemService = new SystemService();
	private static FuncionarioService funcionarioService = new FuncionarioService();
	private static FornecedorService fornecedorService=new FornecedorService();
	
	
	public static void main(String[] arg){
		port(HerokuPort());
		
		//System.out.println("Executando Host.java");
		staticFiles.location("/public");
        
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                new ModelAndView(model, "index.html")
            );
        });
        
        post("/produto/deletar_produto", (request, response) -> systemService.deletarProduto(request, response));
        post("/lote/deletar_lote", (request, response) -> systemService.deletarLote(request, response));
        post("/funcionario/deletar_funcionario", (request, response) -> funcionarioService.deletarFuncionario(request, response));
        post("/fornecedor/deletar_fornecedor", (request, response) -> fornecedorService.deletarFornecedor(request, response));          
        
        
        ///produto/deletar_produto
        post("/fornecedor/alter_fornecedor_endereco", (request, response) -> fornecedorService.alterFornecedorEndereco(request, response)); 
        post("/fornecedor/alter_fornecedor_email", (request, response) -> fornecedorService.alterFornecedorEmail(request, response)); 
        post("/fornecedor/alter_fornecedor_nome", (request, response) -> fornecedorService.alterFornecedorNome(request, response)); 
        post("/funcionario/alter_funcionario_nome", (request, response) -> funcionarioService.alterFuncionarioNome(request, response));
        post("/funcionario/alter_funcionario_cargo", (request, response) -> funcionarioService.alterFuncionarioCargo(request, response));
        post("/funcionario/alter_funcionario_salario", (request, response) -> funcionarioService.alterFuncionarioSalario(request, response));
        
        post("/lote/alter_lote_estoque", (request, response) -> systemService.alterLoteEstoque(request, response)); 
        post("/lote/alter_lote_validade", (request, response) -> systemService.alterLoteValidade(request, response)); 
        post("/lote/alter_lote_categoria", (request, response) -> systemService.alterLoteCategoria(request, response));
        post("/lote/alter_lote_preco_un", (request, response) -> systemService.alterLotePrecoUn(request, response)); 
        post("/lote/alter_lote_preco", (request, response) -> systemService.alterLotePreco(request, response)); 
        post("/lote/alter_lote_entrada", (request, response) -> systemService.alterLoteEntrada(request, response)); 
        post("/lote/alter_lote", (request, response) -> systemService.alterLote(request, response));  
        
        post("/produto/alter_produto", (request, response) -> systemService.alterProduto(request, response));  
        
        post("/usuarios/search", (request, response) -> userService.searchUser(request, response));
        
        //"/produto/alter_produto
        
        post("/produto/add_produto", (request, response) -> systemService.addProduto(request, response)); 
        post("/site/add_lote", (request, response) -> systemService.addLote(request, response));   
        post("/fornecedor/add_fornecedor", (request, response) -> fornecedorService.addFornecedor(request, response));    
        post("/usuarios/add_user",(request, response) -> userService.addUser(request, response));
        post("/funcionario/add_funcionario",(request, response) -> funcionarioService.addFuncionario(request, response));
        
        get("/usuarios/find_all", (request, response) -> userService.acharTodos(request, response)); 
        get("/lote/find_all_lote", (request, response) -> systemService.acharTodosLote(request, response)); 
        get("/funcionario/find_all_funcionarios", (request, response) -> funcionarioService.acharTodosFuncionario(request, response));
        get("/fornecedor/find_all_fornecedores", (request, response) -> fornecedorService.acharTodosFornecedor(request, response));
        get("/produto/find_all_produto", (request, response) -> systemService.acharTodosProduto(request, response));
	}
}

private static int HerokuPort() {
    ProcessBuilder port = new ProcessBuilder();
    if (port.environment().get("PORT") != null) {
        return Integer.parseInt(port.environment().get("PORT"));
    }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
}