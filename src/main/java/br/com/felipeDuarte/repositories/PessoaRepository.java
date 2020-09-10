package br.com.felipeDuarte.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Integer>{

	public Optional<Pessoa> findByCpf(String cpf);
	public Page<Pessoa> findByAtivoAndTipoPessoaOrTipoPessoa(Boolean ativo,Integer tipo,Integer tipo2,Pageable pg);
	public List<Pessoa> findByAtivoAndTipoPessoaOrTipoPessoa(Boolean ativo,Integer tipo,Integer tipo2,Sort sort);
	
	@Query(value = "SELECT p FROM Pessoa p WHERE (p.ativo = ?1) and (p.nome like %?2%) and (p.tipoPessoa = ?3 or p.tipoPessoa = ?4) ")
	public Page<Pessoa> findByAtivoAndNomeContainingAndTipoPessoaOrTipoPessoa(Boolean ativo,String nome,Integer tipo,Integer tipo2,Pageable pg);

}