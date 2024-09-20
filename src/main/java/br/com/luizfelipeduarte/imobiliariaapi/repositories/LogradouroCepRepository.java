package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.LogradouroCep;

@Repository
public interface LogradouroCepRepository extends JpaRepository<LogradouroCep,Long>{
	
	LogradouroCep findByCep(String cep);
	
}
