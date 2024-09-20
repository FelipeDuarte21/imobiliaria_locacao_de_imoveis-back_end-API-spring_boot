package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro,Long>{
	
	Bairro findByNome(String nome);
	
}
