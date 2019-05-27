package br.com.felipeDuarte.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Aluguel;
import br.com.felipeDuarte.domain.Locacao;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel,Integer>{
	
	public List<Aluguel> findByLocacao(Locacao locacao);
	public List<Aluguel> findByDataVencimentoBetweenOrderByDataVencimento(Date inicio,Date fim);
	
}
