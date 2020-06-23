package com.truper.samples.ws.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidosW implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BigDecimal total;
	private Date dateSale;
	private UsuariosW username;
}
