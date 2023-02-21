package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "logradouroCep")
public class LogradouroCep implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idLogradouroCep;
	
	private String logradouro;

	@Column(unique = true)
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "id_Complemento")
	private Complemento complemento;
	
	@ManyToOne
	@JoinColumn(name = "id_Bairro")
	private Bairro bairro;
	
	@JsonIgnore
	@OneToMany(mappedBy = "logradouroCep")
	private Set<Endereco> enderecos = new HashSet<>();
	
	public LogradouroCep() {
		
	}

	public LogradouroCep(Integer idLogradouroCep, String logradouro, String cep, Bairro bairro,
	Complemento complemento) {
		super();
		this.idLogradouroCep = idLogradouroCep;
		this.logradouro = logradouro;
		this.cep = cep;
		this.bairro = bairro;
		this.complemento = complemento;
	}

	public Integer getIdLogradouroCep() {
		return idLogradouroCep;
	}

	public void setIdLogradouroCep(Integer idLogradouroCep) {
		this.idLogradouroCep = idLogradouroCep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Complemento getComplemento() {
		return complemento;
	}

	public void setComplemento(Complemento complemento) {
		this.complemento = complemento;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLogradouroCep == null) ? 0 : idLogradouroCep.hashCode());
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
		LogradouroCep other = (LogradouroCep) obj;
		if (idLogradouroCep == null) {
			if (other.idLogradouroCep != null)
				return false;
		} else if (!idLogradouroCep.equals(other.idLogradouroCep))
			return false;
		return true;
	}

}
