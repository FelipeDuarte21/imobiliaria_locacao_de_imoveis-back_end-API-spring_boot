package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "logradouro_cep")
public class LogradouroCep implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String logradouro;

	@Column(unique = true)
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "id_Complemento")
	private Complemento complemento;
	
	@ManyToOne
	@JoinColumn(name = "id_Bairro")
	private Bairro bairro;
	
	@OneToMany(mappedBy = "logradouroCep")
	private Set<Endereco> enderecos = new HashSet<>();
	
	
	public LogradouroCep(String logradouro,String cep, String complemento, 
			String bairro, String cidade, String estado) {
		this.logradouro = logradouro;
		this.cep = cep;
		this.complemento = new Complemento(complemento);
		this.bairro = new Bairro(bairro,cidade,estado);
	}

}
