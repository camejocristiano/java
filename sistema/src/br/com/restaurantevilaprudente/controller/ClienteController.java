package br.com.restaurantevilaprudente.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.restaurantevilaprudente.dao.ClienteDAO;
import br.com.restaurantevilaprudente.model.Cliente;

/**
 * Servlet implementation class ClienteController
 */
@WebServlet("/clientecontroller.do")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		ClienteDAO clienteDao = new ClienteDAO();
		
		
		//Excluir cliente
		if(acao != null && acao.equals("excluir")){
			String id = request.getParameter("id");			
			Cliente cliente = new Cliente();			
			cliente.setId(Integer.parseInt(id));			
			clienteDao.excluir(cliente);
			response.sendRedirect("clientecontroller.do?acao=listar");
		}
		
		//Alterar cliente
		if(acao != null && acao.equals("alterar")){
			String id = request.getParameter("id");			
			Cliente cliente = clienteDao.buscarById(Integer.parseInt(id));		
			request.setAttribute("cliente", cliente);
			List<Cliente> lista = clienteDao.listarTodos();
			request.setAttribute("lista", lista);
			RequestDispatcher saida = request.getRequestDispatcher("clientes.jsp");
			saida.forward(request, response);			
		}
		
		/**
		 * 	Cadastrar cliente
		 */
		if(acao != null && acao.equals("cadastrar")){
			Cliente cliente = new Cliente();
			cliente.setId(0);
			cliente.setNome("nome");
			cliente.setEndereco("endereco");
			cliente.setTelefone(Integer.parseInt("telefone"));
			cliente.setEmpresa("empresa");
			cliente.setTelefoneempresa(Integer.parseInt("telefoneempresa"));
			cliente.setObservacoes("observacoes");
			request.setAttribute("cliente", cliente);
			RequestDispatcher saida = request.getRequestDispatcher("clientes.jsp");
			saida.forward(request, response);			
		}
		
		/**
		 * 	Listar todos os clientes
		 */	
		if(acao != null && acao.equals("listar")){
			List<Cliente> lista = clienteDao.listarTodos();
			request.setAttribute("lista", lista);
			RequestDispatcher saida = request.getRequestDispatcher("clientes.jsp");
			saida.forward(request, response);
		}	
		
		/**
		 * 	Novo cliente
		 */
		if(acao != null && acao.equals("novo")){
			RequestDispatcher saida = request.getRequestDispatcher("cliente.jsp");
			saida.forward(request, response);
		}
		
		/**
		 * Listar Json
		 */
		if (acao != null && acao.equals("listarjson")) {
			JSONArray listaClientes = new JSONArray();

			for (Cliente c: new ClienteDAO().listarTodos()){ 
				listaClientes.put(new JSONObject(c));
			}

			response.setContentType("application/json");
			response.getWriter().write(listaClientes.toString());
		}
	}
	

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recebe dados do formulário
				String id = request.getParameter("id");
				String nome = request.getParameter("nome");
				String endereco = request.getParameter("endereco");
				String telefone = request.getParameter("telefone");
				String empresa = request.getParameter("empresa");
				String telefoneempresa = request.getParameter("telefoneempresa");
				String observacoes = request.getParameter("observacoes");
				
				//Cria o objeto e seta os valores da tela
				Cliente cliente = new Cliente();
				if(id != null && id != "" && id != "0"){
					cliente.setId(Integer.parseInt(id));
				}
				cliente.setNome(nome);
				cliente.setEndereco(endereco);
				if(telefoneempresa == "" || telefoneempresa == null){
					cliente.setTelefone(0);
				} else {
					cliente.setTelefone(Integer.parseInt(telefone));
				}
				cliente.setEmpresa(empresa);
				if(telefoneempresa == "" || telefoneempresa == null){
					cliente.setTelefoneempresa(0);
				} else {
					cliente.setTelefoneempresa(Integer.parseInt(telefoneempresa));
				}
				
				cliente.setObservacoes(observacoes);

				/**
				 * Salvar cliente.
				 */				
				ClienteDAO clienteDao = new ClienteDAO();
				clienteDao.salvar(cliente);
				
				/**
				 * Retorna para a homepage.
				 */
				response.sendRedirect("clientecontroller.do?acao=listar");
	}

}
