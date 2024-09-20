package br.com.luizfelipeduarte.imobiliariaapi.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.luizfelipeduarte.imobiliariaapi.ImobiliariaApiApplicationTest;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.PessoaDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.enums.TipoPessoa;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.PessoaRepository;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.IllegalParameterException;
import br.com.luizfelipeduarte.imobiliariaapi.service.exception.ObjectNotFoundFromParameterException;

public class PessoaServiceTest extends ImobiliariaApiApplicationTest {
	
	@MockBean
	private PessoaRepository repository;
	
	@MockBean
	private ContatoService contatoService;
	
	@MockBean
	private EnderecoService enderecoService;
	
	@Autowired
	private PessoaService service;
	
	@Test
	public void verificandoErroCasoNaoEncontrePessoaEmBuscarPorCPF() {
		
		Mockito.when(repository.findByCpf(Mockito.anyString())).thenReturn(Optional.empty());
		
		assertThrows(ObjectNotFoundFromParameterException.class, () -> this.service.buscarPorCPF("99999999999"));
		
	}
	
	@Test
	public void verificandoErroCasoPessoaJaFoiCadastradaEmCadastrar() {
		
		Pessoa p = new Pessoa();
		p.setAtivo(true);
		p.setTipoPessoa(TipoPessoa.PROPRIETARIO);
		
		Mockito.when(repository.findByCpf(Mockito.anyString())).thenReturn(Optional.of(p));
		
		PessoaDadosDTO pd = new PessoaDadosDTO();
		pd.setCpf("99999999999");
		pd.setTipoPessoa(TipoPessoa.PROPRIETARIO.getCod());
		
		assertThrows(IllegalParameterException.class, () -> this.service.cadastrar(pd));
		
	}
	
}
