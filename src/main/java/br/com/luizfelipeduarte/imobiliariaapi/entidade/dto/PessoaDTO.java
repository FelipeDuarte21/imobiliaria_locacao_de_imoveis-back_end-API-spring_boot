package br.com.luizfelipeduarte.imobiliariaapi.entidade.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;

public class PessoaDTO {
	
	private Long id;
	private String tipoPessoa;
	private String nome;
	private String nacionalidade;
	private String estadoCivil;
	private LocalDate dataNascimento;
	private String identidade;
	private String orgaoEmissor;
	private LocalDate dataExpedicao;
	private String cpf;
	private String email;
	private BigDecimal salario;
	private Boolean ativo;
	private EnderecoDTO endereco;
	private List<ContatoDTO> contatos = new ArrayList<>();
	private List<ImovelDTO> imoveis = new ArrayList<>();
	private List<LocacaoDTO> locacoes = new ArrayList<>();
	
	public PessoaDTO() {}
	
	public PessoaDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.tipoPessoa = pessoa.getTipoPessoa().getDescricao();
		this.nome = pessoa.getNome();
		this.nacionalidade = pessoa.getNacionalidade();
		this.estadoCivil = pessoa.getEstadoCivil().getDescricao();
		this.dataNascimento = pessoa.getDataNascimento();
		this.identidade = pessoa.getIdentidade();
		this.orgaoEmissor = pessoa.getOrgaoEmissor();
		this.dataExpedicao = pessoa.getDataExpedicao();
		this.cpf = pessoa.getCpf();
		this.email = pessoa.getEmail();
		this.salario = pessoa.getSalario();
		this.ativo = pessoa.getAtivo();
		this.endereco = new EnderecoDTO(pessoa.getEndereco());
		this.contatos = pessoa.getContatos().stream().map(ContatoDTO::new).collect(Collectors.toList());
		this.imoveis = pessoa.getImoveis().stream().map(ImovelDTO::new).collect(Collectors.toList());
		this.locacoes = pessoa.getLocacoes().stream().map(LocacaoDTO::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
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

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
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

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public List<ContatoDTO> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoDTO> contatos) {
		this.contatos = contatos;
	}

	public List<ImovelDTO> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<ImovelDTO> imoveis) {
		this.imoveis = imoveis;
	}

	public List<LocacaoDTO> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<LocacaoDTO> locacoes) {
		this.locacoes = locacoes;
	}
	
	public void addContato(ContatoDTO contatoDTO) {
		this.contatos.add(contatoDTO);
	}
	
}
