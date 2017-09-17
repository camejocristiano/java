package br.com.restaurantevilaprudente.model;

public class Cliente {

	private Integer id;
	private String nome;
	private String endereco;
	private Integer telefone;
	private String empresa;
	private Integer telefoneempresa;
	private String observacoes;
	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Integer getTelefoneempresa() {
		return telefoneempresa;
	}
	public void setTelefoneempresa(Integer telefoneempresa) {
		this.telefoneempresa = telefoneempresa;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getTelefone() {
		return telefone;
	}
	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
	
}
