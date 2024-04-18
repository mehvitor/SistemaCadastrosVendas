package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import banco_de_dados.ConexaoPostgreSQL;
import pojo.Produto;

public class ProdutoDao {

	private final String SQL_INCLUIR = "INSERT INTO PRODUTO VALUES (?, ?, ?, ?, ?)";
	private final String SQL_ALTERAR = "UPDATE PRODUTO SET NOME = ?, QUANTIDADEESTOQUE = ?, PRECOVENDA = ?, PRECOCOMPRA = ? WHERE IDPRODUTO = ?";
	private final String SQL_EXCLUIR = "DELETE FROM PRODUTO WHERE IDPRODUTO = ?";
	private final String SQL_CONSULTAR = "SELECT * FROM PRODUTO WHERE IDPRODUTO = ?";
	public static final String SQL_PESQUISAR = "SELECT * FROM PRODUTO ORDER BY IDPRODUTO";
	public static final String SQL_COMBOBOX = "SELECT * FROM PRODUTO ORDER BY NOME";
	private Produto produto;
	
	public ProdutoDao(Produto produto) {
		this.produto = produto;
	}
	
	public boolean inserir() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_INCLUIR);
			ps.setInt(1, produto.getId());
			ps.setString(2, produto.getNome());
			ps.setInt(3, produto.getQuantidade());
			ps.setDouble(4, produto.getPrecoVenda());
			ps.setDouble(5, produto.getPrecoCompra());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível incluir o produto");
			return false;
		}
	}

		
	public boolean alterar() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_ALTERAR);
			ps.setString(1, produto.getNome());
			ps.setInt(2, produto.getQuantidade());
			ps.setDouble(3, produto.getPrecoVenda());
			ps.setDouble(4, produto.getPrecoCompra());
			ps.setInt(5, produto.getId());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível alterar o produto");
			return false;
		}
	}

	public boolean excluir() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_EXCLUIR);
			ps.setInt(1, produto.getId());	
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível excluir o produto");
			return false;
		}
	}
	
	public boolean consultar() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_CONSULTAR);
			ps.setInt(1, produto.getId());
			ResultSet rs = ps.executeQuery();
		if (rs == null) {
			return false;
		}
		if (rs.next()) {
			produto.setNome(rs.getString("NOME"));
			produto.setQuantidade(rs.getInt("QUANTIDADEESTOQUE"));
			produto.setPrecoCompra(rs.getDouble("PRECOCOMPRA"));
			produto.setPrecoVenda(rs.getDouble("PRECOVENDA"));
			return true;
		}	else {
			JOptionPane.showMessageDialog(null, "Estado não encontrado ( " + produto.getId() + ") .");
			return false;
		}
			
		} catch (Exception e ) {
			JOptionPane.showMessageDialog(null, "Não foi possivel consultar o produto");
			return false;
		}
	}
}
