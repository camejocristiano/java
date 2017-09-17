package br.com.santander.test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.santander.modelo.Conta;
import br.com.santander.util.JPAUtil;

public class TesteContaVerificarSaldo {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		Query query = manager.createQuery("select c from Conta c where c.idConta = :idConta");
		query.setParameter("idConta", 4);
		Conta conta = (Conta) query.getSingleResult();
		manager.close();

		System.out.println("\nCPF ..: " + conta.getCpfCliente());
		System.out.println("\nAgência ..: " + conta.getNumeroAgencia());
		System.out.println("\nConta ..: " + conta.getNumeroConta());
		System.out.println("\nSaldo ..: " + conta.getSaldoConta());

	}
		
}