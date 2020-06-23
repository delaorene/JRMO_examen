package com.truper.samples.service;

import java.util.List;

import com.truper.samples.ws.dto.Pedido;

/**
 * @author José René Miranda de la O
 *
 */
public interface PedidoDetalleService {

	/**
	 * Metodo para crear el detalle del pedido
	 *
	 * @param pedido
	 */
	void crearPedidoDetalle(final Pedido pedido);

	/**
	 * Metodo para obtener el detalle de un pedido
	 *
	 * @param idPedido
	 * @return
	 */
	Pedido obtenerDetallePorPedido(final Integer idPedido);

	/**
	 * Metodo para obtener todos los detalles
	 *
	 * @return
	 */
	List<Pedido> obtenerDetalles();
}
