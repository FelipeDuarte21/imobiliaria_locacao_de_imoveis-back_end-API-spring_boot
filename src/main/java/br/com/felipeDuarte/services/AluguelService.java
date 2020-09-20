package br.com.felipeDuarte.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	
	public Aluguel recordPayment(Integer idAluguel){
		
		try {
			
			Aluguel aluguel = this.findById(idAluguel);
			
			if(aluguel == null) {
				Aluguel a = new Aluguel();
				a.setQuite(false);
				return a;
			}
			
			aluguel.setQuite(true);
			
			Calendar data = Calendar.getInstance();
			aluguel.setDataPagamento(data.getTime());
			
			aluguelRepository.save(aluguel);
			
			return aluguel;
			
		}catch(RuntimeException ex) {
			return null;
		}
		
	}
	
	public Aluguel findById(Integer id){
		
		Optional<Aluguel> aluguel = aluguelRepository.findById(id);
		
		if(!aluguel.isPresent()) {
			return null;
		}
		
		return aluguel.get();
	}
	
	public Page<Aluguel> findByLocacao(Integer id, Integer page, Integer size){
		
		PageRequest pageable = PageRequest.of(page, size,Direction.ASC,"dataVencimento");
		
		Page<Aluguel> alugueis = aluguelRepository.findByLocacao(locacaoService.findById(id),pageable);
		
		return alugueis;
	}
	
	public Page<Aluguel> findByPeriodo(String inicio,String fim,Integer page,Integer size){
		
		PageRequest pageable = PageRequest.of(page, size,Direction.ASC,"dataVencimento");
		
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFim = Calendar.getInstance();
		
		String[] datas = inicio.split("/");
		dataInicio.set(Integer.parseInt(datas[2]),
			Integer.parseInt(datas[1]) - 1,
			Integer.parseInt(datas[0]));
		
		datas = fim.split("/");
		dataFim.set(Integer.parseInt(datas[2]),
				Integer.parseInt(datas[1]) - 1,
				Integer.parseInt(datas[0]));
		
		Page<Aluguel> alugueis = 
		aluguelRepository.findByDataVencimentoBetweenOrderByDataVencimento(dataInicio.getTime(), dataFim.getTime(),pageable);
		
		return alugueis;
	}
	
	public Page<Aluguel> findAll(Integer page,Integer size){
		
		PageRequest pageable = PageRequest.of(page, size,Direction.ASC,"dataVencimento");
		
		Page<Aluguel> alugueis = aluguelRepository.findAll(pageable);
		
		return alugueis;
	}
	
	public void delete(Aluguel aluguel) {
		aluguelRepository.delete(aluguel);
	}
	
	public Page<Aluguel> findAllAtrasados(Integer page,Integer size){
		
		PageRequest pageable = PageRequest.of(page, size,Direction.ASC, "dataVencimento");
		
		Date hora = new Date();
		
		Page<Aluguel> alugueis = 
				this.aluguelRepository.findByDataVencimentoLessThanOrderByDataVencimento(hora, pageable);
		
		return alugueis;
	}
	
}
