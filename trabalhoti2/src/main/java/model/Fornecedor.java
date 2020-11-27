package model;

public class Fornecedor {
	private int codigo; 
	private String nome, email, endereco;
	public Fornecedor(int codigo, String nome, String endereco, String email) {
		this.endereco=endereco;
		this.nome=nome;
		this.email=email;
		this.codigo=codigo;
	}

}
