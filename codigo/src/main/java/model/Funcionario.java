package model;

public class Funcionario {
	private int codigo;
	private double salario; 
	private String cargo, nome;
	public Funcionario(int codigo, double salario, String cargo, String nome){
			this.codigo=codigo;
			this.salario=salario;
			this.cargo=cargo;
			this.nome=nome;
}
	
	
	
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	} 
	
	

}
