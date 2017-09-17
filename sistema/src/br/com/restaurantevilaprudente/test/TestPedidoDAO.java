package br.com.restaurantevilaprudente.test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.restaurantevilaprudente.dao.ClienteDAO;
import br.com.restaurantevilaprudente.dao.ItensDAO;
import br.com.restaurantevilaprudente.dao.PedidoDAO;
import br.com.restaurantevilaprudente.model.Cardapio;
import br.com.restaurantevilaprudente.model.Cliente;
import br.com.restaurantevilaprudente.model.Pedido;

public class TestPedidoDAO {

	public static void main(String [] args) {
		testCadastrar();
		testBuscarById(5);
		testListarTodos();
		testListarTodosLastCaixa();
		testListarTodosByCaixa(1);
		testBuscarByIdINNERJOIN(4);
		testBuscarByLastId();
		testData();
		testBuscarByJoin();
	}

	private static void testData(){
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		
		//Formata a data
		DateFormat formataData = DateFormat.getDateInstance();
		System.out.println("Data atual com formatação: "+ formataData.format(data));
				
		//Formata Hora
		DateFormat hora = DateFormat.getTimeInstance();
		System.out.println("Hora formatada: "+hora.format(data));

		//Formata Data e Hora
		DateFormat dtHora = DateFormat.getDateTimeInstance();
		System.out.println(dtHora.format(data));
		
	}
	
	private static void testCadastrar() {
				
		int [][] itens = new int[2][2];
		itens[0][0] = 2; // cardapio
		itens[0][1] = 3; // quantia
		itens[1][0] = 6; // cardapio
		itens[1][1] = 5; // quantia
		
		itens[0][0] = 7; // cardapio
		itens[0][1] = 4; // quantia
		itens[1][0] = 5; // cardapio
		itens[1][1] = 6; // quantia
        
		Pedido pedido = new Pedido();
		//pedido.setNumero("testando");
		pedido.setCliente(3);
		pedido.setValor(new BigDecimal("12.9"));
		
		Calendar c = Calendar.getInstance();
		
		pedido.setHora(c.get(Calendar.HOUR));
		pedido.setMinuto(c.get(Calendar.MINUTE));
		
		PedidoDAO pedidoDao = new PedidoDAO();
		pedidoDao.cadastrar(pedido, itens);
		
	}//cadastrar
	
	private static void testBuscarById(Integer id){
		String resultadoPedido = " \n \n \n ";
		PedidoDAO pedidoDao = new PedidoDAO();
		Pedido pedido = pedidoDao.buscarById(id);
		resultadoPedido = "Id do pedido: " + pedido.getId() + "\n" 
                + "Número do pedido: " + pedido.getNumero() + "\n" 
          		+ "Código do cliente do pedido: " + pedido.getCliente() + "\n"
          		+ "\n\n\n";
		ClienteDAO clienteDao = new ClienteDAO();		
		Cliente cliente;
		cliente = clienteDao.buscarById(pedido.getCliente());
		resultadoPedido += "Cliente do pedido: " + cliente.getNome() + "\n"
                + "Endereço do cliente: " + cliente.getEndereco() + "\n"
                + "Telefone do cliente: " + cliente.getTelefone() + "\n"
                + "\n\n\n";
		ItensDAO itensDao = new ItensDAO();
		List<Cardapio> listarItens = itensDao.buscarByIdPedido(id);
		for(Cardapio c: listarItens){
			resultadoPedido += "Id do Cardapio: " + c.getId() + "\n" 
                    + "Titulo do cardápio: " + c.getTitulo() + "\n"
                    + "Descrição do cardápio: " + c.getDescricao() + "\n"
                    + "Valor do cardápio: " + c.getValor() + "\n"
                    + "\n\n\n";
		}    
		resultadoPedido += "Valor do pedido: " + pedido.getValor() + "\n\n\n";
   		System.out.println(resultadoPedido);
	}//testBuscarById
	
