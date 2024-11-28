package com.capgemini.cadastroAnuncios.service.utils;

import java.util.Date;

/**
 * Interface destinada a calcular o número aproximado da quantidade máxima de
 * visualização de uma publicação
 *
 * @author Davi Ramos
 */

public class Calculadora {

	/**
	 * Método destinado para calcular número de visualizações no post original
	 * 
	 * @param valor Double - Valor de investimento
	 * @return Double - Nº de views no post original
	 */
	public static Double calculaViewsPostOriginal(Double valor) {
		return (valor * 30);
	}

	/**
	 * Método destinado para calcular número de clicks no post original
	 * 
	 * @param viewsPostOriginal Double - Número de views do post original
	 * @return Double - Nº de cliques
	 */
	public static Double calculaCliques(Double valor) {
		Double viewsPostOriginal = calculaViewsPostOriginal(valor);
		return (viewsPostOriginal * (0.12));
	}

	/**
	 * Método destinado para calcular número de compartilhamentos do post original
	 * em fnção do número de cliques
	 * 
	 * @param cliques Double - Número de cliques em relação as views do post
	 *                Original
	 * @return Double - Número de compartilhamentos em relação aos cliques
	 */
	public static Double calcularCompartilhamentos(Double valor) {
		Double cliques = calculaCliques(valor);
		return (cliques * (0.15));
	}

	/**
	 * Etapa final do calculo da quantidade de views do post (compartilhamento +
	 * post original)
	 * 
	 * @param valor Double - Valor a ser investido
	 * @return Integer - Resultado final do número de views para post +
	 *         compartilhamentos
	 */
	public static Integer calculaQtdViewsTotal(Double valor) {

		double originalViews = 0;
		double totalCompartilhado = 0;
		int total = 0;
		for (int i = 0; i < 3; i++) {
			originalViews = calculaViewsPostOriginal(valor);
			totalCompartilhado = calcularCompartilhamentos(valor);
			total += (int) originalViews;
			valor = totalCompartilhado * 40.0;
		}

		return total;

	}

	public static Double calculaValorTotalInvestido(Date inicio, Date fim, Double valor) {
		long dt = 3600000 + (fim.getTime() - inicio.getTime());
		dt = dt / 86400000L;
		return valor * dt;
	}

}
