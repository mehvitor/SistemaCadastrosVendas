package tela;

import componente.MeuCampoTexto;
import componente.MeuDBCombo;
import dao.CidadeDao;
import dao.EstadoDao;
import pojo.Cidade;

public class TelaCadastroCidade extends TelaCadastro{
	
	public Cidade cidade = new Cidade();
	public CidadeDao cidadeDao = new CidadeDao(cidade);
	private MeuCampoTexto jtfCodigo = new MeuCampoTexto(10, true, "Código");
	private MeuCampoTexto jtfNome = new MeuCampoTexto(50, true, "Nome");
	private MeuDBCombo campoEstado = new MeuDBCombo(EstadoDao.SQL_COMBOBOX, true, "Estado");
	
	public TelaCadastroCidade() {
		super("Cadastro de Cidade");
		adicionaComponente(1,1,1,1, jtfCodigo);
		adicionaComponente(2,1,1,1,jtfNome);
		adicionaComponente(3,1,1,1,campoEstado);
		pack();
		habilitaComponentes(false);
	}
		
		public void setPersistencia() {
			cidade.setId(Integer.parseInt(jtfCodigo.getText()));
			cidade.setNome(jtfNome.getText());
			cidade.getEstado().setId(campoEstado.getValor());
		}
		
		@Override
		public void incluirBD() {
			setPersistencia();
			cidadeDao.inserir();
		}
		
		@Override
		public void alterarBD() {
			setPersistencia();
			cidadeDao.alterar();
		}
		
		@Override
		public void excluirBD() {
			cidadeDao.excluir();
			super.excluirBD();
		}
		
		@Override
		public void consultar() {
			super.consultar();
			new TelaConsulta(this, "Consulta de Cidade", new String[] {"Código", "Nome", "Estado"}, CidadeDao.SQL_PESQUISAR);
		}
		
		
		@Override
		public void preencherDados(int pk) {
			cidade.setId(pk);
			cidadeDao.consultar();
			jtfCodigo.setText("" + cidade.getId());
			jtfNome.setText(cidade.getNome());
			campoEstado.setValor(cidade.getEstado().getId());
			super.preencherDados(pk);
			
		
		}

}
