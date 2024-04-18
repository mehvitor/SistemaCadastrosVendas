package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import banco_de_dados.ConexaoPostgreSQL;
import pojo.Cliente;

public class ClienteDao {
	
	private final String SQL_INCLUIR = "INSERT INTO CLIENTE VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final String SQL_ALTERAR = "UPDATE CLIENTE SET NOME = ?, CPF = ?, RG = ?, TELEFONE = ?, EMAIL = ? WHERE IDCLIENTE = ?";
	private final String SQL_EXCLUIR = "DELETE FROM CLIENTE WHERE IDCLIENTE = ?";
	private final String SQL_CONSULTAR = "SELECT * FROM CLIENTE WHERE IDCLIENTE = ?";
	public static final String SQL_PESQUISAR = "SELECT CLIENTE.IDCLIENTE, CLIENTE.NOME, CLIENTE.CPF, CLIENTE.RG, CLIENTE.TELEFONE, CLIENTE.EMAIL, ENDERECO.LOGRADOURO FROM CLIENTE, ENDERECO WHERE CLIENTE.ENDERECO_IDENDERECO = ENDERECO.IDENDERECO ORDER BY IDCLIENTE";
	public static final String SQL_COMBOBOX = "SELECT IDCLIENTE, NOME FROM CLIENTE ORDER BY NOME";
	private Cliente cliente;
	
	public ClienteDao(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean inserir() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_INCLUIR);
			ps.setInt(1, cliente.getId());
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getCpf());
			ps.setString(4, cliente.getRg());
			ps.setString(5, cliente.getTelefone());
			ps.setString(6, cliente.getEmail());
		    ps.setInt(7, cliente.getEndereco().getId());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível incluir o cliente");
			return false;
		}
	}

		
	public boolean alterar() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_ALTERAR);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getRg());
			ps.setString(4, cliente.getTelefone());
			ps.setString(5, cliente.getEmail());
			ps.setInt(6, cliente.getId());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível alterar o cliente");
			return false;
		}
	}

	public boolean excluir() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_EXCLUIR);
			ps.setInt(1, cliente.getId());	
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível excluir o cliente");
			return false;
		}
	}
	
	public boolean consultar() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_CONSULTAR);
			ps.setInt(1, cliente.getId());
			ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			cliente.setNome(rs.getString("NOME"));
			cliente.setCpf(rs.getString("CPF"));
			cliente.setRg(rs.getString("RG"));
			cliente.setTelefone(rs.getString("TELEFONE"));
			cliente.setEmail(rs.getString("EMAIL"));
			cliente.getEndereco().setId(rs.getInt("ENDERECO_IDENDERECO"));
			return true;
		}	else {
			JOptionPane.showMessageDialog(null, "Cliente não encontrado ( " + cliente.getId() + ") .");
			return false;
		}
			
		} catch (Exception e ) {
			JOptionPane.showMessageDialog(null, "Não foi possivel consultar o cliente");
			return false;
		}
	}
	
}


