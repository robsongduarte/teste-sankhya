package com.capgemini.cadastroAnuncios.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.capgemini.cadastroAnuncios.domain.Anuncio;
import com.capgemini.cadastroAnuncios.domain.Cliente;
import com.capgemini.cadastroAnuncios.domain.DTO.AnuncioDTO;
import com.capgemini.cadastroAnuncios.repositories.AnuncioRepository;

@Service
public class AnuncioService {

	@Autowired
	public AnuncioRepository anuncioRepository;

	@Autowired
	public ClienteService clienteService;

	public List<AnuncioDTO> findAll() {
		List<Anuncio> a = anuncioRepository.findAll();
		List<AnuncioDTO> list = new ArrayList<>();
		for (Anuncio anuncio : a) {
			list.add(new AnuncioDTO(anuncio));
		}

		return list;
	}

	public Anuncio insert(Anuncio obj) {
		obj.setId(null);
		return anuncioRepository.save(obj);
	}

	public Page<AnuncioDTO> findByCliente(Integer id) {
		Cliente cliente = clienteService.findById(id);
		List<Anuncio> a = anuncioRepository.findByCliente(cliente);
		List<AnuncioDTO> list = new ArrayList<>();
		for (Anuncio anuncio : a) {
			list.add(new AnuncioDTO(anuncio));
		}
		Page<AnuncioDTO> pageAnuncio = new PageImpl<>(list);

		return pageAnuncio;
	}

	public Page<AnuncioDTO> findByDataInicioBetween(String start, String end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date inicio = null;
		Date fim = null;
		try {
			inicio = sdf.parse(start);
			fim = sdf.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Anuncio> a = anuncioRepository.findByDataInicioBetween(inicio, fim);
		List<AnuncioDTO> list = new ArrayList<>();
		for (Anuncio anuncio : a) {
			list.add(new AnuncioDTO(anuncio));
		}

		Page<AnuncioDTO> pageAnuncio = new PageImpl<>(list);

		return pageAnuncio;
	}

	public Page<Anuncio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,
				org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);
		return anuncioRepository.findAll(pageRequest);
	}

	public Page<AnuncioDTO> findByDataInicioBetweenAndCliente(String start, String end, Cliente cliente) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date inicio = null;
		Date fim = null;
		try {
			inicio = sdf.parse(start);
			fim = sdf.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Anuncio> a = anuncioRepository.findByDataInicioBetweenAndCliente(inicio, fim, cliente);
		List<AnuncioDTO> list = new ArrayList<>();
		for (Anuncio anuncio : a) {
			list.add(new AnuncioDTO(anuncio));
		}

		Page<AnuncioDTO> pageAnuncio = new PageImpl<>(list);

		return pageAnuncio;
	}

}
