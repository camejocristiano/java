package br.com.restaurantevilaprudente.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.restaurantevilaprudente.model.Cliente;

public class ClienteDAO {

	private Connection conn = ConectaMySQL.getConnection();

	//Método para cadastrar os produtos
	public void cadastrar(Cliente cliente) 
	{
		String sql ="INSERT INTO cliente(nome, endereco, telefone, empresa, telefoneempresa, observacoes) VALUES (?,?,?,?,?,?)";

		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, cliente.getNome());
			preparador.setString(2, cliente.getEndereco());
			preparador.setInt(3, cliente.getTelefone());
			preparador.setString(4, cliente.getEmpresa());
			preparador.setInt(5, cliente.getTelefoneempresa());
			preparador.setString(6, cliente.getObservacoes());

			preparador.execute();
			preparador.close();

			System.out.println("Cliente cadastrado com sucesso!");

		}
		catch(SQLException e)
		{
			System.out.println("Erro ao incluir produto. Mensagem: " + e.getMessage());
		}

	}//cadastrar





	//Método para buscar produto por id
	public Cliente buscarById(Integer id)
	{
		String sql = "SELECT * FROM cliente WHERE id = ?";
		Cliente cliente = null;	
		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();

			if(resultado.next()){
				cliente = new Cliente();
				cliente.setId(resultado.getInt("id"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setEndereco(resultado.getString("endereco"));
				cliente.setTelefone(resultado.getInt("telefone"));
				cliente.setEmpresa(resultado.getString("empresa"));
				cliente.setTelefoneempresa(resultado.getInt("telefoneempresa"));
				cliente.setObservacoes(resultado.getString("observacoes"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os clientes. Mensagem: " + e.getMessage());
		}

		return cliente;

	}//buscarById	








	//Método para buscar produto por nome
	public Cliente buscarByNome(String nome)
	{
		String sql = "SELECT * FROM cliente WHERE nome = ?";
		Cliente cliente = null;	
		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, nome);

			ResultSet resultado = preparador.executeQuery();

			if(resultado.next()){
				cliente = new Cliente();
				cliente.setId(resultado.getInt("id"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setEndereco(resultado.getString("endereco"));
				cliente.setTelefone(resultado.getInt("telefone"));
				cliente.setEmpresa(resultado.getString("empresa"));
				cliente.setTelefoneempresa(resultado.getInt("telefoneempresa"));
				cliente.setObservacoes(resultado.getString("observacoes"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os clientes. Mensagem: " + e.getMessage());
		}

		return cliente;

	}//buscarByNome	













	//Método para buscar produto por nome
	public List<Cliente> buscarByNomeAproximado(String nome)
	{
		String sql = "SELECT * FROM cliente WHERE nome like ?";
		List<Cliente> lista = new ArrayList<Cliente>();	
		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, "%" + nome + "%");

			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				Cliente cliente = new Cliente();
				cliente.setId(resultado.getInt("id"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setEndereco(resultado.getString("endereco"));
				cliente.setTelefone(resultado.getInt("telefone"));

				lista.add(cliente);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os produto. Mensagem: " + e.getMessage());
		}

		return lista;

	}//buscarByNomeAproximado	








	//Método para listar os produtos
	public List<Cliente> listarTodos()
	{
		String sql = "SELECT * FROM cliente ORDER BY nome ASC";
		List<Cliente> lista = new ArrayList<Cliente>();

		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				Cliente cliente = new Cliente();

				cliente.setId(resultado.getInt("id"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setEndereco(resultado.getString("endereco"));
				cliente.setTelefone(resultado.getInt("telefone"));

				lista.add(cliente);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os clientes. Mensagem: " + e.getMessage());
		}

		return lista;

	}//listarTodos








	public void salvar(Cliente cliente)
	{
		if (cliente.getId() != null && cliente.getId() != 0) {
			alterar(cliente);
		} else {
			cadastrar(cliente);
		}
	}//salvar








	//Método para alterar os produtos
	public void alterar(Cliente cliente) 
	{
		String sql ="UPDATE cliente SET nome = ?, endereco = ?, telefone = ? WHERE id = ?";

		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, cliente.getNome());
			preparador.setString(2, cliente.getEndereco());
			preparador.setInt(3, cliente.getTelefone());
			preparador.setInt(4, cliente.getId());

			preparador.execute();
			preparador.close();
			System.out.println("Cadastro alterado com sucesso!");
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao alterar cliente. Mensagem: " + e.getMessage());
		}

	}//alterar








	//Método para excluir os produtos
	public void excluir(Cliente cliente) 
	{
		String sql ="DELETE FROM cliente WHERE id=?";

		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, cliente.getId());

			preparador.execute();
			preparador.close();
			System.out.println("Cadastro excluido com sucesso!");
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao excluir produto. Mensagem: " + e.getMessage());
		}

	}//excluir


}
