package br.com.luizfelipeduarte.imobiliariaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Bairro;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Cidade;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Complemento;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Endereco;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Estado;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.LogradouroCep;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Numero;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.EnderecoDadosDTO;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.BairroRepository;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.CidadeRepository;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.ComplementoRepository;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.EnderecoRepository;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.EstadoRepository;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.LogradouroCepRepository;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.NumeroRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private BairroRepository bairroRepository;
	
	@Autowired
	private ComplementoRepository complementoRepository;
	
	@Autowired
	private LogradouroCepRepository logradouroCepRepository;
	
	@Autowired
	private NumeroRepository numeroRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Endereco save(Endereco endereco) {
		
		Estado estado = estadoRepository.findByNome(
				endereco.getLogradouroCep().getBairro().getCidade().getEstado().getNome());
		if(estado == null) {
			estadoRepository.save(
					endereco.getLogradouroCep().getBairro().getCidade().getEstado());
		}else {
			endereco.getLogradouroCep().getBairro().getCidade().getEstado().setIdEstado(
					estado.getIdEstado());
		}
		
		
		Cidade cidade = cidadeRepository.findByNome(
				endereco.getLogradouroCep().getBairro().getCidade().getNome());
		if(cidade == null) {
			cidadeRepository.save(endereco.getLogradouroCep().getBairro().getCidade());
		}else {
			endereco.getLogradouroCep().getBairro().getCidade().setIdCidade(cidade.getIdCidade());
		}
		
		
		Bairro bairro = bairroRepository.findByNome(
				endereco.getLogradouroCep().getBairro().getNome());
		if(bairro == null) {
			bairroRepository.save(endereco.getLogradouroCep().getBairro());
		}else {
			endereco.getLogradouroCep().getBairro().setIdBairro(bairro.getIdBairro());
		}
		
		if(endereco.getLogradouroCep().getComplemento() != null) {
			Complemento complemento = complementoRepository.findByComplemento(
					endereco.getLogradouroCep().getComplemento().getComplemento());
			if(complemento == null) {
				complementoRepository.save(endereco.getLogradouroCep().getComplemento());
			}else {
				endereco.getLogradouroCep().getComplemento().setIdComplemento(
						complemento.getIdComplemento());
			}
		}
		
		LogradouroCep logradouroCep = logradouroCepRepository.findByCep(
				endereco.getLogradouroCep().getCep());
		if(logradouroCep == null) {
			logradouroCepRepository.save(endereco.getLogradouroCep());
		}else {
			endereco.getLogradouroCep().setIdLogradouroCep(logradouroCep.getIdLogradouroCep());
		}
		
		Numero numero = numeroRepository.findByNumero(endereco.getNumero().getNumero());
		if(numero == null) {
			endereco.getNumero().setIdNumero(null);
			numeroRepository.save(endereco.getNumero());
		}else {
			endereco.getNumero().setIdNumero(numero.getIdNumero());
		}
		
		
		Endereco e = enderecoRepository.findByLogradouroCepAndNumero(
				endereco.getLogradouroCep(), endereco.getNumero());
		
		if(e == null) {
			enderecoRepository.save(endereco);
		}else {
			endereco.setIdEndereco(e.getIdEndereco());
		}
		
		return endereco;
	}
	
	public Endereco estruturaEndereco(EnderecoDadosDTO enderecoDTO) {
		
		Endereco e = new Endereco();
		e.setLogradouroCep(new LogradouroCep());
		e.getLogradouroCep().setBairro(new Bairro());
		e.getLogradouroCep().getBairro().setCidade(new Cidade());
		e.getLogradouroCep().getBairro().getCidade().setEstado(new Estado());
		e.setNumero(new Numero());
		
		e.getLogradouroCep().setCep(enderecoDTO.getCep());
		e.getLogradouroCep().setLogradouro(enderecoDTO.getLogradouro());
		e.getLogradouroCep().getBairro().setNome(enderecoDTO.getBairro());
		e.getLogradouroCep().getBairro().getCidade().setNome(enderecoDTO.getCidade());
		e.getLogradouroCep().getBairro().getCidade().getEstado().setNome(enderecoDTO.getEstado());
		e.getNumero().setNumero(enderecoDTO.getNumero());
		
		if(enderecoDTO.getComplemento() != null) {
			e.getLogradouroCep().setComplemento(new Complemento());
			e.getLogradouroCep().getComplemento().setComplemento(enderecoDTO.getComplemento());
		}
		
		return e;
	}
	
	
	
}
