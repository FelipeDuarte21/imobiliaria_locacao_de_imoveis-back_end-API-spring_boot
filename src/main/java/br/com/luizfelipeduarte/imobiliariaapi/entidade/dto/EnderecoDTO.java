package br.com.luizfelipeduarte.imobiliariaapi.entidade.dto;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Endereco;

public class EnderecoDTO {
	
	private Long id;
	private String numero;
	private String logradouro;
	private String cep;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	
	public EnderecoDTO() {}
	
	public EnderecoDTO(Endereco endereco) {
		this.id = endereco.getId();
		this.numero = endereco.getNumero().getNumero();
		this.logradouro = endereco.getLogradouroCep().getLogradouro();
		this.cep = endereco.getLogradouroCep().getCep();
		this.complemento = endereco.getLogradouroCep().getComplemento().getComplemento();
		this.bairro = endereco.getLogradouroCep().getBairro().getNome();
		this.cidade = endereco.getLogradouroCep().getBairro().getCidade().getNome();
		this.estado = endereco.getLogradouroCep().getBairro().getCidade().getEstado().getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
