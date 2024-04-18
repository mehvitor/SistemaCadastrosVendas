package tela;

import componente.MeuCampoTexto;
import dao.ProdutoDao;
import pojo.Produto;

public class TelaCadastroProduto extends TelaCadastro{
	
	public Produto produto = new Produto();
	public ProdutoDao produtoDao = new ProdutoDao(produto);			
	public MeuCampoTexto jtfCodigo = new MeuCampoTexto(10, true, "Código");
	public MeuCampoTexto jtfNome = new MeuCampoTexto(50,true, "Nome");
	public MeuCampoTexto jtfQuantidade = new MeuCampoTexto(3,true, "Quantidade");		//true é para dizer se o campo é obrigatorio
	public MeuCampoTexto jtfPrecoVenda = new MeuCampoTexto(10,true, "Preço de Venda");
	public MeuCampoTexto jtfPrecoCompra = new MeuCampoTexto(10,true, "Preço de Compra");
	
	public TelaCadastroProduto() {
		super("Cadastro de Produto");
		adicionaComponente(1,1,1,1, jtfCodigo);
		adicionaComponente(2,1,1,1,jtfNome);
		adicionaComponente(3,1,1,1, jtfQuantidade);
		adicionaComponente(4,1,1,1,jtfPrecoCompra);
		adicionaComponente(5,1,1,1,jtfPrecoVenda);
		pack();
		habilitaComponentes(false);	
	
	}
	
	public void setPersistencia() {
		produto.setId(Integer.parseInt(jtfCodigo.getText()));
		produto.setNome(jtfNome.getText());
		produto.setQuantidade(Integer.parseInt(jtfQuantidade.getText()));
		produto.setPrecoCompra(Double.parseDouble(jtfPrecoCompra.getText()));
		produto.setPrecoVenda(Double.parseDouble(jtfPrecoVenda.getText()));
	}
	
	@Override
	public void incluirBD() {
		setPersistencia();
		produtoDao.inserir();
	}
	
	@Override
	public void alterarBD() {
		setPersistencia();
		produtoDao.alterar();
	}
	
	@Override
	public void excluirBD() {
		produtoDao.excluir();
		super.excluirBD();
	}
	
	@Override
	public void consultar() {
		super.consultar();
		new TelaConsulta(this, "Consulta de Produto", new String[] {"Código", "Nome", "Quantidade", "Preço de Venda", "Preço de Compra"}, ProdutoDao.SQL_PESQUISAR);
	}
	
	
	@Override
	public void preencherDados(int pk) {
		produto.setId(pk);
		produtoDao.consultar();
		jtfCodigo.setText("" + produto.getId());
		jtfNome.setText(produto.getNome());
		jtfQuantidade.setText("" + produto.getQuantidade());
		jtfPrecoCompra.setText("" + produto.getPrecoCompra());
		jtfPrecoVenda.setText("" + produto.getPrecoVenda());
		super.preencherDados(pk);
		
	}

}
