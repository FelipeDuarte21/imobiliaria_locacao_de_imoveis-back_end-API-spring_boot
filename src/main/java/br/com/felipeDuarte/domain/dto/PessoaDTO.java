package br.com.felipeDuarte.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PessoaDTO {
	
	private Integer idPessoa;
	
	@NotNull(message = "Informe o tipo da pessoa")
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
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataExpedicao;
	
	@NotNull(message = "Informe um cpf")
	@NotBlank(message = "Informe um cpf")
	@Length(min=11,max=11,message = "Informe um cpf de {max} caracteres")
	private String cpf;
	
	@Length(max=50,message = "Informe um email de até {max} caracteres")
	private String email;
	
	private Double salario;

	@NotNull(message = "Informe um contato")
	private List<ContatoDTO> contatos = new ArrayList<>();
	
	@NotNull(message = "Informe um endereço")
	private EnderecoDTO endereco;
	
	public PessoaDTO() {
		
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

	public Integer getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(Integer estadoCivil) {
		this.estadoCivil = estadoCivil;
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

	public List<ContatoDTO> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoDTO> contatos) {
		this.contatos = contatos;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
	
}
