package br.com.santander.test;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.santander.modelo.Conta;
import br.com.santander.modelo.Movimentacao;
import br.com.santander.modelo.TipoMovimentacao;
import br.com.santander.util.JPAUtil;

public class TesteMovimentacaoDebito {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		BigDecimal valorParaDebito = new BigDecimal("100.00");
		Conta conta = null;
		try {
			// Busca a conta
			Query query = manager.createQuery("select c from Conta c where c.numeroConta = :numeroConta");
			query.setParameter("numeroConta", "123456-1");
			conta = (Conta) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("\nConta inexistente!");
		}
		BigDecimal saldoDaConta = conta.getSaldoConta();
		
		// Inicia uma movimentação
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setContaMovimentada(conta);
		movimentacao.setValorMovimentacao(valorParaDebito);
		movimentacao.setTipoMovimentacao(TipoMovimentacao.DEBITO);
		
		// Atualiza a conta
		saldoDaConta = saldoDaConta.subtract(valorParaDebito);
		conta.setSaldoConta(saldoDaConta);
		
		// "Comita" a transição
		manager.getTransaction().begin();
		manager.persist(conta);
		manager.persist(movimentacao);
		manager.getTransaction().commit();
		manager.close();

	}
	
}
