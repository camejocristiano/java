package br.com.startup.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer mesa;
	@Enumerated(EnumType.STRING)
	private StatusConta statusConta;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMesa() {
		return mesa;
	}
	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}
	public StatusConta getStatusConta() {
		return statusConta;
	}
	public void setStatusConta(StatusConta statusConta) {
		this.statusConta = statusConta;
	}

	@Override
	public String toString(){
		return "id="+id+", mesa="+mesa;
	}
}