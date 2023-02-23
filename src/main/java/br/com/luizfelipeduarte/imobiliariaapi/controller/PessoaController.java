package br.com.luizfelipeduarte.imobiliariaapi.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.luizfelipeduarte.imobiliariaapi.controller.exception.ObjectBadRequestException;
import br.com.luizfelipeduarte.imobiliariaapi.controller.exception.ObjectNotFoundException;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.PessoaDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.PessoaDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.service.PessoaService;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.IllegalParameterException;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.ObjectNotFoundFromParameterException;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/pessoa")
public class PessoaController {
	
	private PessoaService service;
	
	@Autowired
	public PessoaController(PessoaService service) {
		this.service = service;
	}
	
	@GetMapping
	private ResponseEntity<PessoaDTO> buscarPorCPF(@RequestParam(name = "cpf") String cpf){
		
		try {
			
			PessoaDTO pessoa = this.service.buscarPorCPF(cpf);
			
			return ResponseEntity.ok(pessoa);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			return ResponseEntity.ok().build();
			
		}
		
	}
	
	@PostMapping
	private ResponseEntity<PessoaDTO> cadastrar(@Valid @RequestBody PessoaDadosDTO pessoaDadosDTO,
			UriComponentsBuilder uriBuilder) {
		
		try {
			
			PessoaDTO pessoa  = this.service.cadastrar(pessoaDadosDTO);
			
			URI uri = uriBuilder.path("api/v1/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
			
			return ResponseEntity.created(uri).body(pessoa);
			
		}catch(IllegalParameterException ex) {
			throw new ObjectBadRequestException(ex.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<PessoaDTO> atualizar(@PathVariable(name ="id") Long id,  
			@Valid @RequestBody PessoaDadosDTO pessoaDadosDTO) {
		
		try {
			
			PessoaDTO pessoa = this.service.atualizar(id,pessoaDadosDTO);
			
			return ResponseEntity.ok(pessoa);
			
		}catch (ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> excluir(@PathVariable(name="id") Long id) {
		
		try {
			
			this.service.excluir(id);
			
			return ResponseEntity.ok().build();
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<PessoaDTO> buscarPorId(@PathVariable(name="id") Long id) {
		
		try {
			
			PessoaDTO pessoa = this.service.buscarPorId(id);
			
			return ResponseEntity.ok(pessoa);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/proprietarios")
	private ResponseEntity<Page<PessoaDTO>> buscarProprietarios(@PageableDefault
			(page = 0, size = 6, direction = Direction.ASC, sort = "nome") Pageable paginacao){
		
		Page<PessoaDTO> pgProprietarios = this.service.buscarProprietarios(paginacao);
		
		return ResponseEntity.ok(pgProprietarios);
		
	}
	
	@GetMapping("/proprietario")
	private ResponseEntity<Page<PessoaDTO>> buscarProprietarioPorNome(@RequestParam(defaultValue = "nome") String nome,
			@PageableDefault (page = 0, size = 6, direction = Direction.ASC, sort = "nome") Pageable paginacao){
		
		Page<PessoaDTO> pgProprietarios = this.service.buscarProprietarioPorNome(nome,paginacao);
		
		return ResponseEntity.ok(pgProprietarios);
		
	}
	
	@GetMapping("/inquilinos")
	private ResponseEntity<Page<PessoaDTO>> buscarInquilinos(
			@PageableDefault (page = 0, size = 6, direction = Direction.ASC, sort = "nome") Pageable paginacao){
		
		Page<PessoaDTO> pgInquilinos  = this.service.buscarInquilinos(paginacao);
		
		return ResponseEntity.ok(pgInquilinos);
		
	}
	
	@GetMapping("/inquilino")
	private ResponseEntity<Page<PessoaDTO>> findByNomeInquilino(@RequestParam(defaultValue = "") String nome,
			@PageableDefault (page = 0, size = 6, direction = Direction.ASC, sort = "nome") Pageable paginacao){
		
		Page<PessoaDTO> pqInquilinos = this.service.buscarInquilinoPorNome(nome, paginacao);
		
		return ResponseEntity.ok(pqInquilinos);
	
	}
	
}
