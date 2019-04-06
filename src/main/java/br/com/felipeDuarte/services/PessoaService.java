package br.com.felipeDuarte.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipeDuarte.domain.Pessoa;
import br.com.felipeDuarte.domain.enums.TipoPessoa;
import br.com.felipeDuarte.repositories.ContatoRepository;
import br.com.felipeDuarte.repositories.PessoaRepository;


@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private ImovelService imovelService;
	
	@Autowired
	private LocacaoService locacaoService;
	
	
	public Pessoa findByCpf(String cpf) {
		 Optional<Pessoa> p = pessoaRepository.findByCpf(cpf);
		 
		 if(!p.isPresent()) {
			 return null;
		 }
		 
		 return p.get();
	}
	
	private Pessoa logicaSaveUpdate(Pessoa pessoa,boolean opcao) {
		
		Optional<Pessoa> p = pessoaRepository.findByCpf(pessoa.getCpf());
		
		if(p.isPresent()) {
			
			if(opcao) {
				if(p.get().getAtivo()) {
					
					if(pessoa.getTipoPessoa().equals(p.get().getTipoPessoa())) { 
						return null;
					}else {
						pessoa.setTipoPessoa(TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod());	
					}
				
				}else {
					pessoa.setAtivo(true);
				}
			}
			
			int idContatoCadastrado;
			int idContatoRecebido;
				
			for(int i=0; i < p.get().getContatos().size();i++) {
				idContatoCadastrado = p.get().getContatos().get(i).getIdContato();
					
				int j = 0;
				while(j < pessoa.getContatos().size()) {
						
					idContatoRecebido = pessoa.getContatos().get(j).getIdContato();
						
					if(idContatoCadastrado == idContatoRecebido) {
						break;
					}else if((j + 1) == pessoa.getContatos().size()) {	
						contatoRepository.delete(p.get().getContatos().get(i));
					}
					
					j++;
						
				}
			}
			
		}
		
		enderecoService.save(pessoa.getEndereco());
		pessoaRepository.save(pessoa);
		
		pessoa.getContatos().forEach(contato -> {
			contatoRepository.save(contato);
		});
		
		return pessoa;
	}
	
	public Pessoa save(Pessoa pessoa) {
		return this.logicaSaveUpdate(pessoa, true);
	}
	
	public Pessoa update(Pessoa pessoa) {
		return this.logicaSaveUpdate(pessoa, false);
	}
	
	public void delete(Pessoa pessoa){
		
		Pessoa p = this.findById(pessoa.getIdPessoa());
		
		if(p.getTipoPessoa() == TipoPessoa.PROPRIETARIO.getCod() || 
			p.getTipoPessoa() == TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod()) {
			
			if(p.getImoveis().size() > 0) {
				p.getImoveis().forEach(imovel -> {
					imovelService.delete(imovel);
				});
			}
			
			this.deleteInquilinoProprietario(pessoa);
		}
		
		if(p.getTipoPessoa() == TipoPessoa.INQUILINO.getCod() ||
			p.getTipoPessoa() == TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod()){
		
			if(p.getLocacoes().size() > 0) {
				
				p.getLocacoes().forEach(locacao -> {
					locacaoService.delete(locacao);
				});
				
			}
			
		}
		
	}
	
	public void deleteInquilinoProprietario(Pessoa pessoa) {
		pessoa.setAtivo(false);
		pessoaRepository.save(pessoa);
	}
	
	public Pessoa findById(Integer id) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		
		if(!pessoa.isPresent()) {
			return null;
		}
		
		return pessoa.get();
		
	}
	
	public List<Pessoa> findByLetra(String letra) {
		return null;
	}

	public List<Pessoa> findAllProprietarios(){
		List<Pessoa> proprietarios;
		List<Pessoa> proprietariosInquilinos;
		
		try {
			proprietarios = 
					pessoaRepository.findByTipoPessoaAndAtivo(TipoPessoa.PROPRIETARIO.getCod(),true);
		
			proprietariosInquilinos = 
					pessoaRepository.findByTipoPessoaAndAtivo(TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod(),true);
			
			
		}catch(RuntimeException ex) {
			return null;
		}
		
		if(proprietarios.isEmpty() && proprietariosInquilinos.isEmpty()) {
			return null;
		}else {
			proprietarios.addAll(proprietariosInquilinos);
		}
		
		return proprietarios;
	}

	public List<Pessoa> findAllInquilinos(){
		
		List<Pessoa> inquilinos;
		List<Pessoa> inquilinosProprietarios;
		
		try {
			inquilinos = 
					pessoaRepository.findByTipoPessoaAndAtivo(TipoPessoa.INQUILINO.getCod(),true);
			
			inquilinosProprietarios = 
					pessoaRepository.findByTipoPessoaAndAtivo(TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod(), true);
			
		}catch(RuntimeException ex) {
			return null;
		}
		
		if(inquilinos.isEmpty() && inquilinosProprietarios.isEmpty()) {
			return null;
		}else {
			inquilinos.addAll(inquilinosProprietarios);
		}
		
		return inquilinos;
	}

}