package br.com.santander.test;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.santander.modelo.Conta;
import br.com.santander.util.JPAUtil;

public class TesteBooleanContaVerificarSeSaldoMaiorQueSaque {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		// Entrada com valor do saque
		BigDecimal valorDoSaque = new BigDecimal("37.79");
		
		// Busca o saldo da conta indicada
		Query query = manager.createQuery("select c from Conta c where c.idConta = :idConta");
		query.setParameter("idConta", 4);
		Conta conta = (Conta) query.getSingleResult();
		manager.close();
		BigDecimal saldoDaConta = conta.getSaldoConta();
		BigDecimal saldoAnterior = conta.getSaldoConta();
		
		if (saldoDaConta.compareTo(valorDoSaque) == 1){
			System.out.println("\nSaque permitido!");
			saldoDaConta = saldoDaConta.subtract(valorDoSaque);
		} else if(valorDoSaque.compareTo(saldoDaConta) == 1){
			System.out.println("\nSaldo insuficiente!");
		}
		System.out.println("\nSaque ..: " + valorDoSaque);
		System.out.println("\nSaldo Anterior ..: " + saldoAnterior);
		System.out.println("\nSaldo ..: " + saldoDaConta);

	}
		
}