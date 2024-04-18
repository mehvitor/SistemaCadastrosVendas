package componente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import banco_de_dados.ConexaoPostgreSQL;

public class MeuDBCombo extends JComboBox implements MeuComponente {

		private String sql;
		private boolean obrigatorio;
		private String dica;
		private List<Integer> pks;
		
		public MeuDBCombo(String sql, boolean obrigatorio, String dica) {
			this.obrigatorio = obrigatorio;
			this.dica = dica;
			preencher(sql);	
		}
		
		public void preencher(String sql) {
			this.sql = sql;
			pks = new ArrayList();
			removeAllItems();
			pks.add(-1);
			addItem("Selecione...");
			List<String[]> dados = ConexaoPostgreSQL.executeQuery(sql);
			for(int i = 0; i < dados.size(); i++) {
				pks.add(Integer.parseInt(dados.get(i)[0]));
				addItem(dados.get(i)[1]);
			}
		}

		
		@Override
		public boolean eObrigatorio() {
			return obrigatorio;
		}

		@Override
		public String getDica() {
			return dica;
		}

		@Override
		public boolean eVazio() {
			return getSelectedIndex() <= 0;
		}

		@Override
		public void limpar() {
			setSelectedIndex(0);
		}

		@Override
		public void habilitar(boolean status) {
			setEnabled(status);
		}

		@Override
		public boolean eValido() {
			return true;
		}
		
		public Integer getValor() {
			return pks.get(getSelectedIndex());
		}
		
		public void setValor(int pk) {
			for(int i = 0; i < pks.size(); i++) {
				if(pks.get(i) == pk) {
					setSelectedIndex(i);
					return;
				}
			}
			JOptionPane.showMessageDialog(null, getDica() + "não encontrado(a). ");
		}
		
	}

