package br.com.felipeDuarte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Numero;

@Repository
public interface NumeroRepository extends JpaRepository<Numero,Integer>{
	
	public Numero findByNumero(String numero);
	
}
