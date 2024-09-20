package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.EnderecoDadosDTO;
import jakarta.persistence.Entity;
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
@Table(name = "endereco")
public class Endereco implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private LogradouroCep logradouroCep;

	@ManyToOne
	private Numero numero;

	public Endereco(EnderecoDadosDTO enderecoDadosDTO) {
		this.numero = new Numero(enderecoDadosDTO.getNumero());
		this.logradouroCep = new LogradouroCep(enderecoDadosDTO.getLogradouro(),enderecoDadosDTO.getCep(),
				enderecoDadosDTO.getComplemento(),enderecoDadosDTO.getBairro(),enderecoDadosDTO.getCidade(),
				enderecoDadosDTO.getEstado());
	}

}
