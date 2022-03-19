package testutils;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import model.JavaBeans;

public class TestUtils {

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
						quantos++;
					}
				}
			}
		}

		if (a.size() == quantos) {
			return true;
		} else {
			return false;
		}
	}
}
