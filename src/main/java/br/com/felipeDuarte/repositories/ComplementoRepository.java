package br.com.felipeDuarte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Complemento;

@Repository
public interface ComplementoRepository extends JpaRepository<Complemento,Integer>{

	public Complemento findByComplemento(String complemento);	
	
}
