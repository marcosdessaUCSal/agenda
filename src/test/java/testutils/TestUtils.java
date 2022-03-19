package testutils;

import java.sql.Connection;
import java.util.ArrayList;

import model.DAO;
import model.JavaBeans;

public class TestUtils {
	
	public void resetTabela() {
		DAO dao = new DAO();
		dao.reset();
	}

	public boolean saoEquivalentes(ArrayList<JavaBeans> a, ArrayList<JavaBeans> b) {
		int quantos = 0;
		if (a.size() != b.size()) {
			return false;
		} else {
			for (int i = 0; i < a.size(); i++) {
				for (int j = 0; j < b.size(); j++) {
					if (a.get(i).getNome().equals(b.get(i).getNome())
							&& a.get(i).getEmail().equals(b.get(i).getEmail())
							&& a.get(i).getFone().equals(b.get(i).getFone())) {
						b.set(j, null);
						quantos++;
						break;
					}
				}
			}
		}
		return a.size() == quantos;
	}
}
