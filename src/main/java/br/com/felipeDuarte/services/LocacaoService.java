package br.com.felipeDuarte.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipeDuarte.domain.Aluguel;
import br.com.felipeDuarte.domain.Imovel;
import br.com.felipeDuarte.domain.Locacao;
import br.com.felipeDuarte.repositories.LocacaoRepository;

@Service
public class LocacaoService {
	
	@Autowired
	private LocacaoRepository locacaoRepository;
	
	@Autowired
	private AluguelService aluguelService;
	
	@Autowired
	private ImovelService imovelService;
	
	@Autowired
	private PessoaService pessoaService;
	
	public Locacao save(Locacao locacao) {
		
		Optional<Locacao> l =  locacaoRepository.findByImovel(
					imovelService.findById(locacao.getImovel().getIdImovel()));
		
		if(l.isPresent()) {
			return null;
		}
		
		locacao.setAlugueis(createAluguel(locacao));
		
		locacaoRepository.save(locacao);
		
		aluguelService.saveAll(locacao.getAlugueis());
		
		locacao.getImovel().setDisponivel(false);
		imovelService.update(locacao.getImovel());
		
		return locacao;
	}
	
	private List<Aluguel> createAluguel(Locacao locacao) {
		
		List<Aluguel> alugueis = new ArrayList<>();
	
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.setTime(locacao.getDataInicio());
		
		for(int i = 1; i <= Integer.parseInt(locacao.getTempo()); i++) {
			
			Aluguel aluguel = new Aluguel();
			
			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTime(dataInicio.getTime());
			dataVencimento.add(Calendar.MONTH, i);
 			
			aluguel.setDataVencimento(dataVencimento.getTime());
			aluguel.setLocacao(locacao);
			
			alugueis.add(aluguel);
		}
		
		return alugueis;
	}
	
	public Locacao update(Locacao locacao) {
		
		Locacao l = this.findById(locacao.getIdLocacao());
		
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.setTime(locacao.getDataInicio());
		
		for(int i = 1; i <= Integer.parseInt(l.getTempo()); i++) {
			
			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTime(dataInicio.getTime());
			dataVencimento.add(Calendar.MONTH, i);
 			
			l.getAlugueis().get(i-1).setDataVencimento(dataVencimento.getTime());
		}
		
		aluguelService.saveAll(l.getAlugueis());
		
		try {
			locacaoRepository.save(locacao);
		}catch(RuntimeException ex) {
			return null;
		}
		
		return locacao;
	}
	
	public void delete(Locacao locacao) {
		
		Locacao l = this.findById(locacao.getIdLocacao());
		
		l.getAlugueis().forEach(aluguel -> {
			aluguelService.delete(aluguel);
		});
		
		l.getImovel().setDisponivel(true);
		imovelService.update(l.getImovel());
		
		if(this.findByInquilino(l.getInquilino().getIdPessoa()).size() == 1 || 
			this.findByInquilino(l.getInquilino().getIdPessoa()) == null) {
			pessoaService.deleteInquilinoProprietario(l.getInquilino());
		}
		
		locacaoRepository.delete(l);
		
	}
	
	public Locacao findById(Integer id) {
		
		Optional<Locacao> l = locacaoRepository.findById(id);
		
		if(!l.isPresent()) {
			return null;
		}
		
		return l.get();
	}
	
	public List<Locacao> findAll(){
		List<Locacao> locacoes = locacaoRepository.findAll();
		
		if(locacoes.isEmpty()) {
			return null;
		}
		
		return locacoes;
	}
	
	public List<Locacao> findByInquilino(Integer id){
		List<Locacao> locacoes = locacaoRepository.findByInquilino(pessoaService.findById(id));
		
		if(locacoes.isEmpty()) {
			return null;
		}
		
		return locacoes;
	}
	
	public Locacao findByImovel(Imovel imovel) {
		Optional<Locacao> l = locacaoRepository.findByImovel(imovel);
		return l.get();
	}
	
}
