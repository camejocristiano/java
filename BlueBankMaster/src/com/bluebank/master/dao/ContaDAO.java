package com.bluebank.master.dao;

import java.util.List;

import com.bluebank.master.model.Conta;

public interface ContaDAO {

	Conta findById(long id);

	Conta findByAgenciaNumero(String agencia, String numero);
	
	Conta findByAgenciaNumeroCpf(String agencia, String numero, String cpf);

	void saveConta(Conta Conta);

	void updateConta(Conta Conta);

	void deleteContaById(long id);

	List<Conta> findAllContas();

	void deleteAllContas();

	boolean isContaExist(Conta Conta);
}

