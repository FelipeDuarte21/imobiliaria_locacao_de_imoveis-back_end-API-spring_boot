package br.com.felipeDuarte.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bairro")
public class Bairro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idBairro;
	
	@NotNull(message = "Informe o nome do bairro")
	@NotBlank(message = "Informe o nome do bairro")
	@Length(max = 50, message = "Informe o nome até {max} caractere")
	private String nome;
	
	@NotNull(message = "Informe a cidade")
	@ManyToOne
	@JoinColumn(name = "id_Cidade")
	private Cidade cidade;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bairro")
	private List<LogradouroCep> logradourosCeps = new ArrayList<>();
	
	public Bairro() {
		
	}
	
	public Bairro(Integer idBairro, String nome, Cidade cidade) {
		this.idBairro = idBairro;
		this.nome = nome;
		this.cidade = cidade;
	}

	public Integer getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(Integer idBairro) {
		this.idBairro = idBairro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<LogradouroCep> getLogradourosCeps() {
		return logradourosCeps;
	}

	public void setLogradourosCeps(List<LogradouroCep> logradourosCeps) {
		this.logradourosCeps = logradourosCeps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBairro == null) ? 0 : idBairro.hashCode());
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
		Bairro other = (Bairro) obj;
		if (idBairro == null) {
			if (other.idBairro != null)
				return false;
		} else if (!idBairro.equals(other.idBairro))
			return false;
		return true;
	}
	
}
