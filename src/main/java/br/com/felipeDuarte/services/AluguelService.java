package br.com.felipeDuarte.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipeDuarte.domain.Aluguel;
import br.com.felipeDuarte.repositories.AluguelRepository;

@Service
public class AluguelService {
	
	@Autowired
	private AluguelRepository aluguelRepository;
	
	@Autowired
	private LocacaoService locacaoService;
	
	
	public List<Aluguel> saveAll(List<Aluguel> alugueis) {
		return aluguelRepository.saveAll(alugueis);
	}
	
	
	public Aluguel recordPayment(Aluguel aluguel){
		
		Aluguel a = this.findById(aluguel.getIdAluguel());
		
		if(a.getQuite()) {
			return null;
		}
		
		aluguelRepository.save(aluguel);
		
		return aluguel;
	}
	
	public Aluguel findById(Integer id){
		
		Optional<Aluguel> aluguel = aluguelRepository.findById(id);
		
		if(!aluguel.isPresent()) {
			return null;
		}
		
		return aluguel.get();
	}
	
	public List<Aluguel> findByLocacao(Integer id){
		
		List<Aluguel> alugueis = aluguelRepository.findByLocacao(locacaoService.findById(id));
		
		if(alugueis.isEmpty()) {
			return null;
		}
		
		return alugueis;
	}
	
	public List<Aluguel> findByPeriodo(String inicio,String fim){
		return null;
	}
	
	public List<Aluguel> findAll(){
		
		List<Aluguel> alugueis = aluguelRepository.findAll();
		
		if(alugueis.isEmpty()) {
			return null;
		}
		
		return alugueis;
	}
	
	public void delete(Aluguel aluguel) {
		aluguelRepository.delete(aluguel);
	}
	
}
