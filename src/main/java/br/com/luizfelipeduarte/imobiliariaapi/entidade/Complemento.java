package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private Long id;
	
	private String complemento;
	
	@OneToMany(mappedBy = "complemento")
	private List<LogradouroCep> logradourosCeps = new ArrayList<>();
	
	public Complemento() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return Objects.hash(complemento, id, logradourosCeps);
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
		return Objects.equals(complemento, other.complemento) && Objects.equals(id, other.id)
				&& Objects.equals(logradourosCeps, other.logradourosCeps);
	}
	
}
