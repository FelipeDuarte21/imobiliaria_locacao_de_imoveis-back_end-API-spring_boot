package br.com.luizfelipeduarte.imobiliariaapi.entidade.dto;

import java.math.BigDecimal;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Imovel;

public class ImovelDTO {
	
	private Long id;
	private BigDecimal preco;
	private String tipo;
	private String descricao;
	private Boolean disponivel;
	private EnderecoDTO endereco;
	
	public ImovelDTO() {}
	
	public ImovelDTO(Imovel imovel) {
		this.id = imovel.getId();
		this.preco = imovel.getPreco();
		this.tipo = imovel.getTipo();
		this.descricao = imovel.getDescricao();
		this.disponivel = imovel.getDisponivel();
		this.endereco = new EnderecoDTO(imovel.getEndereco());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

}
