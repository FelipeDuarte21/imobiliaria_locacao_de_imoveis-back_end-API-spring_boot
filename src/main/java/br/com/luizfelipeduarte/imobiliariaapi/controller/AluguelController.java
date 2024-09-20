package br.com.luizfelipeduarte.imobiliariaapi.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizfelipeduarte.imobiliariaapi.controller.exception.ObjectNotFoundException;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.AluguelDTO;
import br.com.luizfelipeduarte.imobiliariaapi.service.AluguelService;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.ObjectNotFoundFromParameterException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/aluguel")
public class AluguelController {

	private AluguelService service;
	
	public AluguelController(AluguelService service) {
		this.service = service;
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<AluguelDTO> pagarAlguel(@PathVariable Long id){
		
		try {
			
			AluguelDTO aluguel = this.service.pagarAlguel(id);
			
			return ResponseEntity.ok(aluguel);
			
		}catch (ObjectNotFoundFromParameterException  ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<AluguelDTO> buscarPorId(@PathVariable Long id){
		
		try {
			
			AluguelDTO aluguel = this.service.buscarPorId(id);
			
			return ResponseEntity.ok(aluguel);
		
		}catch (ObjectNotFoundFromParameterException  ex) {
			throw new ObjectNotFoundException(ex.getMessage());
		}
		
	}
	
	@GetMapping("/periodo")
	private ResponseEntity<Page<AluguelDTO>> buscarPorPeriodo(
			@RequestParam(name="inicio") LocalDate inicio,
			@RequestParam(name="fim") LocalDate fim,
			@PageableDefault(page = 0, size = 6, direction = Direction.ASC, sort = "dataVencimento") Pageable paginacao){
		
		Page<AluguelDTO> pgAlugueis = this.service.buscarPorPeriodo(inicio, fim, paginacao);
		
		return ResponseEntity.ok(pgAlugueis);
	}
	
	@GetMapping("/atrasados")
	private ResponseEntity<Page<AluguelDTO>> buscarTodosAtrasados(
			@PageableDefault(page = 0, size = 6, direction = Direction.ASC, sort = "dataVencimento") Pageable paginacao){
		
		Page<AluguelDTO> pgAlugueis = this.service.buscarTodosAtrasados(paginacao);
		
		return ResponseEntity.ok(pgAlugueis);
		
	}
	
	@GetMapping
	private ResponseEntity<Page<AluguelDTO>> findAll(
			@PageableDefault(page = 0, size = 6, direction = Direction.ASC, sort = "dataVencimento") Pageable paginacao){
		
		Page<AluguelDTO> pgAlugueis = this.service.buscarTodos(paginacao);
		
		return ResponseEntity.ok(pgAlugueis);
		
	}
	
	
	
}
