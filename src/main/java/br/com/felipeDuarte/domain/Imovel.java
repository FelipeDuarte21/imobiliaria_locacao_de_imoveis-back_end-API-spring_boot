package br.com.felipeDuarte.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "imovel")
public class Imovel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idImovel;
	
	@NotNull(message = "Informe o preço do aluguel do imóvel")
	@Column(precision = 2)
	private Double preco;
	
	@NotNull(message = "Informe o tipo do imóvel(ex: Casa, Apartamento etc..)")
	@NotBlank(message = "Informe o tipo do imóvel(ex: Casa, Apartamento etc..)")
	@Length(max= 100, message = "Informe o tipo até {max} caracteres")
	private String tipo;
	
	@NotNull(message = "Informe uma descrição do imóvel")
	@NotBlank(message = "Informe uma descrição do imóvel")
	@Column(columnDefinition = "text")
	private String descricao;
	
	@Column(nullable = false, columnDefinition="boolean not null default '1'")
	private Boolean disponivel;
	
	@NotNull(message = "Informe o endereço do imóvel")
	@OneToOne
	@JoinColumn(name = "id_Endereco")
	private Endereco endereco;
	
	@NotNull(message = "Informe o proprietário")
	@ManyToOne
	@JoinColumn(name = "id_Proprietario")
	private Pessoa proprietario;
	
	public Imovel() {
		
	}

	public Imovel(Integer idImovel, Double preco, String tipo, String descricao, Boolean disponivel,
		Endereco endereco, Pessoa proprietario) {
		super();
		this.idImovel = idImovel;
		this.preco = preco;
		this.tipo = tipo;
		this.descricao = descricao;
		this.disponivel = disponivel;
		this.endereco = endereco;
		this.proprietario = proprietario;
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

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Pessoa getProprietario() {
		return proprietario;
	}

	public void setProprietario(Pessoa proprietario) {
		this.proprietario = proprietario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idImovel == null) ? 0 : idImovel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imovel other = (Imovel) obj;
		if (idImovel == null) {
			if (other.idImovel != null)
				return false;
		} else if (!idImovel.equals(other.idImovel))
			return false;
		return true;
	}
	
}
