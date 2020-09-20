package br.com.felipeDuarte.domain.dto;

import java.io.Serializable;

public class AluguelDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idAluguel;
	private Integer idLocacao;
	
	
	public AluguelDTO() {
		
	}

	public Integer getIdAluguel() {
		return idAluguel;
	}

	public void setIdAluguel(Integer idAluguel) {
		this.idAluguel = idAluguel;
	}

	public Integer getIdLocacao() {
		return idLocacao;
	}

	public void setIdLocacao(Integer idLocacao) {
		this.idLocacao = idLocacao;
	}
		
}
