package tela;

import componente.MeuCampoTexto;
import componente.MeuDBCombo;
import dao.ClienteDao;
import dao.ProdutoDao;
import dao.VendaDao;
import pojo.Venda;

public class TelaCadastroVenda extends TelaCadastro {
	
	public Venda venda = new Venda();
	public VendaDao vendaDao = new VendaDao(venda);
	private MeuCampoTexto jtfCodigo = new MeuCampoTexto(10, true, "Código");
	private MeuCampoTexto jtfData = new MeuCampoTexto(20, true, "Data");
	private MeuDBCombo campoProduto = new MeuDBCombo(ProdutoDao.SQL_COMBOBOX, true, "Produto");
	private MeuCampoTexto jtfQuantidadeVenda = new MeuCampoTexto(2, true, "Quantidade");
	private MeuCampoTexto jtfPrecoUnidade = new MeuCampoTexto(20, true, "Preço Unitário");
	private MeuCampoTexto jtfDesconto = new MeuCampoTexto(20, true, "Desconto");
	private MeuCampoTexto jtfValorTotal = new MeuCampoTexto(50, true, "Valor Total");
	private MeuDBCombo campoCliente = new MeuDBCombo(ClienteDao.SQL_COMBOBOX, true, "Cliente");
	
	public TelaCadastroVenda() {
		super("Vendas");
		adicionaComponente(1,1,1,1, jtfCodigo);
		adicionaComponente(2,1,1,1,jtfData);
		adicionaComponente(3,1,1,1,campoProduto);
		adicionaComponente(4,1,1,1,jtfQuantidadeVenda);
		adicionaComponente(5,1,1,1,jtfPrecoUnidade);
		adicionaComponente(6,1,1,1,jtfDesconto);
		adicionaComponente(7,1,1,1,jtfValorTotal);
		adicionaComponente(8,1,1,1,campoCliente);
		pack();
		habilitaComponentes(false);
	}
		
		public void setPersistencia() {
			venda.setId(Integer.parseInt(jtfCodigo.getText()));
			venda.setData(jtfData.getText());
			venda.getProduto().setId(campoProduto.getValor());
			venda.setQuantidadeVendida(Integer.parseInt(jtfQuantidadeVenda.getText()));
			venda.setPrecoUnidade(Double.parseDouble(jtfPrecoUnidade.getText()));
			venda.setDesconto(Double.parseDouble(jtfDesconto.getText()));
			venda.setValorTotal(Double.parseDouble(jtfValorTotal.getText()));
			venda.getCliente().setId(campoCliente.getValor());
		}
		
		@Override
		public void incluirBD() {
			setPersistencia();
			vendaDao.inserir();
		}
		
		@Override
		public void alterarBD() {
			setPersistencia();
			vendaDao.alterar();
		}
		
		@Override
		public void excluirBD() {
			vendaDao.excluir();
			super.excluirBD();
		}
		
		@Override
		public void consultar() {
			super.consultar();
			new TelaConsulta(this, "Consulta de Cliente", new String[] {"Código", "Data", "Produto", "Quantidade", "Preço Unitário", "Desconto", "Valor Total", "Cliente"}, VendaDao.SQL_PESQUISAR);
		}
		
		
		@Override
		public void preencherDados(int pk) {
			venda.setId(pk);
			vendaDao.consultar();
			jtfCodigo.setText("" + venda.getId());
			jtfData.setText("" + venda.getData());
			campoProduto.setValor(venda.getProduto().getId());
			jtfQuantidadeVenda.setText("" + venda.getQuantidadeVendida());
			jtfPrecoUnidade.setText(""+ venda.getPrecoUnidade());
			jtfDesconto.setText(""+ venda.getDesconto());
			jtfValorTotal.setText(""+ venda.getValorTotal());
			campoCliente.setValor(venda.getCliente().getId());
			super.preencherDados(pk);
			
		
		}

}
