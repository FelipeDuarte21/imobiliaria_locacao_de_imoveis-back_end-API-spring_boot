package br.com.luizfelipeduarte.imobiliariaapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Aluguel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Locacao;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.AluguelDTO;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.AluguelRepository;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.ObjectNotFoundFromParameterException;

@Service
public class AluguelService {
	
	private AluguelRepository repository;
	
	public AluguelService(AluguelRepository repository) {
		this.repository = repository;
	}
	
	public List<AluguelDTO> gerarAlugueis(Locacao locacao){
		
		List<Aluguel> alugueis = new ArrayList<>();
		
		for(int i = 1; i <= Integer.parseInt(locacao.getTempo()); i++) {
			
			Aluguel aluguel = new Aluguel();
			aluguel.setDataVencimento(locacao.getDataInicio().plusMonths(i));
			aluguel.setLocacao(locacao);
			
			alugueis.add(aluguel);
			
		}
		
		alugueis = this.repository.saveAll(alugueis);
		
		return alugueis.stream().map(AluguelDTO::new).toList();
		
	}
	
	public List<AluguelDTO> alterarDataVencimentoAlugueis(Locacao locacao){
		
		List<Aluguel> alugueis = locacao.getAlugueis();
		
		for(int i = 1; i <= Integer.parseInt(locacao.getTempo()); i++) {
			
			LocalDate dataVencimento = locacao.getDataInicio().plusMonths(i);
 			
			alugueis.get(i-1).setDataVencimento(dataVencimento);
		
		}
		
		alugueis = this.repository.saveAll(alugueis);
		
		return alugueis.stream().map(AluguelDTO::new).toList();
		
	}

	public AluguelDTO pagarAlguel(Long id){
		
		Optional<Aluguel> optAluguel = this.repository.findById(id);
		
		if(optAluguel.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! Aluguel não encontrado!");
		
		Aluguel aluguel = optAluguel.get();
		aluguel.setQuite(true);
		aluguel.setDataPagamento(LocalDate.now());
			
		aluguel = this.repository.save(aluguel);
			
		return new AluguelDTO(aluguel);
		
	}
	
	public AluguelDTO buscarPorId(Long id){
		
		Optional<Aluguel> optAluguel = this.repository.findById(id);
		
		if(optAluguel.isEmpty()) throw new ObjectNotFoundFromParameterException("Erro! aluguel não econtrado!");
		
		return new AluguelDTO(optAluguel.get());
		
	}
	
	public Page<AluguelDTO> buscarPorLocacao(Locacao locacao, Pageable paginacao){
		
		Page<Aluguel> pgAlugueis = this.repository.findByLocacao(locacao,paginacao);
		
		return pgAlugueis.map(AluguelDTO::new);
		
	}
	
	public Page<AluguelDTO> buscarPorPeriodo(LocalDate inicio, LocalDate fim, Pageable paginacao){
		
		Page<Aluguel> pgAlugueis = 
				this.repository.findByDataVencimentoBetweenOrderByDataVencimento(inicio,fim,paginacao);
		
		return pgAlugueis.map(AluguelDTO::new);
		
	}
	
	public Page<AluguelDTO> buscarTodosAtrasados(Pageable paginacao){
		
		LocalDate agora = LocalDate.now();
		
		Page<Aluguel> pgAlugueis = 
				this.repository.findByDataVencimentoLessThanAndQuiteOrderByDataVencimento(agora, false, paginacao);
		
		return pgAlugueis.map(AluguelDTO::new);
		
	}
	
	public Page<AluguelDTO> buscarTodos(Pageable paginacao){
		
		Page<Aluguel> pgAlugueis = this.repository.findAll(paginacao);
		
		return pgAlugueis.map(AluguelDTO::new);
		
	}
	
}
