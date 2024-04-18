package pojo;

public class Venda {

	private int id;
	private String data;
	private int	quantidadeVendida;
	private Double precoUnidade;
	private Double desconto;
	private Double valorTotal;
	private Cliente cliente = new Cliente();
	private Produto produto = new Produto();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String string) {
		this.data = string;
	}
	public int getQuantidadeVendida() {
		return quantidadeVendida;
	}
	public void setQuantidadeVendida(int quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}
	public Double getPrecoUnidade() {
		return precoUnidade;
	}
	public void setPrecoUnidade(Double precoUnidade) {
		this.precoUnidade = precoUnidade;
	}
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
}

