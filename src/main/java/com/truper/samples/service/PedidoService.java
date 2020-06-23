package com.truper.samples.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.truper.samples.ws.dto.PedidosW;

/**
 * @author José René Miranda de la O
 *
 */
@Service
public interface PedidoService {

	/**
	 * Metodo para obtener pedidos por usuario
	 * 
	 * @param username
	 * @return
	 */
	List<PedidosW> obtenerPedidosPorUsuario(final String username);

	/**
	 * Metodo para obtener todos los pedidos
	 * 
	 * @return
	 */
	List<PedidosW> obtenerPedidos();

}
