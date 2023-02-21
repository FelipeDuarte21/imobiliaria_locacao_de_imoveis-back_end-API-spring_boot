package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "imovel")
public class Imovel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idImovel;
	
	@Column(precision = 2)
	private Double preco;
	
	private String tipo;

	@Column(columnDefinition = "text")
	private String descricao;
	
	@Column(nullable = false, columnDefinition="boolean not null default '1'")
	private Boolean disponivel;
	
	@OneToOne
	@JoinColumn(name = "id_Endereco")
	private Endereco endereco;
	
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
