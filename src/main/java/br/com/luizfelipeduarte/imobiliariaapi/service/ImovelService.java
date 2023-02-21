package br.com.luizfelipeduarte.imobiliariaapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Imovel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ImovelDTO;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.ImovelRepository;

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
	
	private Imovel converteImovelDTO(ImovelDTO imovelDTO) {
		
		//Dados Gerais
		Imovel i = new Imovel();
		i.setIdImovel(imovelDTO.getIdImovel());
		i.setPreco(imovelDTO.getPreco());
		i.setDescricao(imovelDTO.getDescricao());
		i.setTipo(imovelDTO.getTipo());
		i.setDisponivel(true);
		
		//Endereco
		i.setEndereco(this.enderecoService.estruturaEndereco(imovelDTO.getEndereco()));
		
		//Proprietario
		Pessoa p = this.pessoaService.findById(imovelDTO.getIdProprietario());
		if(p == null) {
			i.setProprietario(null);
		}else {
			i.setProprietario(p);
		}
		
		return i;
	}
	
	public Imovel save(ImovelDTO imovelDTO) {
		
		Imovel imovel = this.converteImovelDTO(imovelDTO);
		
		if(imovel.getProprietario() == null) {
			return imovel;
		}
		
		enderecoService.save(imovel.getEndereco());
		
		if(imovelRepository.findByEndereco(imovel.getEndereco()) != null) {
			return null;
		}
		
		imovelRepository.save(imovel);
		
		return imovel;
	}
	
	public Imovel update(ImovelDTO imovelDTO) {
		
		Imovel imovel = this.converteImovelDTO(imovelDTO);
		if(imovel.getProprietario() == null) {
			return imovel;
		}
		
		return this.update(imovel);
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
				locacaoService.delete(locacaoService.findByImovel(imovel).getIdLocacao());
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
	
	public List<Imovel> findAll(){
		
		List<Imovel> imoveis = this.imovelRepository.findAll();
		
		if(imoveis.isEmpty()) {
			return null;
		}
		
		return imoveis;
	}
	
	public List<Imovel> findByDisponivel(Integer id){
		
		List<Imovel> imoveis = this.imovelRepository.findByDisponivelAndProprietario(true,
				this.pessoaService.findById(id));
		
		if(imoveis.isEmpty()) {
			return null;
		}
		
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
