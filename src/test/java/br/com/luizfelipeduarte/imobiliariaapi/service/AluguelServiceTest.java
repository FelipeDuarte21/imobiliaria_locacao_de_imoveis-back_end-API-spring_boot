package br.com.luizfelipeduarte.imobiliariaapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.luizfelipeduarte.imobiliariaapi.ImobiliariaApiApplicationTest;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Aluguel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Locacao;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.AluguelRepository;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.ObjectNotFoundFromParameterException;

public class AluguelServiceTest extends ImobiliariaApiApplicationTest {
	
	@MockBean
	private AluguelRepository repository;
	
	@Autowired
	private AluguelService service;
	
	@Captor
	private ArgumentCaptor<List<Aluguel>> captorAlugueis;
	
	@Captor
	private ArgumentCaptor<Aluguel> captorAluguel;
	
	@Test
	public void verificandoDataVencimentoEmAlugueisGerados() {
		
		Locacao l = new Locacao();
		l.setTempo("12");
		l.setDataInicio(LocalDate.of(2023, Month.MARCH, 11));
		
		this.service.gerarAlugueis(l);
		
		Mockito.verify(repository).saveAll(captorAlugueis.capture());
		
		List<Aluguel> alugueisRetornado = captorAlugueis.getValue();
		
		for(int i=1; i <= Integer.parseInt(l.getTempo()); i++) {
			Aluguel aluguel = alugueisRetornado.get((i-1));
			assertEquals(l.getDataInicio().plusMonths(i), aluguel.getDataVencimento());
		}
		
	}
	
	@Test
	public void verificandoQuiteEDataPagamentoEmPagamentoAluguel() {
		
		Aluguel aluguel = new Aluguel();
		aluguel.setId(1L);
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(aluguel));
		
		Mockito.when(repository.save(Mockito.any())).thenReturn(aluguel);
		
		this.service.pagarAlguel(1L);
		
		Mockito.verify(repository).save(captorAluguel.capture());
		
		Aluguel aluguelCapturado = captorAluguel.getValue();
		
		assertEquals(true, aluguelCapturado.getQuite());
		assertEquals(LocalDate.now(), aluguelCapturado.getDataPagamento());
		
	}
	
	@Test
	public void verificandoErroCasoNaoEncontreAluguelEmPagamentoAluguel() {
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		assertThrows(ObjectNotFoundFromParameterException.class, () -> this.service.pagarAlguel(1L));
		
	}
	
	@Test
	public void verificandoErroCasoNaoEcontreAluguelQuandoBuscadoPeloId() {
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		assertThrows(ObjectNotFoundFromParameterException.class, () -> service.buscarPorId(1L));
		
	}

}
