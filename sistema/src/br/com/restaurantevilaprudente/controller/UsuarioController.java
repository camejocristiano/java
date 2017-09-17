package br.com.restaurantevilaprudente.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.restaurantevilaprudente.dao.UsuarioDAO;
import br.com.restaurantevilaprudente.model.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usuariocontroller.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		UsuarioDAO usuarioDao = new UsuarioDAO();
				
		//Excluir cliente
		if(acao != null && acao.equals("excluir")){
			String id = request.getParameter("id");			
			Usuario usuario = new Usuario();			
			usuario.setId(Integer.parseInt(id));			
			usuarioDao.excluir(usuario);
			response.sendRedirect("usuariocontroller.do?acao=listar");
		}
		
		//Alterar usuario
		if(acao != null && acao.equals("alterar")){
			String id = request.getParameter("id");			
			Usuario usuario = usuarioDao.buscarById(Integer.parseInt(id));		
			request.setAttribute("usuario", usuario);
			List<Usuario> listaUsuarios = usuarioDao.listarTodos();
			request.setAttribute("listaUsuarios", listaUsuarios);
			RequestDispatcher saida = request.getRequestDispatcher("usuarios.jsp");
			saida.forward(request, response);			
		}
		
		/**
		 * 	Cadastrar usuario
		 */
		if(acao != null && acao.equals("cadastrar")){
			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("nome");
			usuario.setEmail("email");
			usuario.setSenha("senha");
			request.setAttribute("usuario", usuario);
			RequestDispatcher saida = request.getRequestDispatcher("usuarios.jsp");
			saida.forward(request, response);			
		}
		
		/**
		 * 	Listar todos os usuarios
		 */	
		if(acao != null && acao.equals("listar")){
			List<Usuario> listaUsuarios = usuarioDao.listarTodos();
			request.setAttribute("listaUsuarios", listaUsuarios);
			RequestDispatcher saida = request.getRequestDispatcher("usuarios.jsp");
			saida.forward(request, response);
		}	
		
		/**
		 * 	Novo cliente
		 */
		if(acao != null && acao.equals("novo")){
			RequestDispatcher saida = request.getRequestDispatcher("usuario.jsp");
			saida.forward(request, response);
		}
	}

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recebe dados do formulário
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
				
		//Cria o objeto e seta os valores da tela
		Usuario usuario = new Usuario();
		if(id != null && id != "" && id != "0"){
			usuario.setId(Integer.parseInt(id));
		}
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);

		/**
		 * Salvar usuario.
		 */				
		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.salvar(usuario);
			
		/**
		 * Retorna para a homepage.
		 */
		response.sendRedirect("usuariocontroller.do?acao=listar");
	}

}
