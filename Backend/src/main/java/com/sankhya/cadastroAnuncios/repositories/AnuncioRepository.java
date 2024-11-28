package com.capgemini.cadastroAnuncios.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.cadastroAnuncios.domain.Anuncio;
import com.capgemini.cadastroAnuncios.domain.Cliente;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>{
		
	@Transactional(readOnly=true)
	List<Anuncio> findByCliente(Cliente cliente);
	
	@Transactional(readOnly=true)
	List<Anuncio> findByDataInicioBetweenAndCliente(Date start, Date end,Cliente cliente);

	
	@Transactional(readOnly=true)
	List<Anuncio> findByDataInicioBetween(Date start, Date end);
}
