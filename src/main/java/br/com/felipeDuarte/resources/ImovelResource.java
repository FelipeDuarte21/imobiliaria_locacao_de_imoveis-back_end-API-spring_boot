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

import br.com.felipeDuarte.domain.Imovel;
import br.com.felipeDuarte.resources.exception.ObjectBadRequestException;
import br.com.felipeDuarte.resources.exception.ObjectNotFoundException;
import br.com.felipeDuarte.services.ImovelService;

@CrossOrigin("http://localhost")
@RestController
public class ImovelResource {
	
	@Autowired
	private ImovelService imovelService;
	
	
	@PostMapping("/imovel")
	private ResponseEntity<?> save(@Valid @RequestBody Imovel imovel){
		
		Imovel i = imovelService.save(imovel);
		
		if(i == null) {
			throw new ObjectBadRequestException("Imóvel Já cadastrado!");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(i);
		
	}
	
	@PutMapping("/imovel")
	private ResponseEntity<?> update(@Valid @RequestBody Imovel imovel){
		
		Imovel i = imovelService.update(imovel);
		
		if(i == null) {
			throw new ObjectBadRequestException("Falha ao tentar atualizar!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(i);
	}
	
	@DeleteMapping("/imovel")
	private ResponseEntity<?> delete(@Valid @RequestBody Imovel imovel){
		
		imovelService.delete(imovel);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
		
	}
	
	@GetMapping("/imovel/{id}")
	private ResponseEntity<?> findById(@PathVariable Integer id){
		
		Imovel imovel = imovelService.findById(id);
		
		if(imovel == null) {
			throw new ObjectNotFoundException("Não há imovel para o id informado!");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(imovel);
		}
		
	}
	
	@GetMapping("/imovel")
	private List<Imovel> findAll(){
		
		List<Imovel> imoveis = imovelService.findAll();
		
		if(imoveis == null) {
			throw new ObjectNotFoundException("Não há imóveis cadastrados!");
		}
		
		return imoveis;
	}
	
	@GetMapping("/imovel/disponivel")
	private List<Imovel> findByDisponivel(){
		
		List<Imovel> imoveis = imovelService.findByDisponivel();
		
		if(imoveis == null) {
			throw new ObjectNotFoundException("Não há imóveis disponíveis para locação!");
		}
		
		return imoveis;
	}
	
	@GetMapping("/imovel/disponivel/{preco}")
	private List<Imovel> findByDisponivel(@PathVariable Double preco){
		
		List<Imovel> imoveis = imovelService.findByDisponivel(preco);
		
		if(imoveis == null) {
			throw new ObjectNotFoundException("Não há nenhum imóvel para o valor informado!");
		}
		
		return imoveis;
	}
	
	@GetMapping("/imovel/proprietario/{id}")
	private List<Imovel> findByProprietario(@PathVariable Integer id){
		
		List<Imovel> imoveis = imovelService.findByProprietario(id);
		
		if(imoveis == null) {
			throw new ObjectNotFoundException("Não há nenhum imóvel para este proprietário!");
		}
		
		return imoveis;
	}
	
	@GetMapping("/imovel/proprietario/nome/{nome}")
	private List<Imovel> findByNome(@PathVariable String nome){
		
		List<Imovel> imoveis = imovelService.findByNomeProprietario(nome);
		
		if(imoveis == null) {
			throw new ObjectNotFoundException("Não há nenhum imóvel para este proprietário!");
		}
		
		return imoveis;
	}

}
