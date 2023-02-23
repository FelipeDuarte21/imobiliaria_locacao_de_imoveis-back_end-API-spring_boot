package br.com.luizfelipeduarte.imobiliariaapi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.luizfelipeduarte.imobiliariaapi.controller.exception.ObjectNotFoundException;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Imovel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ImovelDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ImovelDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.ImovelRepository;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.ObjectNotFoundFromParameterException;

@Service
public class ImovelService {
	
	private ImovelRepository repository;
	
	private EnderecoService enderecoService;
	private PessoaService pessoaService;
	
	@Autowired
	public ImovelService(ImovelRepository repository,EnderecoService enderecoService,
			PessoaService pessoaService) {
		this.repository = repository;
		this.enderecoService = enderecoService;
		this.pessoaService = pessoaService;
	}
	
	public ImovelDTO cadastrar(ImovelDadosDTO imovelDadosDTO) {
		
		Pessoa proprietario = this.pessoaService.buscarProprietarioPorId(imovelDadosDTO.getIdProprietario());
		
		Imovel imovel = new Imovel(imovelDadosDTO);
		imovel.setProprietario(proprietario);
		imovel.setEndereco(this.enderecoService.salvar(imovelDadosDTO.getEndereco()));
		
		return new ImovelDTO(imovel);
	}
	
	public ImovelDTO atualizar(Long id, ImovelDadosDTO imovelDadosDTO) {
		
		Optional<Imovel> optImovel = this.repository.findById(imovelDadosDTO.getId());
		
		if(optImovel.isEmpty()) throw new ObjectNotFoundException("Erro! Imóvel não encontrado!");
		
		Imovel imovel = optImovel.get();
		imovel.setDescricao(imovelDadosDTO.getDescricao());
		imovel.setPreco(imovelDadosDTO.getPreco());
		imovel.setTipo(imovelDadosDTO.getTipo());
		imovel.setEndereco(this.enderecoService.salvar(imovelDadosDTO.getEndereco()));
		
		imovel = this.repository.save(imovel);
		
		return new ImovelDTO(imovel);
		
	}
	
	public void excluir(Long id) {
		
		Optional<Imovel> optImovel = this.repository.findById(id);
		
		if(optImovel.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! imóvel não econtrado!");
		
		this.repository.delete(optImovel.get());
 		
	}
	
	public ImovelDTO buscarPorId(Long id) {
		
		Optional<Imovel> optImovel = this.repository.findById(id);
		
		if(optImovel.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! imóvel não econtrado!");
		
		return new ImovelDTO(optImovel.get());
		
	}
	
	public Page<ImovelDTO> buscarTodos(Pageable paginacao){
		
		Page<Imovel> imoveis = this.repository.findAll(paginacao);
		
		return imoveis.map(ImovelDTO::new);
		
	}
	
	public Page<ImovelDTO> buscarDisponiveis(Pageable paginacao){
		
		Page<Imovel> imoveis = this.repository.findByDisponivel(true, paginacao);
		
		return imoveis.map(ImovelDTO::new);
		
	}
	
	public Page<ImovelDTO> buscarDisponiveisPorPreco(BigDecimal preco,Pageable paginacao){
		
		Page<Imovel> imoveis = this.repository.findByPrecoLessThanEqualAndDisponivel(preco,true,paginacao);
		
		return imoveis.map(ImovelDTO::new);
		
	}
	
	public Page<ImovelDTO> buscarDisponiveisPorProprietario(Long id, Pageable paginacao){
		
		Pessoa proprietario = this.pessoaService.buscarProprietarioPorId(id);
		
		Page<Imovel> imoveis = this.repository.findByDisponivelAndProprietario(true, proprietario, paginacao);
	
		return imoveis.map(ImovelDTO::new);
		
	}
	
	public Page<ImovelDTO> buscarPorProprietario(Long id, Pageable paginacao) {
		
		Pessoa proprietario = this.pessoaService.buscarProprietarioPorId(id);
		
		Page<Imovel> imoveis = this.repository.findByProprietario(proprietario, paginacao);
		
		return imoveis.map(ImovelDTO::new);
		
	}
	
	public Page<ImovelDTO> buscarPorNomeProprietario(String nome,Pageable paginacao){
		
		Page<Imovel> imoveis;
		
		if(nome.isEmpty() || nome == null) {
			imoveis = this.repository.findAll(paginacao);
		}else {
			imoveis = this.repository.findByNomeProprietario(nome, paginacao);
		}
		
		return imoveis.map(ImovelDTO::new);
	}
	
}
