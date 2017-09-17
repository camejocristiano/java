package br.com.restaurantevilaprudente.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.restaurantevilaprudente.dao.CaixaDAO;
import br.com.restaurantevilaprudente.dao.UsuarioDAO;
import br.com.restaurantevilaprudente.model.Caixa;
import br.com.restaurantevilaprudente.model.Usuario;

/**
 * Servlet implementation class CaixaController
 */
@WebServlet("/caixacontroller.do")
public class CaixaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaixaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		CaixaDAO caixaDao = new CaixaDAO();

		/**
		 * Iniciar caixa
		 */
		if (acao != null && acao.equals("cadastrar")) {
			Caixa caixa = new Caixa();
			caixa.setId(0);
			caixa.setUsuario(Integer.parseInt("usuario"));
			//caixa.setPedidos(Integer.parseInt("pedidos"));
			//caixa.setValor(new BigDecimal("valor"));
			request.setAttribute("caixa", caixa);
			RequestDispatcher saida = request.getRequestDispatcher("caixas.jsp");
			saida.forward(request, response);
		}

		/**
		 * Listar todos os caixas
		 */
		if (acao != null && acao.equals("listar")) {
			List<Caixa> listaCaixas = caixaDao.listarTodos();
			request.setAttribute("listaCaixas", listaCaixas);
			RequestDispatcher saida = request.getRequestDispatcher("caixas.jsp");
			saida.forward(request, response);
		}

		/**
		 * Listar todos os pedidos de um caixas
		 */
		if (acao != null && acao.equals("visualizar")) {
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			RequestDispatcher saida = request.getRequestDispatcher("caixa.jsp");
			saida.forward(request, response);
		}
		
		/**
		 * Novo caixa
		 */
		if (acao != null && acao.equals("novo")) {
			RequestDispatcher saida = request.getRequestDispatcher("caixas.jsp");
			saida.forward(request, response);
		}
		
		/**
		 * Fechar caixa
		 */
		if (acao != null && acao.equals("fechar")) {
			response.sendRedirect("impressaocaixacontroller.do");
			//RequestDispatcher saida = request.getRequestDispatcher("caixas.jsp");
			//saida.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * Recebe os dados do formulário
		 */
		String id = request.getParameter("id");
		//String usuario = request.getParameter("usuario");
		//String pedidos = request.getParameter("pedidos");
		//String valor = request.getParameter("valor");
		Calendar c = Calendar.getInstance();
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int _mes = c.get(Calendar.MONTH);
		int mes = _mes + 1;
		int ano = c.get(Calendar.YEAR);
		
		/**
		 * Função para conferir o usuário
		 */
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		UsuarioDAO usuDao = new UsuarioDAO();
		Usuario usu = new Usuario();
		usu.setEmail(email);
		usu.setSenha(senha);
		if(usuDao.autenticar(usu)){
			Usuario usuario = usuDao.buscarByEmail(email);
		/**
		 * Cria o objeto cardápio.
		 */
		Caixa caixa = new Caixa();
		
		/**
		 * Confere se o Id do objeto cardápio está incluso,
		 * caso sim seta ao objeto,
		 * caso não seta o objeto sem id.
		 */
		if (id != null && id != "" && id != "0") {
			caixa.setId(Integer.parseInt(id));
		}
		
		/**
		 * Seta os demais atributos do objeto caixa.
		*/
		caixa.setUsuario(usuario.getId());
		//caixa.setPedidos(Integer.parseInt(pedidos));
		//caixa.setValor(new BigDecimal(valor));
		caixa.setDia(dia);
		caixa.setMes(mes);
		caixa.setAno(ano);
		
		/**
		 * Salva o objeto cardápio.
		*/
		CaixaDAO caixaDao = new CaixaDAO();
		caixaDao.salvar(caixa);
		
		/**
		 * Retorna para a homepage.
		*/
		}
		response.sendRedirect("caixacontroller.do?acao=listar");
	}

}
