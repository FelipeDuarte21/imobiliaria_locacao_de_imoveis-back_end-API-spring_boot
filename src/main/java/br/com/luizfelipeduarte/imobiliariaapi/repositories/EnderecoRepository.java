package br.com.luizfelipeduarte.imobiliariaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizfelipeduarte.imobiliariaapi.entidade.Endereco;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.LogradouroCep;
import br.com.luizfelipeduarte.imobiliariaapi.entidade.Numero;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Integer>{
	
	public Endereco findByLogradouroCepAndNumero(LogradouroCep l,Numero n);
	
}
