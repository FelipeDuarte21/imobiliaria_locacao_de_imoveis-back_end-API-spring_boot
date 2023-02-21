package br.com.luizfelipeduarte.imobiliariaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizfelipeduarte.imobiliariaapi.controller.exception.ObjectBadRequestException;
import br.com.luizfelipeduarte.imobiliariaapi.controller.exception.ObjectNotFoundException;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Aluguel;
import br.com.luizfelipeduarte.imobiliariaapi.service.AluguelService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/aluguel")
public class AluguelResource {

	@Autowired
	private AluguelService aluguelService;
	
	@PutMapping("/{id}")
	private ResponseEntity<Aluguel> recordPayment(@PathVariable Integer id){
		
		Aluguel a = aluguelService.recordPayment(id);
		
		if(a == null) {
			throw new ObjectBadRequestException("Erro ao Registrar Pagamento!");
		}
		
		if(a.getQuite() == false) {
			throw new ObjectNotFoundException("Aluguel Não Encontrado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(a);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Aluguel> findById(@PathVariable Integer id){
		
		Aluguel aluguel = aluguelService.findById(id);
		
		if(aluguel == null) {
			throw new ObjectNotFoundException("Nenhum aluguel encontrado para o id informado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(aluguel);
	}
	
	@GetMapping("/locacao/{id}")
	private ResponseEntity<Page<Aluguel>> findByInquilino(@PathVariable Integer id,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Aluguel> alugueis = aluguelService.findByLocacao(id,page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(alugueis);
	}
	
	@GetMapping("/periodo")
	private ResponseEntity<Page<Aluguel>> findByPeriodo(
			@RequestParam(name="inicio") String inicio,
			@RequestParam(name="fim") String fim,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Aluguel> alugueis = aluguelService.findByPeriodo(inicio, fim, page, size);
		
		return ResponseEntity.status(HttpStatus.OK).body(alugueis);
	}
	
	@GetMapping
	private ResponseEntity<Page<Aluguel>> findAll(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Aluguel> alugueis = aluguelService.findAll(page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(alugueis);
	}
	
	@GetMapping("/atrasados")
	private ResponseEntity<Page<Aluguel>> findAllAtrasados(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Aluguel> alugueis = aluguelService.findAllAtrasados(page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(alugueis);
	}
	
	@GetMapping("/atrasados/all")
	private ResponseEntity<List<Aluguel>> findAllAtrasados(){
		
		List<Aluguel> alugueis = this.aluguelService.findAllAtrasados();
		
		if(alugueis == null) {
			throw new ObjectNotFoundException("Não Há Alugueis Cadastrados!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(alugueis);
	}
	
}
