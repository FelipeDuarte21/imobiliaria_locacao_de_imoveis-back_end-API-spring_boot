package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ImovelDadosDTO;
import jakarta.persistence.CascadeType;
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
	private Long id;
	
	private BigDecimal preco;
	
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
	
	@OneToOne(mappedBy = "imovel", cascade = CascadeType.REMOVE)
	private Locacao locacao;
	
	public Imovel() {
		
	}
	
	public Imovel(ImovelDadosDTO imovelDadosDTO) {
		this.id = imovelDadosDTO.getId();
		this.preco = imovelDadosDTO.getPreco();
		this.tipo = imovelDadosDTO.getTipo();
		this.descricao = imovelDadosDTO.getDescricao();
		this.disponivel = true;
		this.endereco = new Endereco(imovelDadosDTO.getEndereco());	
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

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, disponivel, id, preco, tipo);
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
		return Objects.equals(descricao, other.descricao) && Objects.equals(disponivel, other.disponivel)
				&& Objects.equals(id, other.id) && Objects.equals(preco, other.preco)
				&& Objects.equals(tipo, other.tipo);
	}
	
}
