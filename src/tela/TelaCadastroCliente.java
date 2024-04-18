package tela;

import componente.MeuCampoTexto;
import componente.MeuDBCombo;
import dao.ClienteDao;
import dao.EnderecoDao;
import pojo.Cliente;

public class TelaCadastroCliente extends TelaCadastro {
	
	public Cliente cliente = new Cliente();
	public ClienteDao clienteDao = new ClienteDao(cliente);
	private MeuCampoTexto jtfCodigo = new MeuCampoTexto(10, true, "Código");
	private MeuCampoTexto jtfNome = new MeuCampoTexto(50, true, "Nome");
	private MeuCampoTexto jtfCpf = new MeuCampoTexto(11, true, "CPF");
	private MeuCampoTexto jtfRg = new MeuCampoTexto(20, true, "RG");
	private MeuCampoTexto jtfTelefone = new MeuCampoTexto(20, true, "Telefone");
	private MeuCampoTexto jtfEmail = new MeuCampoTexto(50, true, "Email");
	private MeuDBCombo campoEndereco = new MeuDBCombo(EnderecoDao.SQL_COMBOBOX, true, "Endereço");
	
	public TelaCadastroCliente() {
		super("Cadastro de Cliente");
		adicionaComponente(1,1,1,1, jtfCodigo);
		adicionaComponente(2,1,1,1,jtfNome);
		adicionaComponente(3,1,1,1,jtfCpf);
		adicionaComponente(4,1,1,1,jtfRg);
		adicionaComponente(5,1,1,1,jtfTelefone);
		adicionaComponente(6,1,1,1,jtfEmail);
		adicionaComponente(7,1,1,1,campoEndereco);
		pack();
		habilitaComponentes(false);
	}
		
		public void setPersistencia() {
			cliente.setId(Integer.parseInt(jtfCodigo.getText()));
			cliente.setNome(jtfNome.getText());
			cliente.setCpf(jtfCpf.getText());
			cliente.setRg(jtfRg.getText());
			cliente.setTelefone(jtfTelefone.getText());
			cliente.setEmail(jtfEmail.getText());
			cliente.getEndereco().setId(campoEndereco.getValor());
		}
		
		@Override
		public void incluirBD() {
			setPersistencia();
			clienteDao.inserir();
		}
		
		@Override
		public void alterarBD() {
			setPersistencia();
			clienteDao.alterar();
		}
		
		@Override
		public void excluirBD() {
			clienteDao.excluir();
			super.excluirBD();
		}
		
		@Override
		public void consultar() {
			super.consultar();
			new TelaConsulta(this, "Consulta de Cliente", new String[] {"Código", "Nome", "CPF", "RG", "Telefone", "Email", "Endereço"}, ClienteDao.SQL_PESQUISAR);
		}
		
		
		@Override
		public void preencherDados(int pk) {
			cliente.setId(pk);
			clienteDao.consultar();
			jtfCodigo.setText("" + cliente.getId());
			jtfNome.setText("" + cliente.getNome());
			jtfCpf.setText(""+ cliente.getCpf());
			jtfRg.setText(""+ cliente.getRg());
			jtfTelefone.setText(""+ cliente.getTelefone());
			jtfEmail.setText(""+ cliente.getEmail());
			campoEndereco.setValor(cliente.getEndereco().getId());
			super.preencherDados(pk);
			
		
		}
}



