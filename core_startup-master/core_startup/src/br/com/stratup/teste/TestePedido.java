package br.com.stratup.teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.startup.modelo.Cardapio;
import br.com.startup.modelo.Pedido;
import br.com.startup.modelo.Usuario;
import br.com.stratup.util.JPAUtil;

public class TestePedido {

	public static void main(String[] args) {

		Usuario usuario = new Usuario();
        usuario.setId(1);
        
		Cardapio item = new Cardapio();
		item.setId(1);
		Cardapio itemII = new Cardapio();
		itemII.setId(2);
        List<Cardapio> cardapios = new ArrayList<Cardapio>();
        cardapios.add(item);
        cardapios.add(itemII);
		
		Pedido pedido = new Pedido();
		pedido.setMesa(21);
		pedido.setUsuario(usuario);
		pedido.setCardapios(cardapios);
		pedido.setData_hora(Calendar.getInstance());
		
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		manager.merge(pedido);

		manager.getTransaction().commit();
		manager.close();

		
	}

}
