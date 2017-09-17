package br.com.restaurantevilaprudente.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.restaurantevilaprudente.model.Usuario;

public class UsuarioDAO {

	private Connection conn = ConectaMySQL.getConnection();

	//Método para cadastrar os usuarios
	public void cadastrar(Usuario usuario) 
	{
		String sql ="INSERT INTO usuario(nome, email, senha) VALUES (?,?,?)";

		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getEmail());
			preparador.setString(3, usuario.getSenha());

			preparador.execute();
			preparador.close();

			System.out.println("Usuario cadastrado com sucesso!");

		}
		catch(SQLException e)
		{
			System.out.println("Erro ao cadastrar usuario. Mensagem: " + e.getMessage());
		}

	}//cadastrar





	//Método para buscar usuario por id
	public Usuario buscarById(Integer id)
	{
		String sql = "SELECT * FROM usuario WHERE id = ?";
		Usuario usuario = null;	
		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();

			if(resultado.next()){
				usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setSenha(resultado.getString("senha"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os usuarios. Mensagem: " + e.getMessage());
		}

		return usuario;

	}//buscarById	








	//Método para buscar produto por nome
	public Usuario buscarByNome(String nome)
	{
		String sql = "SELECT * FROM usuario WHERE nome = ?";
		Usuario cliente = null;	
		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, nome);

			ResultSet resultado = preparador.executeQuery();

			if(resultado.next()){
				cliente = new Usuario();
				cliente.setId(resultado.getInt("id"));
				cliente.setNome(resultado.getString("nome"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os clientes. Mensagem: " + e.getMessage());
		}

		return cliente;

	}//buscarByNome	






	
	//Método para buscar produto por nome
		public Usuario buscarByEmail(String email)
		{
			String sql = "SELECT * FROM usuario WHERE email = ?";
			Usuario usuario = null;	
			try
			{
				PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
				preparador.setString(1, email);

				ResultSet resultado = preparador.executeQuery();

				if(resultado.next()){
					usuario = new Usuario();
					usuario.setId(resultado.getInt("id"));
					usuario.setNome(resultado.getString("nome"));
					usuario.setEmail(email);
				}
			}
			catch(SQLException e)
			{
				System.out.println("Erro ao listar o usuario. Mensagem: " + e.getMessage());
			}

			return usuario;

		}//buscarByNome	







	//Método para buscar produto por nome
	public List<Usuario> buscarByNomeAproximado(String nome)
	{
		String sql = "SELECT * FROM cliente WHERE nome like ?";
		List<Usuario> lista = new ArrayList<Usuario>();	
		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, "%" + nome + "%");

			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				Usuario cliente = new Usuario();
				cliente.setId(resultado.getInt("id"));
				cliente.setNome(resultado.getString("nome"));

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
	public List<Usuario> listarTodos()
	{
		String sql = "SELECT * FROM usuario";
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				Usuario usuario = new Usuario();

				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setSenha(resultado.getString("senha"));

				listaUsuarios.add(usuario);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os usuarios. Mensagem: " + e.getMessage());
		}

		return listaUsuarios;

	}//listarTodos








	public void salvar(Usuario usuario)
	{
		if (usuario.getId() != null && usuario.getId() != 0) {
			alterar(usuario);
		} else {
			cadastrar(usuario);
		}
	}//salvar








	//Método para alterar os produtos
	public void alterar(Usuario usuario) 
	{
		String sql ="UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";

		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getEmail());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());

			preparador.execute();
			preparador.close();
			System.out.println("Cadastro alterado com sucesso!");
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao alterar usuario. Mensagem: " + e.getMessage());
		}

	}//alterar








	//Método para excluir os produtos
	public void excluir(Usuario usuario) 
	{
		String sql ="DELETE FROM usuario WHERE id=?";

		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, usuario.getId());

			preparador.execute();
			preparador.close();
			System.out.println("Cadastro excluido com sucesso!");
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao excluir usuario. Mensagem: " + e.getMessage());
		}

	}//excluir

	public boolean autenticar(Usuario usuario) {

		String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, usuario.getEmail());
			preparador.setString(2, usuario.getSenha());

			ResultSet resultado = preparador.executeQuery();
			return resultado.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}// autenticar

}
