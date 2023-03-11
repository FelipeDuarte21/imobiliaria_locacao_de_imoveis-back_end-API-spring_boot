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
	
	private EstadoRepository estadoRepository;
	private CidadeRepository cidadeRepository;
	private BairroRepository bairroRepository;
	private ComplementoRepository complementoRepository;
	private LogradouroCepRepository logradouroCepRepository;
	private NumeroRepository numeroRepository;
	private EnderecoRepository enderecoRepository;
	
	
	@Autowired
	public EnderecoService(EstadoRepository estadoRepository, CidadeRepository cidadeRepository,
			BairroRepository bairroRepository, ComplementoRepository complementoRepository,
			LogradouroCepRepository logradouroCepRepository, NumeroRepository numeroRepository,
			EnderecoRepository enderecoRepository) {
		this.estadoRepository = estadoRepository;
		this.cidadeRepository = cidadeRepository;
		this.bairroRepository = bairroRepository;
		this.complementoRepository = complementoRepository;
		this.logradouroCepRepository = logradouroCepRepository;
		this.numeroRepository = numeroRepository;
		this.enderecoRepository = enderecoRepository;
	}

	public Endereco salvar(EnderecoDadosDTO enderecoDadosDTO) {
		
		Endereco endereco = new Endereco(enderecoDadosDTO);
		
		Estado estado = estadoRepository.findByNome(
				endereco.getLogradouroCep().getBairro().getCidade().getEstado().getNome());
		if(estado == null) {
			estado = estadoRepository.save(endereco.getLogradouroCep().getBairro().getCidade().getEstado());
		}
		endereco.getLogradouroCep().getBairro().getCidade().setEstado(estado);
		
		
		Cidade cidade = cidadeRepository.findByNome(
				endereco.getLogradouroCep().getBairro().getCidade().getNome());
		if(cidade == null) {
			cidade = cidadeRepository.save(endereco.getLogradouroCep().getBairro().getCidade());
		}
		endereco.getLogradouroCep().getBairro().setCidade(cidade);
		
		
		Bairro bairro = bairroRepository.findByNome(
				endereco.getLogradouroCep().getBairro().getNome());
		if(bairro == null) {
			bairro = bairroRepository.save(endereco.getLogradouroCep().getBairro());
		}
		endereco.getLogradouroCep().setBairro(bairro);
		
		
		if(endereco.getLogradouroCep().getComplemento() != null) {
			Complemento complemento = complementoRepository.findByComplemento(
					endereco.getLogradouroCep().getComplemento().getComplemento());
			if(complemento == null) {
				complemento = complementoRepository.save(endereco.getLogradouroCep().getComplemento());
			}
			endereco.getLogradouroCep().setComplemento(complemento);
			
		}
		
		LogradouroCep logradouroCep = logradouroCepRepository.findByCep(endereco.getLogradouroCep().getCep());
		if(logradouroCep == null) {
			logradouroCep = logradouroCepRepository.save(endereco.getLogradouroCep());
		}
		endereco.setLogradouroCep(logradouroCep);
		
		
		Numero numero = numeroRepository.findByNumero(endereco.getNumero().getNumero());
		if(numero == null) {
			numero = numeroRepository.save(endereco.getNumero());
		}
		endereco.setNumero(numero);
		
		
		Endereco e = enderecoRepository.findByLogradouroCepAndNumero(endereco.getLogradouroCep(), endereco.getNumero());
		
		if(e != null) {
			endereco.setId(e.getId());
		}
		
		endereco = enderecoRepository.save(endereco);
		
		return endereco;
	}
	
}
