package com.truper.sinv.entity;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "PEDIDOS_W")
@Getter
@Setter
public class Pedidos_w {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "TOTAL")
	private BigDecimal total;

	@Column(name = "DATE_SALE")
	private Date dateSale;

	@ManyToOne
	@JoinColumn(name = "USERNAME")
	private Usuarios_w username;
}
