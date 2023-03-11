package br.com.luizfelipeduarte.imobiliariaapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Contato;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ContatoDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ContatoDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.PessoaDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.PessoaDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.enums.TipoEstadoCivil;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.enums.TipoPessoa;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.PessoaRepository;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.IllegalParameterException;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.ObjectNotFoundFromParameterException;

@Service
public class PessoaService {
	
	private PessoaRepository repository;
	
	private ContatoService contatoService;
	private EnderecoService enderecoService;
	
	@Autowired
	public PessoaService(PessoaRepository repository, ContatoService contatoService,EnderecoService enderecoService) {
		this.repository = repository;
		this.contatoService = contatoService;
		this.enderecoService = enderecoService;
	}
	
	public PessoaDTO buscarPorCPF(String cpf) {
		
		Optional<Pessoa> optPessoa = this.repository.findByCpf(cpf);
		 
		if(optPessoa.isEmpty()) 
			 throw new ObjectNotFoundFromParameterException("Erro! Pessoa não encontrada para o CPF Informado!");
		 
		return new PessoaDTO(optPessoa.get());
		 
	}
	
	public PessoaDTO cadastrar(PessoaDadosDTO pessoaDadosDTO) {
		
		Optional<Pessoa> optPessoa = this.repository.findByCpf(pessoaDadosDTO.getCpf());
		
		if(optPessoa.isPresent() && optPessoa.get().getAtivo() && 
				TipoPessoa.toEnum(pessoaDadosDTO.getTipoPessoa()).equals(optPessoa.get().getTipoPessoa())) {
			throw new IllegalParameterException("Erro! pessoa já cadastrada!");
		}
		
		Pessoa pessoa = new Pessoa(pessoaDadosDTO);
		
		if(optPessoa.isPresent()) pessoa.setId(optPessoa.get().getId());
		
		if(optPessoa.isPresent() && !pessoa.getTipoPessoa().equals(optPessoa.get().getTipoPessoa()))
			pessoa.setTipoPessoa(TipoPessoa.PROPRIETARIO_E_INQUILINO);
		
		pessoa.setEndereco(this.enderecoService.salvar(pessoaDadosDTO.getEndereco()));
		
		pessoa = this.repository.save(pessoa);
		
		PessoaDTO pessoaDTO  = new PessoaDTO(pessoa);
		
		//Exclui contatos
		if(optPessoa.isPresent()) {
			List<Contato> contatosExcluidos = new ArrayList<>();
			for(Contato contato: optPessoa.get().getContatos()) {
				boolean achou = false;
				for(Contato contato2: pessoa.getContatos()) {
					if(contato.getNumero().equals(contato2.getNumero())) {
						achou = true;
						break;
					}
				}
				if(!achou) {
					contatosExcluidos.add(contato);
				}
			}
			contatosExcluidos.forEach(contato -> this.contatoService.excluir(contato.getId()));
		}
		
		//Cadastrar contatos
		List<ContatoDTO> contatos = new ArrayList<>();
		for(ContatoDadosDTO contato: pessoaDadosDTO.getContatos()) {
			contatos.add(this.contatoService.cadastrar(pessoa, contato));
		}
		pessoaDTO.setContatos(contatos);
		
		return pessoaDTO;
		
	}
	
	public PessoaDTO atualizar(Long id, PessoaDadosDTO pessoaDadosDTO) {
		
		Optional<Pessoa> optPessoa = this.repository.findById(id);
		
		if(optPessoa.isEmpty()) 
			throw new ObjectNotFoundFromParameterException("Erro! Pessoa não encontrada para o id informado!");
		
		Pessoa pessoa  = optPessoa.get();
		pessoa.setNome(pessoaDadosDTO.getNome());
		pessoa.setNacionalidade(pessoaDadosDTO.getNacionalidade());
		pessoa.setDataNascimento(pessoaDadosDTO.getDataNascimento()); 
		pessoa.setEstadoCivil(TipoEstadoCivil.toEnum(pessoaDadosDTO.getEstadoCivil()));
		pessoa.setCpf(pessoaDadosDTO.getCpf());
		pessoa.setIdentidade(pessoaDadosDTO.getIdentidade());
		pessoa.setOrgaoEmissor(pessoaDadosDTO.getOrgaoEmissor());
		pessoa.setDataExpedicao(pessoaDadosDTO.getDataExpedicao());
		pessoa.setSalario(pessoaDadosDTO.getSalario());
		pessoa.setEmail(pessoaDadosDTO.getEmail());
		pessoa.setEndereco(this.enderecoService.salvar(pessoaDadosDTO.getEndereco()));
		
		pessoa  = this.repository.save(pessoa);
		
		PessoaDTO pessoaDTO = new PessoaDTO(pessoa);
		
		//Exclui contatos
		List<Contato> contatosExcluidos = new ArrayList<>();
		for(Contato contato: pessoa.getContatos()) {
			boolean achou = false;
			for(Contato contato2: pessoa.getContatos()) {
				if(contato.getNumero().equals(contato2.getNumero())) {
					achou = true;
					break;
				}
			}
			if(!achou) {
				contatosExcluidos.add(contato);
			}
		}
		contatosExcluidos.forEach(contato -> this.contatoService.excluir(contato.getId()));
				
		//Cadastrar contatos
		List<ContatoDTO> contatos = new ArrayList<>();
		for(ContatoDadosDTO contato: pessoaDadosDTO.getContatos()) {
			contatos.add(this.contatoService.cadastrar(pessoa, contato));
		}
		pessoaDTO.setContatos(contatos);
		
		return pessoaDTO;
		
	}
	
