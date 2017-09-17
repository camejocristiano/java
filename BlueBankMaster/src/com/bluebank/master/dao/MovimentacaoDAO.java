package com.bluebank.master.dao;

import java.util.List;

import com.bluebank.master.model.Conta;
import com.bluebank.master.model.Movimentacao;

public interface MovimentacaoDAO {

	Movimentacao findById(long id);

	void saveMovimentacao(Movimentacao Movimentacao);
	
	void depositar(Movimentacao Movimentacao, Conta Conta);
	
	void sacar(Movimentacao Movimentacao, Conta Conta);
	
	void transferir(Movimentacao Movimentacaoo, Conta Contao, Movimentacao Movimentacaod, Conta Contad);

	void updateMovimentacao(Movimentacao Movimentacao);

	void deleteMovimentacaoById(long id);

	List<Movimentacao> findAllMovimentacoes();

	void deleteAllMovimentacoes();

	boolean isMovimentacaoExist(Movimentacao Movimentacao);
	
}
