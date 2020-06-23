package com.truper.sinv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author José René Miranda de la O
 *
 */
@Entity
@Table(name = "USUARIOS_W")
@Getter
@Setter
public class Usuarios_w {

	@Id
	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "APELLIDOS")
	private String apellidos;

}
