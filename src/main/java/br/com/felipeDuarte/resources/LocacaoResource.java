package br.com.felipeDuarte.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeDuarte.domain.Locacao;
import br.com.felipeDuarte.resources.exception.ObjectBadRequestException;
import br.com.felipeDuarte.resources.exception.ObjectNotFoundException;
import br.com.felipeDuarte.services.LocacaoService;

@CrossOrigin
@RestController
@RequestMapping("/locacao")
public class LocacaoResource {

	@Autowired
	private LocacaoService locacaoService;
	
	@PostMapping
	private ResponseEntity<Locacao> save(@Valid @RequestBody Locacao locacao) {
		
		Locacao l = locacaoService.save(locacao);
		
		if(l == null) {
			throw new ObjectBadRequestException("Imóvel já alugado!");
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(l);
		}
		
	}
	
	@PutMapping
	private ResponseEntity<Locacao> update(@Valid @RequestBody Locacao locacao){
		
		Locacao l = locacaoService.update(locacao);
		
		if(l == null) {
			throw new ObjectBadRequestException("Falha ao tentar atualizar!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> delete(@PathVariable Integer id) {
		
		boolean confirma =  locacaoService.delete(id);
		
		if(confirma) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			throw new ObjectNotFoundException("Id Inválido!");
		}
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Locacao> findById(@PathVariable Integer id) {
		
		Locacao l = locacaoService.findById(id);
		
		if(l == null) {
			throw new ObjectNotFoundException("Nenhuma locação encontrada para o id informado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}
	
	@GetMapping
	private ResponseEntity<Page<Locacao>> findAll(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Locacao> locacoes = locacaoService.findAll(page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(locacoes);
	}
	
	@GetMapping("/inquilino/{id}")
	private ResponseEntity<Page<Locacao>> findByInquilino(@PathVariable Integer id,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Locacao> locacoes = locacaoService.findByInquilino(id,page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(locacoes);
	}
	 
}
