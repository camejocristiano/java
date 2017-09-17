package br.com.santander.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMovimentacao;
	private BigDecimal valorMovimentacao;
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	@ManyToOne
	private Conta contaMovimentada;
	
	public Integer getIdMovimentacao() {
		return idMovimentacao;
	}
	
	public void setIdMovimentacao(Integer idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}
	
	public BigDecimal getValorMovimentacao() {
		return valorMovimentacao;
	}
	
	public void setValorMovimentacao(BigDecimal valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}
	
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	
	public Conta getContaMovimentada() {
		return contaMovimentada;
	}
	
	public void setContaMovimentada(Conta contaMovimentada) {
		this.contaMovimentada = contaMovimentada;
	}

}
