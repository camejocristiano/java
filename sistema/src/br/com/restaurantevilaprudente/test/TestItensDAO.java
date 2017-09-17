package br.com.restaurantevilaprudente.test;

import java.util.List;

import br.com.restaurantevilaprudente.dao.ItensDAO;
import br.com.restaurantevilaprudente.model.Cardapio;

public class TestItensDAO {

	public static void main(String [] args) {
		buscarByIdPedido(2);
	}

	private static void buscarByIdPedido(Integer id){
			ItensDAO itensDao = new ItensDAO();
			List<Cardapio> listarItens = itensDao.buscarByIdPedido(id);
			for(Cardapio c: listarItens){
				System.out.println("Id do Cardapio: " + c.getId() + "\n" 
                        + "Titulo do cardápio: " + c.getTitulo() + "\n"
                        + "Descrição do cardápio: " + c.getDescricao() + "\n"
                        + "Valor do cardápio: " + c.getValor() + "\n"
                );
			}	
	}
}