package br.com.luizfelipeduarte.imobiliariaapi.controller;

import java.net.URI;

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
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.AluguelDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.LocacaoDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.LocacaoDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.service.LocacaoService;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.IllegalParameterException;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.ObjectNotFoundFromParameterException;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/locacao")
public class LocacaoController {

	private LocacaoService service;

	public LocacaoController(LocacaoService service) {
		this.service = service;
	}
	
	@PostMapping
	private ResponseEntity<LocacaoDTO> cadastrar(@Valid @RequestBody LocacaoDadosDTO locacaoDadosDTO,
			UriComponentsBuilder uriBuilder) {
		
		try {
			
			LocacaoDTO locacao = this.service.cadastrar(locacaoDadosDTO);
			
			URI uri = uriBuilder.path("api/v1/locacao/{id}").buildAndExpand(locacao.getId()).toUri();
			
			return ResponseEntity.created(uri).body(locacao);
			
		}catch(ObjectNotFoundFromParameterException | IllegalParameterException ex) {
			throw new ObjectBadRequestException(ex.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<LocacaoDTO> atualizar(@PathVariable(name = "id") Long id, 
			@Valid @RequestBody LocacaoDadosDTO locacaoDadosDTO){
		
		try {
			
			LocacaoDTO locacao = this.service.atualizar(id,locacaoDadosDTO);
			
			return ResponseEntity.ok(locacao);
			
		}catch(ObjectNotFoundFromParameterException  ex) {
			throw new ObjectNotFoundException(ex.getMessage());
			
		}
		
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> excluir(@PathVariable Long id) {
		
		try {
			
			this.service.excluir(id);
			
			return ResponseEntity.ok().build();
			
		}catch(ObjectNotFoundFromParameterException  ex) {
			throw new ObjectNotFoundException(ex.getMessage());
			
		}
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<LocacaoDTO> buscarPorId(@PathVariable Long id) {
		
		try {
			
			LocacaoDTO locacao = this.service.buscarPorId(id);
			
			return ResponseEntity.ok(locacao);
			
		}catch(ObjectNotFoundFromParameterException  ex) {
			throw new ObjectNotFoundException(ex.getMessage());
			
		}
	
	}
	
	@GetMapping("/{id}/alugueis")
	private ResponseEntity<Page<AluguelDTO>> buscarAlugueis(
			@PathVariable(name = "id") Long id,
			@PageableDefault (page = 0, size = 6, direction = Direction.ASC, sort = "dataVencimento") Pageable paginacao){
		
		try {
			
			Page<AluguelDTO> pgAlugueis = this.service.buscarAlugueis(id, paginacao);
			
			return ResponseEntity.ok(pgAlugueis);
			
		}catch(ObjectNotFoundFromParameterException  ex) {
			throw new ObjectNotFoundException(ex.getMessage());
			
		}
		
	}
	
	@GetMapping("/inquilino/{id}")
	private ResponseEntity<Page<LocacaoDTO>> buscarPorInquilino(@PathVariable Long idInquilino,
			@PageableDefault (page = 0, size = 6, direction = Direction.ASC, sort = "data") Pageable paginacao){
		
		try {
			
			Page<LocacaoDTO> pgLocacoes = this.service.buscarPorInquilino(idInquilino, paginacao);
			
			return ResponseEntity.ok(pgLocacoes);
			
		}catch(ObjectNotFoundFromParameterException  ex) {
			throw new ObjectNotFoundException(ex.getMessage());
			
		}
		
	}
	
	@GetMapping("/inquilino")
	private ResponseEntity<Page<LocacaoDTO>> buscarPorNomeInquilino(
			@RequestParam(defaultValue = "", name = "nome") String nome,
			@PageableDefault (page = 0, size = 6, direction = Direction.ASC, sort = "data") Pageable paginacao){
		
		Page<LocacaoDTO> pgLocacoes = this.service.buscarPorNomeInquilino(nome, paginacao);
		
		return ResponseEntity.ok(pgLocacoes);
		
	}
	
	@GetMapping("/proprietario")
	private ResponseEntity<Page<LocacaoDTO>> findByNomeProprietario(
			@RequestParam(defaultValue = "", name = "nome") String nome,
			@PageableDefault (page = 0, size = 6, direction = Direction.ASC, sort = "data") Pageable paginacao){
		
		Page<LocacaoDTO> pgLocacoes = this.service.buscarPorNomeProprietario(nome, paginacao);
		
		return ResponseEntity.ok(pgLocacoes);
		
	}
	
	@GetMapping
	private ResponseEntity<Page<LocacaoDTO>> buscarTodos(
			@PageableDefault (page = 0, size = 6, direction = Direction.ASC, sort = "data") Pageable paginacao){
		
		Page<LocacaoDTO> pgLocacoes = this.service.buscarTodos(paginacao);
		
		return ResponseEntity.ok(pgLocacoes);
		
	}
	 
}
