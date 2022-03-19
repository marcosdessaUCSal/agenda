package model;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import testutils.TestUtils;

public class DAOTest {

	ArrayList<JavaBeans> esperados;
	ArrayList<JavaBeans> atuais;
	TestUtils utils;
	DAO dao;

	@Test
	@DisplayName("Inserção de novos contatos")
	void novosContatosTeste() {
		dao = new DAO();
		utils = new TestUtils();

		// valores esperados
		esperados = new ArrayList<JavaBeans>();
		esperados.add(new JavaBeans(null, "Marcos Dessa", "88887777", "marcos@email.com"));
		esperados.add(new JavaBeans(null, "Misael Ferreira", "85878787", "misael@email.com"));
		esperados.add(new JavaBeans(null, "MVinicius Silva", "45457874", "vinicius@email.com"));
		esperados.add(new JavaBeans(null, "Emily Nascimento", "78998789", "emily@email.com"));
		esperados.add(new JavaBeans(null, "Heider da Siilva", "454784464", "heider@email.com"));
		esperados.add(new JavaBeans(null, "Matheus Maia", "1245789", "matheus@email.com"));

		// inserindo no banco
		dao.inserirContato(esperados.get(0));
		dao.inserirContato(esperados.get(1));
		dao.inserirContato(esperados.get(2));
		dao.inserirContato(esperados.get(3));
		dao.inserirContato(esperados.get(4));
		dao.inserirContato(esperados.get(5));

		// recuperando os valores atuais no banco
		atuais = dao.listarContatos();

		// contatos esperados e contatos atuais devem ter o mesmo número
		if (esperados.size() != atuais.size()) {
			fail("Número de contatos inconsistente");
		}

		if (utils.saoEquivalentes(esperados, atuais)) {
			fail("A lista de contatos não confere");
		}
		
	}
}
