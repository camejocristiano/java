package com.bluebank.master.service;

import java.math.BigDecimal;
import java.util.List;

import com.bluebank.master.model.Conta;
import com.bluebank.master.model.Movimentacao;

public interface MovimentacaoService {

	public void addMovimentacao(Movimentacao m);
	public void depositar(Conta c, BigDecimal valor);
	public void sacar(Conta c, BigDecimal valor);
	public void transferir(Conta co, Conta cd, BigDecimal valor);
	public void updateMovimentacao(Movimentacao m);
	public List<Movimentacao> listMovimentacoes();
	public Movimentacao getMovimentacaoById(int id);
	public void removeMovimentacao(int id);
}
