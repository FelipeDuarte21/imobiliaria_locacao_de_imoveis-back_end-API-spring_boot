package br.com.luizfelipeduarte.imobiliariaapi.dto;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.luizfelipeduarte.imobiliariaapi.ImobiliariaApiApplicationTest;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Aluguel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Bairro;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Cidade;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Complemento;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Endereco;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Estado;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Imovel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.LogradouroCep;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Numero;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.AluguelDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.EnderecoDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.EnderecoDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ImovelDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.ImovelDadosDTO;

public class DTOsTest extends ImobiliariaApiApplicationTest {
	
	@Test
	public void verificandoConversaoDeAlugelParaAluguelDTO() {
		
		Aluguel aluguel = new Aluguel();
		aluguel.setId(1L);
		aluguel.setDataVencimento(LocalDate.now());
		aluguel.setQuite(false);
		aluguel.setDataPagamento(null);
		
		AluguelDTO aluguelDTO = new AluguelDTO(aluguel);
		
		assertEquals(1L,aluguelDTO.getId());
		assertEquals(LocalDate.now(), aluguelDTO.getDataVencimento());
		assertEquals(false, aluguelDTO.getQuite());
		assertEquals(null, aluguelDTO.getDataPagamento());
		
	}
	
	@Test
	public void verificandoConversaoDeImovelParaImovelDTO() {
		
		Imovel imovel = new Imovel();
		imovel.setId(1L);
		imovel.setPreco(new BigDecimal(200000));
		imovel.setTipo("Casa");
		imovel.setDescricao("blablabla");
		imovel.setDisponivel(true);
		imovel.setEndereco(getEnderecoTeste());
		
		ImovelDTO imovelDTO = new ImovelDTO(imovel);
		
		assertEquals(1L, imovelDTO.getId());
		assertEquals(new BigDecimal(200000), imovelDTO.getPreco());
		assertEquals("Casa", imovelDTO.getTipo());
		assertEquals("blablabla", imovelDTO.getDescricao());
		assertEquals(true, imovelDTO.getDisponivel());
		
	}
	
	@Test
	public void verificandoConversaoDeEnderecoParaEnderecoDTO() {
		
		EnderecoDTO e = new EnderecoDTO(getEnderecoTeste());
		
		assertEquals(1L,e.getId());
		assertEquals("99999999", e.getCep());
		assertEquals("0", e.getNumero());
		assertEquals("Rua Sobe desce e o numero desaparece", e.getLogradouro());
		assertEquals("Complemento inexistente", e.getComplemento());
		assertEquals("lugarNenhum", e.getBairro());
		assertEquals("Cidade do nada", e.getCidade());
		assertEquals("Tao tao distante", e.getEstado());
		
	}
	
	@Test
	public void verificandoConversaoDeImovelDadosDTOParaImovel() {
		
		ImovelDadosDTO imovelDadosDTO = new ImovelDadosDTO();
		imovelDadosDTO.setId(1L);
		imovelDadosDTO.setDescricao("blablabla");
		imovelDadosDTO.setPreco(new BigDecimal(100000));
		imovelDadosDTO.setEndereco(getEnderecoDadosDTO());
		
		Imovel imovel = new Imovel(imovelDadosDTO);
		
		assertEquals(1L, imovel.getId());
		assertEquals("blablabla", imovel.getDescricao());
		assertEquals(new BigDecimal(100000), imovel.getPreco());
		assertTrue(imovel.getDisponivel());
		
	}
	
	@Test
	public void verificandoConversaoDeEnderecoDadosDTOParaEndereco() {
		
		Endereco e = new Endereco(getEnderecoDadosDTO());
		
		assertEquals("99999999", e.getLogradouroCep().getCep());
		assertEquals("AAA", e.getLogradouroCep().getComplemento().getComplemento());
		assertEquals("BBBB", e.getLogradouroCep().getBairro().getNome());
		assertEquals("XXXX", e.getLogradouroCep().getBairro().getCidade().getNome());
		assertEquals("JJJJ", e.getLogradouroCep().getBairro().getCidade().getEstado().getNome());
		assertEquals("UUUUU", e.getLogradouroCep().getLogradouro());
		assertEquals("0", e.getNumero().getNumero());
		
	}
	
	
	private Endereco getEnderecoTeste() {
		
		Endereco e = new Endereco();
		e.setId(1L);
		
		Numero n = new Numero();
		n.setId(1L);
		n.setNumero("0");
		
		e.setNumero(n);
		
		LogradouroCep lc = new LogradouroCep();
		lc.setId(1L);
		lc.setCep("99999999");
		lc.setLogradouro("Rua Sobe desce e o numero desaparece");
		
		Complemento c = new Complemento();
		c.setId(1L);
		c.setComplemento("Complemento inexistente");
		
		lc.setComplemento(c);
		
		Bairro b = new Bairro();
		b.setId(1L);
		b.setNome("lugarNenhum");
		
		Cidade ci = new Cidade();
		ci.setId(1L);
		ci.setNome("Cidade do nada");

		Estado es = new Estado();
		es.setId(1L);
		es.setNome("Tao tao distante");
		
		ci.setEstado(es);
		b.setCidade(ci);
		lc.setBairro(b);
		
		e.setLogradouroCep(lc);
		
		return e;
		
	}
	
	private EnderecoDadosDTO getEnderecoDadosDTO() {
		
		EnderecoDadosDTO e = new EnderecoDadosDTO();
		e.setCep("99999999");
		e.setComplemento("AAA");
		e.setBairro("BBBB");
		e.setCidade("XXXX");
		e.setEstado("JJJJ");
		e.setLogradouro("UUUUU");
		e.setNumero("0");
		
		return e;
		
	}
	
}
