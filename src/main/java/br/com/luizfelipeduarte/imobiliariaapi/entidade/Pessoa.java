package br.com.luizfelipeduarte.imobiliariaapi.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.enums.TipoEstadoCivil;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.enums.TipoPessoa;
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
	
	@Enumerated(EnumType.STRING)
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
	
	@OneToMany(mappedBy = "pessoa")
	private List<Contato> contatos = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "id_Endereco")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "proprietario")
	private List<Imovel> imoveis = new ArrayList<>();
	
	@OneToMany(mappedBy = "inquilino")
	private List<Locacao> locacoes = new ArrayList<>();
	
	public Pessoa() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public TipoEstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(TipoEstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public LocalDate getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(LocalDate dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<Locacao> locacoes) {
		this.locacoes = locacoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ativo, cpf, dataExpedicao, dataNascimento, email, estadoCivil, id, identidade,
				nacionalidade, nome, orgaoEmissor, salario, tipoPessoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(ativo, other.ativo) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(dataExpedicao, other.dataExpedicao)
				&& Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(email, other.email)
				&& estadoCivil == other.estadoCivil && Objects.equals(id, other.id)
				&& Objects.equals(identidade, other.identidade) && Objects.equals(nacionalidade, other.nacionalidade)
				&& Objects.equals(nome, other.nome) && Objects.equals(orgaoEmissor, other.orgaoEmissor)
				&& Objects.equals(salario, other.salario) && tipoPessoa == other.tipoPessoa;
	}

}
