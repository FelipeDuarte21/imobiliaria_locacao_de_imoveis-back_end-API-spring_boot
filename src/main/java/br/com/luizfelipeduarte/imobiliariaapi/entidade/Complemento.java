package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "complemento")
public class Complemento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idComplemento;
	
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
