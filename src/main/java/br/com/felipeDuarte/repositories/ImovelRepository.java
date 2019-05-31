package br.com.felipeDuarte.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeDuarte.domain.Endereco;
import br.com.felipeDuarte.domain.Imovel;
import br.com.felipeDuarte.domain.Pessoa;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel,Integer>{
	
	public List<Imovel> findByDisponivel(boolean value);
	public List<Imovel> findByProprietario(Pessoa proprietario);
	public List<Imovel> findByPrecoLessThanEqualAndDisponivel(Double preco,Boolean valor);
	public Imovel findByEndereco(Endereco endereco);
	
}
