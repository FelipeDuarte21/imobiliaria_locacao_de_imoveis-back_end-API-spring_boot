package br.com.felipeDuarte.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipeDuarte.domain.Imovel;
import br.com.felipeDuarte.repositories.ImovelRepository;

@Service
public class ImovelService {
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private LocacaoService locacaoService;
	
	
	public Imovel save(Imovel imovel) {
		
		enderecoService.save(imovel.getEndereco());
		
		if(imovelRepository.findByEndereco(imovel.getEndereco()) != null) {
			return null;
		}
		
		imovelRepository.save(imovel);
		
		return imovel;
	}
	
	public Imovel update(Imovel imovel) {
		
		enderecoService.save(imovel.getEndereco());
		
		imovelRepository.save(imovel);
		
		return imovel;
	}
	
	public void delete(Imovel imovel) {
		
		if(!imovel.getDisponivel()) {
			locacaoService.delete(locacaoService.findByImovel(imovel));
		}
		
		imovelRepository.delete(imovel);
	}
	
	public Imovel findById(Integer id) {
		
		Optional<Imovel> imovel = imovelRepository.findById(id);
		
		if(!imovel.isPresent()) {
			return null;
		}
		
		return imovel.get();
	}
	
	public List<Imovel> findAll(){
		
		List<Imovel> imoveis;
		
		try {
			imoveis = imovelRepository.findAll();
		}catch(Exception ex) {
			return null;
		}
		
		if(imoveis.isEmpty()) {
			return null;
		}
		
		return imoveis;
	}
	
	public List<Imovel> findByDisponivel(){
		
		List<Imovel> imoveis = imovelRepository.findByDisponivel(true);
		
		if(imoveis.isEmpty()) {
			return null;
		}
		
		return imoveis;
	}
	
	public List<Imovel> findByDisponivel(Double preco){
		
		List<Imovel> imoveis = imovelRepository.findByPrecoLessThanEqualAndDisponivel(preco,true);
		
		if(imoveis.isEmpty()) {
			return null;
		}
		
		return imoveis;
	}
	
	public List<Imovel> findByProprietario(Integer id){
		
		List<Imovel> imoveis = imovelRepository.findByProprietario(pessoaService.findById(id));
		
		if(imoveis.isEmpty()) {
			return null;
		}
		
		return imoveis;
	}
	
	public List<Imovel> findByNomeProprietario(String nome){
		
		List<Imovel> imoveis;
		
		if(nome.isEmpty() || nome == null) {
			imoveis = imovelRepository.findAll();
		}else {
			imoveis = imovelRepository.findByNomeProprietario(nome);
		}
		
		if(imoveis.isEmpty()) {
			return null;
		}
		
		return imoveis;
	}
	
}
