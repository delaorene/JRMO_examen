package com.truper.sinv.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo del objeto Productos utilizando anotaciones Lombok
 * @author cgarcias
 *
 */
@Entity
@Table(name="PRODUCTO_W")
@Getter
@Setter
public class Producto_w implements Serializable {

	private static final long serialVersionUID = 1392847982365987234L;
	
	@Id
	@Column(name="SKU")
	private String sku;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="EXISTENCIA")
	private Integer existencia;
	
	@Column(name="PRICE")
	private BigDecimal price;
}
