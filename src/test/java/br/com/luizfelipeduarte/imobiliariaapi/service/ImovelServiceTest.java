package br.com.luizfelipeduarte.imobiliariaapi.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.luizfelipeduarte.imobiliariaapi.ImobiliariaApiApplicationTest;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.ImovelRepository;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.ObjectNotFoundFromParameterException;

public class ImovelServiceTest extends ImobiliariaApiApplicationTest {
	
	@MockBean
	private ImovelRepository repository;

	@Autowired
	private ImovelService service;
	
	@Test
	public void verificandoErroCasoImovelNaoEncontradoEmAtualizar() {
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		assertThrows(ObjectNotFoundFromParameterException.class, () -> this.service.atualizar(1L, null));
		
	}
	
	@Test
	public void verificandoErroCasoImovelNaoEncontradoEmExcluir() {
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		assertThrows(ObjectNotFoundFromParameterException.class, () -> this.service.excluir(1L));
		
	}
	
	@Test
	public void verificandoErroCasoImovelNaoEncontradoEmBuscarPorId() {
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		assertThrows(ObjectNotFoundFromParameterException.class, () -> this.service.buscarPorId(1L));
		
	}
	
	@Test
	public void verificandoErroCasoImovelNaoEncontradoEmBuscarImovelPorId() {
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		assertThrows(ObjectNotFoundFromParameterException.class, () -> this.service.buscarImovelPorId(1L));
		
	}
	
	
}
