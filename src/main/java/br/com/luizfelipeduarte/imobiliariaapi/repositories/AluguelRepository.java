package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Aluguel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Locacao;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel,Long>{
	
	public Page<Aluguel> findByLocacao(Locacao locacao,Pageable pageable);
	public Page<Aluguel> findByDataVencimentoBetweenOrderByDataVencimento(LocalDate inicio,LocalDate fim,Pageable pageable);
	public Page<Aluguel> findByDataVencimentoLessThanAndQuiteOrderByDataVencimento(LocalDate dataVencimento, Boolean quite, Pageable pageable);
	public List<Aluguel> findByDataVencimentoLessThanAndQuiteOrderByDataVencimento(LocalDate dataVencimento, Boolean quite);
}
