package br.com.luizfelipeduarte.imobiliariaapi.controller;

import java.util.List;

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

import br.com.luizfelipeduarte.imobiliariaapi.controller.exception.ObjectBadRequestException;
import br.com.luizfelipeduarte.imobiliariaapi.controller.exception.ObjectNotFoundException;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.PessoaDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.service.PessoaService;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/cpf")
	private ResponseEntity<Pessoa> findByCpf(@RequestParam(name = "value") String cpf){
		Pessoa p = pessoaService.findByCpf(cpf);
		
		if(p == null) {
			throw new ObjectNotFoundException("Nenhuma pessoa encontrada para o CPF informado!");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(p);
		}
	}
	
	@PostMapping
	private ResponseEntity<Pessoa> save(@Valid @RequestBody PessoaDadosDTO pessoa) {
		
		Pessoa p  = pessoaService.save(pessoa);
		
		if(p == null) {
			throw new ObjectBadRequestException("Cliente Já cadastrado!");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(p);
	}
	
	@PutMapping
	private ResponseEntity<Pessoa> update(@Valid @RequestBody PessoaDadosDTO pessoa) {
		
		Pessoa p = pessoaService.update(pessoa);
		
		if(p == null) {
			throw new ObjectBadRequestException("Falha ao tentar atualizar!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(p);
		
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> delete(@PathVariable Integer id) {
		
		boolean ok = pessoaService.delete(id);
		
		if(ok) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			throw new ObjectBadRequestException("Id Inválido!");
		}
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Pessoa> buscarPessoaId(@PathVariable Integer id) {
		
		Pessoa p = pessoaService.findById(id);
		
		if(p == null) {
			throw new ObjectNotFoundException("Não há cliente para esse id!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}
	
	@GetMapping("/proprietarios/all")
	private ResponseEntity<List<Pessoa>> findAllProprietarios(){
		
		List<Pessoa> proprietarios = this.pessoaService.findAllProprietarios();
		
		if(proprietarios == null) {
			throw new ObjectNotFoundException("Nenhum Proprietário Cadastrado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(proprietarios);
	}
	
	@GetMapping("/proprietarios")
	private ResponseEntity<Page<Pessoa>> findAllProprietarios(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Pessoa> pageProps = pessoaService.findAllProprietarios(page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(pageProps);
	}
	
	@GetMapping("/proprietarios/search")
	private ResponseEntity<Page<Pessoa>> findByNomeProprietario(@RequestParam(defaultValue = "") String nome,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Pessoa> pageProps = pessoaService.findContainingNomeProprietario(nome,page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(pageProps);
	}
	
	@GetMapping("/inquilinos/all")
	private ResponseEntity<List<Pessoa>> findAllInquilinos(){
		
		List<Pessoa> inquilinos = this.pessoaService.findAllInquilinos();
		
		if(inquilinos == null) {
			throw new ObjectNotFoundException("Nenhum Inquilino Cadastrado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(inquilinos);
	}
	
	@GetMapping("/inquilinos")
	private ResponseEntity<Page<Pessoa>> findAllInquilinos(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Pessoa> pageInq  = pessoaService.findAllInquilinos(page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(pageInq);
	}
	
	@GetMapping("/inquilinos/search")
	private ResponseEntity<Page<Pessoa>> findByNomeInquilino(@RequestParam(defaultValue = "") String nome,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Pessoa> pageInq = pessoaService.findContainingNomeInquilino(nome,page,size);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(pageInq);
	}
	
	
}
