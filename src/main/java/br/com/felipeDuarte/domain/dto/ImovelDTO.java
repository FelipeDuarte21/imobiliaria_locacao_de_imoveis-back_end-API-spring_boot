package br.com.felipeDuarte.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ImovelDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer idImovel;
	
	@NotNull(message = "Informe o preço do aluguel do imóvel")
	private Double preco;
	
	@NotNull(message = "Informe o tipo do imóvel(ex: Casa, Apartamento etc..)")
	@NotBlank(message = "Informe o tipo do imóvel(ex: Casa, Apartamento etc..)")
	@Length(max= 100, message = "Informe o tipo até {max} caracteres")
	private String tipo;
	
	@NotNull(message = "Informe uma descrição do imóvel")
	@NotBlank(message = "Informe uma descrição do imóvel")
	private String descricao;
	
	@NotNull(message = "Informe o endereço do imóvel")
	private EnderecoDTO endereco;
	
	@NotNull(message = "Informe o proprietário")
	private Integer idProprietario;
	
	public ImovelDTO() {
		
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
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

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public Integer getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(Integer idProprietario) {
		this.idProprietario = idProprietario;
	}
	
}
