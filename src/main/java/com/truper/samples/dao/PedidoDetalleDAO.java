package com.truper.samples.dao;

import java.io.Serializable;
import java.util.List;

import com.truper.sinv.entity.Pedidos_Detalle_w;

/**
 * @author José René Miranda de la O
 *
 */
public interface PedidoDetalleDAO extends GenericDAO<Pedidos_Detalle_w, Serializable> {

	/**
	 * Metodo para obtener el detalle de un pedido
	 *
	 * @param idPedido
	 * @return
	 */
	List<Pedidos_Detalle_w> obtenerDetallePorPedido(final Integer idPedido);

	/**
	 * Metodo para obtener todos los detalles
	 *
	 * @param idPedido
	 * @return
	 */
	List<Pedidos_Detalle_w> obtenerDetalles();
}
