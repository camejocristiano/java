package br.com.restaurantevilaprudente.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mysql.jdbc.PreparedStatement;

import br.com.restaurantevilaprudente.model.Cardapio;

public class CardapioDAO {

	private Connection conn = ConectaMySQL.getConnection();

	//Método para cadastrar os produtos
	public void cadastrar(Cardapio cardapio) 
	{
		String sql ="INSERT INTO cardapio(titulo, descricao, valor) VALUES (?,?,?)";

		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, cardapio.getTitulo());
			preparador.setString(2, cardapio.getDescricao());
			preparador.setBigDecimal(3, cardapio.getValor());

			preparador.execute();
			preparador.close();

			System.out.println("Cardapio cadastrado com sucesso!");

		}
		catch(SQLException e)
		{
			System.out.println("Erro ao incluir cardapio. Mensagem: " + e.getMessage());
		}

	}//cadastrar





	//Método para buscar cardapio por id
	public Cardapio buscarById(Integer id)
	{
		String sql = "SELECT * FROM cardapio WHERE id = ?";
		Cardapio cardapio = null;	
		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();

			if(resultado.next()){
				cardapio = new Cardapio();
				cardapio.setId(resultado.getInt("id"));
				cardapio.setTitulo(resultado.getString("titulo"));
				cardapio.setDescricao(resultado.getString("descricao"));
				cardapio.setValor(resultado.getBigDecimal("valor"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os cardapios. Mensagem: " + e.getMessage());
		}

		return cardapio;

	}//buscarById	








	//Método para buscar produto por nome
	public Cardapio buscarByTitulo(String titulo)
	{
		String sql = "SELECT * FROM cardapio WHERE titulo = ?";
		Cardapio cardapio = null;	
		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, titulo);

			ResultSet resultado = preparador.executeQuery();

			if(resultado.next()){
				cardapio = new Cardapio();
				cardapio.setId(resultado.getInt("id"));
				cardapio.setTitulo(resultado.getString("titulo"));
				cardapio.setDescricao(resultado.getString("descricao"));
				cardapio.setValor(resultado.getBigDecimal("valor"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os clientes. Mensagem: " + e.getMessage());
		}

		return cardapio;

	}//buscarByTitulo	













	//Método para buscar cardapio por nome
	public List<Cardapio> buscarByTituloAproximado(String titulo)
	{
		String sql = "SELECT * FROM cardapio WHERE titulo like ?";
		List<Cardapio> lista = new ArrayList<Cardapio>();	
		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, "%" + titulo + "%");

			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				Cardapio cardapio = new Cardapio();
				cardapio.setId(resultado.getInt("id"));
				cardapio.setTitulo(resultado.getString("titulo"));
				cardapio.setDescricao(resultado.getString("descricao"));
				cardapio.setValor(resultado.getBigDecimal("valor"));

				lista.add(cardapio);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os cardapios. Mensagem: " + e.getMessage());
		}

		return lista;

	}//buscarByNomeAproximado	








	//Método para listar os produtos
	public List<Cardapio> listarTodos()
	{
		String sql = "SELECT * FROM cardapio ORDER BY titulo";
		List<Cardapio> lista = new ArrayList<Cardapio>();

		try
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				Cardapio cardapio = new Cardapio();

				cardapio.setId(resultado.getInt("id"));
				cardapio.setTitulo(resultado.getString("titulo"));
				cardapio.setDescricao(resultado.getString("descricao"));
				cardapio.setValor(resultado.getBigDecimal("valor"));

				lista.add(cardapio);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao listar os cardapios. Mensagem: " + e.getMessage());
		}

		return lista;

	}//listarTodos








	public void salvar(Cardapio cardapio)
	{
		if (cardapio.getId() != null && cardapio.getId() != 0) {
			alterar(cardapio);
		} else {
			cadastrar(cardapio);
		}
	}//salvar








	//Método para alterar os produtos
	public void alterar(Cardapio cardapio) 
	{
		String sql ="UPDATE cardapio SET titulo = ?, descricao = ?, valor = ? WHERE id = ?";

		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, cardapio.getTitulo());
			preparador.setString(2, cardapio.getDescricao());
			preparador.setBigDecimal(3, cardapio.getValor());
			preparador.setInt(4, cardapio.getId());

			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastro alterado com sucesso!");
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao alterar cardapio. Mensagem: " + e.getMessage());
		}
		
	}//alterar








	//Método para excluir os produtos
	public void excluir(Cardapio cardapio) 
	{
		String sql ="DELETE FROM cardapio WHERE id=?";

		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, cardapio.getId());

			preparador.execute();
			preparador.close();
		
			System.out.println("Cadastro excluido com sucesso!");
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao excluir cardapio. Mensagem: " + e.getMessage());
		}

	}//excluir
	
	public void gerarJeson(){
		CardapioDAO cardapioDao = new CardapioDAO();
		List<Cardapio> listarResultados = cardapioDao.listarTodos();
		int contador = 1;
		String json ="[";
		try { 
		for(Cardapio c: listarResultados){
			//System.out.println(c.getId() + " " + c.getTitulo() + " " +  c.getDescricao() + " " + c.getValor());
			Gson gson = new Gson();  
			 if(contador == 1){
			  json = json + gson.toJson(c); 
			  contador++;
			 } else {
			  json = json + ", " + gson.toJson(c);  
			 }
		}//for
		  json = json + "]";
		  //write converted json data to a file named "CountryGSON.json"  
		   FileWriter writer = new FileWriter("cardapios.json");  
		   writer.write(json);  
		   writer.close();  
		    
		  } catch (IOException e) {  
		   e.printStackTrace();  
		  }  
	   
	}

}
