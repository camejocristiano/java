package br.com.restaurantevilaprudente.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.restaurantevilaprudente.dao.CaixaDAO;
import br.com.restaurantevilaprudente.dao.CardapioDAO;
import br.com.restaurantevilaprudente.dao.ClienteDAO;
import br.com.restaurantevilaprudente.dao.ItensDAO;
import br.com.restaurantevilaprudente.dao.PedidoDAO;
import br.com.restaurantevilaprudente.model.Caixa;
import br.com.restaurantevilaprudente.model.Cardapio;
import br.com.restaurantevilaprudente.model.Cliente;
import br.com.restaurantevilaprudente.model.Pedido;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/pedidocontroller.do")
public class PedidoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		PedidoDAO pedidoDao = new PedidoDAO();
		CardapioDAO cardapioDao = new CardapioDAO();
		ClienteDAO clienteDao = new ClienteDAO();
		
		/**
		 * 	Excluir cliente
		 */
		if(acao != null && acao.equals("excluir")){
			String id = request.getParameter("id");			
			Pedido pedido = new Pedido();			
			pedido.setId(Integer.parseInt(id));			
			pedidoDao.excluir(pedido);
			response.sendRedirect("pedidocontroller.do?acao=listar");
		}
		
		
		/**
		 * 	Visualizar um pedido pelo id
		 * 
		 *  Retorno = pedido, cliente e os itens do mesmo
		 */
		if(acao != null && acao.equals("alterar")){
			String id = request.getParameter("id");
			Pedido pedido = pedidoDao.buscarById(Integer.parseInt(id));
			request.setAttribute("pedido", pedido);
			Cliente cliente = clienteDao.buscarById(pedido.getCliente());
			request.setAttribute("cliente", cliente);
			ItensDAO itensDao = new ItensDAO();
			List<Cardapio> itens = itensDao.buscarByIdPedido(Integer.parseInt(id));
			request.setAttribute("itens", itens);
			
			RequestDispatcher saida = request.getRequestDispatcher("pedido.jsp");
			saida.forward(request, response);			
		}
	
		
		/**
		 * 	Listar todos os pedidos
		 */
		if(acao != null && acao.equals("listar")){
			List<Pedido> listaPedidos = pedidoDao.listarTodosLastCaixa();
			request.setAttribute("listaPedidos", listaPedidos);
			RequestDispatcher saida = request.getRequestDispatcher("pedidos.jsp");
			saida.forward(request, response);
		}
		
		/**
		 * 	Novo Pedido
		 */
		if(acao != null && acao.equals("novo")){
			List<Cardapio> lista = cardapioDao.listarTodos();
			request.setAttribute("lista", lista);
			List<Cliente> listaClientes = clienteDao.listarTodos();
			request.setAttribute("listaClientes", listaClientes);
			List<Pedido> listaPedidos = pedidoDao.listarTodosLastCaixa();
			request.setAttribute("listaPedidos", listaPedidos);
			RequestDispatcher saida = request.getRequestDispatcher("pedindo.jsp");
			saida.forward(request, response);
		}
		
		/**
		 * 	Novo Pedido
		 */
		if(acao != null && acao.equals("waiter")){
			List<Cardapio> lista = cardapioDao.listarTodos();
			request.setAttribute("lista", lista);
			List<Cliente> listaClientes = clienteDao.listarTodos();
			request.setAttribute("listaClientes", listaClientes);
			List<Pedido> listaPedidos = pedidoDao.listarTodosLastCaixa();
			request.setAttribute("listaPedidos", listaPedidos);
			RequestDispatcher saida = request.getRequestDispatcher("waiter.jsp");
			saida.forward(request, response);
		}
		
	}

	/** 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * Pega os valores vindos do formulário
		 */
		String cliente = request.getParameter("cliente");
		BigDecimal valor = new BigDecimal(request.getParameter("valor"));
		String cardapios[] = request.getParameterValues("itens");
		String quantidades[] = request.getParameterValues("quantidades");
		String pagamento = request.getParameter("pagamento");
		String troco_recebido = request.getParameter("troco");
		BigDecimal troco = new BigDecimal(0.00);
		if (troco_recebido != null && troco_recebido != "" && troco_recebido != "0") {
			troco = new BigDecimal(troco_recebido);
		}
		String diferenca_recebida = request.getParameter("diferenca");
		BigDecimal diferenca = new BigDecimal(0.00);
		if (diferenca_recebida != null && diferenca_recebida != "" && diferenca_recebida != "0") {
			diferenca = new BigDecimal(diferenca_recebida);
		}	
		String entrega = request.getParameter("entrega");
		String observacoes = request.getParameter("observacoes");
			
		/**
		 * Função para conferir o caixa, caso seja do dia anterior solicita novo caixa
		 *
		 * Pega o dia do caixa a ser utilizado
		 */
		CaixaDAO caixaDaoDia = new CaixaDAO();
		Caixa caixaDia = caixaDaoDia.buscarByLastId();
		int diaCaixa = caixaDia.getDia();
		/**
		 * Pega o dia atual do computador
		 */
		Calendar diaAtual = Calendar.getInstance();		
		int diaAtualParaConf = diaAtual.get(Calendar.DAY_OF_MONTH);
		/**
		 * Check entre dia atual e do caixa, caso diferentes retorna e solicita a abertura
		 */
		if(diaCaixa == diaAtualParaConf){
			
		
		
		/**
		 * Percorre os arrays de itens e quantidades dos arrays recebidos e 
		 * seta um novo array para os itens a serem impressos
		 */
		int [][] itensparacadastro = new int[cardapios.length][2];
		for(int i = 0; i < cardapios.length; i++){
			for(int j = 0; j < cardapios.length; j++){
				itensparacadastro[i][0] = Integer.parseInt(cardapios[i]); // quantia
				itensparacadastro[j][1] = Integer.parseInt(quantidades[j]);
			
			}
		}
		
		/**
		 * Seta a data e hora do Pedido
		 */
		Calendar c = Calendar.getInstance();
		int hora = c.get(Calendar.HOUR);
		int minuto = c.get(Calendar.MINUTE);

		
		
		/**
		 * Cadastrando um pedido
		 * 
		 * juntamente com os itens de cardápios
		 * 
		 */
		Pedido pedido = new Pedido();
		pedido.setCliente(Integer.parseInt(cliente));
		pedido.setValor(valor);
		pedido.setHora(hora);
		pedido.setMinuto(minuto);
		pedido.setPagamento(pagamento);
		pedido.setDiferenca(troco);
		pedido.setDiferenca(diferenca);
		pedido.setEntrega(entrega);
		pedido.setObservacoes(observacoes);
		PedidoDAO pedidoDAO = new PedidoDAO();
		pedidoDAO.salvar(pedido, itensparacadastro);		
		} else {
			String classeBootstrap = "alert alert-danger";
			String msg = "Necessário iniciar o caixa do dia primeiro!";
			request.setAttribute("classeBootstrap", classeBootstrap);
			request.setAttribute("msg", msg);
			RequestDispatcher home = request.getRequestDispatcher("home.jsp");
			home.forward(request, response);
		}
		response.sendRedirect("impressaocontroller.do");
		//response.sendRedirect("maincontroller.do");		
		}

	
}
