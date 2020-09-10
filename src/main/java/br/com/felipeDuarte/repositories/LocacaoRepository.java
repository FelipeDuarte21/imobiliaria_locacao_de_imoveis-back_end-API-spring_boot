package br.com.felipeDuarte.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Imovel;
import br.com.felipeDuarte.domain.Locacao;
import br.com.felipeDuarte.domain.Pessoa;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao,Integer>{
	
	public Optional<Locacao> findByImovel(Imovel imovel);
	public List<Locacao> findByInquilino(Pessoa id);
	public Page<Locacao> findByInquilino(Pessoa id,Pageable pageable);
	
}
