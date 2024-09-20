package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.PessoaDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.enums.TipoEstadoCivil;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.enums.TipoPessoa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoPessoa tipoPessoa;
	
	private String nome;
	private String nacionalidade;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoEstadoCivil estadoCivil;
	
	private LocalDate dataNascimento;
	
	private String identidade;
	private String orgaoEmissor;

	private LocalDate dataExpedicao;
	
	@Column(unique = true)
	private String cpf;
	
	private String email;
	
	private BigDecimal salario;

	@Column(columnDefinition = "boolean not null default '1'")
	private Boolean ativo;
	
	@OneToOne
	@JoinColumn(name = "id_Endereco")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.REMOVE)
	private List<Contato> contatos = new ArrayList<>();
	
	@OneToMany(mappedBy = "proprietario", cascade = CascadeType.REMOVE)
	private List<Imovel> imoveis = new ArrayList<>();
	
	@OneToMany(mappedBy = "inquilino", cascade = CascadeType.REMOVE)
	private List<Locacao> locacoes = new ArrayList<>();
	
	public Pessoa(PessoaDadosDTO pessoaDadosDTO) {
		this.id = pessoaDadosDTO.getId();
		this.tipoPessoa = TipoPessoa.toEnum(pessoaDadosDTO.getTipoPessoa());
		this.nome = pessoaDadosDTO.getNome();
		this.nacionalidade = pessoaDadosDTO.getNacionalidade();
		this.estadoCivil = TipoEstadoCivil.toEnum(pessoaDadosDTO.getEstadoCivil());
		this.dataNascimento = pessoaDadosDTO.getDataNascimento();
		this.identidade = pessoaDadosDTO.getIdentidade();
		this.orgaoEmissor = pessoaDadosDTO.getOrgaoEmissor();
		this.dataExpedicao = pessoaDadosDTO.getDataExpedicao();
		this.cpf = pessoaDadosDTO.getCpf();
		this.email = pessoaDadosDTO.getEmail();
		this.salario = pessoaDadosDTO.getSalario();
		this.ativo = true;
		this.endereco = new Endereco(pessoaDadosDTO.getEndereco());
		this.contatos = pessoaDadosDTO.getContatos().stream().map(Contato::new).toList();
	}

}
