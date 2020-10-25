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

import classes.Produto;
import classes.RegProduto;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;

public class ProdutoScreen {

	private JFrame frame;
	private JTextField descricaoCustoField;
	private JTextField valorCustoField;
	private JTextField idCustoField;
	private static Produto produto = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoScreen window = new ProdutoScreen(produto);
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
	public ProdutoScreen(Produto p) {
		this.produto = p;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ModeloLista modeloLista = new ModeloLista(produto.listCustos());
		JList listCustos = new JList(modeloLista);
		listCustos.setBounds(340, 108, 334, 327);
		frame.getContentPane().add(listCustos);
		
		JLabel nome = new JLabel(produto.getNome());
		nome.setBounds(10, 108, 320, 18);
		frame.getContentPane().add(nome);
		
		JLabel id = new JLabel(String.valueOf(produto.getId()));
		id.setBounds(10, 173, 320, 18);
		frame.getContentPane().add(id);
		
		JLabel precoVenda = new JLabel(String.valueOf(produto.getPrecoVenda()));
		precoVenda.setBounds(10, 238, 320, 18);
		frame.getContentPane().add(precoVenda);
		
		JLabel lucro = new JLabel(String.valueOf(produto.getLucro()));
		lucro.setBounds(10, 303, 320, 18);
		frame.getContentPane().add(lucro);
		
		JLabel descricao = new JLabel(produto.getDescricao());
		descricao.setBounds(10, 368, 320, 67);
		frame.getContentPane().add(descricao);		
		
		JButton addCusto = new JButton("Cadastrar");
		addCusto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cadastra novo produto.

				String descricao = descricaoCustoField.getText();
				String valorStr = valorCustoField.getText();
				if (descricao.length() <1 || valorStr.length() <1) {
					//erro falta info
					JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
				} else {
					//cadastra
					double valor = Double.parseDouble(valorStr);				
					boolean status = produto.addCusto(descricao, valor);
					if (status) {
						JOptionPane.showMessageDialog(null, "Novo custo: Concluído");
						modeloLista.update(produto.listCustos());
						lucro.setText(String.valueOf(produto.getLucro()));					
					} else {
						JOptionPane.showMessageDialog(null, "Novo custo: Falha.");
					}
				}
				
				
			}
		});
		addCusto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		addCusto.setBounds(385, 496, 89, 46);
		frame.getContentPane().add(addCusto);
		
		JLabel logo = new JLabel("Produto");
		logo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		logo.setBounds(10, 11, 180, 50);
		frame.getContentPane().add(logo);
		
		JLabel tituloNome = new JLabel("Nome:");
		tituloNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tituloNome.setBounds(10, 72, 110, 25);
		frame.getContentPane().add(tituloNome);
		
		JLabel valor = new JLabel("Valor (formato 23.45):");
		valor.setBounds(225, 496, 150, 14);
		frame.getContentPane().add(valor);
		
		JLabel tituloDescricaoCusto = new JLabel("Descri\u00E7\u00E3o do custo:");
		tituloDescricaoCusto.setBounds(10, 496, 180, 14);
		frame.getContentPane().add(tituloDescricaoCusto);
		
		JLabel tituloCadastro = new JLabel("Cadastrar novo custo:");
		tituloCadastro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tituloCadastro.setBounds(10, 464, 180, 21);
		frame.getContentPane().add(tituloCadastro);
		
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(480, 464, 8, 86);
		frame.getContentPane().add(separator);
		
		JLabel tituloRemover = new JLabel("Remover custo:");
		tituloRemover.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tituloRemover.setBounds(498, 464, 150, 21);
		frame.getContentPane().add(tituloRemover);
		
		JLabel tituloId2 = new JLabel("Id do custo:");
		tituloId2.setBounds(498, 496, 58, 14);
		frame.getContentPane().add(tituloId2);
		
		JButton delCusto = new JButton("Apagar");
		delCusto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//apaga custo cujo id esta especificado
				
				String idStr = idCustoField.getText();
				if (idStr.length() <1) {
					//erro falta info
					JOptionPane.showMessageDialog(null, "Preencha o campo \"ID do custo\" obrigatório");
				} else {
					//cadastra
					int id = Integer.parseInt(idStr);
					boolean status = produto.delCusto(id);
					if (status) {
						JOptionPane.showMessageDialog(null, "Remoção: Concluída");
						modeloLista.update(produto.listCustos());
						lucro.setText(String.valueOf(produto.getLucro()));
					} else {
						JOptionPane.showMessageDialog(null, "Remoção: Falha.\nVerifique se o ID está correto.");
					}
				}
			}
		});
		delCusto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		delCusto.setBounds(585, 496, 89, 46);
		frame.getContentPane().add(delCusto);
		
		JLabel tituloPV = new JLabel("Pre\u00E7o de venda:");
		tituloPV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tituloPV.setBounds(10, 202, 195, 25);
		frame.getContentPane().add(tituloPV);
		
		JLabel tituloDescricao = new JLabel("Descri\u00E7\u00E3o:");
		tituloDescricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tituloDescricao.setBounds(10, 332, 195, 25);
		frame.getContentPane().add(tituloDescricao);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(10, 451, 664, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel tituloLucro = new JLabel("Lucro:");
		tituloLucro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tituloLucro.setBounds(10, 267, 195, 25);
		frame.getContentPane().add(tituloLucro);
		
		JLabel tituloCustos = new JLabel("Custos");
		tituloCustos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tituloCustos.setBounds(340, 72, 195, 25);
		frame.getContentPane().add(tituloCustos);
		
		descricaoCustoField = new JTextField();
		descricaoCustoField.setBounds(10, 521, 195, 20);
		frame.getContentPane().add(descricaoCustoField);
		descricaoCustoField.setColumns(10);
		
		valorCustoField = new JTextField();
		valorCustoField.setColumns(10);
		valorCustoField.setBounds(225, 521, 150, 20);
		frame.getContentPane().add(valorCustoField);
		
		idCustoField = new JTextField();
		idCustoField.setColumns(10);
		idCustoField.setBounds(498, 522, 58, 20);
		frame.getContentPane().add(idCustoField);
		
		JLabel tituloId = new JLabel("ID:");
		tituloId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tituloId.setBounds(10, 137, 110, 25);
		frame.getContentPane().add(tituloId);
		
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		voltar.setBounds(585, 11, 89, 23);
		frame.getContentPane().add(voltar);
		
		frame.setVisible(true);
	}
}
