package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;

import classes.Produto;
import classes.RegProduto;

public class Home {

	private JFrame frame;
	private JTextField nomeField;
	private JTextField precoField;
	private JTextField descricaoField;
	private JTextField idField;
	private RegProduto registro;
	private FileOutputStream fileWrite;
	private FileInputStream fileRead;
	private ObjectOutputStream write;
	private ObjectInputStream read;
	private JTextField idApagarField;
	private final String fileLocation = "C:\\siAdmin\\armazenamento/db.dat";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		
		try {
			fileRead = new FileInputStream(fileLocation);
			read = new ObjectInputStream(fileRead);
			registro = (RegProduto) read.readObject();
			read.close();
			fileRead.close();		
		} catch(Exception e) {
			e.printStackTrace();
		}	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		ModeloLista modeloLista = new ModeloLista(registro.listProdutos());
		JList list = new JList(modeloLista);
		list.setBounds(10, 108, 664, 285);
		frame.getContentPane().add(list);
		
		JButton addProduto = new JButton("Cadastrar");
		addProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cadastra novo produto.
				String nome = nomeField.getText();
				String precoStr = precoField.getText();
				String descricao = descricaoField.getText();
				
				if (nome.length() <1 || precoStr.length() <1) {
					//erro falta info
					JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
				} else {
					//cadastra
					double preco = Double.parseDouble(precoStr);
					boolean status = registro.addProduto(nome, descricao, preco);
					if (status) {
						JOptionPane.showMessageDialog(null, "Cadastro: Concluído");
						modeloLista.update(registro.listProdutos());
					} else {
						JOptionPane.showMessageDialog(null, "Cadastro: Falha.");
					}
				}
				
				
			}
		});
		addProduto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		addProduto.setBounds(385, 461, 89, 90);
		frame.getContentPane().add(addProduto);
		
		JLabel logo = new JLabel("SiAdmin");
		logo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		logo.setBounds(10, 11, 180, 50);
		frame.getContentPane().add(logo);
		
		JLabel tituloLista = new JLabel("Produtos");
		tituloLista.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tituloLista.setBounds(10, 72, 110, 25);
		frame.getContentPane().add(tituloLista);
		
		JLabel tituloNome = new JLabel("Nome:");
		tituloNome.setBounds(10, 436, 46, 14);
		frame.getContentPane().add(tituloNome);
		
		nomeField = new JTextField();
		nomeField.setBounds(10, 461, 180, 20);
		frame.getContentPane().add(nomeField);
		nomeField.setColumns(10);
		
		JLabel tituloPreco = new JLabel("Pre\u00E7o (formato: 23.45):");
		tituloPreco.setBounds(225, 436, 150, 14);
		frame.getContentPane().add(tituloPreco);
		
		precoField = new JTextField();
		precoField.setBounds(225, 461, 150, 20);
		frame.getContentPane().add(precoField);
		precoField.setColumns(10);
		
		JLabel tituloDescricao = new JLabel("Descri\u00E7\u00E3o(opcional):");
		tituloDescricao.setBounds(10, 492, 180, 14);
		frame.getContentPane().add(tituloDescricao);
		
		descricaoField = new JTextField();
		descricaoField.setBounds(10, 517, 365, 33);
		frame.getContentPane().add(descricaoField);
		descricaoField.setColumns(10);
		
		JLabel tituloCadastro = new JLabel("Cadastrar novo produto:");
		tituloCadastro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tituloCadastro.setBounds(10, 404, 180, 21);
		frame.getContentPane().add(tituloCadastro);
		
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(480, 404, 8, 146);
		frame.getContentPane().add(separator);
		
		JLabel tituloAtualizar = new JLabel("Atualizar produto:");
		tituloAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tituloAtualizar.setBounds(498, 404, 176, 21);
		frame.getContentPane().add(tituloAtualizar);
		
		JLabel tituloId = new JLabel("Id do produto:");
		tituloId.setBounds(498, 436, 176, 14);
		frame.getContentPane().add(tituloId);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(498, 461, 176, 20);
		frame.getContentPane().add(idField);
		
		JButton attProduto = new JButton("Abrir");
		attProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idStr = idField.getText();
				if (idStr.length() <1) {
					//erro falta info
					JOptionPane.showMessageDialog(null, "Preencha o campo \"ID do produto\" obrigatório");
				} else {
					//cadastra
					int id = Integer.parseInt(idStr);		
					Produto p = registro.findProduto(id);
					if (p != null) {
						ProdutoScreen janela = new ProdutoScreen(p);
						System.out.println("rodei");
					} else {
						JOptionPane.showMessageDialog(null, "Erro! Produto não encontrado.");
					}
					
				}
			}
		});
		attProduto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		attProduto.setBounds(498, 496, 176, 55);
		frame.getContentPane().add(attProduto);
		
		JButton save = new JButton("Salvar");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fileWrite = new FileOutputStream(fileLocation);
					write = new ObjectOutputStream(fileWrite);
					write.writeObject(registro);
					write.flush();
					write.close();
					fileWrite.flush();
					fileWrite.close();
					Home newHome = new Home();
					frame.dispose();
				} catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		save.setToolTipText("Salva altera\u00E7\u00F5es");
		save.setBounds(585, 33, 89, 64);
		frame.getContentPane().add(save);
		
		JLabel tituloApagar = new JLabel("Apagar produto:");
		tituloApagar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tituloApagar.setBounds(265, 33, 110, 21);
		frame.getContentPane().add(tituloApagar);
		
		JLabel tituloIdApagar = new JLabel("Id do produto:");
		tituloIdApagar.setBounds(265, 58, 110, 14);
		frame.getContentPane().add(tituloIdApagar);
		
		idApagarField = new JTextField();
		idApagarField.setColumns(10);
		idApagarField.setBounds(265, 77, 110, 20);
		frame.getContentPane().add(idApagarField);
		
		JButton Apagar = new JButton("Apagar");
		Apagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Apaga produto.
				String idStr = idApagarField.getText();
				if (idStr.length() <1) {
					//erro falta info
					JOptionPane.showMessageDialog(null, "Preencha o campo \"ID do produto\" obrigatório");
				} else {
					//apaga
					int id = Integer.parseInt(idStr);		
					boolean status = registro.delProduto(id);
					if (status) {
						JOptionPane.showMessageDialog(null, "Remoção de produto: Sucesso.");
					} else {
						JOptionPane.showMessageDialog(null, "Erro! Produto não encontrado.");
					}				
				}
				
				
			}
		});
		Apagar.setToolTipText("Apaga produto");
		Apagar.setBounds(385, 77, 89, 20);
		frame.getContentPane().add(Apagar);
		
		frame.setVisible(true);
	}
}
