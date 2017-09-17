package br.com.restaurantevilaprudente.model;

public class Itens {

	private Integer pedido_id;
	private Integer cardapio_id;
	private Integer quantidade;
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getPedido_id() {
		return pedido_id;
	}
	public void setPedido_id(Integer pedido_id) {
		this.pedido_id = pedido_id;
	}
	public Integer getCardapio_id() {
		return cardapio_id;
	}
	public void setCardapio_id(Integer cardapio_id) {
		this.cardapio_id = cardapio_id;
	}
	
}
