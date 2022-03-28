package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			// Listar contatos
			// criando um objeto que irá receber os dados JavaBeans
			ArrayList<JavaBeans> lista = dao.listarContatos();
			// encaminhar a lista ao documento agenda.jsp
			request.setAttribute("contatos", lista);
			RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
			rd.forward(request, response);

		} else if (action.equals("/insert")) {
			// Novo contato
			// setar as variáveis JavaBeans
			contato.setNome(request.getParameter("nome"));
			contato.setFone(request.getParameter("fone"));
			contato.setEmail(request.getParameter("email"));
			// invocar o método inserirContato passando o objeto contato
			try {
				dao.inserirContato(contato);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// redirecionar para o documento agenda.jsp
			response.sendRedirect("main");

		} else if (action.equals("/select")) {
			// selecionar contato para possibilitar posterior edição
			// recebimento do id do contato que será editado
			String idcon = request.getParameter("idcon");
			// setar a variável JavaBeans
			contato.setIdcon(idcon);
			// executar o método selecionarContato (DAO)
			dao.selecionarContato(contato);
			// setar os atributos
			request.setAttribute("idcon", contato.getIdcon());
			request.setAttribute("nome", contato.getNome());
			request.setAttribute("fone", contato.getFone());
			request.setAttribute("email", contato.getEmail());
			// encaminhar ao documento editar.jsp
			RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
			rd.forward(request, response);

		} else if (action.equals("/update")) {
			// editar contato
			// setar as variáveis JavaBeans
			contato.setIdcon(request.getParameter("idcon"));
			contato.setNome(request.getParameter("nome"));
			contato.setFone(request.getParameter("fone"));
			contato.setEmail(request.getParameter("email"));
			// executar o método alterarContato
			dao.alterarContato(contato);
			// redirecionar para o documento agenda.jsp (atualizando as alterações)
			response.sendRedirect("main");

		} else if (action.equals("/delete")) {
			// remover contato
			// recebimento do id do contato a ser excluído (validador.js)
			String idcon = request.getParameter("idcon");
			// setar a variável idcon JavaBeans
			contato.setIdcon(idcon);
			// executar o método deletarContato (DAO) passando o objeto contato
			dao.deletarContato(contato);
			// redirecionar para o documento agenda.jsp (atualizando as alterações)
			response.sendRedirect("main");

		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("agenda.jsp");
	}

	// Novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// setar as variáveis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// invocar o método inserirContato passando o objeto contato
		dao.inserirContato(contato);

		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

}
