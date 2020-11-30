package model;
//classe para declara��o de atributos do Usu�rio
public class User {
	private int id;
	private String nome, email, senha, empresa, cnpj;  //atributos do usu�rio
	
	
	public User(int id, String nome, String email, String senha, String empresa, String cnpj){
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.empresa = empresa;
		this.cnpj = cnpj;
		this.senha = senha;
	} 
	//atributos do usu�rio presentes na tabela de=o Banco de dados 


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getEmpresa() {
		return empresa;
	}


	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	 //sets: definir os atributos do usu�rio que foram passados pelo formul�rio de cadastro

}