package br.com.luizfelipeduarte.imobiliariaapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Imovel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Locacao;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.AluguelDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.LocacaoDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.LocacaoDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.LocacaoRepository;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.IllegalParameterException;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.ObjectNotFoundFromParameterException;

@Service
public class LocacaoService {
	
	private LocacaoRepository repository;
	
	private AluguelService aluguelService;
	private ImovelService imovelService;
	private PessoaService pessoaService;
	
	@Autowired
	public LocacaoService(LocacaoRepository repository, AluguelService aluguelService,
			ImovelService imovelService, PessoaService pessoaService) {
		this.repository = repository;
		this.aluguelService = aluguelService;
		this.imovelService = imovelService;
		this.pessoaService = pessoaService;
	}
	
	public LocacaoDTO cadastrar(LocacaoDadosDTO locacaoDadosDTO) {
		
		Imovel imovel = this.imovelService.buscarImovelPorId(locacaoDadosDTO.getIdImovel());
		Pessoa inquilino = this.pessoaService.buscarInquilinoPorId(locacaoDadosDTO.getIdInquilino());
		
		Locacao locacao = new Locacao(locacaoDadosDTO);
		locacao.setImovel(imovel);
		locacao.getImovel().setDisponivel(false);
		locacao.setInquilino(inquilino);
		
		Optional<Locacao> optLocacao =  this.repository.findByImovel(imovel);
		
		if(optLocacao.isPresent()) throw new IllegalParameterException("Erro! O imóvel já está alugado!");
		
		locacao = this.repository.save(locacao);
		
		LocacaoDTO locacaoDTO  = new LocacaoDTO(locacao);
		locacaoDTO.setAlugueis(this.aluguelService.gerarAlugueis(locacao));
		
		return locacaoDTO;
		
	}
	
	public LocacaoDTO atualizar(Long id, LocacaoDadosDTO locacaoDadosDTO) {
		
		Optional<Locacao> optLocacao = this.repository.findById(id);
		
		if(optLocacao.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Locação não econtrada!");
		
		Locacao locacao  = optLocacao.get();
		locacao.setData(locacaoDadosDTO.getData());
		locacao.setDataInicio(locacaoDadosDTO.getDataInicio());
		locacao.setDataTermino(locacaoDadosDTO.getDataTermino());
		locacao.setTempo(locacaoDadosDTO.getTempo());
		locacao.setValor(locacaoDadosDTO.getValor());
	
		locacao = this.repository.save(locacao);
		
		LocacaoDTO locacaoDTO = new LocacaoDTO(locacao);
		locacaoDTO.setAlugueis(this.aluguelService.alterarDataVencimentoAlugueis(locacao));
		
		return locacaoDTO;
		
	}
	
	public void excluir(Long id) {
		
		Optional<Locacao> optLocacao = this.repository.findById(id);
		
		if(optLocacao.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! locação não econtrada!");
		
		this.imovelService.disponibilizarImovel(optLocacao.get().getImovel().getId());
		
		this.repository.delete(optLocacao.get());
		
	}
	
	public LocacaoDTO buscarPorId(Long id) {
		
		Optional<Locacao> optLocacao = this.repository.findById(id);
		
		if(optLocacao.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! locação não econtrada!");
		
		return new LocacaoDTO(optLocacao.get());
		
	}
	
	public Locacao buscarLocacaoPorId(Long id) {
		
		Optional<Locacao> optLocacao = this.repository.findById(id);
		
		if(optLocacao.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! locação não econtrada!");
		
		return optLocacao.get();
		
	}
	
	public Page<AluguelDTO> buscarAlugueis(Long id, Pageable paginacao) {
		
		Locacao locacao = this.buscarLocacaoPorId(id);
		
		Page<AluguelDTO> pgAluguel = this.aluguelService.buscarPorLocacao(locacao, paginacao);
		
		return pgAluguel;
		
	}
	
	public Page<LocacaoDTO> buscarPorInquilino(Long idInquilino,Pageable paginacao){
		
		Pessoa inquilino = this.pessoaService.buscarInquilinoPorId(idInquilino);
		
		Page<Locacao> locacoes = this.repository.findByInquilino(inquilino,paginacao);
		
		return locacoes.map(LocacaoDTO::new);
		
	}
	
	public Page<LocacaoDTO> buscarPorNomeInquilino(String nome,Pageable paginacao){
		
		Page<LocacaoDTO> locacoes;
		
		if(nome.isEmpty() || nome == null) {
			
			locacoes = this.buscarTodos(paginacao);
			
		}else {
		
			locacoes = this.repository.findByNomeInquilino(nome,paginacao).map(LocacaoDTO::new);
		}
		
		return locacoes;
		
	}
	
	public Page<LocacaoDTO> buscarPorNomeProprietario(String nome,Pageable paginacao){
		
		Page<LocacaoDTO> locacoes;
		
		if(nome.isEmpty() || nome == null) {
			
			locacoes = this.buscarTodos(paginacao);
			
		}else {
		
			locacoes = this.repository.findByNomeProprietario(nome, paginacao).map(LocacaoDTO::new);
		}
		
		return locacoes;
	}
	
	public Page<LocacaoDTO> buscarTodos(Pageable paginacao){
		
		Page<Locacao> locacoes = this.repository.findAll(paginacao);
		
		return locacoes.map(LocacaoDTO::new);
		
	}

	
	
}
