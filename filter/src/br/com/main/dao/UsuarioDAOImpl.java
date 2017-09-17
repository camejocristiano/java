package br.com.main.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import br.com.main.model.Usuario;
import br.com.main.util.ConectaMySQL;

public class UsuarioDAOImpl implements UsuarioDAO {

	private Connection conn = ConectaMySQL.getConnection();

	@Override
	public boolean autenticarUsuario(Usuario usuario) {

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
