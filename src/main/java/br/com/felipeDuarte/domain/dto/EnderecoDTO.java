package br.com.felipeDuarte.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class EnderecoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Informe o Estado")
	@NotBlank(message = "Informe o Estado")
	@Length(max = 2, message = "Informe apenas a Sigla do Estado")
	private String estado;
	
	@NotNull(message = "Informe a Cidade")
	@NotBlank(message = "Informe a Cidade")
	@Length(max = 50, message = "Somente até {max} caracteres")
	private String cidade;
	
	@NotNull(message = "Informe o Bairro")
	@NotBlank(message = "Informe o Bairro")
	@Length(max = 50, message = "Somente até {max} caracteres")
	private String bairro;
	
	@NotNull(message = "Informe o Logradouro")
	@NotBlank(message = "Informe o Logradouro")
	@Length(max = 80, message = "Somente até {max} caracteres")
	private String logradouro;
	
	@NotNull(message = "Informe o Número")
	@NotBlank(message = "Informe o Número")
	@Length(max = 30, message = "Somente até {max} caracteres")
	private String numero;
	
	private String complemento;
	
	@NotNull(message = "Informe o CEP")
	@NotBlank(message = "Informe o CEP")
	@Length(max = 8, message = "Somente até {8} caracteres")
	private String cep;
	
	public EnderecoDTO() {
		
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
}
