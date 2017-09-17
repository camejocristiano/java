package br.com.stratup.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.startup.modelo.Cardapio;
import br.com.startup.modelo.Pedido;
import br.com.stratup.util.JPAUtil;

public class TesteListarPedido {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		Query query = manager.createQuery("select p from Pedido p join fetch p.cardapios where p.id = :pId");
		query.setParameter("pId", 4);
		Pedido pedido = (Pedido) query.getSingleResult();
		List<Cardapio> cardapios = pedido.getCardapios();
		for (Cardapio cardapio : cardapios) {
			System.out.println("\nCardapio Titulo..: " + cardapio.getTitulo());
		}
		manager.close();

	}

}
