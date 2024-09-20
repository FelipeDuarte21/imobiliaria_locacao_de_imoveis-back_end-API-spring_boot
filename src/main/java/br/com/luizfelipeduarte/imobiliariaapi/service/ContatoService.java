package br.com.luizfelipeduarte.imobiliariaapi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Contato;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ContatoDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ContatoDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.ContatoRepository;

@Service
public class ContatoService {
	
	private ContatoRepository repository;
	
	public ContatoService(ContatoRepository repository) {
		this.repository = repository;
	}
	
	public ContatoDTO cadastrar(Pessoa pessoa, ContatoDadosDTO contatoDadosDTO) {
		
		System.out.println(contatoDadosDTO);
		
		Optional<Contato> optContato = this.repository.findByNumero(contatoDadosDTO.getNumero());
		
		if(optContato.isPresent()) return new ContatoDTO(optContato.get());
		
		Contato contato = new Contato(contatoDadosDTO);
		contato.setPessoa(pessoa);
		
		contato = this.repository.save(contato);
		
		return new ContatoDTO(contato);
		
	}
	
	public void excluir(Long id) {
		
		Optional<Contato> optContato = this.repository.findById(id);
		
		if(optContato.isPresent()) {
			this.repository.delete(optContato.get());
		}
		
	}
	
	
	
}
