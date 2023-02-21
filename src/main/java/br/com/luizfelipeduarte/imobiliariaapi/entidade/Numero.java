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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "numero")
public class Numero implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idNumero;
	
	private String numero;
	
	@JsonIgnore
	@OneToMany(mappedBy = "numero")
	private Set<Endereco> enderecos = new HashSet<>();
	
	public Numero() {
		
	}

	public Numero(Integer idNumero, String numero) {
		super();
		this.idNumero = idNumero;
		this.numero = numero;
	}

	public Integer getIdNumero() {
		return idNumero;
	}

	public void setIdNumero(Integer idNumero) {
		this.idNumero = idNumero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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
		result = prime * result + ((idNumero == null) ? 0 : idNumero.hashCode());
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
		Numero other = (Numero) obj;
		if (idNumero == null) {
			if (other.idNumero != null)
				return false;
		} else if (!idNumero.equals(other.idNumero))
			return false;
		return true;
	}
	
}
