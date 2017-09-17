package br.com.main.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.main.model.Usuario;
import br.com.main.util.ConectaMySQL;

public class UsuarioDAOImpl implements UsuarioDAO {

	private Connection conn = ConectaMySQL.getConnection();

	public void addUsuario(Usuario usuario){
		String sql ="INSERT INTO usuario(nome) VALUES (?)";
		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.execute();
			preparador.close();
			System.out.println("Usuario cadastrado com sucesso!");
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao cadastrar usuario. Mensagem: " + e.getMessage());
		}
	}

	@Override
	public Usuario getUsuarioById(int id) {
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
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar o usuario. Mensagem: " + e.getMessage());
		}

		return usuario;
	}
	
	@Override
	public void updateUsuario(Usuario usuario) {
		String sql ="UPDATE usuario SET nome = ? WHERE id = ?";

		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setLong(2, usuario.getId());

			preparador.execute();
			preparador.close();
			System.out.println("Cadastro alterado com sucesso!");
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao alterar usuario. Mensagem: " + e.getMessage());
		}

	}

	@Override
	public List<Usuario> listUsuarios() {
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
					listaUsuarios.add(usuario);
				}
			}
			catch(SQLException e)
			{
				System.out.println("Erro ao listar os usuarios. Mensagem: " + e.getMessage());
			}
			return listaUsuarios;
	}

	@Override
	public void removeUsuario(int id) {
		String sql ="DELETE FROM usuario WHERE id=?";
		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			preparador.execute();
			preparador.close();
			System.out.println("Usuário excluido com sucesso!");
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao excluir usuario. Mensagem: " + e.getMessage());
		}
	}
	
}
