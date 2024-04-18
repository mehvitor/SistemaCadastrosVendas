package tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TelaSistema extends JFrame implements ActionListener {
	
	public JMenuBar jmb = new JMenuBar();
	public JMenu jmCadastros = new JMenu("Cadastros");
	public JMenu jmMovimentos = new JMenu("Movimentos");
	public JMenuItem jmiCliente = new JMenuItem("Cliente");
	public JMenuItem jmiProduto = new JMenuItem("Produtos");
	public JMenuItem jmiVenda = new JMenuItem("Vendas");
	public JMenuItem jmiEstado = new JMenuItem("Estados");
	public JMenuItem jmiCidade = new JMenuItem("Cidades");
	public JMenuItem jmiEndereco = new JMenuItem("Endereços");
	public static JDesktopPane jdp = new JDesktopPane();
	
	
	public TelaSistema() {
		
		getContentPane().add(jdp);
		setTitle("Sistemas Comercial");
		setSize(800		, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(jmb);
		
		jmb.add(jmCadastros);
		jmb.add(jmMovimentos);
		jmCadastros.add(jmiCliente);
		jmCadastros.add(jmiProduto);
		jmMovimentos.add(jmiVenda);
		jmCadastros.add(jmiEstado);
		jmCadastros.add(jmiCidade);
		jmCadastros.add(jmiEndereco);
		
		jmiCliente.addActionListener(this);
		jmiProduto.addActionListener(this);
		jmiVenda.addActionListener(this);
		jmiEstado.addActionListener(this);
		jmiCidade.addActionListener(this);
		jmiEndereco.addActionListener(this);
		
		setVisible(true); //ficar sempre por ultimo
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jmiCliente ) {
			TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente();
			jdp.add(telaCadastroCliente); 
		
		} else if (ae.getSource() == jmiProduto) {
			TelaCadastroProduto telaCadastroProduto = new TelaCadastroProduto();
			jdp.add(telaCadastroProduto);
			
		} else if (ae.getSource() == jmiEstado) {
			TelaCadastroEstado telaCadastroEstado = new TelaCadastroEstado();
			jdp.add(telaCadastroEstado);
			
		} else if (ae.getSource() == jmiCidade) {
			TelaCadastroCidade telaCadastroCidade = new TelaCadastroCidade();
			jdp.add(telaCadastroCidade);
		
		} else if (ae.getSource() == jmiEndereco) {
			TelaCadastroEndereco telaCadastroEndereco = new TelaCadastroEndereco();
			jdp.add(telaCadastroEndereco);
		
		} else if (ae.getSource() == jmiVenda) {
			TelaCadastroVenda telaCadastroVenda = new TelaCadastroVenda();
			jdp.add(telaCadastroVenda);
	}
	
	}

}
