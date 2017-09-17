package com.bluebank.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bluebank.master.dao.ContaDAO;
import com.bluebank.master.model.Conta;


public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaDAO contaDao;

	//setter for contaDao
	public void setContaDAO(ContaDAO contaDao) {
		this.contaDao = contaDao;
	}

	@Override
	public Conta findById(long id) {

		return contaDao.findById(id);
	}

	@Override
	public Conta findByAgenciaNumero(String agencia, String numero) {
		return contaDao.findByAgenciaNumero(agencia, numero);
	}
	
	@Override
	public Conta findByAgenciaNumeroCpf(String agencia, String numero, String cpf) {
		return contaDao.findByAgenciaNumeroCpf(agencia, numero, cpf);
	}

	@Override
	public void saveConta(Conta Conta) {
		contaDao.saveConta(Conta);

	}

	@Override
	public void updateConta(Conta Conta) {
		contaDao.updateConta(Conta);

	}

	@Override
	public void deleteContaById(long id) {
		contaDao.deleteContaById(id);

	}

	@Override
	public List<Conta> findAllContas() {

		return contaDao.findAllContas();
	}

	@Override
	public void deleteAllContas() {
		contaDao.deleteAllContas();

	}

	@Override
	public boolean isContaExist(Conta Conta) {

		return contaDao.isContaExist(Conta);
	}

}

