package br.com.main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.main.dao.UsuarioDAO;
import br.com.main.dao.UsuarioDAOImpl;
import br.com.main.model.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/logincontroller.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recebe dados do formulário
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
									
				//Cria o objeto e seta os valores da tela
				Usuario usuario = new Usuario();
								
				usuario.setEmail(email);
				usuario.setSenha(senha);
				/**
				 * Salvar usuario.
				 */				
				UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
				UsuarioDAO usuarioDAO = usuarioDAOImpl;
				if(usuarioDAO.autenticarUsuario(usuario)){
					HttpSession session = request.getSession();
					session.setAttribute("usuario.logado", usuario);
					RequestDispatcher saida = request.getRequestDispatcher("restrict/home.jsp");
					saida.forward(request, response);
				} else {
					response.sendRedirect("http://localhost:8080/filter/");
				}
	}

}
