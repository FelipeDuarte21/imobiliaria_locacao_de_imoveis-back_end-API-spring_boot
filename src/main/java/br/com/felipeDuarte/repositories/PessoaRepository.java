package br.com.felipeDuarte.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Integer>{

	public Optional<Pessoa> findByCpf(String cpf);
	public List<Pessoa> findByTipoPessoaAndAtivo(int tipoPessoa,boolean ativo);
	
}