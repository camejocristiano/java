package br.com.restaurantevilaprudente.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.restaurantevilaprudente.model.Caixa;
import br.com.restaurantevilaprudente.model.Cliente;
import br.com.restaurantevilaprudente.model.Pedido;

public class PedidoDAO {

	private Connection conn = ConectaMySQL.getConnection();

	// Método para cadastrar pedido
	public void cadastrar(Pedido pedido, int[][] itens) {
		String sqlPedido = "INSERT INTO pedido(numero, cliente, valor, caixa, hora, minuto, pagamento, troco, entrega, observacoes, diferenca) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		String sqlItens = "INSERT INTO itens(pedido_id, cardapio_id, quantidade) VALUES (?, ?, ?)";
		
		/**
		 * Pega o ultimo pedido e soma um ao numero e insere
		 */
		CaixaDAO caixaDao = new CaixaDAO();
		Caixa caixa = caixaDao.buscarByLastId();
		int numeroPedidoNovo = caixa.getPedidos();
		numeroPedidoNovo = numeroPedidoNovo + 1;
		
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sqlPedido);
			preparador.setInt(1, numeroPedidoNovo);
			preparador.setInt(2, pedido.getCliente());
			preparador.setBigDecimal(3, pedido.getValor());
			preparador.setInt(4, caixa.getId());
			preparador.setInt(5, pedido.getHora());
			preparador.setInt(6, pedido.getMinuto());
			preparador.setString(7, pedido.getPagamento());
			preparador.setBigDecimal(8, pedido.getTroco());
			preparador.setString(9, pedido.getEntrega());
			preparador.setString(10, pedido.getObservacoes());
			preparador.setBigDecimal(11, pedido.getDiferenca());
			preparador.execute();
			preparador.close();

			Pedido pedido_id = buscarByLastId();

