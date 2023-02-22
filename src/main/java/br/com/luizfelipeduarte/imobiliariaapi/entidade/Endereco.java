package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;
import java.util.Objects;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.EnderecoDadosDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_logradouroCep")
	private LogradouroCep logradouroCep;

	@ManyToOne
	@JoinColumn(name = "id_numero")
	private Numero numero;

	public Endereco() {
		
	}
	
	public Endereco(EnderecoDadosDTO enderecoDadosDTO) {
		this.numero = new Numero(enderecoDadosDTO.getNumero());
		this.logradouroCep = new LogradouroCep(enderecoDadosDTO.getLogradouro(),enderecoDadosDTO.getCep(),
				enderecoDadosDTO.getComplemento(),enderecoDadosDTO.getBairro(),enderecoDadosDTO.getCidade(),
				enderecoDadosDTO.getEstado());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LogradouroCep getLogradouroCep() {
		return logradouroCep;
	}

	public void setLogradouroCep(LogradouroCep logradouroCep) {
		this.logradouroCep = logradouroCep;
	}

	public Numero getNumero() {
		return numero;
	}

	public void setNumero(Numero numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, logradouroCep, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id) && Objects.equals(logradouroCep, other.logradouroCep)
				&& Objects.equals(numero, other.numero);
	}
	
	

}
