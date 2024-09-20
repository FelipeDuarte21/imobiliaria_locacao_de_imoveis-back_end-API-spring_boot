package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.LocacaoDadosDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "locacao")
public class Locacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate data;
	
	private String tempo;

	private LocalDate dataInicio;
	
	private LocalDate dataTermino;
	
	private BigDecimal valor;
	
	@OneToOne
	@JoinColumn(name = "id_Imovel")
	private Imovel imovel;
	
	@ManyToOne
	@JoinColumn(name = "id_Inquilino")
	private Pessoa inquilino;
	
	@OneToMany(mappedBy = "locacao", cascade = CascadeType.REMOVE)
	private List<Aluguel> alugueis = new ArrayList<>();
	
	public Locacao(LocacaoDadosDTO locacaoDadosDTO) {
		this.id = locacaoDadosDTO.getId();
		this.data = locacaoDadosDTO.getData();
		this.tempo = locacaoDadosDTO.getTempo();
		this.dataInicio = locacaoDadosDTO.getDataInicio();
		this.dataTermino = locacaoDadosDTO.getDataTermino();
		this.valor = locacaoDadosDTO.getValor();
	}

}
