package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ContatoDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.enums.TipoContato;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contato")
public class Contato implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoContato tipoContato;
	
	private String numero;

	@ManyToOne
	private Pessoa pessoa;
	
	public Contato(ContatoDadosDTO contatoDadosDTO) {
		this.id = contatoDadosDTO.getId();
		this.tipoContato = TipoContato.toEnum(contatoDadosDTO.getTipoContato());
		this.numero = contatoDadosDTO.getNumero();
	}

}
