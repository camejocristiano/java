package br.com.restaurantevilaprudente.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.restaurantevilaprudente.model.Caixa;
import br.com.restaurantevilaprudente.model.Pedido;

public class CaixaDAO {

	private Connection conn = ConectaMySQL.getConnection();

	//Método para cadastrar os caixa
	public void cadastrar(Caixa caixa) 
	{
		String sql ="INSERT INTO caixa(usuario, valor, pedidos, dia, mes, ano, situacao) VALUES (?,?,?,?,?,?,?)";

		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, caixa.getUsuario());
			BigDecimal valor = new BigDecimal("0.00");
			preparador.setBigDecimal(2, valor);
			preparador.setInt(3, 0);
			preparador.setInt(4, caixa.getDia());
			preparador.setInt(5, caixa.getMes());
			preparador.setInt(6, caixa.getAno());
			preparador.setString(7, "ABERTO");
			
			preparador.execute();
			preparador.close();

			System.out.println("Caixa iniciado com sucesso!");

		}
		catch(SQLException e)
		{
			System.out.println("Erro ao incluir caixa. Mensagem: " + e.getMessage());
		}

	}//cadastrar


	
	//Método para cadastrar os caixa
	public void somarCaixa() 
	{
		PedidoDAO pedidoDao = new PedidoDAO();
		Pedido pedido = pedidoDao.buscarByLastId();
		BigDecimal valorPedido = pedido.getValor();
		
		CaixaDAO caixaDao = new CaixaDAO();
		Caixa caixa = caixaDao.buscarByLastId();
		BigDecimal valorCaixa = caixa.getValor();
		
		BigDecimal valorTotal = valorCaixa.add( valorPedido );  
		
		Integer pedidos = caixa.getPedidos() + 1;
		
		String sql ="UPDATE caixa SET valor=?, pedidos=? WHERE id = ?";
		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setBigDecimal(1, valorTotal);
			preparador.setInt(2, pedidos);
			preparador.setInt(3, caixa.getId());

			preparador.execute();
			preparador.close();
			
			System.out.println("Caixa atualizado com sucesso!");
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao atualizar o caixa. Mensagem: " + e.getMessage());
		}
		
	}//Somar valor, pedido e itens do caixa



	//Método para buscar caixa por id
	public Caixa buscarById(Integer id)
	{
		String sql = "SELECT * FROM caixa WHERE id = ?";
		Caixa caixa = null;	
		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();

			if(resultado.next()){
				caixa = new Caixa();
				caixa.setId(resultado.getInt("id"));
				caixa.setUsuario(resultado.getInt("usuario"));
				caixa.setDia(resultado.getInt("dia"));
				caixa.setMes(resultado.getInt("mes"));
				caixa.setAno(resultado.getInt("ano"));
				caixa.setValor(resultado.getBigDecimal("valor"));
				caixa.setPedidos(resultado.getInt("pedidos"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os cardapios. Mensagem: " + e.getMessage());
		}

		return caixa;

	}//buscarById	
	
	
	
	// Método para buscar caixa por ultimo id
	public Caixa buscarByLastId() {
		String sql = "SELECT * FROM caixa WHERE id = (SELECT MAX(id) FROM caixa)";
		Caixa caixa = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				caixa = new Caixa();
				caixa.setId(resultado.getInt("id"));
				caixa.setUsuario(resultado.getInt("usuario"));
				caixa.setDia(resultado.getInt("dia"));
				caixa.setMes(resultado.getInt("mes"));
				caixa.setAno(resultado.getInt("ano"));
				caixa.setValor(resultado.getBigDecimal("valor"));
				caixa.setPedidos(resultado.getInt("pedidos"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar os cardapios. Mensagem: " + e.getMessage());
		}
		return caixa;
	}// buscarByLastId



	//Método para listar os produtos
	public List<Caixa> listarTodos()
	{
		String sql = "SELECT * FROM caixa ORDER BY id DESC";
		List<Caixa> listaCaixas = new ArrayList<Caixa>();

		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				Caixa caixa = new Caixa();

				caixa = new Caixa();
				caixa.setId(resultado.getInt("id"));
				caixa.setUsuario(resultado.getInt("usuario"));
				caixa.setValor(resultado.getBigDecimal("valor"));
				caixa.setDia(resultado.getInt("dia"));
				caixa.setMes(resultado.getInt("mes"));
				caixa.setAno(resultado.getInt("ano"));
				caixa.setPedidos(resultado.getInt("pedidos"));

		        listaCaixas.add(caixa);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os cardapios. Mensagem: " + e.getMessage());
		}

		return listaCaixas;

	}//listarTodos

	public void salvar(Caixa caixa)
	{
		if (caixa.getId() != null && caixa.getId() != 0) {
			//alterar(caixa);
		} else {
			cadastrar(caixa);
		}
	}//salvar
	
}
