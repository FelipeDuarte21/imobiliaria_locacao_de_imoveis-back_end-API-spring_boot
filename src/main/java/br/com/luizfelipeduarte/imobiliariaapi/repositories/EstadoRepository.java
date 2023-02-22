package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Long>{
	
	public Estado findByNome(String nome);
	
}
