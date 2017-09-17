package br.com.stratup.teste;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.startup.modelo.Usuario;
import br.com.stratup.util.JPAUtil;

public class TesteUsuario {

	public static void main(String[] args){
		
		Usuario usuario = new Usuario();
		usuario.setNome("Camejo");
		usuario.setData(Calendar.getInstance());
		usuario.setEmail("camejocristiano@gmail.com");
		usuario.setTelefone("11952809967");
		usuario.setSenha("123");
		
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		manager.persist(usuario);

		manager.getTransaction().commit();
		manager.close();
	}
	
}
