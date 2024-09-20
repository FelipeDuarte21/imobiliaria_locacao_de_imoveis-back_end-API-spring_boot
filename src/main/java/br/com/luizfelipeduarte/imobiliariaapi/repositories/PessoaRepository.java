package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.enums.TipoPessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long>{

	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByAtivoAndTipoPessoaAndTipoPessoa(Boolean ativo,TipoPessoa tipo1, TipoPessoa tipo2);
	Page<Pessoa> findByAtivoAndTipoPessoaOrTipoPessoa(Boolean ativo,TipoPessoa tipo,TipoPessoa tipo2,Pageable paginacao);
	List<Pessoa> findByAtivoAndTipoPessoaOrTipoPessoa(Boolean ativo,Integer tipo,Integer tipo2,Sort sort);
	
	@Query(value = "SELECT p FROM Pessoa p WHERE (p.ativo = ?1) and (p.nome like %?2%) and (p.tipoPessoa = ?3 or p.tipoPessoa = ?4) ")
	Page<Pessoa> findByAtivoAndNomeContainingAndTipoPessoaOrTipoPessoa(Boolean ativo,String nome,TipoPessoa tipo,TipoPessoa tipo2,Pageable pg);

}