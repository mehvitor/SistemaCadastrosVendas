package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import banco_de_dados.ConexaoPostgreSQL;
import pojo.Cidade;

public class CidadeDao {
	
	private final String SQL_INCLUIR = "INSERT INTO CIDADE VALUES (?, ?, ?)";
	private final String SQL_ALTERAR = "UPDATE CIDADE SET NOME = ? WHERE IDCIDADE = ?";
	private final String SQL_EXCLUIR = "DELETE FROM CIDADE WHERE IDCIDADE = ?";
	private final String SQL_CONSULTAR = "SELECT * FROM CIDADE WHERE IDCIDADE = ?";
	public static final String SQL_PESQUISAR = "SELECT CIDADE.IDCIDADE, CIDADE.NOME, ESTADO.SIGLA FROM CIDADE, ESTADO WHERE CIDADE.ESTADO_IDESTADO = ESTADO.IDESTADO ORDER BY IDCIDADE";
	public static final String SQL_COMBOBOX = "SELECT IDCIDADE, NOME FROM CIDADE ORDER BY NOME";
	private Cidade cidade;
	
	public CidadeDao(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public boolean inserir() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_INCLUIR);
			ps.setInt(1, cidade.getId());
			ps.setString(2, cidade.getNome());
			ps.setInt(3, cidade.getEstado().getId());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível incluir a cidade");
			return false;
		}
	}

		
	public boolean alterar() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_ALTERAR);
			ps.setString(1, cidade.getNome());
			ps.setInt(2, cidade.getId());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível alterar a cidade");
			return false;
		}
	}

	public boolean excluir() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_EXCLUIR);
			ps.setInt(1, cidade.getId());	
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível excluir a cidade");
			return false;
		}
	}
	
	public boolean consultar() {
		try {
			PreparedStatement ps = ConexaoPostgreSQL.getConexao().prepareStatement(SQL_CONSULTAR);
			ps.setInt(1, cidade.getId());
			ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			cidade.setNome(rs.getString("NOME"));
			cidade.getEstado().setId(rs.getInt("ESTADO_IDESTADO"));
			return true;
		}	else {
			JOptionPane.showMessageDialog(null, "Cidade não encontrado ( " + cidade.getId() + ") .");
			return false;
		}
			
		} catch (Exception e ) {
			JOptionPane.showMessageDialog(null, "Não foi possivel consultar a cidade");
			return false;
		}
	}

}
