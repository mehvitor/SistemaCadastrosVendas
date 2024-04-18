package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import banco_de_dados.ConexaoPostgreSQL;
import pojo.Venda;

public class VendaDao {
	
	private final String SQL_INCLUIR = "INSERT INTO VENDA VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String SQL_ALTERAR = "UPDATE VENDA SET DATA = ?, QUANTIDADEVENDIDA = ?, PRECOUNIDADE = ?, DESCONTO = ?, VALORTOTAL = ? WHERE IDVENDA= ?";
	private final String SQL_EXCLUIR = "DELETE FROM VENDA WHERE IDVENDA = ?";
	private final String SQL_CONSULTAR = "SELECT * FROM VENDA WHERE IDVENDA = ?";
	public static final String SQL_PESQUISAR = "SELECT IDVENDA, DATA, p.nome, QUANTIDADEVENDIDA, PRECOUNIDADE, DESCONTO, VALORTOTAL, c.NOME\r\n"
			+ "FROM VENDA V\r\n"
			+ "join cliente c ON v.cliente_idcliente = c.idcliente\r\n"
			+ "join produto p ON v.produto_idproduto = p.idproduto\r\n"
			+ "ORDER BY IDVENDA";
	public static final String SQL_COMBOBOX = "SELECT IDVENDA, DATA, QUANTIDADEVENDIDA, PRECOUNIDADE, DESCONTO, VALORTOTAL, CLIENTE_IDCLIENTE, PRODUTO_IDPRODUTO FROM VENDA ORDER BY IDVENDA";
	
	private Venda venda;
	
	public VendaDao(Venda venda) {
		this.venda = venda;
	}
	

	public boolean inserir() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_INCLUIR);
			ps.setInt(1, venda.getId());
			ps.setString(2, venda.getData());
			ps.setInt(3, venda.getQuantidadeVendida());
			ps.setDouble(4, venda.getPrecoUnidade());
			ps.setDouble(5, venda.getDesconto());
			ps.setDouble(6, venda.getValorTotal());
			ps.setInt(7, venda.getCliente().getId());
			ps.setInt(8, venda.getProduto().getId());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível incluir a venda");
			return false;
		}
	}

		
	public boolean alterar() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_ALTERAR);
			ps.setString(1, venda.getData());
			ps.setInt(2, venda.getQuantidadeVendida());
			ps.setDouble(3, venda.getPrecoUnidade());
			ps.setDouble(4, venda.getDesconto());
			ps.setDouble(5, venda.getValorTotal());
			ps.setInt(6, venda.getId());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível alterar a venda");
			return false;
		}
	}

	public boolean excluir() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_EXCLUIR);
			ps.setInt(1, venda.getId());	
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível excluir a venda");
			return false;
		}
	}
	
	public boolean consultar() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_CONSULTAR);
			ps.setInt(1, venda.getId());
			ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			venda.setData(rs.getString("DATA"));
			venda.setQuantidadeVendida(rs.getInt("QUANTIDADEVENDIDA"));
			venda.setPrecoUnidade(rs.getDouble("PRECOUNIDADE"));
			venda.setDesconto(rs.getDouble("DESCONTO"));
			venda.setValorTotal(rs.getDouble("VALORTOTAL"));
			venda.getCliente().setId(rs.getInt("CLIENTE_IDCLIENTE"));
			venda.getProduto().setId(rs.getInt("PRODUTO_IDPRODUTO"));
			return true;
		}	else {
			JOptionPane.showMessageDialog(null, "Venda não encontrada ( " + venda.getId() + ") .");
			return false;
		}
			
		} catch (Exception e ) {
			JOptionPane.showMessageDialog(null, "Não foi possivel consultar a venda");
			return false;
		}
	}

}
