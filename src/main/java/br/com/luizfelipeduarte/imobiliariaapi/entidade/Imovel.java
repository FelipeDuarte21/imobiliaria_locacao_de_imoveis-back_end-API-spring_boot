package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ImovelDadosDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "imovel")
public class Imovel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal preco;
	
	private String tipo;

	@Column(columnDefinition = "text")
	private String descricao;
	
	@Column(nullable = false, columnDefinition="boolean not null default '1'")
	private Boolean disponivel;
	
	@OneToOne
	@JoinColumn(name = "id_Endereco")
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name = "id_Proprietario")
	private Pessoa proprietario;
	
	@OneToOne(mappedBy = "imovel", cascade = CascadeType.REMOVE)
	private Locacao locacao;
	
	public Imovel(ImovelDadosDTO imovelDadosDTO) {
		this.id = imovelDadosDTO.getId();
		this.preco = imovelDadosDTO.getPreco();
		this.tipo = imovelDadosDTO.getTipo();
		this.descricao = imovelDadosDTO.getDescricao();
		this.disponivel = true;
		this.endereco = new Endereco(imovelDadosDTO.getEndereco());	
	}

}
