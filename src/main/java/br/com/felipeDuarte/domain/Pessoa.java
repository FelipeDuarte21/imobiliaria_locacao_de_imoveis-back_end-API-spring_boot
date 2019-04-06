package br.com.felipeDuarte.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.felipeDuarte.domain.enums.TipoEstadoCivil;
import br.com.felipeDuarte.domain.enums.TipoPessoa;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idPessoa;
	
	@NotNull(message = "Informe o tipo da pessoa")
	@Column(name = "tipo")
	private Integer tipoPessoa;
	
	@NotNull(message = "Informe o nome")
	@NotBlank(message = "Informe o nome")
	@Length(max=50,message = "Informe um nome até {max} caracteres")
	private String nome;
	
	@NotNull(message = "Infome uma nacionalidade")
	@NotBlank(message = "Informe uma nacionalidade")
	@Length(max=50, message = "Informe uma nacionalidade até {max} caracteres")
	private String nacionalidade;
	
	@NotNull(message = "Informe o estado civil")
	private Integer estadoCivil;
	
	@NotNull(message = "Informe a data de nascimento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@NotNull(message = "Informe o número da identidade")
	@NotBlank(message = "Informe o número da identidade")
	@Length(max = 50, message = "Informe um número de identidade menor que {max} caracteres")
	private String identidade;
	
	@NotNull(message = "Infome o orgão emissor da identidade")
	@NotBlank(message = "Informe o orgão emissor da identidade")
	@Length(max = 30, message = "Informe um orgão emissor menor que {max} caracteres")
	private String orgaoEmissor;
	
	@NotNull(message = "Informe uma data de expedição")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataExpedicao;
	
	@NotNull(message = "Informe um cpf")
	@NotBlank(message = "Informe um cpf")
	@Length(min=11,max=11,message = "Informe um cpf de {max} caracteres")
	@Column(unique = true)
	private String cpf;
	
	@Length(max=50,message = "Informe um email de até {max} caracteres")
	private String email;
	
	@Column(precision = 2)
	private Double salario;

	@Column(columnDefinition = "boolean not null default '1'")
	private Boolean ativo;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pessoa")
	private List<Contato> contatos = new ArrayList<>();
	
	@NotNull(message = "Informe um endereço")
	@OneToOne
	@JoinColumn(name = "id_Endereco")
	private Endereco endereco;
	
	@JsonIgnore
	@OneToMany(mappedBy = "proprietario")
	private List<Imovel> imoveis = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "inquilino")
	private List<Locacao> locacoes = new ArrayList<>();
	
	public Pessoa() {
		
	}

	public Pessoa(Integer idPessoa, Integer tipoPessoa, String nome, String nacionalidade, Integer estadoCivil,
	Date dataNascimento, String identidade, String orgaoEmissor, Date dataExpedicao, String cpf, String email,
	Double salario,Boolean ativo,Endereco endereco) {
		super();
		this.idPessoa = idPessoa;
		this.tipoPessoa = tipoPessoa;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.estadoCivil = estadoCivil;
		this.dataNascimento = dataNascimento;
		this.identidade = identidade;
		this.orgaoEmissor = orgaoEmissor;
		this.dataExpedicao = dataExpedicao;
		this.cpf = cpf;
		this.email = email;
		this.salario = salario;
		this.ativo = ativo;
		this.endereco = endereco;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Integer getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = TipoPessoa.toEnum(tipoPessoa).getCod();
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

	public Integer getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(Integer estadoCivil) {
		this.estadoCivil = TipoEstadoCivil.toEnum(estadoCivil).getCod();
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
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

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
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

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPessoa == null) ? 0 : idPessoa.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (idPessoa == null) {
			if (other.idPessoa != null)
				return false;
		} else if (!idPessoa.equals(other.idPessoa))
			return false;
		return true;
	}
	
}
