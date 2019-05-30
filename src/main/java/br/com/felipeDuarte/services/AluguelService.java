package br.com.felipeDuarte.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
		
		try {
		
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
	
	public List<Aluguel> findByLocacao(Integer id){
		
		List<Aluguel> alugueis = aluguelRepository.findByLocacao(locacaoService.findById(id));
		
		if(alugueis.isEmpty()) {
			return null;
		}
		
		return alugueis;
	}
	
	public List<Aluguel> findByPeriodo(String inicio,String fim){
		
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
		
		List<Aluguel> alugueis = 
		aluguelRepository.findByDataVencimentoBetweenOrderByDataVencimento(dataInicio.getTime(), dataFim.getTime());
		
		if(alugueis.isEmpty()) {
			return null;
		}
		
		return alugueis;
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
	
	public List<Aluguel> findAllAtrasados(){
		Date hora = new Date();
		long dataAtual = hora.getTime();
		
		
		
		List<Aluguel> alugueis = findAll();
		
		if(alugueis == null) {
			return null;
		}else {
			
			List<Aluguel> alugueisAtrasados = new ArrayList<>();
		
			alugueis.forEach(aluguel -> {
				if(aluguel.getDataVencimento().getTime() < dataAtual) {
					alugueisAtrasados.add(aluguel);
				}
			});
			
			if(alugueisAtrasados.isEmpty()) {
				return null;
			}
			
			return alugueisAtrasados;
		}
		
	}
	
}
