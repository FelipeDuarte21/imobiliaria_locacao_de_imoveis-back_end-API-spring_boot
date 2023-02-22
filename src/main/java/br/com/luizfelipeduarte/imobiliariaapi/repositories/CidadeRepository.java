package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Long>{
	
	public Cidade findByNome(String nome);
	
}
