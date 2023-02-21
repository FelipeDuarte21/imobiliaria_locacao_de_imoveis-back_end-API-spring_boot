package br.com.luizfelipeduarte.imobiliariaapi.entidade.dto;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Contato;

public class ContatoDTO {
	
	private Long id;
	private String tipo;
	private String numero;
	
	public ContatoDTO() {}
	
	public ContatoDTO(Contato contato) {
		this.id = contato.getId();
		this.tipo = contato.getTipoContato().getDescricao();
		this.numero = contato.getNumero();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
