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
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Imovel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ImovelDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.service.ImovelService;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/imovel")
public class ImovelResource {
	
	@Autowired
	private ImovelService imovelService;
	
	
	@PostMapping
	private ResponseEntity<Imovel> save(@Valid @RequestBody ImovelDadosDTO imovel){
		
		Imovel i = imovelService.save(imovel);
		
		if(i == null) {
			throw new ObjectBadRequestException("Imóvel Já cadastrado!");
		}
		
		if(i.getProprietario() == null) {
			throw new ObjectNotFoundException("Proprietário do Imóvel não Encontrado!");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(i);
		
	}
	
	@PutMapping
	private ResponseEntity<Imovel> update(@Valid @RequestBody ImovelDadosDTO imovel){
		
		Imovel i = imovelService.update(imovel);
		
		if(i == null) {
			throw new ObjectBadRequestException("Falha ao tentar atualizar!");
		}
		
		if(i.getProprietario() == null) {
			throw new ObjectNotFoundException("Proprietário do Imóvel não Encontrado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(i);
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> delete(@PathVariable Integer id){
		
		boolean confirma = imovelService.delete(id);
		
		if(confirma) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		
		throw new ObjectBadRequestException("Id informado é Inválido!");
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Imovel> findById(@PathVariable Integer id){
		
		Imovel imovel = imovelService.findById(id);
		
		if(imovel == null) {
			throw new ObjectNotFoundException("Não há imovel para o id informado!");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(imovel);
		}
		
	}
	
	@GetMapping
	private ResponseEntity<Page<Imovel>> findAll(
			@RequestParam(defaultValue = "0") Integer page, 
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Imovel> imoveis = imovelService.findAll(page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(imoveis);
	}
	
	@GetMapping("/all")
	private ResponseEntity<List<Imovel>> findAll(){
		
		List<Imovel> imoveis = this.imovelService.findAll();
		
		if(imoveis == null) {
			throw new ObjectNotFoundException("Não Há Imóveis Cadastrados!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(imoveis);
		
	}
	
	@GetMapping("/disponivel")
	private ResponseEntity<Page<Imovel>> findByDisponivel(
			@RequestParam(defaultValue = "0") Integer page, 
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Imovel> imoveis = imovelService.findByDisponivel(page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(imoveis);
	}
	
	@GetMapping("/disponivel/search")
	private ResponseEntity<Page<Imovel>> findByDisponivel(
			@RequestParam(defaultValue = "500") Double preco,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Imovel> imoveis = imovelService.findByDisponivel(preco,page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(imoveis);
	}
	
	@GetMapping("/disponivel/proprietario/{id}")
	private ResponseEntity<List<Imovel>> findByDiponivel(@PathVariable Integer id){
		
		List<Imovel> imoveis = this.imovelService.findByDisponivel(id);
		
		if(imoveis == null) {
			throw new ObjectNotFoundException("Nenhum Imóvel Disponível Encontrado Para o Id Informado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(imoveis);
	}
	
	@GetMapping("/proprietario/{id}")
	private ResponseEntity<List<Imovel>> findByProprietario(@PathVariable Integer id){
		
		List<Imovel> imoveis = imovelService.findByProprietario(id);
		
		if(imoveis == null) {
			throw new ObjectNotFoundException("Não há nenhum imóvel para este proprietário!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(imoveis);
	}
	
	@GetMapping("/proprietario/search")
	private ResponseEntity<Page<Imovel>> findByNome(
			@RequestParam(defaultValue = "") String nome,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "6") Integer size){
		
		Page<Imovel> imoveis = imovelService.findByNomeProprietario(nome,page,size);
		
		return ResponseEntity.status(HttpStatus.OK).body(imoveis);
	}

}
