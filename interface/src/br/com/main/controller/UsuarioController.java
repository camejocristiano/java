package br.com.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.main.dao.UsuarioDAO;
import br.com.main.dao.UsuarioDAOImpl;
import br.com.main.model.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usuariocontroller.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UsuarioController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
			
		// Lista todo os registros
		if(acao != null && acao.equals("listar")){
			UsuarioDAO usuarioDAO = usuarioDAOImpl;
			List<Usuario> listaUsuarios = usuarioDAO.listUsuarios();
			request.setAttribute("listaUsuarios", listaUsuarios);
			RequestDispatcher saida = request.getRequestDispatcher("list-usuarios.jsp");
			saida.forward(request, response);
		}
		// Inicia um formulário para novo cadastro
		if(acao != null && acao.equals("novo")){
			RequestDispatcher saida = request.getRequestDispatcher("novo-usuario.jsp");
			saida.forward(request, response);
		}
		// Busca para edição
		if(acao != null && acao.equals("alterar")){
			String id = request.getParameter("id");
			UsuarioDAO usuarioDAO = usuarioDAOImpl;
			Usuario usuario = usuarioDAO.getUsuarioById(Integer.parseInt(id));		
			request.setAttribute("usuario", usuario);
			RequestDispatcher saida = request.getRequestDispatcher("usuario.jsp");
			saida.forward(request, response);			
		}
		// Excluir
		if(acao != null && acao.equals("excluir")){
			String id = request.getParameter("id");			
			UsuarioDAO usuarioDAO = usuarioDAOImpl;
			usuarioDAO.removeUsuario(Integer.parseInt(id));
			String msg = "Usuário excluido com sucesso!";
			request.setAttribute("msg", msg);
			response.sendRedirect("usuariocontroller.do?acao=listar");

	//		RequestDispatcher home = request.getRequestDispatcher("home.jsp");
	//		home.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);

		UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
		UsuarioDAO usuarioDAO = usuarioDAOImpl;
		
		String msg;
		if(id != null && id != "" && id != "0"){
			usuario.setId(Integer.parseInt(id));
			usuarioDAO.updateUsuario(usuario);
			msg = "Usuario alterado com sucesso!";
		} else {
			usuarioDAO.addUsuario(usuario);
			msg = "Usuario criado com sucesso!";
		}
		
		request.setAttribute("msg", msg);
		RequestDispatcher home = request.getRequestDispatcher("home.jsp");
		home.forward(request, response);
	}

}
