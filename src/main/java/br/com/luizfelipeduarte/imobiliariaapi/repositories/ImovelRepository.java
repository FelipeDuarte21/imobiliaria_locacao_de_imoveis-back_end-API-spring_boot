package br.com.luizfelipeduarte.imobiliariaapi.repositories;

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
	
	public List<Imovel> findByDisponivelAndProprietario(Boolean disponivel,Pessoa proprietario);
	
	public Page<Imovel> findByDisponivel(Boolean disponivel,Pageable pageable);
	
	public List<Imovel> findByProprietario(Pessoa proprietario);
	
	public Page<Imovel> findByPrecoLessThanEqualAndDisponivel(Double preco,Boolean valor,Pageable pageable);
	
	public Imovel findByEndereco(Endereco endereco);
	
	@Query(value = "SELECT * FROM imovel i JOIN pessoa p ON i.id_proprietario = p.id "
			+ "WHERE p.nome LIKE %?1% ",nativeQuery = true)
	public Page<Imovel> findByNomeProprietario(String nome,Pageable pageable);
	
	
}
