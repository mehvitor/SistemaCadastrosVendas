package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import banco_de_dados.ConexaoPostgreSQL;
import pojo.Endereco;

public class EnderecoDao {
	
	private final String SQL_INCLUIR = "INSERT INTO ENDERECO VALUES (?, ?, ?, ?, ?, ?)";
	private final String SQL_ALTERAR = "UPDATE ENDERECO SET LOGRADOURO = ?, NUMERO = ?, COMPLEMENTO = ?, BAIRRO = ? WHERE IDENDERECO = ?";
	private final String SQL_EXCLUIR = "DELETE FROM ENDERECO WHERE IDENDERECO = ?";
	private final String SQL_CONSULTAR = "SELECT * FROM ENDERECO WHERE IDENDERECO = ?";
	public static final String SQL_PESQUISAR = "SELECT ENDERECO.IDENDERECO, ENDERECO.LOGRADOURO, ENDERECO.NUMERO, ENDERECO.COMPLEMENTO, ENDERECO.BAIRRO, CIDADE.NOME FROM ENDERECO, CIDADE WHERE ENDERECO.CIDADE_IDCIDADE = CIDADE.IDCIDADE ORDER BY IDENDERECO";
	public static final String SQL_COMBOBOX = "SELECT IDENDERECO, LOGRADOURO FROM ENDERECO ORDER BY IDENDERECO";
	private Endereco endereco;
	
	public EnderecoDao(Endereco endereco) {
		this.endereco = endereco;
	}
	

	public boolean inserir() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_INCLUIR);
			ps.setInt(1, endereco.getId());
			ps.setString(2, endereco.getLogadouro());
			ps.setString(3, endereco.getNumero());
			ps.setString(4, endereco.getComplemento());
			ps.setString(5, endereco.getBairro());
			ps.setInt(6, endereco.getCidade().getId());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível incluir o endereço");
			return false;
		}
	}

		
	public boolean alterar() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_ALTERAR);
			ps.setString(1, endereco.getLogadouro());
			ps.setString(2, endereco.getNumero());
			ps.setString(3, endereco.getComplemento());
			ps.setString(4, endereco.getBairro());
			ps.setInt(5, endereco.getId());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível alterar o endereço");
			return false;
		}
	}

	public boolean excluir() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_EXCLUIR);
			ps.setInt(1, endereco.getId());	
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível excluir o endereço");
			return false;
		}
	}
	
	public boolean consultar() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_CONSULTAR);
			ps.setInt(1, endereco.getId());
			ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			endereco.setLogadouro(rs.getString("LOGRADOURO"));
			endereco.setNumero(rs.getString("NUMERO"));
			endereco.setComplemento(rs.getString("COMPLEMENTO"));
			endereco.setBairro(rs.getString("BAIRRO"));
			endereco.getCidade().setId(rs.getInt("CIDADE_IDCIDADE"));
			return true;
		}	else {
			JOptionPane.showMessageDialog(null, "Endereço não encontrado ( " + endereco.getId() + ") .");
			return false;
		}
			
		} catch (Exception e ) {
			JOptionPane.showMessageDialog(null, "Não foi possivel consultar o endereço");
			return false;
		}
	}

}
