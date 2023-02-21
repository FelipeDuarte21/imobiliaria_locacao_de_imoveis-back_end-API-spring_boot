package br.com.luizfelipeduarte.imobiliariaapi.entidade.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ImovelDadosDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message = "Informe o preço do aluguel do imóvel")
	private BigDecimal preco;
	
	@NotNull(message = "Informe o tipo do imóvel(ex: Casa, Apartamento etc..)")
	@NotBlank(message = "Informe o tipo do imóvel(ex: Casa, Apartamento etc..)")
	@Length(max= 100, message = "Informe o tipo até {max} caracteres")
	private String tipo;
	
	@NotNull(message = "Informe uma descrição do imóvel")
	@NotBlank(message = "Informe uma descrição do imóvel")
	private String descricao;
	
	@NotNull(message = "Informe o endereço do imóvel")
	private EnderecoDadosDTO endereco;
	
	@NotNull(message = "Informe o proprietário")
	private Long idProprietario;
	
	public ImovelDadosDTO() {
		
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

	public EnderecoDadosDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDadosDTO endereco) {
		this.endereco = endereco;
	}

	public Long getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(Long idProprietario) {
		this.idProprietario = idProprietario;
	}
	
}
