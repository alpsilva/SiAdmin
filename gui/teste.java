package gui;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import classes.RegProduto;

public class teste {
	static FileOutputStream fileWrite;
	static FileInputStream fileRead;
	static ObjectInputStream read;
	static ObjectOutputStream write;
	
	static RegProduto registro;
	static RegProduto registroRecuperado;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try {
			//fileWrite = new FileOutputStream("C:\\Users\\alpsi\\OneDrive\\Área de Trabalho\\siAdmin\\armazenamento/db.dat");
			fileRead = new FileInputStream("C:\\Users\\alpsi\\OneDrive\\Área de Trabalho\\siAdmin\\armazenamento/db.dat");
			//escrever();
			ler();
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}
	public static void escrever() {
		try {
			registro = new RegProduto();
			registro.addProduto("Teste", "teste de produto", 23.45);
			registro.addProduto("Teste2", "teste de produto2", 67.89);
			
			System.out.println("salvando registro");
			write = new ObjectOutputStream(fileWrite);
			write.writeObject(registro);
			write.flush();
			write.close();
			fileWrite.flush();
			fileWrite.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void ler() {		
		try {
			System.out.println("recuperando o registro");
			read = new ObjectInputStream(fileRead);
			registroRecuperado = (RegProduto) read.readObject();
			read.close();
			System.out.println(registroRecuperado.getQtd());
			ArrayList<String> produtos = registroRecuperado.listProdutos();
			for (String s : produtos) {
				System.out.println(s);
			}
			fileRead.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		} 
	}
	

}
