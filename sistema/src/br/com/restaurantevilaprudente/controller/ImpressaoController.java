package br.com.restaurantevilaprudente.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.restaurantevilaprudente.dao.ClienteDAO;
import br.com.restaurantevilaprudente.dao.ImpressaoDAO;
import br.com.restaurantevilaprudente.dao.ItensDAO;
import br.com.restaurantevilaprudente.dao.PedidoDAO;
import br.com.restaurantevilaprudente.model.Cardapio;
import br.com.restaurantevilaprudente.model.Cliente;
import br.com.restaurantevilaprudente.model.Pedido;

/**
 * Servlet implementation class ImpressaoController
 */
@WebServlet("/impressaocontroller.do")
public class ImpressaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImpressaoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		//PrintWriter saida = response.getWriter();
		//saida.println("Para testes!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * Pega id do caixa
		*/
		String id = request.getParameter("id");
		ImpressaoDAO impressaoDao = new ImpressaoDAO();
		
		String resultadoParaImpressao = "";
		String space = " ";
		
		String numeroDoPedido = "";
		String dataPedido = "";
		String valorPedido = "";
		
		String nome = "";
		String endereco = "";
		String telefone = "";
		
		String header = "";
		String pagamento = "";
		String troco = "";
		String diferenca = "";
		String entrega = "";
		String observacoes = "";
		
		PedidoDAO pedidoDao = new PedidoDAO();
		Pedido pedido;
		
		if (id != null && id != "" && id != "0") {
			pedido = pedidoDao.buscarById(Integer.parseInt(id));
		} else {
			pedido = pedidoDao.buscarByLastId();
		}			
        numeroDoPedido = pedido.getNumero();
		valorPedido =  pedido.getValor().toString();
		
		while (valorPedido.length() < 6) {
			valorPedido = space + valorPedido;
		}
		
		if (pedido.getTroco() != null) {
			troco =  pedido.getTroco().toString();
		} else {
			troco =  "";
		}		
		while (troco.length() < 6) {
			troco = space + troco;
		}

		if (pedido.getDiferenca() != null) {
			diferenca = pedido.getDiferenca().toString();
		} else {
			diferenca = "";
		}		
		while (diferenca.length() < 6) {
			diferenca = space + diferenca;
		}
		
		if (pedido.getEntrega() != null) {
			entrega = pedido.getEntrega();
		} else {
			entrega = "";
		}		
		while (entrega.length() < 5) {
			entrega = entrega + space;
		}
		
		if (pedido.getObservacoes() != null) {
			observacoes = pedido.getObservacoes();
		} else {
			observacoes = "";
		}
		while (observacoes.length() < 135) {
			observacoes = observacoes + space;
		}
		
		if (pedido.getPagamento() != null) {
			pagamento = pedido.getPagamento();
		} else {
			pagamento = "";
		}		
		while (pagamento.length() < 24) {
			pagamento = pagamento + space;
		}
        
		Calendar calendario = Calendar.getInstance();
		Date data = calendario.getTime();
		
		DateFormat dtHora = DateFormat.getDateTimeInstance();
		dataPedido = dtHora.format(data);
		
		
		
	
		ClienteDAO clienteDao = new ClienteDAO();		
		Cliente cliente;
		cliente = clienteDao.buscarById(pedido.getCliente());
		nome = cliente.getNome();
        
	    /**
		 * Tratando o pagamento para impressão
		 * 
		 * Funções String
		 * 
		 */
		telefone = cliente.getTelefone().toString();
		while (telefone.length() < 36) {
			telefone = telefone + space;
		}
        // Pedido  
        
        
        /**
		 * Tratando o nome do cliente para impressão
		 * 
		 * Funções String
		 * 
		 */
		while (nome.length() < 85) {
			nome = nome + space;
		}
        
        
		/**
		 * Tratando o endereço para impressão
		 * 
		 * Funções String
		 * 
		 */
		endereco = cliente.getEndereco();
		while (endereco.length() < 84) {
			endereco = endereco + space;
		}
		// Cliente
         
		ItensDAO itensDao = new ItensDAO();
		List<Cardapio> listarItens = itensDao.buscarByIdPedido(pedido.getId());
		for(Cardapio c: listarItens){
			c.getId();
            c.getTitulo();
            c.getDescricao();
            c.getValor();
		}    

		header = ""
				    + "RESTAURANTEVILAPRUDENTE||RESTAURANTEVILAPRUDENTE"
					+ "DELEVERY-20212178-LIGUE||DELEVERY-20214651-LIGUE"
					+ "CONFIRA-PROMOCOES-LIGUE||WHATS11-952.809.967-APP"
					+ "                                                "
					+ "           OBRIGADO PELA PREFERENCIA            "
					+ "    QUE DEUS ABENCOE E ILUMINE SEUS CAMINHOS    "
					+ "                                                "
	                + "           RESTAURANTE VILA PRUDENTE            "
	                + "                                                "
	                + "  DATA: "+dataPedido+"                     "
	                + "                                                "
					+ "  PEDIDO NUMERO                         " + numeroDoPedido + "      "  
					+ "                                                "
					+ "================================================"
					+ "  OBSERVACOES:                                  "
					+ "         " + observacoes
					+ "                                                "
					+ "================================================"
					;
			
			/**
			 * 
			 * Itens de cardápios tratados para impressão
			 * 
			 * Função para tratar o titulo do cardápio
			 * 
			 */
			String tituloCardapioImpressao = "";
			String valorCardapioImpressao = "";
			String itensCardapiosImpressao = ""
					
					+ "  ****************** ITENS: ******************  ";
					for (Cardapio cardapio : listarItens) {
						tituloCardapioImpressao = cardapio.getTitulo();
						valorCardapioImpressao = cardapio.getValor().toString();
						
						while(tituloCardapioImpressao.length() < 35){
							tituloCardapioImpressao = tituloCardapioImpressao + space;						
						}
						while(valorCardapioImpressao.length() < 6){
							valorCardapioImpressao = space + valorCardapioImpressao;						
						}
						String cardapioImprimir = "  " + tituloCardapioImpressao + "R$ " + valorCardapioImpressao + "  ";
						itensCardapiosImpressao = itensCardapiosImpressao + cardapioImprimir;
					}
					
			String valorImpressao = ""
	                + "  ____________________________________________  "
					+ "                                                "
					+ "  VALOR TOTAL:                       R$ " + valorPedido + "  "
					+ "  TROCO:                             R$ " + diferenca + "  "
					+ "                                                "
					+ "  FORMA DE PAGAMENTO: " + pagamento + "  "
					;
			
			String clienteImpressao = ""
					+ "                                                "
					+ "================================================"
					+ "  DADOS DO CLIENTE:                             "
					+ "                                                "
					+ "  CLIENTE: " + nome
					+ "  ENDERECO: " + endereco 
					+ "  TELEFONE: " + telefone
					+ "================================================"
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

			resultadoParaImpressao = resultadoParaImpressao + header + itensCardapiosImpressao + valorImpressao + clienteImpressao;
					impressaoDao.imprime(resultadoParaImpressao);
					
			String classeBootstrap = "alert alert-success";
			String msg = "Pedido lançado com sucesso!";
			request.setAttribute("classeBootstrap", classeBootstrap);
			request.setAttribute("msg", msg);
			RequestDispatcher home = request.getRequestDispatcher("home.jsp");
			home.forward(request, response);
	}

}
