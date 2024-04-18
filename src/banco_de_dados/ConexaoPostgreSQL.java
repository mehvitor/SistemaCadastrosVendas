package banco_de_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ConexaoPostgreSQL {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/AtividadeReflexivaPOO";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "9qtfoaoc";

    private static Connection conexao;
    
    public static Connection getConexao() {
        try {	
            if(conexao == null) {
            	Class.forName("org.postgresql.Driver");
            	conexao = DriverManager.getConnection(URL, USUARIO, SENHA);  
            
            }
            return conexao;
            
        } catch (ClassNotFoundException e ) {
        	JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o driver de acesso");
        	return null;
        
        } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel conectar no Banco de Dados");
			return null;
		}
   
    }
 
    
    public static List<String[]> executeQuery(String sql){
    	try {
    		List<String[]> dados = new ArrayList();	
    		Statement st = getConexao().createStatement();
    		ResultSet rs = st.executeQuery(sql);
    		int numeroColunas = rs.getMetaData().getColumnCount();
    		while (rs.next()) {
    			String[] linha = new String[numeroColunas];
    			for(int i = 1; i <= numeroColunas; i++) {
    				linha[i - 1] = rs.getString(i);
    			}
    			dados.add(linha);
    		}
    			return dados;
    	} catch (Exception e) {
    		JOptionPane.showMessageDialog(null, "Não foi possivel consultar o banco de dados");
    		return new ArrayList();
    	}
    }
    
}
    