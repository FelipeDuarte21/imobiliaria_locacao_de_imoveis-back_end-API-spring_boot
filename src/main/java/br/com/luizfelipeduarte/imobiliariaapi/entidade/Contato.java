package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.enums.TipoContato;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
