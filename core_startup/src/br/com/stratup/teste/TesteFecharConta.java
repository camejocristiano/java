package br.com.stratup.teste;

import javax.persistence.EntityManager;

import br.com.startup.modelo.Conta;
import br.com.startup.modelo.StatusConta;
import br.com.stratup.util.JPAUtil;

public class TesteFecharConta {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		Conta conta = new Conta();
		boolean query = manager.createQuery("select c from Conta c where c.mesa = 21") != null;
		if (!query){
			conta.setStatusConta(StatusConta.FECHADA);
			manager.getTransaction().begin();
			manager.merge(conta);
			manager.getTransaction().commit();
			
		}
		System.out.println("Conta já existe!");
		manager.close();
	}
}

