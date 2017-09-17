package com.bluebank.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bluebank.master.model.Conta;
import com.bluebank.master.service.ContaService;

@RestController // combination of @Controller and @ResponseBody annotations
public class ContaController {

	@Autowired
	private ContaService contaService;

	//setter for contaService
	public void setContaService(ContaService contaService) {
		this.contaService = contaService;
	}

	// Add Conta
	@RequestMapping(value = "/conta/new", method = RequestMethod.POST)
	public ResponseEntity<Void> addConta(@RequestBody Conta conta, UriComponentsBuilder ucb) {
		if (contaService.isContaExist(conta)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {

			contaService.saveConta(conta);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucb.path("/conta/{id}").buildAndExpand(conta.getId()).toUri());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}

	// Get Single Conta
	@RequestMapping(value = "/conta/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Conta> getConta(@PathVariable("id") int id) {

		Conta conta = contaService.findById(id);
		if (conta == null) {
			return new ResponseEntity<Conta>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Conta>(conta, HttpStatus.OK);
	}
	
	// Get Single Conta
	@RequestMapping(value = "/conta-agencia-numero/{agencia}/{numero}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Conta> getContaAgenciaNumero(@PathVariable("agencia") String agencia, @PathVariable("numero") String numero) {

		Conta conta = contaService.findByAgenciaNumero(agencia, numero);
		if (conta == null) {
			return new ResponseEntity<Conta>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Conta>(conta, HttpStatus.OK);
	}
	
	// Get Single Conta
	@RequestMapping(value = "/conta-agencia-numero-cpf/{agencia}/{numero}/{cpf}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Conta> getContaAgenciaNumeroCpf(@PathVariable("agencia") String agencia, @PathVariable("numero") String numero, @PathVariable("cpf") String cpf) {

		Conta conta = contaService.findByAgenciaNumeroCpf(agencia, numero, cpf);
		if (conta == null) {
			return new ResponseEntity<Conta>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Conta>(conta, HttpStatus.OK);
	}

	// Get All Contas
	@RequestMapping(value = "/contas", method = RequestMethod.GET)
	public ResponseEntity<List<Conta>> listAllContas() {
		List<Conta> contas = contaService.findAllContas();
		if (contas.isEmpty()) {
			return new ResponseEntity<List<Conta>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Conta>>(contas, HttpStatus.OK);
	}

	// Update Conta
	@RequestMapping(value = "/conta/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Conta> updateConta(@PathVariable("id") int id, @RequestBody Conta conta) {

		Conta c = contaService.findById(id);

		if (conta == null) {
			return new ResponseEntity<Conta>(HttpStatus.NOT_FOUND);
		}

		c.setNumero(conta.getNumero());
		c.setAgencia(conta.getAgencia());
		c.setCpf(conta.getCpf());
		c.setSaldo(conta.getSaldo());

		contaService.updateConta(c);
		return new ResponseEntity<Conta>(c, HttpStatus.OK);
	}

	// Delete Conta
	@RequestMapping(value = "/conta/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Conta> deleteConta(@PathVariable("id") long id) {

		Conta conta = contaService.findById(id);
		if (conta == null) {
			return new ResponseEntity<Conta>(HttpStatus.NOT_FOUND);
		}

		contaService.deleteContaById(id);
		return new ResponseEntity<Conta>(HttpStatus.NO_CONTENT);
	}

	// Delete All Contas
	@RequestMapping(value = "/conta/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<Conta> deleteAllContas() {

		contaService.deleteAllContas();
		return new ResponseEntity<Conta>(HttpStatus.NO_CONTENT);
	}
}