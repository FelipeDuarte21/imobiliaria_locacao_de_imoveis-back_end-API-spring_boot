package br.com.felipeDuarte.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	
	public boolean delete(Integer id) {
		
		Imovel imovel = this.findById(id);
		
		if(imovel != null) {
			
			if(!imovel.getDisponivel()) {
				locacaoService.delete(locacaoService.findByImovel(imovel));
			}
			
			imovelRepository.delete(imovel);
			
			return true;
		}
		
		return false;
	}
	
	public Imovel findById(Integer id) {
		
		Optional<Imovel> imovel = imovelRepository.findById(id);
		
		if(!imovel.isPresent()) {
			return null;
		}
		
		return imovel.get();
	}
	
	public Page<Imovel> findAll(Integer page, Integer size){
		
		PageRequest pageable = PageRequest.of(page, size);
		
		Page<Imovel> imoveis = this.imovelRepository.findAll(pageable);
		
		return imoveis;
	}
	
	public Page<Imovel> findByDisponivel(Integer page, Integer size){
		
		PageRequest pageable = PageRequest.of(page, size,Direction.ASC,"preco");
		
		Page<Imovel> imoveis = imovelRepository.findByDisponivel(true, pageable);
		
		return imoveis;
	}
	
	public Page<Imovel> findByDisponivel(Double preco,Integer page,Integer size){
		
		PageRequest pageable = PageRequest.of(page, size,Direction.ASC, "preco");
		
		Page<Imovel> imoveis = imovelRepository.findByPrecoLessThanEqualAndDisponivel(preco,true,pageable);
		
		return imoveis;
	}
	
	public List<Imovel> findByProprietario(Integer id){
		
		List<Imovel> imoveis = imovelRepository.findByProprietario(pessoaService.findById(id));
		
		if(imoveis.isEmpty()) {
			return null;
		}
		
		return imoveis;
	}
	
	public Page<Imovel> findByNomeProprietario(String nome,Integer page,Integer size){
		
		PageRequest pageable = PageRequest.of(page, size,Direction.ASC,"preco");
		
		Page<Imovel> imoveis;
		
		if(nome.isEmpty() || nome == null) {
			imoveis = imovelRepository.findAll(pageable);
		}else {
			imoveis = imovelRepository.findByNomeProprietario(nome,pageable);
		}
		
		return imoveis;
	}
	
}
