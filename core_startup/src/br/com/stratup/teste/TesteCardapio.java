package br.com.stratup.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.startup.modelo.Cardapio;
import br.com.stratup.util.JPAUtil;

public class TesteCardapio {

	public static void main(String[] args){
		
		Cardapio cardapio = new Cardapio();
		cardapio.setTitulo("Cardapio II");
		cardapio.setData(Calendar.getInstance());
		cardapio.setDescricao("Testando o cadastro");
		cardapio.setValor(new BigDecimal("29.9"));
		
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		manager.persist(cardapio);

		manager.getTransaction().commit();
		manager.close();
	}
	
}
