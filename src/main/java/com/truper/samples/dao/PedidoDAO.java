package com.truper.samples.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.truper.sinv.entity.Pedidos_w;

/**
 * @author José René Miranda de la O
 *
 */
@Repository
public interface PedidoDAO extends GenericDAO<Pedidos_w, Serializable> {

	/**
	 * Metodo para obtener pedidos por usuario
	 *
	 * @param username
	 * @return
	 */
	List<Pedidos_w> obtenerPedidosPorUsuario(final String username);

	/**
	 * Metodo para obtener todos los pedidos
	 *
	 * @return
	 */
	List<Pedidos_w> obtenerPedidos();
}
