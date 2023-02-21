package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Complemento;

@Repository
public interface ComplementoRepository extends JpaRepository<Complemento,Integer>{

	public Complemento findByComplemento(String complemento);	
	
}
