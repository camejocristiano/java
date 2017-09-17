package br.com.stratup.teste;

import javax.persistence.EntityManager;

import br.com.startup.modelo.Conta;
import br.com.startup.modelo.StatusConta;
import br.com.stratup.util.JPAUtil;

public class TesteConta {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		Conta conta = new Conta();
		conta.setStatusConta(StatusConta.FECHADA);
		conta.setMesa(21);

		manager.getTransaction().begin();
		manager.merge(conta);
		manager.getTransaction().commit();
		manager.close();

	}
		
}

