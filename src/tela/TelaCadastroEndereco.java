package tela;

import componente.MeuCampoTexto;
import componente.MeuDBCombo;
import dao.CidadeDao;
import dao.EnderecoDao;
import pojo.Endereco;

public class TelaCadastroEndereco extends TelaCadastro {

	public Endereco endereco = new Endereco();
	public EnderecoDao enderecoDao = new EnderecoDao(endereco);
	private MeuCampoTexto jtfCodigo = new MeuCampoTexto(10, true, "Código");
	private MeuCampoTexto jtfLogadouro = new MeuCampoTexto(100, true, "Logadouro");
	private MeuCampoTexto jtfNumero = new MeuCampoTexto(5, true, "Número");
	private MeuCampoTexto jtfComplemento = new MeuCampoTexto(45, true, "Complemento");
	private MeuCampoTexto jtfBairro = new MeuCampoTexto(45, true, "Bairro");
	private MeuDBCombo campoCidade = new MeuDBCombo(CidadeDao.SQL_COMBOBOX, true, "Cidade");
	
	public TelaCadastroEndereco() {
		super("Cadastro de Endereços");
		adicionaComponente(1,1,1,1, jtfCodigo);
		adicionaComponente(2,1,1,1, jtfLogadouro);
		adicionaComponente(3,1,1,1, jtfNumero);
		adicionaComponente(4,1,1,1, jtfComplemento);
		adicionaComponente(5,1,1,1, jtfBairro);
		adicionaComponente(6,1,1,1, campoCidade);
		pack();
		habilitaComponentes(false);
	}
		
		public void setPersistencia() {
		    endereco.setId(Integer.parseInt(jtfCodigo.getText()));
			endereco.setLogadouro(jtfLogadouro.getText());;
			endereco.setNumero(jtfNumero.getText());
			endereco.setComplemento(jtfComplemento.getText());
			endereco.setBairro(jtfBairro.getText());
			endereco.getCidade().setId(campoCidade.getValor());
		}
		
		@Override
		public void incluirBD() {
			setPersistencia();
			enderecoDao.inserir();
		}
		
		@Override
		public void alterarBD() {
			setPersistencia();
			enderecoDao.alterar();
		}
		
		@Override
		public void excluirBD() {
			enderecoDao.excluir();
			super.excluirBD();
		}
		
		@Override
		public void consultar() {
			super.consultar();
			new TelaConsulta(this, "Consulta de Endereço", new String[] {"Código", "Logadouro", "Número", "Complemento", "Bairro", "Cidade"}, EnderecoDao.SQL_PESQUISAR);
		}
		
		
		@Override
		public void preencherDados(int pk) {
			endereco.setId(pk);
			enderecoDao.consultar();
			jtfCodigo.setText("" + endereco.getId());
			jtfLogadouro.setText("" + endereco.getLogadouro());
			jtfNumero.setText("" + endereco.getNumero());
			jtfComplemento.setText("" + endereco.getComplemento());
			jtfBairro.setText("" + endereco.getBairro());
			campoCidade.setValor(endereco.getCidade().getId());
			super.preencherDados(pk);
			
		
		}
}
