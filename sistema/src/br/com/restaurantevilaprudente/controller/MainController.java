package br.com.restaurantevilaprudente.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.restaurantevilaprudente.dao.UsuarioDAO;
import br.com.restaurantevilaprudente.model.Usuario;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/maincontroller.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 *  Home
		*/
		RequestDispatcher saida = request.getRequestDispatcher("home.jsp");
		saida.forward(request, response);
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
		UsuarioDAO usuarioDao = new UsuarioDAO();
		if(usuarioDao.autenticar(usuario)){
			RequestDispatcher saida = request.getRequestDispatcher("home.jsp");
			saida.forward(request, response);
		}
					
		/**
		 * Retorna para a homepage.
		 */
		response.sendRedirect("http://localhost:8080/RestauranteVilaPrudente/");
	}

}
