package br.com.felipeDuarte.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	
	private Pessoa logicaSaveUpdate(Pessoa pessoa,boolean ativa) {
		
		Optional<Pessoa> p = pessoaRepository.findByCpf(pessoa.getCpf());
		
		if(p.isPresent()) {
			
			if(ativa) {
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
	
	public boolean delete(Integer id){
		
		Pessoa p = this.findById(id);
		
		if(p == null) return false;
		
		if(p.getTipoPessoa().equals(TipoPessoa.PROPRIETARIO.getDescricao()) || 
			p.getTipoPessoa().equals(TipoPessoa.PROPRIETARIO_E_INQUILINO.getDescricao())) {
			
			if(p.getImoveis().size() > 0) {
				p.getImoveis().forEach(imovel -> {
					imovelService.delete(imovel);
				});
			}
			
			this.deleteInquilinoProprietario(p);
		}
		
		if(p.getTipoPessoa().equals(TipoPessoa.INQUILINO.getDescricao()) ||
			p.getTipoPessoa().equals(TipoPessoa.PROPRIETARIO_E_INQUILINO.getDescricao())){
			
			if(p.getLocacoes().size() > 0) {
				
				p.getLocacoes().forEach(locacao -> {
					locacaoService.delete(locacao);
				});
				
			}else {
				this.deleteInquilinoProprietario(p);
			}
			
		}
		
		return true;
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
	
	private Page<Pessoa> findAll(Integer tipo,Integer tipo2,Integer page,Integer size){
		
		PageRequest pg = PageRequest.of(page, size,Direction.ASC,"nome");
		
		Page<Pessoa> pageResp = 
			this.pessoaRepository.findByAtivoAndTipoPessoaOrTipoPessoa(true,tipo, tipo2,pg);
		
		return pageResp;	
	}

	public Page<Pessoa> findAllProprietarios(Integer page,Integer size){
		return this.findAll(
				TipoPessoa.PROPRIETARIO.getCod(), TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod(),
					page,size);
	}
	
	public Page<Pessoa> findAllInquilinos(Integer page,Integer size){
		return this.findAll(
				TipoPessoa.INQUILINO.getCod(), TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod(), 
					page, size);
	}
	
	private Page<Pessoa> findContainingNome(String nome,Integer tipo,Integer tipo2,
			Integer page,Integer size){
		
		PageRequest pg = PageRequest.of(page, size,Direction.ASC, "nome");
		
		Page<Pessoa>  pageResp;
		
		if(!nome.isEmpty()) {
		
			pageResp = this.pessoaRepository.findByAtivoAndNomeContainingAndTipoPessoaOrTipoPessoa(
					true,nome,tipo,tipo2,pg);
		
		}else {
			pageResp = this.findAll(tipo, tipo2, page, size);	
		}
		
		return pageResp;
	}

	public Page<Pessoa> findContainingNomeInquilino(String nome,Integer page,Integer size){	
		return this.findContainingNome(nome, 
				TipoPessoa.INQUILINO.getCod(), TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod(), 
					page, size);
	}
	
	public Page<Pessoa> findContainingNomeProprietario(String nome,Integer page,Integer size){
		return this.findContainingNome(nome,
				TipoPessoa.PROPRIETARIO.getCod(), TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod(), 
					page, size);
	}
	
}