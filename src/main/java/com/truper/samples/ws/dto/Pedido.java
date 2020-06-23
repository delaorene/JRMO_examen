package com.truper.samples.ws.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author José René Miranda de la O
 *
 */
@Getter
@Setter
public class Pedido {

	private PedidosW pedido;
	private List<PedidosDetalleW> detalle;
	
}
