package br.com.restaurantevilaprudente.test;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.google.gson.Gson;

import br.com.restaurantevilaprudente.model.Cardapio;
import br.com.restaurantevilaprudente.dao.CardapioDAO;

public class TestCardapioDAO {

	public static void main(String [] args) {
		testCadastrar();
		testAlterar();
		testExcluir();
		testListarTodos();
		testBuscarById();
		testBuscarByTitulo();
		testBuscarByTituloAproximado();
	}

	private static void testCadastrar() {
		Cardapio cardapio = new Cardapio();
		cardapio.setTitulo("teste 1");
		cardapio.setDescricao("faça");
		cardapio.setValor(new BigDecimal("12.9"));
		cardapio.setData(Calendar.getInstance());
		
		CardapioDAO cardapioDao = new CardapioDAO();
		cardapioDao.cadastrar(cardapio);
	}//cadastrar
	
	private static void testBuscarById(){
		CardapioDAO cardapioDao = new CardapioDAO();
		Cardapio cardapio = cardapioDao.buscarById(2);
		
		System.out.println("Título: " + cardapio.getTitulo());
	}//testBuscarById
	
	private static void testBuscarByTitulo(){
		CardapioDAO cardapioDao = new CardapioDAO();
		Cardapio cardapio = cardapioDao.buscarByTitulo("teste 1");
		
		System.out.println("Descrição: " + cardapio.getDescricao());
	}//testBuscarByTitulo
	
		
	private static void testBuscarByTituloAproximado(){
		CardapioDAO cardapioDao = new CardapioDAO();
		List<Cardapio> listarResultados = cardapioDao.buscarByTituloAproximado("tes");
		
		for(Cardapio c: listarResultados){
			System.out.println(c.getId() + " " + c.getTitulo() + " " + c.getDescricao() + " " + c.getValor());
		}//for
	}//testBuscarByNomeAproximado()
	
	private static void testListarTodos(){
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
		    
		  System.out.println(json); 
	}//testeListarTodos()
	
	private static void testAlterar() {
		Cardapio cardapio = new Cardapio();
		cardapio.setId(2);
		cardapio.setTitulo("Xis Salada");
		cardapio.setDescricao("Lanche");
		cardapio.setValor(new BigDecimal("12.9"));
		
		CardapioDAO cardapioDao = new CardapioDAO();
		cardapioDao.alterar(cardapio);
	}//alterar	
	
	private static void testExcluir() {
		Cardapio cardapio = new Cardapio();
		cardapio.setId(2);
		
		CardapioDAO cardapioDao = new CardapioDAO();
		cardapioDao.excluir(cardapio);
	}//excluir
	
	
}
