package com.truper.samples.util.mapper;

/**
 *
 * Interfaz con los metodos para realizar el mapeo entre beans expuestos y los
 * beans de persistencia Esta interfaz es con el fin de no exponer los beans de
 * persistencia al front
 *
 * @author José René Miranda de la O
 *
 */
public interface Mapper<I, O> {

	/**
	 * Metodo para realizar el mapeo entre un bean expuesto a un bean de
	 * persistencia
	 *
	 * @param outer bean expuesto al front
	 * @return bean de persistencia
	 */
	I mapToInner(final O outer);

	/**
	 * Metodo para realizar el mapeo entre un bean de persistencia a un bean
	 * expuesto al front
	 *
	 * @param inner bean de persistencia
	 * @return bean expuesto al front
	 */
	O mapToOuter(final I inner);
}
