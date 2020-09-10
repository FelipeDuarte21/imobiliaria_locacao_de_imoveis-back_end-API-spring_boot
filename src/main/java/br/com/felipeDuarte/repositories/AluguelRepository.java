package br.com.felipeDuarte.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Aluguel;
import br.com.felipeDuarte.domain.Locacao;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel,Integer>{
	
	public Page<Aluguel> findByLocacao(Locacao locacao,Pageable pageable);
	public Page<Aluguel> findByDataVencimentoBetweenOrderByDataVencimento(Date inicio,Date fim,Pageable pageable);
	public Page<Aluguel> findByDataVencimentoLessThanOrderByDataVencimento(Date dataVencimento,Pageable pageable);
}
