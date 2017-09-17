package com.bluebank.master.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bluebank.master.model.Conta;
import com.bluebank.master.service.ContaService;
import com.bluebank.master.service.MovimentacaoService;


@RestController // combination of @Controller and @ResponseBody annotations
public class MovimentacaoController {
	
	@Autowired
	private MovimentacaoService movimentacaoService;

	//setter for movimentacaoService
	public void setMovimentacaoService(MovimentacaoService movimentacaoService) {
		this.movimentacaoService = movimentacaoService;
	}
	

	@Autowired
	private ContaService contaService;

	//setter for contaService
	public void setContaService(ContaService contaService) {
		this.contaService = contaService;
	}
	
	// Depositar Conta
	@RequestMapping(value = "/depositar/{id}/{valor}/{decimal}", method = RequestMethod.PUT)
	public ResponseEntity<Conta> depositar(@PathVariable("id") int id, @PathVariable("valor") String valor, @PathVariable("decimal") String decimal) {

		Conta c = contaService.findById(id);
		
		if (c == null || valor == null) {
			return new ResponseEntity<Conta>(HttpStatus.NOT_FOUND);
		}

		BigDecimal creditar = new BigDecimal(valor+"."+decimal);

		movimentacaoService.depositar(c, creditar);
		return new ResponseEntity<Conta>(c, HttpStatus.OK);
	}
	
	// Sacar Conta
	@RequestMapping(value = "/sacar/{id}/{valor}/{decimal}", method = RequestMethod.PUT)
	public ResponseEntity<Conta> sacar(@PathVariable("id") int id, @PathVariable("valor") String valor, @PathVariable("decimal") String decimal) {

		Conta c = contaService.findById(id);
			
		if (c == null || valor == null) {
			return new ResponseEntity<Conta>(HttpStatus.NOT_FOUND);
		}

		BigDecimal creditar = new BigDecimal(valor + "." + decimal);
		BigDecimal saldo = c.getSaldo();
		if(saldo.compareTo(creditar) < 0){
			return new ResponseEntity<Conta>(HttpStatus.EXPECTATION_FAILED);
		}
		
		movimentacaoService.sacar(c, creditar);
		return new ResponseEntity<Conta>(c, HttpStatus.OK);
	}

	// Transferir Conta
	@RequestMapping(value = "/transferir/{origem}/{destino}/{valor}/{decimal}", method = RequestMethod.PUT)
	public ResponseEntity<Conta> transferir(@PathVariable("origem") int origem, @PathVariable("destino") int destino, @PathVariable("valor") String valor, @PathVariable("decimal") String decimal) {
		Conta co = contaService.findById(origem);
		Conta cd = contaService.findById(destino);
		if (co == null || cd == null || valor == null ) {
			return new ResponseEntity<Conta>(HttpStatus.NOT_FOUND);
		}
		String depositar = valor + "." + decimal;
		BigDecimal creditar = new BigDecimal(depositar);
		BigDecimal saldo = co.getSaldo();
		if(saldo.compareTo(creditar) < 0){
			return new ResponseEntity<Conta>(HttpStatus.EXPECTATION_FAILED);
		}
		movimentacaoService.transferir(co, cd, creditar);
		return new ResponseEntity<Conta>(co, HttpStatus.OK);
	}
}
