package br.com.felipeDuarte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato,Integer>{
	
}
