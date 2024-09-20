package br.com.luizfelipeduarte.imobiliariaapi.controller;

import java.math.BigDecimal;
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
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ImovelDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ImovelDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.service.ImovelService;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.ObjectNotFoundFromParameterException;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/imovel")
public class ImovelController {
	
	private ImovelService service;
	
	public ImovelController(ImovelService service) {
		this.service = service;
	}
	
	@PostMapping
	private ResponseEntity<ImovelDTO> cadastrar(@Valid @RequestBody ImovelDadosDTO imovelDadosDTO,
			UriComponentsBuilder uriBuilder){
		
		try {
			
			ImovelDTO imovel = this.service.cadastrar(imovelDadosDTO);
			
			
			URI uri = uriBuilder.path("api/v1/imovel/{id}").buildAndExpand(imovel.getId()).toUri();
			
			return ResponseEntity.created(uri).body(imovel);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectBadRequestException(ex.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<ImovelDTO> atualizar(@PathVariable(name = "id") Long id, 
			@Valid @RequestBody ImovelDadosDTO imovelDadosDTO){
		
		try {
			
			ImovelDTO imovel = this.service.atualizar(id,imovelDadosDTO);
			
			return ResponseEntity.ok(imovel);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
			
		}
		
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> excluir(@PathVariable(name = "id") Long id){
		
		try {
			
			this.service.excluir(id);
			
			return ResponseEntity.ok().build();
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
			
		}

	}
	
	@GetMapping("/{id}")
	private ResponseEntity<ImovelDTO> buscarPorId(@PathVariable(name = "id") Long id){
		
		try {
			
			ImovelDTO imovel = this.service.buscarPorId(id);
			
			return ResponseEntity.ok(imovel);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
			
		}
		
	}
	
	@GetMapping
	private ResponseEntity<Page<ImovelDTO>> buscarTodos(
			@PageableDefault(page = 0, size = 6, direction = Direction.ASC, sort = "preco") Pageable paginacao){
		
		Page<ImovelDTO> imoveis = this.service.buscarTodos(paginacao);
		
		return ResponseEntity.ok(imoveis);
		
	}
	
	@GetMapping("/disponivel")
	private ResponseEntity<Page<ImovelDTO>> buscarDisponiveis(
			@PageableDefault(page = 0, size = 6, direction = Direction.ASC, sort = "preco") Pageable paginacao){
		
		Page<ImovelDTO> imoveis = this.service.buscarDisponiveis(paginacao);
		
		return ResponseEntity.ok(imoveis);
		
	}
	
	@GetMapping("/disponiveis")
	private ResponseEntity<Page<ImovelDTO>> buscarDiponiveisPorPreco(
		@RequestParam(defaultValue = "500", name = "preco") BigDecimal preco,
		@PageableDefault(page = 0, size = 6, direction = Direction.ASC, sort = "preco") Pageable paginacao){
		
		Page<ImovelDTO> imoveis = this.service.buscarDisponiveisPorPreco(preco,paginacao);
		
		return ResponseEntity.ok(imoveis);
		
	}
	
	@GetMapping("/disponivel/proprietario/{id}")
	private ResponseEntity<Page<ImovelDTO>> buscarDisponiveisPorProprietario(@PathVariable Long id,
			@PageableDefault(page = 0, size = 6, direction = Direction.ASC, sort = "preco") Pageable paginacao){
		
		try{
			
			Page<ImovelDTO> imoveis = this.service.buscarDisponiveisPorProprietario(id,paginacao);
			
			return ResponseEntity.ok(imoveis);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/proprietario/{id}")
	private ResponseEntity<Page<ImovelDTO>> buscarPorProprietario(@PathVariable Long id,
			@PageableDefault(page = 0, size = 6, direction = Direction.ASC, sort = "preco") Pageable paginacao){
		
		try{
			
			Page<ImovelDTO> imoveis = this.service.buscarPorProprietario(id,paginacao);
			
			return ResponseEntity.ok(imoveis);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/proprietario")
	private ResponseEntity<Page<ImovelDTO>> buscarPorNomeProprietario(
		@RequestParam(defaultValue = "", name = "nome") String nome,
		@PageableDefault(page = 0, size = 6, direction = Direction.ASC, sort = "preco") Pageable paginacao){
		
		Page<ImovelDTO> imoveis = this.service.buscarPorNomeProprietario(nome,paginacao);
		
		return ResponseEntity.ok(imoveis);
		
	}

}
