package br.com.main.test;

import java.util.List;

import br.com.main.dao.UsuarioDAO;
import br.com.main.dao.UsuarioDAOImpl;
import br.com.main.model.Usuario;

public class TestUsuarioDAO {

	public static void main(String [] args) {
		testAddUsuario();
		testGetUsuarioById(1);
		testListarTodos();
	}

	private static void testAddUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Camejo");
		UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
		UsuarioDAO usuarioDAO = usuarioDAOImpl;
		usuarioDAO.addUsuario(usuario);
	}//testAddUsuario
	
	private static void testGetUsuarioById(int id){
		UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
		UsuarioDAO usuarioDAO = usuarioDAOImpl;
		Usuario usuario = usuarioDAO.getUsuarioById(id);
	    System.out.println(usuario.getNome());
	}//testGetUsuarioById
	
	private static void testListarTodos(){
		UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
		UsuarioDAO usuarioDAO = usuarioDAOImpl;
		List<Usuario> listarResultados = (List<Usuario>) usuarioDAO.listUsuarios();
		for(Usuario c: listarResultados){
			System.out.println(c.getId() + " " + c.getNome());
		}//for	  
	}//testeListarTodos()

}
