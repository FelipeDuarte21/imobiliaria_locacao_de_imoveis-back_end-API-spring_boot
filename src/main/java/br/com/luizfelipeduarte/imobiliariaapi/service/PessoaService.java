package br.com.luizfelipeduarte.imobiliariaapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Contato;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.dto.PessoaDTO;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.enums.TipoPessoa;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.ContatoRepository;
import br.com.luizfelipeduarte.imobiliariaapi.repositories.PessoaRepository;


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
	
	private Pessoa convertePessoaDTO(PessoaDTO pessoaDTO) {
		
		//Dados Pessoais
		Pessoa pessoa = new Pessoa();
		pessoa.setIdPessoa(pessoaDTO.getIdPessoa());
		pessoa.setTipoPessoa(pessoaDTO.getTipoPessoa());
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setNacionalidade(pessoaDTO.getNacionalidade());
		pessoa.setEstadoCivil(pessoaDTO.getEstadoCivil());
		pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
		pessoa.setIdentidade(pessoaDTO.getIdentidade());
		pessoa.setOrgaoEmissor(pessoaDTO.getOrgaoEmissor());
		pessoa.setDataExpedicao(pessoaDTO.getDataExpedicao());
		pessoa.setCpf(pessoaDTO.getCpf());
		pessoa.setSalario(pessoaDTO.getSalario());
		pessoa.setEmail(pessoaDTO.getEmail());
		pessoa.setAtivo(true);
		
		//Contatos
		pessoaDTO.getContatos().forEach(contato -> {
			Contato c = new Contato();
			c.setIdContato(contato.getIdContato());
			c.setTipoContato(contato.getTipoContato());
			c.setNumero(contato.getNumero());
			c.setPessoa(pessoa);
			pessoa.getContatos().add(c);
		});
		
		//Endereco
		pessoa.setEndereco(this.enderecoService.estruturaEndereco(pessoaDTO.getEndereco()));
		
		return pessoa;
	}
	
	public Pessoa save(PessoaDTO pessoaDTO) {
		return this.logicaSaveUpdate(this.convertePessoaDTO(pessoaDTO), true);
	}
	
	public Pessoa update(PessoaDTO pessoa) {
		return this.logicaSaveUpdate(this.convertePessoaDTO(pessoa), false);
	}
	
	public boolean delete(Integer id){
		
		Pessoa p = this.findById(id);
		
		if(p == null) return false;
		
		if(p.getTipoPessoa().equals(TipoPessoa.PROPRIETARIO.getDescricao()) || 
			p.getTipoPessoa().equals(TipoPessoa.PROPRIETARIO_E_INQUILINO.getDescricao())) {
			
			if(p.getImoveis().size() > 0) {
				p.getImoveis().forEach(imovel -> {
					imovelService.delete(imovel.getIdImovel());
				});
			}
			
			this.deleteInquilinoProprietario(p);
		}
		
		if(p.getTipoPessoa().equals(TipoPessoa.INQUILINO.getDescricao()) ||
			p.getTipoPessoa().equals(TipoPessoa.PROPRIETARIO_E_INQUILINO.getDescricao())){
			
			if(p.getLocacoes().size() > 0) {
				
				p.getLocacoes().forEach(locacao -> {
					locacaoService.delete(locacao.getIdLocacao());
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
	
	public List<Pessoa> findAllProprietarios(){
		
		List<Pessoa> proprietarios = 
			this.pessoaRepository.findByAtivoAndTipoPessoaOrTipoPessoa(
					true,TipoPessoa.PROPRIETARIO.getCod(), TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod(), 
						Sort.by(Direction.ASC, "nome"));
		
		if(proprietarios.isEmpty()) {
			return null;
		}
		
		return proprietarios;
	}

	public Page<Pessoa> findAllProprietarios(Integer page,Integer size){
		return this.findAll(
				TipoPessoa.PROPRIETARIO.getCod(), TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod(),
					page,size);
	}
	
	public List<Pessoa> findAllInquilinos(){
		
		List<Pessoa> inquilinos = this.pessoaRepository.findByAtivoAndTipoPessoaOrTipoPessoa(
				true,TipoPessoa.INQUILINO.getCod(), TipoPessoa.PROPRIETARIO_E_INQUILINO.getCod(), 
				Sort.by(Direction.ASC, "nome"));
		
		if(inquilinos.isEmpty()) {
			return null;
		}
		
		return inquilinos;
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