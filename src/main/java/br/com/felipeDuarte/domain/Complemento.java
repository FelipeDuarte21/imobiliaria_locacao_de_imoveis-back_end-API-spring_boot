package br.com.felipeDuarte.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "complemento")
public class Complemento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idComplemento;
	
	@NotNull(message = "Informe um complemento")
	@NotBlank(message = "Informe um complemento")
	@Length(max = 200, message = "Informe um complemento até {max} caracteres")
	private String complemento;
	
	@JsonIgnore
	@OneToMany(mappedBy = "complemento")
	private List<LogradouroCep> logradourosCeps = new ArrayList<>();
	
	public Complemento() {
		
	}

	public Complemento(Integer idComplemento, String complemento) {
		super();
		this.idComplemento = idComplemento;
		this.complemento = complemento;
	}

	public Integer getIdComplemento() {
		return idComplemento;
	}

	public void setIdComplemento(Integer idComplemento) {
		this.idComplemento = idComplemento;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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
		result = prime * result + ((idComplemento == null) ? 0 : idComplemento.hashCode());
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
		Complemento other = (Complemento) obj;
		if (idComplemento == null) {
			if (other.idComplemento != null)
				return false;
		} else if (!idComplemento.equals(other.idComplemento))
			return false;
		return true;
	}

}
