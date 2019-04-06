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

import br.com.felipeDuarte.domain.Pessoa;
import br.com.felipeDuarte.resources.exception.ObjectBadRequestException;
import br.com.felipeDuarte.resources.exception.ObjectNotFoundException;
import br.com.felipeDuarte.services.PessoaService;

@CrossOrigin("http://localhost")
@RestController
public class PessoaResource {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/pessoa/cpf/{cpf}")
	private ResponseEntity<?> findByCpf(@PathVariable String cpf){
		Pessoa p = pessoaService.findByCpf(cpf);
		
		if(p == null) {
			throw new ObjectNotFoundException("Nenhuma pessoa encontrada para o CPF informado!");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(p);
		}
	}
	
	@PostMapping("/pessoa")
	private ResponseEntity<?> save(@Valid @RequestBody Pessoa pessoa) {
		
		Pessoa p  = pessoaService.save(pessoa);
		
		if(p == null) {
			throw new ObjectBadRequestException("Cliente Já cadastrado!");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(p);
	}
	
	@PutMapping("/pessoa")
	private ResponseEntity<?> update(@Valid @RequestBody Pessoa pessoa) {
		
		Pessoa p = pessoaService.update(pessoa);
		
		if(p == null) {
			throw new ObjectBadRequestException("Falha ao tentar atualizar!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(p);
		
	}
	
	@DeleteMapping("/pessoa")
	private ResponseEntity<?> delete(@Valid @RequestBody Pessoa pessoa) {
		
		pessoaService.delete(pessoa);
		
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	@GetMapping("/pessoa/{id}")
	private ResponseEntity<?> buscarPessoaId(@PathVariable Integer id) {
		
		Pessoa p = pessoaService.findById(id);
		
		if(p == null) {
			throw new ObjectNotFoundException("Não há cliente para esse id!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}
	
	@GetMapping("/proprietarios")
	private List<Pessoa> findAllProprietarios(){
		
		List<Pessoa> proprietarios = pessoaService.findAllProprietarios();
		
		if(proprietarios == null) {
			throw new ObjectNotFoundException("Não há proprietários cadastrados!");
		}
		
		return proprietarios;
	}
	
	@GetMapping("/inquilinos")
	private List<Pessoa> findAllInquilinos(){
		
		List<Pessoa> inquilinos  = pessoaService.findAllInquilinos();
		
		if(inquilinos == null) {
			throw new ObjectNotFoundException("Não há inquilinos cadastrados!");
		}
		
		return inquilinos;
	}
	
	
}
