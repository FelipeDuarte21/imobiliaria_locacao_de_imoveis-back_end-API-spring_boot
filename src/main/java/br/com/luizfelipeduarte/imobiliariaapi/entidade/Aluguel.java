package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "aluguel")
public class Aluguel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dataVencimento;
	
	@Column(nullable = true, columnDefinition="boolean default '0'")
	private Boolean quite;
	
	private LocalDate dataPagamento;
	
	@ManyToOne
	@JoinColumn(name = "id_locacao")
	private Locacao locacao;

}
