package br.com.felipeDuarte.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LocacaoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer idLocacao;
	
	@NotNull(message = "Informe a data da locação")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	@NotNull(message = "Informe o tempo da locação")
	@NotBlank(message = "Informe o tempo da locação")
	@Length(max = 5, message = "Informe o tempo em até {max} caracteres")
	private String tempo;
	
	@NotNull(message = "Informe a data de início da locação")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataInicio;
	
	@NotNull(message = "Informe o data de término da locação")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataTermino;
	
	@NotNull(message = "Informe o valor da locação")
	private Double valor;
	
	@NotNull(message = "Informe o imóvel da locação")
	private Integer idImovel;
	
	@NotNull(message = "Informe o inquilino")
	private Integer idInquilino;
	
	public LocacaoDTO() {
		
	}

	public Integer getIdLocacao() {
		return idLocacao;
	}

	public void setIdLocacao(Integer idLocacao) {
		this.idLocacao = idLocacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdInquilino() {
		return idInquilino;
	}

	public void setIdInquilino(Integer idInquilino) {
		this.idInquilino = idInquilino;
	}
	
}
