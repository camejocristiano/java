package br.com.santander.test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.santander.modelo.Conta;
import br.com.santander.util.JPAUtil;

public class TesteBooleanContaExiste {

	public static void main(String[] args) {

		Conta conta = null;
		EntityManager manager = new JPAUtil().getEntityManager();
		try {
			Query query = manager.createQuery("select c from Conta c where c.numeroConta = :numeroConta");
			query.setParameter("numeroConta", "123456-1");
			conta = (Conta) query.getSingleResult();
			manager.close();
			if (!conta.toString().isEmpty()) {
				System.out.println("\nConta ativa!");
			}
		} catch (Exception e) {
			if (e.getMessage().matches("No entity found for query")) {
				System.out.println("\nConta inexistente!");
			} else {
				System.out.println("\nAlgo saiu errado! Consulte seu gerente! \nServidor: " + e.getMessage());
			}
		}
		
	}
		
}