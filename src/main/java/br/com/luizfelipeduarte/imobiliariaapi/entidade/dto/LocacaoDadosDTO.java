package br.com.luizfelipeduarte.imobiliariaapi.entidade.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LocacaoDadosDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Informe a data da locação")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@NotNull(message = "Informe o tempo da locação")
	@NotBlank(message = "Informe o tempo da locação")
	@Length(max = 5, message = "Informe o tempo em até {max} caracteres")
	private String tempo;
	
	@NotNull(message = "Informe a data de início da locação")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;
	
	@NotNull(message = "Informe o data de término da locação")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataTermino;
	
	@NotNull(message = "Informe o valor da locação")
	private BigDecimal valor;
	
	@NotNull(message = "Informe o imóvel da locação")
	private Long idImovel;
	
	@NotNull(message = "Informe o inquilino")
	private Long idInquilino;
	
	public LocacaoDadosDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Long idImovel) {
		this.idImovel = idImovel;
	}

	public Long getIdInquilino() {
		return idInquilino;
	}

	public void setIdInquilino(Long idInquilino) {
		this.idInquilino = idInquilino;
	}

}
