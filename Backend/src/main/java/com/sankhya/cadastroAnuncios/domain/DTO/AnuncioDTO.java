package com.capgemini.cadastroAnuncios.domain.DTO;

import java.io.Serializable;

import com.capgemini.cadastroAnuncios.domain.Anuncio;
import com.capgemini.cadastroAnuncios.service.utils.Calculadora;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnuncioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double valorTotalInvetido;
	private Integer qtdMaxVisualizacao;
	private Integer qtdMaxCliques;
	private Integer qtdMaxCompartilhamentos;

	public AnuncioDTO() {
	}

	public AnuncioDTO(Anuncio obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.valorTotalInvetido = Calculadora.calculaValorTotalInvestido(obj.getDataInicio(), obj.getDataTermino(), obj.getInvestimentoDia());
		this.qtdMaxVisualizacao = Calculadora.calculaQtdViewsTotal(valorTotalInvetido);
		this.qtdMaxCliques = (Calculadora.calculaCliques(valorTotalInvetido)).intValue();
		this.qtdMaxCompartilhamentos = (Calculadora.calcularCompartilhamentos(valorTotalInvetido)).intValue();
	}
	
}
