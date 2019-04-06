package br.com.felipeDuarte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro,Integer>{
	
	public Bairro findByNome(String nome);
	
}
