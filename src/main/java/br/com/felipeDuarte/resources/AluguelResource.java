package br.com.felipeDuarte.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipeDuarte.domain.Aluguel;
import br.com.felipeDuarte.resources.exception.ObjectBadRequestException;
import br.com.felipeDuarte.resources.exception.ObjectNotFoundException;
import br.com.felipeDuarte.services.AluguelService;

@CrossOrigin("http://localhost")
@RestController
public class AluguelResource {

	@Autowired
	private AluguelService aluguelService;
	
	@PutMapping("/aluguel")
	private ResponseEntity<?> recordPayment(@Valid @RequestBody Aluguel aluguel){
		
		Aluguel a = aluguelService.recordPayment(aluguel);
		
		if(a == null) {
			throw new ObjectBadRequestException("O aluguel já foi pago!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(a);
	}
	
	@GetMapping("/aluguel/{id}")
	private ResponseEntity<?> findById(@PathVariable Integer id){
		
		Aluguel aluguel = aluguelService.findById(id);
		
		if(aluguel == null) {
			throw new ObjectNotFoundException("Nenhum aluguel encontrado para o id informado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(aluguel);
	}
	
	@GetMapping("/aluguel/locacao/{id}")
	private List<Aluguel> findByInquilino(@PathVariable Integer id){
		
		List<Aluguel> alugueis = aluguelService.findByLocacao(id);
		
		if(alugueis == null) {
			throw new ObjectNotFoundException("Nenhum aluguel encontrado para a locação informada!");
		}
		
		return alugueis;
	}
	
	@GetMapping("/aluguel/periodo/{inicio}/{fim}")
	private List<Aluguel> findByPeriodo(@PathVariable("{inicio}") String inicio,@PathVariable("{fim}") String fim){
		return null;
	}
	
	@GetMapping("/aluguel")
	private List<Aluguel> findAll(){
		
		List<Aluguel> alugueis = aluguelService.findAll();
		
		if(alugueis == null) {
			throw new ObjectNotFoundException("Nenhum aluguel cadastrado!");
		}
		
		return alugueis;
	}
	
}
