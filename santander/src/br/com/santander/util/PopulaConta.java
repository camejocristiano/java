package br.com.santander.util;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.santander.modelo.Conta;

public class PopulaConta {

	public static void main(String[] args) {

		Conta conta1 = new Conta();
		conta1.setCpfCliente("123.456.778-89");
		conta1.setNumeroAgencia("123-1");
		conta1.setNumeroConta("123456-1");
		conta1.setSaldoConta(new BigDecimal("123.90"));
		
		Conta conta2 = new Conta();
		conta2.setCpfCliente("234.456.778-89");
		conta2.setNumeroAgencia("123-1");
		conta2.setNumeroConta("234456-1");
		conta2.setSaldoConta(new BigDecimal("1234.98"));
		
		Conta conta3 = new Conta();
		conta3.setCpfCliente("345.456.778-89");
		conta3.setNumeroAgencia("345-1");
		conta3.setNumeroConta("345456-1");
		conta3.setSaldoConta(new BigDecimal("345.00"));
		
		Conta conta4 = new Conta();
		conta4.setCpfCliente("456.456.778-89");
		conta4.setNumeroAgencia("345-1");
		conta4.setNumeroConta("456456-1");
		conta4.setSaldoConta(new BigDecimal("456.00"));
		
		Conta conta5 = new Conta();
		conta5.setCpfCliente("567.456.778-89");
		conta5.setNumeroAgencia("567-1");
		conta5.setNumeroConta("567456-1");
		conta5.setSaldoConta(new BigDecimal("567.00"));
		
		Conta conta6 = new Conta();
		conta6.setCpfCliente("678.456.778-89");
		conta6.setNumeroAgencia("123-1");
		conta6.setNumeroConta("678456-1");
		conta6.setSaldoConta(new BigDecimal("678.00"));

		Conta conta7 = new Conta();
		conta7.setCpfCliente("789.456.778-89");
		conta7.setNumeroAgencia("567-1");
		conta7.setNumeroConta("789456-1");
		conta7.setSaldoConta(new BigDecimal("789.00"));

		
		EntityManager manager = new JPAUtil().getEntityManager();

		manager.getTransaction().begin();
		// persistindo as contas
		manager.persist(conta1);
		manager.persist(conta2);
		manager.persist(conta3);
		manager.persist(conta4);
		manager.persist(conta5);
		manager.persist(conta6);
		manager.persist(conta7);

		manager.getTransaction().commit();
		manager.close();

	}
}
