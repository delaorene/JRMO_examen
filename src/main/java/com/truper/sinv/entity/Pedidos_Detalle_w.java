package com.truper.sinv.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author José René Miranda de la O
 *
 */
@Entity
@Table(name = "PEDIDOS_DETALLE_W")
@Getter
@Setter
public class Pedidos_Detalle_w {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_PEDIDO")
	private Pedidos_w idPedido;

	@ManyToOne
	@JoinColumn(name = "SKU")
	private Producto_w sku;

	@Column(name = "AMOUT")
	private BigDecimal amout;

	@Column(name = "PRICE")
	private BigDecimal price;

}
