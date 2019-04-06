package br.com.felipeDuarte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Integer>{
	
	public Cidade findByNome(String nome);
	
}
