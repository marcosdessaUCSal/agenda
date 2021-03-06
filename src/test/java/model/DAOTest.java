package model;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import testutils.TestUtils;

public class DAOTest {

	ArrayList<JavaBeans> esperados;
	ArrayList<JavaBeans> atuais;
	TestUtils utils;
	DAO dao;

	@BeforeEach
	void inicio() {
		utils = new TestUtils();

		// limpar a tabela
		utils.resetTabela();
	}

	@AfterEach
	void fim() {
		// limpar a tabela novamente
		utils.resetTabela();
	}

	@Test
	@DisplayName("Inserir um contato com campos nulos")
	void contatoCamposNulos() {
		dao = new DAO();

		// a aplicação deveria lançar alguma exceção
		assertThrows(Exception.class, () -> dao.inserirContato(new JavaBeans(null, null, null, null)),
				"Deveria ser lançada exceção");

	}

	@Test
	@DisplayName("Inserção de novos contatos")
	void novosContatosTeste() throws Exception {
		dao = new DAO();
		utils = new TestUtils();

		// limpar a tabela
		utils.resetTabela();

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

		// verifica se o esperado e o obtido conferem (ordem pode ser diferente)
		if (utils.saoEquivalentes(esperados, atuais)) {
			fail("A lista de contatos não confere");
		}
	}

	@Test
	@DisplayName("Dados repetidos")
	void dadosRepetidosTeste() throws Exception {
		dao = new DAO();
		utils = new TestUtils();

		// inserindo o mesmo contato duas vezes
		JavaBeans contato = new JavaBeans(null, "Marcos Dessa", "88887777", "marcos@email.com");
		dao.inserirContato(contato);
		dao.inserirContato(contato);

		// recuperando os valores atuais no banco
		atuais = dao.listarContatos();

		// se houver dois registros, há duplicação nos dados
		assertEquals(1, atuais.size(), "Contato idêntico inserido duas vezes");
	}

	@Test
	@DisplayName("Eliminação de contatos")
	void deletarContatos() throws Exception {
		dao = new DAO();
		utils = new TestUtils();

		// inserindo um contato
		JavaBeans contato = new JavaBeans(null, "Bill Gates", "123456789", "bill@email.com");
		dao.inserirContato(contato);

		// recuperando o contato
		atuais = dao.listarContatos();

		// deletando o contato recém-inserido
		dao.deletarContato(atuais.get(0));

		// recuperar de novo para verificar se o registro persistiu ou não
		atuais = dao.listarContatos();

		// se houver algum elemento no banco, o registro não foi eliminado
		assertEquals(0, atuais.size(), "O contato não foi eliminado");
	}

	@Test
	@DisplayName("Alteração de um contato")
	void alterarContato() throws Exception {
		dao = new DAO();
		utils = new TestUtils();

		// inserindo um contato
		JavaBeans contato = new JavaBeans(null, "Bill Gates", "123456789", "bill@email.com");
		dao.inserirContato(contato);

		// recuperando o contato (para que o dado tenha informação do id)
		atuais = dao.listarContatos();
		String id = atuais.get(0).getIdcon();

		// alterando o contato
		dao.alterarContato(new JavaBeans(id, "Peter Parker", "987456321", "spider@man.com"));

		// recuperando o contato novamente para depois checar alterações
		JavaBeans contatoAtual = dao.listarContatos().get(0);

		// verificando
		assertAll("Alteração de um contato", () -> assertEquals("Peter Parker", contatoAtual.getNome()),
				() -> assertEquals("987456321", contatoAtual.getFone()),
				() -> assertEquals("spider@man.com", contatoAtual.getEmail()));

	}
}
