package testutils;

import java.util.ArrayList;

import model.DAO;
import model.JavaBeans;

public class TestUtils {
	
	public void resetTabela() {
		DAO dao = new DAO();
		dao.reset();
	}

	// 'esperados', por hipótese, não tem elementos iguais
	public boolean saoEquivalentes(ArrayList<JavaBeans> esperados, ArrayList<JavaBeans> obtidos) {
		int iguais = 0;
		if (esperados.size() != obtidos.size()) {
			return false;
		} else {
			for (int i = 0; i < esperados.size(); i++) {
				for (int j = 0; j < obtidos.size(); j++) {
					if (esperados.get(i).getNome().equals(obtidos.get(i).getNome())
							&& esperados.get(i).getEmail().equals(obtidos.get(i).getEmail())
							&& esperados.get(i).getFone().equals(obtidos.get(i).getFone())) {
						iguais++;
						break;
					}
				}
			}
		}
		return esperados.size() == iguais;
	}
}
