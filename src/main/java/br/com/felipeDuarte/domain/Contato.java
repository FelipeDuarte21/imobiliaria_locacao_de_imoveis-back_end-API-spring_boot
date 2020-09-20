package br.com.felipeDuarte.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.felipeDuarte.domain.enums.TipoContato;

@Entity
@Table(name = "contato")
public class Contato implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idContato;
	
	@Column(name = "tipo")
	private Integer tipoContato;
	
	private String numero;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_Pessoa")
	private Pessoa pessoa;
	
	public Contato() {
		
	}

	public Contato(Integer idContato, Integer tipoContato, String numero,Pessoa pessoa) {
		super();
		this.idContato = idContato;
		this.tipoContato = tipoContato;
		this.numero = numero;
		this.pessoa = pessoa;
	}

	public Integer getIdContato() {
		return idContato;
	}

	public void setIdContato(Integer idContato) {
		this.idContato = idContato;
	}

	public String getTipoContato() {
		return TipoContato.toEnum(this.tipoContato).getDescricao();
	}

	public void setTipoContato(Integer tipoContato) {
		this.tipoContato = TipoContato.toEnum(tipoContato).getCod();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idContato == null) ? 0 : idContato.hashCode());
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
		Contato other = (Contato) obj;
		if (idContato == null) {
			if (other.idContato != null)
				return false;
		} else if (!idContato.equals(other.idContato))
			return false;
		return true;
	}
	
}
