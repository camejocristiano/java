package br.com.restaurantevilaprudente.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.restaurantevilaprudente.dao.CaixaDAO;
import br.com.restaurantevilaprudente.dao.ImpressaoDAO;
import br.com.restaurantevilaprudente.dao.ItensDAO;
import br.com.restaurantevilaprudente.dao.PedidoDAO;
import br.com.restaurantevilaprudente.model.Caixa;
import br.com.restaurantevilaprudente.model.Cardapio;
import br.com.restaurantevilaprudente.model.Pedido;

/**
 * Servlet implementation class ImpressaoController
 */
@WebServlet("/impressaocaixacontroller.do")
public class ImpressaoCaixaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImpressaoCaixaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Inicia a classe de impressão e variaveis
		 * 		  
		 */
		ImpressaoDAO impressaoDao = new ImpressaoDAO();
		String space = " ";
		/**
		 * Variaveis a imprimir
		 */
		String impressao = "";
		String numeroPedidos = "";
		while(numeroPedidos.length() < 27){
			numeroPedidos = numeroPedidos + space;
		}
		String valorCaixa = "";
		while(valorCaixa.length() < 33){
			valorCaixa = valorCaixa + space;
		}
		String dataCaixa = "";
		while(dataCaixa.length() < 17){
			dataCaixa = dataCaixa + space;
		}
		/**
		 * Caixa
		 */		
		CaixaDAO caixaDao = new CaixaDAO();
		String id = request.getParameter("id");
		Caixa caixa = caixaDao.buscarById(Integer.parseInt(id));
		caixa.getId();
		numeroPedidos = caixa.getPedidos().toString();
		valorCaixa = caixa.getValor().toString();
		//data
		dataCaixa = caixa.getDia().toString() +"/"+ caixa.getMes().toString() +"/"+ caixa.getAno().toString();
		
		/**
		 * Variaveis a imprimir
		 */
		String cardapios = "";
		/**
		 * Pedidos
		 */
		PedidoDAO pedidoDao = new PedidoDAO();
		List<Pedido> pedidos = pedidoDao.listarTodosByCaixa(caixa.getId());
		ItensDAO itensDao = new ItensDAO();
		List<Cardapio> itens = null;
		for(Pedido pedido : pedidos){
			// buscando os pedidos
			itens = itensDao.buscarByIdPedido(pedido.getId());
			for(Cardapio cardapio : itens){
				// buscando os cardapios
				cardapios = cardapios + " - " + cardapio.getTitulo();
			}
		}
		
		impressao = ""
			    + "RESTAURANTEVILAPRUDENTE||RESTAURANTEVILAPRUDENTE"
				+ "DELEVERY-20212178-LIGUE||DELEVERY-20214651-LIGUE"
				+ "CONFIRA-PROMOCOES-LIGUE||WHATS11-952.809.967-APP"
				+ "                                                "
				+ "           OBRIGADO PELA PREFERENCIA            "
				+ "    QUE DEUS ABENCOE E ILUMINE SEUS CAMINHOS    "
				+ "                                                "
                + "           RESTAURANTE VILA PRUDENTE            "
                + "                                                "
                + "                                                "
                + "                                                "
                + "                                                "
                + "           RELATORIO DIARIO DO CAIXA            "
                + "                                                "
                + "  DATA: " + dataCaixa + "                       " 
                + "                                                "
				+ "  NUMERO DE PEDIDOS: " + numeroPedidos  
				+ "                                                "
				+ "  VALOR TOTAL: " + valorCaixa  
		 		+ "                                                "
		 		+ "                                                "
		 		+ "                                                "
		 		+ "                                                "
		 		+ "                                                "
		 		+ "                                                "
		 		+ "RESTAURANTEVILAPRUDENTE||RESTAURANTEVILAPRUDENTE"
				+ "DELEVERY-22725374-LIGUE||WHATSAPP-(11)-952809967"
				+ "CONFIRA-PROMOCOES-LIGUE||TODOS-OS-BAIRROS-DE-SP-"
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "                                                "
				;
		 
		impressaoDao.imprime(impressao);
		response.sendRedirect("caixacontroller.do?acao=listar");	

		
	}

}
