package br.com.luizfelipeduarte.imobiliariaapi.entidade.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PessoaDadosDTO {
	
	private Long id;
	
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
	private LocalDate dataNascimento;
	
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
	private LocalDate dataExpedicao;
	
	@NotNull(message = "Informe um cpf")
	@NotBlank(message = "Informe um cpf")
	@Length(min=11,max=11,message = "Informe um cpf de {max} caracteres")
	private String cpf;
	
	@Length(max=50,message = "Informe um email de até {max} caracteres")
	private String email;
	
	private BigDecimal salario;

	@NotNull(message = "Informe um contato")
	private List<ContatoDadosDTO> contatos = new ArrayList<>();
	
	@NotNull(message = "Informe um endereço")
	private EnderecoDadosDTO endereco;
	
	public PessoaDadosDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<ContatoDadosDTO> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoDadosDTO> contatos) {
		this.contatos = contatos;
	}

	public EnderecoDadosDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDadosDTO endereco) {
		this.endereco = endereco;
	}

}
