package br.com.luizfelipeduarte.imobiliariaapi.entidade.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Locacao;

public class LocacaoDTO {
	
	private Long id;
	private LocalDate data;
	private String tempo;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private BigDecimal valor;
	private ImovelDTO imovel;
	private List<AluguelDTO> alugueis = new ArrayList<>();
	
	public LocacaoDTO() {}
	
	public LocacaoDTO(Locacao locacao) {
		this.id = locacao.getId();
		this.data = locacao.getData();
		this.tempo = locacao.getTempo();
		this.dataInicio = locacao.getDataInicio();
		this.dataTermino = locacao.getDataTermino();
		this.valor = locacao.getValor();
		this.imovel = new ImovelDTO(locacao.getImovel());
		this.alugueis = locacao.getAlugueis().stream().map(AluguelDTO::new).toList();
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

	public ImovelDTO getImovel() {
		return imovel;
	}

	public void setImovel(ImovelDTO imovel) {
		this.imovel = imovel;
	}

	public List<AluguelDTO> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(List<AluguelDTO> alugueis) {
		this.alugueis = alugueis;
	}
	
}
