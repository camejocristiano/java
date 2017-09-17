package br.com.santander.test;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.santander.modelo.Conta;
import br.com.santander.modelo.Movimentacao;
import br.com.santander.modelo.TipoMovimentacao;
import br.com.santander.util.JPAUtil;

public class TesteMovimentacaoTransferencia {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();
		Query query = null;
		BigDecimal valorParaTransacao = new BigDecimal("100.00");
		
		Conta contaDebitada = null;
		Conta contaCreditada = null;
		String numeroContaDebitada = "234456-1";
		String numeroContaCreditada = "123456-1";
		try {
			// Busca a contaDebitada
			query = manager.createQuery("select c from Conta c where c.numeroConta = :numeroContaDebitada");
			query.setParameter("numeroContaDebitada", numeroContaDebitada);
			contaDebitada = (Conta) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("\nConta " + numeroContaDebitada + " inexistente!");
		}
		try {
			// Busca a contaCreditada		
			query = manager.createQuery("select c from Conta c where c.numeroConta = :numeroContaCreditada");
			query.setParameter("numeroContaCreditada", numeroContaCreditada);
			contaCreditada = (Conta) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("\nConta " + numeroContaCreditada + " inexistente!");
		}		
	
		// Inicia a movimentação de débito 
		Movimentacao movimentacaoContaDebitada = new Movimentacao();
		movimentacaoContaDebitada.setContaMovimentada(contaDebitada);
		movimentacaoContaDebitada.setValorMovimentacao(valorParaTransacao);
		movimentacaoContaDebitada.setTipoMovimentacao(TipoMovimentacao.DEBITO);
		// Atualiza a contaDebitada
		BigDecimal saldoContaDebitada = contaDebitada.getSaldoConta();
		saldoContaDebitada = saldoContaDebitada.subtract(valorParaTransacao);
		contaDebitada.setSaldoConta(saldoContaDebitada);
		
		// Inicia a movimentação de Crédito 
		Movimentacao movimentacaoContaCreditada = new Movimentacao();
		movimentacaoContaCreditada.setContaMovimentada(contaCreditada);
		movimentacaoContaCreditada.setValorMovimentacao(valorParaTransacao);
		movimentacaoContaCreditada.setTipoMovimentacao(TipoMovimentacao.CREDITO);
		// Atualiza a contaCreditada
		BigDecimal saldoContaCreditada = contaCreditada.getSaldoConta();
		saldoContaCreditada = saldoContaCreditada.add(valorParaTransacao);
		contaCreditada.setSaldoConta(saldoContaCreditada);
		
		// "Comita" as transições
		manager.getTransaction().begin();
		manager.persist(contaDebitada);
		manager.persist(movimentacaoContaDebitada);
		manager.persist(contaCreditada);
		manager.persist(movimentacaoContaCreditada);
		manager.getTransaction().commit();
		manager.close();
	}
	
}
