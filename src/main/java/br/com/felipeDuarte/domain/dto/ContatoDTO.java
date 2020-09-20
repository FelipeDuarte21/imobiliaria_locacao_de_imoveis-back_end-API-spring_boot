package br.com.felipeDuarte.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ContatoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer idContato;
	
	@NotNull(message = "Informe o tipo do número")
	private Integer tipoContato;
	
	@NotNull(message = "Informe um número")
	@NotBlank(message = "Informe um número")
	@Length(min=10,max=11,message = "Número Inválido")
	private String numero;
	
	public ContatoDTO() {
		
	}

	public Integer getIdContato() {
		return idContato;
	}

	public void setIdContato(Integer idContato) {
		this.idContato = idContato;
	}

	public Integer getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(Integer tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
