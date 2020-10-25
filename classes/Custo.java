package classes;

import java.io.Serializable;

public class Custo implements Serializable{
	private int id;
	private String descricao;
	private double preco;
	
	public Custo (int i, String d, double p) {
		super();
		id = i;
		descricao = d;
		preco = p;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public int getId() {
		return this.id;
	}

}
