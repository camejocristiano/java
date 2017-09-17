package br.com.stratup.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.startup.modelo.Cardapio;
import br.com.startup.modelo.Pedido;
import br.com.stratup.util.JPAUtil;

public class TesteListarConta {

	public static void main(String [] args){
		EntityManager manager = new JPAUtil().getEntityManager();
		Query query_pedidos = manager.createQuery("SELECT p FROM Pedido p WHERE p.mesa = 21");

		@SuppressWarnings("unchecked")
		List<Pedido> pedidos = query_pedidos.getResultList();

		for (Pedido p: pedidos) {
			Query query_cardapios = manager.createQuery("select p from Pedido p join fetch p.cardapios where p.id = :pId");
			query_cardapios.setParameter("pId", p.getId());
			Pedido pedido = (Pedido) query_cardapios.getSingleResult();
			List<Cardapio> cardapios = pedido.getCardapios();
			for (Cardapio cardapio : cardapios) {
				System.out.println("\nCardapio Titulo..: " + cardapio.getTitulo());
				System.out.println("\nCardapio Valor..: " + cardapio.getValor());
			}
		}
		manager.close();
	}
	
}
