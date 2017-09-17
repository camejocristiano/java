package br.com.main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMySQL
{
	public static Connection getConnection()
	{
		Connection conexao = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/interface";
			String usuario = "root";
			String senha = "";
			conexao = (Connection) DriverManager.getConnection(url, usuario, senha);
		}
		catch(SQLException e)
		{	
			System.out.println("Ocorreu um erro de SQL. Erro: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado!");
		}
		return conexao;
	}
}

