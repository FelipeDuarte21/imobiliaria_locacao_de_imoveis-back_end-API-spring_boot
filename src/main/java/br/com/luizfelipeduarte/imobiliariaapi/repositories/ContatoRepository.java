package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato,Long>{
	
	public Optional<Contato> findByNumero(String numero);
	
}
