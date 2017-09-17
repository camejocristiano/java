package com.bluebank.master.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluebank.master.dao.MovimentacaoDAO;
import com.bluebank.master.model.Conta;
import com.bluebank.master.model.Movimentacao;
import com.bluebank.master.model.TipoMovimentacao;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

	@Autowired
	private MovimentacaoDAO movimentacaoDAO;

	//setter for movimentacaoDAO
	public void setMovimentacaoDAO(MovimentacaoDAO movimentacaoDAO) {
		this.movimentacaoDAO = movimentacaoDAO;
	}
	
	@Override
	public void addMovimentacao(Movimentacao m) {
		
	}

	@Override
	public void depositar(Conta c, BigDecimal valor) {
		c.setSaldo(c.getSaldo().add(valor));
		Movimentacao m = new Movimentacao();
		m.setConta(c);
		m.setTipo(TipoMovimentacao.CREDITO);
		m.setValor(valor);
		this.movimentacaoDAO.depositar(m, c);
	}
	
	@Override
	public void sacar(Conta c, BigDecimal valor) {
		c.setSaldo(c.getSaldo().subtract(valor));
		Movimentacao m = new Movimentacao();
		m.setConta(c);
		m.setTipo(TipoMovimentacao.DEBITO);
		m.setValor(valor);
		this.movimentacaoDAO.sacar(m, c);
	}	
	
	@Override
	public void transferir(Conta co, Conta cd, BigDecimal valor) {
		co.setSaldo(co.getSaldo().subtract(valor));
		Movimentacao mo = new Movimentacao();
		mo.setConta(co);
		mo.setTipo(TipoMovimentacao.DEBITO);
		mo.setValor(valor);
		
		cd.setSaldo(cd.getSaldo().add(valor));
		Movimentacao md = new Movimentacao();
		md.setConta(cd);
		md.setTipo(TipoMovimentacao.CREDITO);
		md.setValor(valor);
		this.movimentacaoDAO.transferir(mo, co, md, cd);
	}	

	@Override
	public void updateMovimentacao(Movimentacao m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Movimentacao> listMovimentacoes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movimentacao getMovimentacaoById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeMovimentacao(int id) {
		// TODO Auto-generated method stub
		
	}

}
