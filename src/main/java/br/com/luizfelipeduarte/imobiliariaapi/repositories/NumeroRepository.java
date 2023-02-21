package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Numero;

@Repository
public interface NumeroRepository extends JpaRepository<Numero,Integer>{
	
	public Numero findByNumero(String numero);
	
}