			for (int cardapio = 0; cardapio < itens.length; cardapio++) {
				PreparedStatement preparadorItens = (PreparedStatement) conn.prepareStatement(sqlItens);
				preparadorItens.setInt(1, pedido_id.getId());
				preparadorItens.setInt(2, itens[cardapio][0]);
				preparadorItens.setInt(3, itens[cardapio][1]);
				preparadorItens.execute();
				preparadorItens.close();
			}
			System.out.println("Pedido cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao incluir pedido. Mensagem: " + e.getMessage());
		}
		caixaDao.somarCaixa();
	}// cadastrar

	// Método para buscar cardapio por id
	public Pedido buscarByLastId() {
		String sql = "SELECT * FROM pedido WHERE id = (SELECT MAX(id) FROM pedido)";
		Pedido pedido = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				pedido = new Pedido();
				pedido.setId(resultado.getInt("id"));
				pedido.setNumero(resultado.getString("numero"));
				pedido.setCliente(resultado.getInt("cliente"));
				pedido.setValor(resultado.getBigDecimal("valor"));
				pedido.setTroco(resultado.getBigDecimal("troco"));
				pedido.setPagamento(resultado.getString("pagamento"));
				pedido.setEntrega(resultado.getString("entrega"));
				pedido.setObservacoes(resultado.getString("observacoes"));
				pedido.setDiferenca(resultado.getBigDecimal("diferenca"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar os cardapios. Mensagem: " + e.getMessage());
		}
		return pedido;
	}// buscarById

	public void salvar(Pedido pedido, int[][] itens) {
		cadastrar(pedido, itens);
	}// salvar

	// Método para alterar os produtos
	public void alterar(Pedido cliente) {

	}// alterar

	/**
	 * 
	 * @param id
	 * @return
	 * 
	 * 		Método para buscar pedido por id
	 */
	public Pedido buscarById(Integer id) {
		String sql = "SELECT * FROM pedido WHERE id = ?";
		Pedido pedido = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				pedido = new Pedido();
				pedido.setId(resultado.getInt("id"));
				pedido.setNumero(resultado.getString("numero"));
				pedido.setCliente(resultado.getInt("cliente"));
				pedido.setValor(resultado.getBigDecimal("valor"));
				pedido.setTroco(resultado.getBigDecimal("troco"));
				pedido.setPagamento(resultado.getString("pagamento"));
				pedido.setEntrega(resultado.getString("entrega"));
				pedido.setObservacoes(resultado.getString("observacoes"));
				pedido.setValor(resultado.getBigDecimal("valor"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar o pedido. Mensagem: " + e.getMessage());
		}
		return pedido;
	}// buscarById
	
	/**
	 * 
	 * @param id
	 * @return
	 * 
	 * 		Método para buscar pedido por id  INNER JOIN cliente e itens
	 */
	public List<Pedido> buscarByJoin() {
		String sql = "SELECT pedido.id, pedido.numero, pedido.valor, cliente.nome FROM pedido JOIN cliente ON cliente.id = pedido.cliente";
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
			    /**
			     * Novo Pedido
			     */
				Pedido pedido = new Pedido();
				pedido.setId(resultado.getInt("id"));
				pedido.setNumero(resultado.getString("numero"));
				pedido.setCliente(resultado.getInt("cliente"));
				Cliente cliente = new Cliente();
				cliente.setNome(resultado.getString("nome"));
				pedido.setValor(resultado.getBigDecimal("valor"));
				listaPedidos.add(pedido);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar os pedidos. Mensagem: " + e.getMessage());
		}
		return listaPedidos;
	}// buscarById INNER JOIN
	
	// Método para listar os produtos
	public List<Pedido> listarTodos() {
		String sql = "select * from pedido ORDER BY id DESC";
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
			    /**
			     * Novo Pedido
			     */
				Pedido pedido = new Pedido();
				pedido.setId(resultado.getInt("id"));
				pedido.setNumero(resultado.getString("numero"));
				pedido.setCliente(resultado.getInt("cliente"));
				pedido.setValor(resultado.getBigDecimal("valor"));
				listaPedidos.add(pedido);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar os pedidos. Mensagem: " + e.getMessage());
		}
		return listaPedidos;
	}// listarTodos
	
	// Método para listar os produtos
		public List<Pedido> listarTodosLastCaixa() {
			/**
			 * 
			 * Busca o último caixa aberto
			 * 
			 * 		Método para buscar o ultimo caixa aberto
			 */
			CaixaDAO caixaDao = new CaixaDAO();
			Caixa caixa = caixaDao.buscarByLastId();
			
			String sql = "select * from pedido as p WHERE p.caixa="+caixa.getId()+" ORDER BY id DESC";
			List<Pedido> listaPedidos = new ArrayList<Pedido>();
			
			try {
				PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet resultado = preparador.executeQuery();
				while (resultado.next()) {
				    /**
				     * Novo Pedido
				     */
					Pedido pedido = new Pedido();
					pedido.setId(resultado.getInt("id"));
					pedido.setNumero(resultado.getString("numero"));
					pedido.setCliente(resultado.getInt("cliente"));
					pedido.setValor(resultado.getBigDecimal("valor"));
					listaPedidos.add(pedido);
				}
			} catch (SQLException e) {
				System.out.println("Erro ao listar os pedidos. Mensagem: " + e.getMessage());
			}
			return listaPedidos;
		}// listarTodos
		
		
		// Método para listar os produtos
				public List<Pedido> listarTodosByCaixa(Integer id) {
					/**
					 * 
					 * Busca o último caixa aberto
					 * 
					 * 		Método para buscar o ultimo caixa aberto
					 */
					
					String sql = "select * from pedido as p WHERE p.caixa="+id+" ORDER BY id DESC";
					List<Pedido> listaPedidos = new ArrayList<Pedido>();
					
					try {
						PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
						ResultSet resultado = preparador.executeQuery();
						while (resultado.next()) {
						    /**
						     * Novo Pedido
						     */
							Pedido pedido = new Pedido();
							pedido.setId(resultado.getInt("id"));
							pedido.setNumero(resultado.getString("numero"));
							pedido.setCliente(resultado.getInt("cliente"));
							pedido.setValor(resultado.getBigDecimal("valor"));
							listaPedidos.add(pedido);
						}
					} catch (SQLException e) {
						System.out.println("Erro ao listar os pedidos. Mensagem: " + e.getMessage());
					}
					return listaPedidos;
				}// listarTodos


	// Método para excluir os produtos
	public void excluir(Pedido pedido) {

	}// excluir

}
