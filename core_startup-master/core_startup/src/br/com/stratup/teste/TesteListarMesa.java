package br.com.stratup.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.startup.modelo.Pedido;
import br.com.stratup.util.JPAUtil;

public class TesteListarMesa {

	public static void main(String [] args){
		EntityManager manager = new JPAUtil().getEntityManager();
		Query query = manager
				.createQuery("SELECT p FROM Pedido p WHERE p.mesa = 21");
		@SuppressWarnings("unchecked")
		List<Pedido> pedidos = query.getResultList();
		for (Pedido p: pedidos) {

			System.out.println("\nPedido ID ..: " + p.getId());
		}
		manager.close();

	}
}
