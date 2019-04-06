package br.com.felipeDuarte.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeDuarte.domain.Locacao;
import br.com.felipeDuarte.resources.exception.ObjectBadRequestException;
import br.com.felipeDuarte.resources.exception.ObjectNotFoundException;
import br.com.felipeDuarte.services.LocacaoService;

@CrossOrigin("http://localhost")
@RestController
public class LocacaoResource {

	@Autowired
	private LocacaoService locacaoService;
	
	@PostMapping("/locacao")
	private ResponseEntity<?> save(@Valid @RequestBody Locacao locacao) {
		
		Locacao l = locacaoService.save(locacao);
		
		if(l == null) {
			throw new ObjectBadRequestException("Imóvel já alugado!");
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(l);
		}
		
	}
	
	@PutMapping("/locacao")
	private ResponseEntity<?> update(@Valid @RequestBody Locacao locacao){
		
		Locacao l = locacaoService.update(locacao);
		
		if(l == null) {
			throw new ObjectBadRequestException("Falha ao tentar atualizar!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}
	
	@DeleteMapping("/locacao")
	private ResponseEntity<?> delete(@Valid @RequestBody Locacao locacao) {
		
		locacaoService.delete(locacao);
		
		return ResponseEntity.status(HttpStatus.OK).body("");
		
	}
	
	@GetMapping("/locacao/{id}")
	private ResponseEntity<?> findById(@PathVariable Integer id) {
		
		Locacao l = locacaoService.findById(id);
		
		if(l == null) {
			throw new ObjectNotFoundException("Nenhuma locação encontrada!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}
	
	@GetMapping("/locacao")
	private List<Locacao> findAll(){
		
		List<Locacao> locacoes = locacaoService.findAll();
		
		if(locacoes == null) {
			throw new ObjectNotFoundException("Nenhuma locação cadastrada!");
		}
		
		return locacoes;
	}
	
	@GetMapping("/locacao/inquilino/{id}")
	private List<Locacao> findByInquilino(@PathVariable Integer id){
		
		List<Locacao> locacoes = locacaoService.findByInquilino(id);
		
		if(locacoes == null) {
			throw new ObjectNotFoundException("Nenhuma locação para o inquilino informado!");
		}
		
		return locacoes;
	}
	 
}
