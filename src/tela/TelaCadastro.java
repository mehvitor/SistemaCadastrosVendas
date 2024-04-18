package tela;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import componente.MeuComponente;

public class TelaCadastro extends JInternalFrame implements ActionListener {

	public List<MeuComponente> componentes = new ArrayList();
	
	public JPanel jpComponentes = new JPanel();
	
	public JPanel jpBotoes = new JPanel();
	public JButton jbIncluir = new JButton("Incluir");
	public JButton jbAlterar = new JButton("Alterar");
	public JButton jbExcluir = new JButton("Excluir");
	public JButton jbConsultar = new JButton("Consultar");
	public JButton jbConfirmar = new JButton("Confirmar");
	public JButton jbCancelar = new JButton("Cancelar");
	
	public final int padrao = 0;
	public final int incluindo = 1;
	public final int alterando = 2;
	public final int excluindo = 3;
	public final int consultando = 4;
	public int estadoTela = 0; 
	public boolean temDadosNaTela = false;
	
	
	public TelaCadastro(String titulo) {
		super(titulo, true, true, true, true); //para habilitar o X, minimizar, icone
		
		getContentPane().setLayout(new BorderLayout()); 		//criando o layout
		getContentPane().add(BorderLayout.PAGE_END, jpBotoes); 
		getContentPane().add(BorderLayout.CENTER, jpComponentes);	
	
		jpComponentes.setLayout(new GridBagLayout());
		jpBotoes.setLayout(new GridLayout(1, 6));				// 1 linha para 6 botoes
		jpBotoes.add(jbIncluir);
		jpBotoes.add(jbAlterar);
		jpBotoes.add(jbExcluir);
		jpBotoes.add(jbConsultar);
		jpBotoes.add(jbConfirmar);
		jpBotoes.add(jbCancelar);
		jbIncluir.addActionListener(this);
		jbAlterar.addActionListener(this);
		jbExcluir.addActionListener(this);
		jbConsultar.addActionListener(this);
		jbConfirmar.addActionListener(this);
		jbCancelar.addActionListener(this);
		
		pack();
		setVisible(true);
		habilitaBotoes();
	}
	
	public void habilitaBotoes() {
		jbIncluir.setEnabled(estadoTela == padrao);
		jbAlterar.setEnabled(estadoTela == padrao && temDadosNaTela);
		jbExcluir.setEnabled(estadoTela == padrao && temDadosNaTela);
		jbConsultar.setEnabled(estadoTela == padrao);
		jbConfirmar.setEnabled(estadoTela != padrao);
		jbCancelar.setEnabled(estadoTela != padrao);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbIncluir) {
			incluir();
		
		} else if( ae.getSource() == jbAlterar) {
			alterar();
		
		} else if( ae.getSource() == jbExcluir) {
			excluir();
		
		} else if( ae.getSource() == jbConsultar) {
			consultar();	
		
		} else if( ae.getSource() == jbConfirmar) {
			confirmar();	
		
		} else if( ae.getSource() == jbCancelar) {
			cancelar();
		
		}	
		
		habilitaBotoes();
		
	}
	
	public void adicionaComponente(int linha, int coluna, int linhas, int colunas, JComponent componente) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = linha;
		gbc.gridx = coluna;
		if(componente instanceof MeuComponente) {
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			String texto = "<html><body>";
			texto = texto + (((MeuComponente) componente).getDica());
			if(((MeuComponente) componente).eObrigatorio()) {
				texto = texto + "<font color=red>*</font>";
			}
			texto = texto + ": " + "</body></html>";
			JLabel jl = new JLabel(texto);
			gbc.anchor = GridBagConstraints.EAST;
			jpComponentes.add(jl, gbc);
		}
		gbc.gridheight = linhas;
		gbc.gridwidth = colunas;
		gbc.gridx++;
		gbc.anchor = GridBagConstraints.WEST;
		jpComponentes.add(componente, gbc);
		if (componente instanceof MeuComponente) {
			componentes.add((MeuComponente) componente);
		}
	}
	
	
	public void habilitaComponentes(boolean status) {
		for (int i = 0; i < componentes.size(); i++) {
			componentes.get(i).habilitar(status);
		}
	}
	
	public void limpaComponentes() {
		for (int i = 0; i < componentes.size(); i++) {
			componentes.get(i).limpar();
		}
	}
	
	public void incluir() {
		estadoTela = incluindo;
		limpaComponentes();
		habilitaComponentes(true);
	}

	public void alterar () {
		estadoTela = alterando;
		habilitaComponentes(true);
	}
	
	public void excluir() {
		estadoTela = excluindo;
	}
	
	public void consultar() {
		estadoTela = consultando;
		habilitaBotoes();
	}
	
	public void confirmar() {
		if (estadoTela == incluindo) {
			if(validaComponentes()) {
				incluirBD();
			}else{
				return;
			}
		} else if(estadoTela == alterando) {
			if(validaComponentes()) {
				alterarBD();
			}else {
				return;
			}
		} else if(estadoTela == excluindo) {
			excluirBD();
		}
		estadoTela = padrao;
		habilitaComponentes(false);
		habilitaBotoes();
	}
	
	public void cancelar() {
		estadoTela = padrao;
	}
	
	public void incluirBD() {
		
	}
	
	public void alterarBD() {
			
	}
	
	public void excluirBD() {
		limpaComponentes();
	}
	
	public void preencherDados(int pk) {
		estadoTela = padrao;
		temDadosNaTela = true;
		habilitaBotoes();
	}
	
	public boolean validaComponentes() {
		String erroObrigatorios = "";
		for(int i = 0; i < componentes.size(); i++) {
			if(componentes.get(i).eObrigatorio() && componentes.get(i).eVazio()) {
				erroObrigatorios = erroObrigatorios + componentes.get(i).getDica() + "\n";	
			}		
		}
		if(erroObrigatorios.isEmpty()) {
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "Os campos abaixos são obrigátoros e não foram preenchidos: \n\n" + "Campo: " + erroObrigatorios);
			return false;
		}
	}

}

