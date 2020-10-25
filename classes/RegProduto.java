package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class RegProduto implements Serializable{
	
	private ArrayList<Produto> registro;
	private int qtd;
	private int idCount;
	
	public RegProduto() {
		super();
		registro = new ArrayList<Produto>();
		qtd = 0;
		idCount = 1;
	}
	
	public boolean addProduto(String nome, String descricao, double precoVenda) {
		Produto prod = new Produto (idCount, nome, descricao, precoVenda);
		idCount++;
		qtd++;
		return registro.add(prod);
	}
	
	public Produto findProduto(int id) {
		for (Produto p : registro) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	public boolean delProduto (int id) {
		Produto p = findProduto(id);
		if (p != null) {
			qtd--;
			return registro.remove(p);
		} else {
			return false;
		}
	}
	
	public ArrayList<String> listProdutos(){
		ArrayList<String> output = new ArrayList<String>();
		String aux;
		for (Produto p : registro) {
			aux = "Id: " + p.getId() + " | ";
			aux = aux + "Nome: " + p.getNome() + " | ";
			aux = aux + "Preço: " + p.getPrecoVenda() + " | ";
			aux = aux + "Lucro: " + p.getLucro();
			output.add(aux);
		}		
		return output;
	}
	
	public int getQtd() {
		return this.qtd;
	}
	

}
