package model;
import java.util.Date;

public class Lote {
	private int codigo, quantidadeEstoque;
	private double valor, precoUn; 
	private String categoria, descricao, dataVal, dataEntrada;
	
	public Lote(int codigo, String data_entrada, double valor, int quantidade, double precoUn, String categoria, String descricao, String data_val) {
			this.categoria=categoria; 
			this.dataVal=data_val;
			this.valor=valor;
			this.quantidadeEstoque=quantidade; 
			this.codigo=codigo;
			this.precoUn=precoUn;
			this.dataEntrada=data_entrada;
			this.descricao=descricao;
			
		
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getPrecoUn() {
		return precoUn;
	}
	public void setPrecoUn(double precoUn) {
		this.precoUn = precoUn;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDataVal() {
		return dataVal;
	}
	public void setDataVal(String dataVal) {
		this.dataVal = dataVal;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	

}
