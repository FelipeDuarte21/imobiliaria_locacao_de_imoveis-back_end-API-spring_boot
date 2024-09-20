package br.com.luizfelipeduarte.imobiliariaapi.service;

/*
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;*/

import br.com.luizfelipeduarte.imobiliariaapi.ImobiliariaApiApplicationTest;
/*import br.com.luizfelipeduarte.imobiliariaapi.entidade.Imovel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Locacao;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.LocacaoDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.LocacaoRepository;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.IllegalParameterException;*/

public class LocacaoServiceTest extends ImobiliariaApiApplicationTest{

	/* 	
	@MockBean
	private LocacaoRepository repository;
	
	@MockBean
	private ImovelService imovelService;
	
	@MockBean
	private PessoaService pessoaService;
	
	@MockBean
	private AluguelService aluguelService;
	
	@Autowired
	private LocacaoService service;*/
	
	/* 
	@Test
	public void verificandoErroDeImovelJaAlugadoEmCadastrar() {
				
		Mockito.when(imovelService.buscarImovelPorId(Mockito.anyLong())).thenReturn(new Imovel());
		Mockito.when(pessoaService.buscarInquilinoPorId(Mockito.anyLong())).thenReturn(new Pessoa());
		
		Mockito.when(repository.findByImovel(Mockito.any(Imovel.class))).thenReturn(Optional.of(new Locacao()));
		
		assertThrows(IllegalParameterException.class, () -> this.service.cadastrar(new LocacaoDadosDTO()));
		
	}*/
	 
}
