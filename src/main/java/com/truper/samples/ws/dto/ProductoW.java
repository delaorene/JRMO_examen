package com.truper.samples.ws.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoW implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sku;
	private String nombre;
	private Integer existencia;
	private BigDecimal price;

}
