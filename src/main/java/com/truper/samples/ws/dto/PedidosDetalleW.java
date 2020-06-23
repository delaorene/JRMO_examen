package com.truper.samples.ws.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidosDetalleW implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private PedidosW idPedido;
	private ProductoW sku;
	private BigDecimal amout;
	private BigDecimal price;

}
