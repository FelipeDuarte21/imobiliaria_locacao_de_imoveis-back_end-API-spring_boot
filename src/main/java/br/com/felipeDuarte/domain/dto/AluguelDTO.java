package br.com.felipeDuarte.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AluguelDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idAluguel;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	private Integer idLocacao;
	
	public AluguelDTO() {
		
	}

	public Integer getIdAluguel() {
		return idAluguel;
	}

	public void setIdAluguel(Integer idAluguel) {
		this.idAluguel = idAluguel;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getIdLocacao() {
		return idLocacao;
	}

	public void setIdLocacao(Integer idLocacao) {
		this.idLocacao = idLocacao;
	}
		
}
