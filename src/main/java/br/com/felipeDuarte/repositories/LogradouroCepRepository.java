package br.com.felipeDuarte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.LogradouroCep;

@Repository
public interface LogradouroCepRepository extends JpaRepository<LogradouroCep,Integer>{
	
	public LogradouroCep findByCep(String cep);
	
}
