package br.com.felipeDuarte.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipeDuarte.domain.Bairro;
import br.com.felipeDuarte.domain.Cidade;
import br.com.felipeDuarte.domain.Complemento;
import br.com.felipeDuarte.domain.Endereco;
import br.com.felipeDuarte.domain.Estado;
import br.com.felipeDuarte.domain.LogradouroCep;
import br.com.felipeDuarte.domain.Numero;
import br.com.felipeDuarte.repositories.BairroRepository;
import br.com.felipeDuarte.repositories.CidadeRepository;
import br.com.felipeDuarte.repositories.ComplementoRepository;
import br.com.felipeDuarte.repositories.EnderecoRepository;
import br.com.felipeDuarte.repositories.EstadoRepository;
import br.com.felipeDuarte.repositories.LogradouroCepRepository;
import br.com.felipeDuarte.repositories.NumeroRepository;

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
	
}
