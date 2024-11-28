package com.capgemini.cadastroAnuncios.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.capgemini.cadastroAnuncios.domain.Anuncio;
import com.capgemini.cadastroAnuncios.domain.Cliente;
import com.capgemini.cadastroAnuncios.domain.enums.TipoCliente;
import com.capgemini.cadastroAnuncios.repositories.AnuncioRepository;
import com.capgemini.cadastroAnuncios.repositories.ClienteRepository;

@Configuration
@Profile("test")
public class Initiable implements CommandLineRunner{
	
	@Autowired
	public AnuncioRepository anuncioRepository;
	
	@Autowired
	public ClienteRepository clienteRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Cliente c1 = new Cliente(null, "Robson", "robson@gmail.com", "01118019075", TipoCliente.PESSOAFISICA);
		Cliente c2 = new Cliente(null, "RGD Sistemas", "publicidade@rgd.com.br", "38154650000164", TipoCliente.PESSOAJURIDICA);
		
		Anuncio a1 = new Anuncio(null, "Anuncio#1", sdf.parse("25/11/2024"), sdf.parse("26/11/2024"), 10.50, c1);
		Anuncio a2 = new Anuncio(null, "Anuncio#2", sdf.parse("27/11/2024"), sdf.parse("01/11/2024"), 100.00, c1);
		Anuncio a3= new Anuncio(null, "Anuncio#3", sdf.parse("02/12/2024"), sdf.parse("05/12/2024"), 50.00, c2);
		
		c1.getAnuncios().addAll(Arrays.asList(a1,a2));
		c2.getAnuncios().add(a3);
		
		clienteRepository.saveAll(Arrays.asList(c1,c2));
		anuncioRepository.saveAll(Arrays.asList(a1,a2,a3));
		
	}
	
	

}
