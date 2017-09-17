package br.com.restaurantevilaprudente.test;

import java.util.List;

import br.com.restaurantevilaprudente.dao.ClienteDAO;
import br.com.restaurantevilaprudente.model.Cliente;

public class TestClienteDAO {

	public static void main(String [] args) {
		testCadastrar();
		testAlterar();
		testExcluir();
		testListarTodos();
		testBuscarById();
		testBuscarByNome();
		testBuscarByNomeAproximado();
	}

	private static void testCadastrar() {
		Cliente cliente = new Cliente();
		cliente.setNome("Tamise");
		cliente.setEndereco("São Leopoldo Paçoca");
		cliente.setTelefone(777333777);
		
		ClienteDAO clienteDao = new ClienteDAO();
		clienteDao.cadastrar(cliente);
	}//cadastrar
	
	private static void testBuscarById(){
		ClienteDAO clienteDao = new ClienteDAO();
		Cliente cliente = clienteDao.buscarById(2);
		
		System.out.println("Nome: " + cliente.getNome());
	}//testBuscarById
	
	private static void testBuscarByNome(){
		ClienteDAO clienteDao = new ClienteDAO();
		Cliente cliente = clienteDao.buscarByNome("Xis Bacon");
		
		System.out.println("Endereço: " + cliente.getEndereco());
	}//testBuscarByNome
	
		
	private static void testBuscarByNomeAproximado(){
		ClienteDAO clienteDao = new ClienteDAO();
		List<Cliente> listarResultados = clienteDao.buscarByNomeAproximado("cor");
		
		for(Cliente c: listarResultados){
			System.out.println(c.getId() + " " + c.getNome() + " " + c.getEndereco() + " " + c.getTelefone());
		}//for
	}//testBuscarByNomeAproximado()
	
	private static void testListarTodos(){
		ClienteDAO clienteDao = new ClienteDAO();
		List<Cliente> listarResultados = clienteDao.listarTodos();
		
		for(Cliente c: listarResultados){
			System.out.println(c.getId() + " " + c.getNome() + " " +  c.getEndereco() + " " + c.getTelefone());
		}//for
	}//testeListarTodos()
	
	private static void testAlterar() {
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNome("Xis Salada");
		cliente.setEndereco("Lanche");
		cliente.setTelefone(999887777);
		
		ClienteDAO clienteDao = new ClienteDAO();
		clienteDao.alterar(cliente);
	}//alterar	
	
	private static void testExcluir() {
		Cliente cliente = new Cliente();
		cliente.setId(2);
		
		ClienteDAO clienteDao = new ClienteDAO();
		clienteDao.excluir(cliente);
	}//excluir
	
	
}
