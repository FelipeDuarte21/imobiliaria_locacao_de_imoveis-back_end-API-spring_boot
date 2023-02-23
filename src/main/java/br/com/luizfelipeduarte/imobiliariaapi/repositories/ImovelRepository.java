package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Endereco;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Imovel;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Pessoa;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel,Long>{
	
	public Page<Imovel> findByDisponivelAndProprietario(Boolean disponivel,Pessoa proprietario,Pageable pageable);
	
	public Page<Imovel> findByDisponivel(Boolean disponivel,Pageable pageable);
	
	public Page<Imovel> findByProprietario(Pessoa proprietario, Pageable pageable);
	
	public Page<Imovel> findByPrecoLessThanEqualAndDisponivel(BigDecimal preco,Boolean valor,Pageable pageable);
	
	public Imovel findByEndereco(Endereco endereco);
	
	@Query(value = "SELECT * FROM imovel i JOIN pessoa p ON i.id_proprietario = p.id "
			+ "WHERE p.nome LIKE %?1% ",nativeQuery = true)
	public Page<Imovel> findByNomeProprietario(String nome,Pageable pageable);
	
	
}
