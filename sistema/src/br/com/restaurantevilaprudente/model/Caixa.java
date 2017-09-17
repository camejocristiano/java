package br.com.restaurantevilaprudente.model;

import java.math.BigDecimal;

public class Caixa {

	private Integer id;
	private Integer usuario;
	private BigDecimal valor;
	private Integer dia;
	private Integer mes;
	private Integer ano;
	private Integer pedidos;
	
	public Integer getPedidos() {
		return pedidos;
	}

	public void setPedidos(Integer pedidos) {
		this.pedidos = pedidos;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}
	
	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}
	
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
		
}
