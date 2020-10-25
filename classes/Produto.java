package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Produto implements Serializable{
	private int id;
	private String nome;
	private String descricao;
	private double precoVenda;
	private ArrayList<Custo> custos;
	private int idCustoCount;
	private double lucro;
	
	public Produto(int i, String n, String d, double pv) {
		super();
		id = i;
		nome = n;
		descricao = d;
		precoVenda = pv;
		custos = new ArrayList<Custo>();
		idCustoCount = 1;
		lucro = pv;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public double getPrecoVenda() {
		return this.precoVenda;
	}
	
	public double getLucro() {
		return this.lucro;
	}
	
	public boolean delCusto(int idCusto) {
		for (Custo c : custos) {
			if (c.getId() == idCusto) {
				boolean status = custos.remove(c);
				calcularLucro();
				return status;
			}
		}
		return false;
	}
	
	public boolean addCusto(String d, double p) {
		Custo novo = new Custo(idCustoCount, d, p);
		idCustoCount++;
		boolean status = custos.add(novo);
		calcularLucro();
		return status;
	}
	
	public double calcularLucro() {
		double custoTotal = 0;
		for (Custo c : custos) {
			custoTotal = custoTotal+c.getPreco();
		}
		lucro = precoVenda - custoTotal;
		return lucro;
	}
	
	public ArrayList<String> listCustos(){
		ArrayList<String> output = new ArrayList<String>();
		String aux;
		for (Custo c : custos) {
			aux = "Id: " + c.getId() + " | ";
			aux = aux + "Descrição: " + c.getDescricao() + " | ";
			aux = aux + "Valor: " + c.getPreco();
			output.add(aux);
		}		
		return output;
	}

}
