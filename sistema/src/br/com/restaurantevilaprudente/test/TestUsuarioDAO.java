package br.com.restaurantevilaprudente.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.com.restaurantevilaprudente.dao.UsuarioDAO;
import br.com.restaurantevilaprudente.model.Usuario;

public class TestUsuarioDAO {

	public static void main(String [] args) {
		testCadastrar();
		testAlterar();
		testExcluir();
		testListarTodos();
		testBuscarById();
		testBuscarByNome();
		testBuscarByNomeAproximado();
		testAutenticar();
	}

	private static void testCadastrar() {
		Usuario usuario = new Usuario();
		usuario.setNome("Restaurante Vila Prudente");
		usuario.setEmail("restaurantevilaprudente@gmail.com");
		usuario.setSenha("");
		
		UsuarioDAO usuarioDao = new UsuarioDAO();
		usuarioDao.cadastrar(usuario);
	}//cadastrar
	
	private static void testBuscarById(){
		UsuarioDAO clienteDao = new UsuarioDAO();
		Usuario cliente = clienteDao.buscarById(1);
		
		//System.out.println("Nome: " + cliente.getNome());
		 Gson gson = new Gson();  
		    
		  // convert java object to JSON format,  
		  // and returned as JSON formatted string  
		  String json = gson.toJson(cliente);  
		    
		  try {  
		   //write converted json data to a file named "CountryGSON.json"  
		   FileWriter writer = new FileWriter("ClienteGSON.json");  
		   writer.write(json);  
		   writer.close();  
		    
		  } catch (IOException e) {  
		   e.printStackTrace();  
		  }  
		    
		  System.out.println(json);  
		    

	}//testBuscarById
	
	private static void testBuscarByNome(){
		//UsuarioDAO usuarioDao = new UsuarioDAO();
		
		System.out.println("Endereço: ");
	}//testBuscarByNome
	
		
	private static void testBuscarByNomeAproximado(){
		UsuarioDAO clienteDao = new UsuarioDAO();
		List<Usuario> listarResultados = clienteDao.buscarByNomeAproximado("cor");
		
		for(Usuario c: listarResultados){
			System.out.println(c.getId() + " " + c.getNome());
		}//for
	}//testBuscarByNomeAproximado()
	
	private static void testListarTodos(){
		UsuarioDAO clienteDao = new UsuarioDAO();
		List<Usuario> listarResultados = clienteDao.listarTodos();
		int contador = 1;
		String json ="[";
		try { 
			
		for(Usuario c: listarResultados){
			//System.out.println("Nome: " + cliente.getNome());
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
			   FileWriter writer = new FileWriter("clientes.json");  
			   writer.write(json);  
			   writer.close();  
			    
			  } catch (IOException e) {  
			   e.printStackTrace();  
			  }  
			  
			  System.out.println(json);  
			//System.out.println(c.getId() + " " + c.getNome() + " " +  c.getEndereco() + " " + c.getTelefone());
		
	}//testeListarTodos()
	
	private static void testAlterar() {
		Usuario cliente = new Usuario();
		cliente.setId(1);
		cliente.setNome("Xis Salada");
		
		UsuarioDAO clienteDao = new UsuarioDAO();
		clienteDao.alterar(cliente);
	}//alterar	
	
	private static void testExcluir() {
		Usuario cliente = new Usuario();
		cliente.setId(2);
		
		UsuarioDAO clienteDao = new UsuarioDAO();
		clienteDao.excluir(cliente);
	}//excluir
	
	public static void testAutenticar(){

		Usuario usuario = new Usuario();
		usuario.setEmail("camejocristiano@gmail.com");
		usuario.setSenha("123");

		UsuarioDAO usuarioDao = new UsuarioDAO();
		System.out.println(usuarioDao.autenticar(usuario));

	}//testAutenticar
	
}