	public void excluir(Long id){
		
		Optional<Pessoa> optPessoa = this.repository.findById(id);
		
		if(optPessoa.isEmpty()) 
			throw new ObjectNotFoundFromParameterException("Erro! Pessoa não econtrada para id informado!");
		
		
		this.repository.delete(optPessoa.get());
		
	}
	
	public PessoaDTO buscarPorId(Long id) {
		
		Optional<Pessoa> optPessoa = this.repository.findById(id);
		
		if(optPessoa.isEmpty()) {
			throw new ObjectNotFoundFromParameterException("Erro! Pessoa não encontrada para id informado!");
		}
			
		return new PessoaDTO(optPessoa.get());
	}
	
	public Page<PessoaDTO> buscarProprietarios(Pageable paginacao){
		return this.buscarTodos(TipoPessoa.PROPRIETARIO, TipoPessoa.PROPRIETARIO_E_INQUILINO, paginacao);
	}
	
	public Pessoa buscarProprietarioPorId(Long id) {
		
		Optional<Pessoa> optProprietario = this.repository.findByAtivoAndTipoPessoaAndTipoPessoa(true, 
				TipoPessoa.PROPRIETARIO, TipoPessoa.PROPRIETARIO_E_INQUILINO);
		
		if(optProprietario.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Proprietario não econtrado!");
		
		return optProprietario.get();
		
	}
	
	public Pessoa buscarInquilinoPorId(Long id) {
		
		Optional<Pessoa> optInquilino = this.repository.findByAtivoAndTipoPessoaAndTipoPessoa(true, 
				TipoPessoa.INQUILINO, TipoPessoa.PROPRIETARIO_E_INQUILINO);
		
		if(optInquilino.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Inquilino não econtrado!");
		
		return optInquilino.get();
		
	}
	
	public Page<PessoaDTO> buscarProprietarioPorNome(String nome,Pageable paginacao){
		return this.buscarContendoNome(TipoPessoa.PROPRIETARIO, 
				TipoPessoa.PROPRIETARIO_E_INQUILINO,nome,paginacao);	
	}
	
	public Page<PessoaDTO> buscarInquilinos(Pageable paginacao){
		return this.buscarTodos(TipoPessoa.INQUILINO, TipoPessoa.PROPRIETARIO_E_INQUILINO,paginacao);
	}
	
	public Page<PessoaDTO> buscarInquilinoPorNome(String nome,Pageable paginacao){	
		return this.buscarContendoNome(TipoPessoa.INQUILINO, TipoPessoa.PROPRIETARIO_E_INQUILINO, nome, paginacao);
	}
	
	private Page<PessoaDTO> buscarContendoNome(TipoPessoa tipo1, TipoPessoa tipo2, String nome, Pageable paginacao){
		
		if(!nome.isEmpty()) {		
			return this.repository.findByAtivoAndNomeContainingAndTipoPessoaOrTipoPessoa(true,nome,tipo1,tipo2,paginacao)
					.map(PessoaDTO::new);
		}
		
		return this.buscarTodos(tipo1, tipo2, paginacao);
		
	}
	
	private Page<PessoaDTO> buscarTodos(TipoPessoa tipo1,TipoPessoa tipo2,Pageable paginacao){
		return this.repository.findByAtivoAndTipoPessoaOrTipoPessoa(true,tipo1,tipo2,paginacao).map(PessoaDTO::new);
	}

	public void deleteInquilinoProprietario(Pessoa pessoa) {
		pessoa.setAtivo(false);
		this.repository.save(pessoa);
	}
	
}