	private static void testBuscarByJoin(){
		PedidoDAO pedidoDao = new PedidoDAO();
		ResultSet resultado = (ResultSet) pedidoDao.buscarByJoin();
		String saidaResultado = "";
		
			try {
				while (resultado.next()){
					saidaResultado += resultado.getString("nome") + "\n";
					saidaResultado += resultado.getString("numero") + "\n";
					saidaResultado += resultado.getString("valor") + "\n";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println(saidaResultado);
		
	}//testBuscarById INNER JOIN
	
	private static void testBuscarByIdINNERJOIN(Integer id){
		String resultadoPedido = " \n \n \n ";
		PedidoDAO pedidoDao = new PedidoDAO();
		Pedido pedido = pedidoDao.buscarById(id);
		resultadoPedido = "Id do pedido: " + pedido.getId() + "\n" 
                + "Número do pedido: " + pedido.getNumero() + "\n" 
          		+ "Código do cliente do pedido: " + pedido.getCliente() + "\n"
          		+ "\n\n\n";
		ClienteDAO clienteDao = new ClienteDAO();		
		Cliente cliente;
		cliente = clienteDao.buscarById(pedido.getCliente());
		resultadoPedido += "Cliente do pedido: " + cliente.getNome() + "\n"
                + "Endereço do cliente: " + cliente.getEndereco() + "\n"
                + "Telefone do cliente: " + cliente.getTelefone() + "\n"
                + "\n\n\n";
		ItensDAO itensDao = new ItensDAO();
		List<Cardapio> listarItens = itensDao.buscarByIdPedido(id);
		for(Cardapio c: listarItens){
			resultadoPedido += "Id do Cardapio: " + c.getId() + "\n" 
                    + "Titulo do cardápio: " + c.getTitulo() + "\n"
                    + "Descrição do cardápio: " + c.getDescricao() + "\n"
                    + "Valor do cardápio: " + c.getValor() + "\n"
                    + "\n\n\n";
		}    
		resultadoPedido += "Valor do pedido: " + pedido.getValor() + "\n\n\n";
   		System.out.println(resultadoPedido);
	}//testBuscarById INNER JOIN
	
	private static void testListarTodos(){
		PedidoDAO pedidoDao = new PedidoDAO();
		List<Pedido> listarResultadosPedidos = pedidoDao.listarTodos();
		ClienteDAO clienteDao = new ClienteDAO();		
		Cliente cliente;
				
		for(Pedido p: listarResultadosPedidos){
			cliente = clienteDao.buscarById(p.getCliente());
			System.out.println("Id do pedido: " + p.getId() + "\n" 
	                         + "Número do pedido: " + p.getNumero() + "\n" 
                       		 + "Código do cliente do pedido: " + p.getCliente() + "\n"
	                         + "Cliente do pedido: " + cliente.getNome() + "\n"
	                         + "Endereço do cliente: " + cliente.getEndereco() + "\n"
	                         + "Telefone do cliente: " + cliente.getTelefone() + "\n"
	                         + "Valor do pedido: " + p.getValor() + "\n" + "\n" + "\n"
            );
		}//for
	}//testeListarTodos()
	
	private static void testListarTodosLastCaixa(){
		PedidoDAO pedidoDao = new PedidoDAO();
		List<Pedido> listarResultadosPedidos = pedidoDao.listarTodosLastCaixa();
		ClienteDAO clienteDao = new ClienteDAO();		
		Cliente cliente;
				
		for(Pedido p: listarResultadosPedidos){
			cliente = clienteDao.buscarById(p.getCliente());
			System.out.println("Id do pedido: " + p.getId() + "\n" 
	                         + "Número do pedido: " + p.getNumero() + "\n" 
                       		 + "Código do cliente do pedido: " + p.getCliente() + "\n"
	                         + "Cliente do pedido: " + cliente.getNome() + "\n"
	                         + "Endereço do cliente: " + cliente.getEndereco() + "\n"
	                         + "Telefone do cliente: " + cliente.getTelefone() + "\n"
	                         + "Valor do pedido: " + p.getValor() + "\n" + "\n" + "\n"
            );
		}//for
	}//testeListarTodos()
	
	private static void testListarTodosByCaixa(Integer id){
		PedidoDAO pedidoDao = new PedidoDAO();
		List<Pedido> listarResultadosPedidos = pedidoDao.listarTodosByCaixa(id);
		ClienteDAO clienteDao = new ClienteDAO();		
		Cliente cliente;
				
		for(Pedido p: listarResultadosPedidos){
			cliente = clienteDao.buscarById(p.getCliente());
			System.out.println("Id do pedido: " + p.getId() + "\n" 
	                         + "Número do pedido: " + p.getNumero() + "\n" 
                       		 + "Código do cliente do pedido: " + p.getCliente() + "\n"
	                         + "Cliente do pedido: " + cliente.getNome() + "\n"
	                         + "Endereço do cliente: " + cliente.getEndereco() + "\n"
	                         + "Telefone do cliente: " + cliente.getTelefone() + "\n"
	                         + "Valor do pedido: " + p.getValor() + "\n" + "\n" + "\n"
            );
		}//for
	}//testeListarTodos()
	
	private static void testBuscarByLastId(){
		PedidoDAO pedidoDao = new PedidoDAO();
		Pedido pedido = pedidoDao.buscarByLastId();
		System.out.println("Pedido: " + pedido.getId() + "\n"
							+ "Valor = " + pedido.getValor());
	}//testBuscarByLastId()
		
}
