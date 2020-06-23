package com.truper.samples.ws.dto;

import org.codehaus.jackson.annotate.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuariosW implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	@JsonIgnore
	private String password;
	private String role;
	private String nombre;
	private String apellidos;

}
