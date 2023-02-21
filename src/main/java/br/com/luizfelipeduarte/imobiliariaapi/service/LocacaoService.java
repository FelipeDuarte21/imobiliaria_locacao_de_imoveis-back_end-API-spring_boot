package br.com.luizfelipeduarte.imobiliariaapi.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Aluguel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Imovel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Locacao;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.LocacaoDTO;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.LocacaoRepository;

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
	
	
	private Locacao converteLocacaoDTO(LocacaoDTO locacaoDTO) {
		
		Locacao locacao = new Locacao();
		locacao.setIdLocacao(locacaoDTO.getIdLocacao());
		locacao.setData(locacaoDTO.getData());
		locacao.setDataInicio(locacaoDTO.getDataInicio());
		locacao.setDataTermino(locacaoDTO.getDataTermino());
		locacao.setTempo(locacaoDTO.getTempo());
		locacao.setValor(locacaoDTO.getValor());
		
		Imovel i = this.imovelService.findById(locacaoDTO.getIdImovel());
		if(i == null) {
			locacao.setImovel(null);
		}else {
			locacao.setImovel(i);
		}
		
		Pessoa p = this.pessoaService.findById(locacaoDTO.getIdInquilino());
		if(p == null) {
			locacao.setInquilino(null);
		}else {
			locacao.setInquilino(p);
		}
		
		return locacao;
	}
	
	public Locacao save(LocacaoDTO locacaoDTO) {
		
		Locacao locacao = this.converteLocacaoDTO(locacaoDTO);
		
		if(locacao.getImovel() == null || locacao.getInquilino() == null) {
			return locacao;
		}
		
		Optional<Locacao> l =  locacaoRepository.findByImovel(
					imovelService.findById(locacao.getImovel().getIdImovel()));
		
		if(l.isPresent()) {
			return null;
		}
		
		locacao.setAlugueis(this.createAluguel(locacao));
		
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
	
	public Locacao update(LocacaoDTO locacaoDTO) {
		
		Locacao loc = this.converteLocacaoDTO(locacaoDTO);
		if(loc.getImovel() == null || loc.getInquilino() == null) {
			return loc;
		}
		
		Locacao l = this.findById(loc.getIdLocacao());
		
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.setTime(loc.getDataInicio());
		
		for(int i = 1; i <= Integer.parseInt(l.getTempo()); i++) {
			
			Calendar dataVencimento = Calendar.getInstance();
			dataVencimento.setTime(dataInicio.getTime());
			dataVencimento.add(Calendar.MONTH, i);
 			
			l.getAlugueis().get(i-1).setDataVencimento(dataVencimento.getTime());
		}
		
		aluguelService.saveAll(l.getAlugueis());
		
		try {
			locacaoRepository.save(loc);
		}catch(RuntimeException ex) {
			return null;
		}
		
		return loc;
	}
	
	public boolean delete(Integer id) {
		
		Locacao locacao = this.findById(id);
		
		if(locacao == null) {
			return false;
		}
		
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
		return true;
	}
	
	public Locacao findById(Integer id) {
		
		Optional<Locacao> l = locacaoRepository.findById(id);
		
		if(!l.isPresent()) {
			return null;
		}
		
		return l.get();
	}
	
	public Page<Locacao> findAll(Integer page,Integer size){
		
		PageRequest pageable = PageRequest.of(page, size,Direction.ASC,"data");
		
		Page<Locacao> locacoes = locacaoRepository.findAll(pageable);
		
		return locacoes;
	}
	
	public List<Locacao> findAll(){
		
		List<Locacao> locacoes = this.locacaoRepository.findAll();
		
		if(locacoes.isEmpty()) {
			return null;
		}
		
		return locacoes;
	}
	
	private List<Locacao> findByInquilino(Integer id){
		
		List<Locacao> locacoes = locacaoRepository.findByInquilino(pessoaService.findById(id));
		
		if(locacoes.isEmpty()) {
			return null;
		}
		
		return locacoes;
	}
	
	public Page<Locacao> findByInquilino(Integer id,Integer page,Integer size){
		
		PageRequest pageable = PageRequest.of(page, size,Direction.ASC,"data");
		
		Page<Locacao> locacoes = locacaoRepository.findByInquilino(pessoaService.findById(id),pageable);
		
		return locacoes;
	}
	
	public Locacao findByImovel(Imovel imovel) {
		Optional<Locacao> l = locacaoRepository.findByImovel(imovel);
		return l.get();
	}
	
	public Page<Locacao> findByNomeInquilino(String nome,Integer page,Integer size){
		
		PageRequest pageable = PageRequest.of(page, size,Direction.ASC,"data");
		
		Page<Locacao> locacoes;
		
		if(nome.isEmpty() || nome == null) {
			
			locacoes = this.findAll(page, size);
			
		}else {
		
			locacoes = this.locacaoRepository.findByNomeInquilino(nome,pageable);	
		}
		
		return locacoes;
	}
	
	public Page<Locacao> findByNomeProprietario(String nome,Integer page,Integer size){
		
		PageRequest pageable = PageRequest.of(page, size,Direction.ASC,"data");
		
		Page<Locacao> locacoes;
		
		if(nome.isEmpty() || nome == null) {
			
			locacoes = this.findAll(page, size);
			
		}else {
		
			locacoes = this.locacaoRepository.findByNomeProprietario(nome, pageable);	
		}
		
		return locacoes;
	}
	
}
