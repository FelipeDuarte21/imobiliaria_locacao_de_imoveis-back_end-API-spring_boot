package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Imovel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Locacao;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao,Long>{
	
	public Optional<Locacao> findByImovel(Imovel imovel);
	public List<Locacao> findByInquilino(Pessoa id);
	public Page<Locacao> findByInquilino(Pessoa id,Pageable pageable);
	
	@Query(value = "SELECT * FROM locacao l JOIN pessoa p ON l.id_inquilino = p.id " 
			+ " WHERE p.nome LIKE %?1%", nativeQuery = true)
	public Page<Locacao> findByNomeInquilino(String nome,Pageable pageable);
	
	@Query(value = "SELECT * FROM locacao l JOIN imovel i on l.id_imovel = i.id "
			+ " JOIN pessoa p ON i.id_proprietario = p.id "
			+ " WHERE p.nome LIKE %?1%", nativeQuery = true)
	public Page<Locacao> findByNomeProprietario(String nome,Pageable pageable);
	
}
