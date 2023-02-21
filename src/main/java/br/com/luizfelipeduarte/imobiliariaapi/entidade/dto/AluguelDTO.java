package br.com.luizfelipeduarte.imobiliariaapi.entidade.dto;

import java.time.LocalDate;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Aluguel;

public class AluguelDTO {
	
	private Long id;
	private LocalDate dataVencimento;
	private Boolean quite;
	private LocalDate dataPagamento;
	
	public AluguelDTO() {
		
	}
	
	public AluguelDTO(Aluguel aluguel) {
		this.id = aluguel.getId();
		this.dataVencimento = aluguel.getDataVencimento();
		this.quite = aluguel.getQuite();
		this.dataPagamento = aluguel.getDataPagamento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Boolean getQuite() {
		return quite;
	}

	public void setQuite(Boolean quite) {
		this.quite = quite;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
}
