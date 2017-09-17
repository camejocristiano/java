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
@WebServlet("/impressaocontrollercopy.do")
public class ImpressaoControllerCopy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImpressaoControllerCopy() {
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
		 * Pega o valor vindos do formulário
		 */
		String id = request.getParameter("id");
		// id
		
		
		
		/**
		 * Inicia a classe de impressão e variaveis
		 * 		  
		 */
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
		// Impressora
		
		
		
		/**
		 * Inicia o Pedido e o DAO
		 */
		PedidoDAO pedidoDao = new PedidoDAO();
		Pedido pedido;
		// PedidoDAO
		
		
		
		/**
		 * Confere se o Id do objeto pedido está incluso,
		 * caso sim, busca o referente,
		 * caso não, busca a ultima inserssao.
		 */
		if (id != null && id != "" && id != "0") {
			pedido = pedidoDao.buscarById(Integer.parseInt(id));
		} else {
			pedido = pedidoDao.buscarByLastId();
		}			
        numeroDoPedido = pedido.getNumero();
		valorPedido =  pedido.getValor().toString();
				
		/**
		 * Tratando o troco para impressão
		 * 
		 * Funções String
		 * 
		 */
		if (pedido.getTroco() != null) {
			troco =  pedido.getTroco().toString();
		} else {
			troco =  "";
		}		
		while (troco.length() < 6) {
			troco = troco + space;
		}

		/**
		 * Tratando o troco para impressão
		 * 
		 * Funções String
		 * 
		 */
		if (pedido.getDiferenca() != null) {
			diferenca = pedido.getDiferenca().toString();
		} else {
			diferenca = "";
		}		
		while (diferenca.length() < 6) {
			diferenca = diferenca + space;
		}
		
	    /**
		 * Tratando o entrega para impressão
		 * 
		 * Funções String
		 * 
		 */
		if (pedido.getEntrega() != null) {
			entrega = pedido.getEntrega();
		} else {
			entrega = "";
		}		
		while (entrega.length() < 5) {
			entrega = entrega + space;
		}
		
		/**
		 * Tratando o observações para impressão
		 * 
		 * Funções String
		 * 
		 */
		if (pedido.getObservacoes() != null) {
			observacoes = pedido.getObservacoes();
		} else {
			observacoes = "";
		}
		while (observacoes.length() < 53) {
			observacoes = observacoes + space;
		}
		
	    /**
		 * Tratando o pagamento para impressão
		 * 
		 * Funções String
		 * 
		 */
		if (pedido.getPagamento() != null) {
			pagamento = pedido.getPagamento();
		} else {
			pagamento = "";
		}		
		while (pagamento.length() < 8) {
			pagamento = pagamento + space;
		}
        // Pedido  
		
		
		
		/**
		 * Data
		 */
		Calendar calendario = Calendar.getInstance();
		Date data = calendario.getTime();
		
		//Formata Data e Hora
		DateFormat dtHora = DateFormat.getDateTimeInstance();
		dataPedido = dtHora.format(data);
		
		
		
		/**
		 * Cliente para impressao
		 */
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
		while (telefone.length() < 38) {
			telefone = telefone + space;
		}
        // Pedido  
        
        
        /**
		 * Tratando o nome do cliente para impressão
		 * 
		 * Funções String
		 * 
		 */
		while (nome.length() < 37) {
			nome = nome + space;
		}
        
        
		/**
		 * Tratando o endereço para impressão
		 * 
		 * Funções String
		 * 
		 */
		endereco = cliente.getEndereco();
		while (endereco.length() < 85) {
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
					+ "                                                "
			;
			
			/**
			 * 
			 * Itens de cardápios tratados para impressão
			 * 
			 * Função para tratar o titulo do cardápio
			 * 
			 */
			String tituloCardapioImpressao = "";
			String itensCardapiosImpressao = ""
					
					+ "   ***************** ITENS: *****************   ";
					for (Cardapio cardapio : listarItens) {
						tituloCardapioImpressao = cardapio.getTitulo();
						
						while(tituloCardapioImpressao.length() < 34){
							tituloCardapioImpressao = tituloCardapioImpressao + space;						
						}
						String cardapioImprimir = "   " + tituloCardapioImpressao + " R$ " + cardapio.getValor() + "   ";
						itensCardapiosImpressao = itensCardapiosImpressao + cardapioImprimir;
					}
					
			String valorImpressao = "   "
	                + "   __________________________________________   "
					+ "                                                "
					+ "   VALOR TOTAL:                R$ " + valorPedido + "          "
					+ "   FORMA DE PAGAMENTO:            " + pagamento + "      "
					+ "   TROCO PRA:                      R$ " + troco + "     "
					+ "   DIFERENCA:                      R$ " + diferenca + "        "
					+ "                                                "
			;
			
			String clienteImpressao = ""
					
					+ " CLIENTE: " + nome
					+ " ENDERECO: " + endereco 
					+ " TELEFONE: " + telefone
					+ "                                                "
					+ " ENTREGA:                       " + entrega + "           "
					+ "                                                "
					+ "                                                "
					+ " OBSERVACOES: " + observacoes
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
