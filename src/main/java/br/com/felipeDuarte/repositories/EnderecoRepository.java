package br.com.felipeDuarte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Endereco;
import br.com.felipeDuarte.domain.LogradouroCep;
import br.com.felipeDuarte.domain.Numero;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Integer>{
	
	public Endereco findByLogradouroCepAndNumero(LogradouroCep l,Numero n);
	
}
