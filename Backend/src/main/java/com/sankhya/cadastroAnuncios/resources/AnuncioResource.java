package com.capgemini.cadastroAnuncios.resources;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.cadastroAnuncios.domain.Anuncio;
import com.capgemini.cadastroAnuncios.domain.Cliente;
import com.capgemini.cadastroAnuncios.domain.DTO.AnuncioDTO;
import com.capgemini.cadastroAnuncios.service.AnuncioService;
import com.capgemini.cadastroAnuncios.service.ClienteService;

@RestController
@RequestMapping(value = "/anuncios")
public class AnuncioResource {

	@Autowired
	private AnuncioService anuncioService;

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Anuncio obj) {
		anuncioService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/pages", method = RequestMethod.GET)
	public ResponseEntity<Page<Anuncio>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Anuncio> anuncios = anuncioService.findPage(page, linesPerPage, orderBy, direction);

		return ResponseEntity.ok().body(anuncios);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<AnuncioDTO>> consultaPorPeriodoUsuario(
			@RequestParam(value = "idcliente", defaultValue = "0") Integer idCliente,
			@RequestParam(value = "datainicio", defaultValue = "") String dataInicio,
			@RequestParam(value = "datafim", defaultValue = "") String dataFim) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDefault = new Date();
		Page<AnuncioDTO> list = null;
		if (dataFim.equals("")) {
			dataFim = sdf.format(dateDefault);
		}

		if (idCliente == 0 && dataInicio.equals("")) {
			list = new PageImpl<>(anuncioService.findAll());
		} else if (dataInicio.equals("")) {
			list = anuncioService.findByCliente(idCliente);
		} else if (idCliente == 0) {
			list = anuncioService.findByDataInicioBetween(dataInicio, dataFim);
		} else {
			Cliente cliente = clienteService.findById(idCliente);
			list = anuncioService.findByDataInicioBetweenAndCliente(dataInicio, dataFim, cliente);
		}

		return ResponseEntity.ok().body(list);
	}

}
