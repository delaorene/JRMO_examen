package com.truper.samples.dao;

import java.io.Serializable;

import com.truper.sinv.entity.Usuarios_w;

/**
 * @author José René Miranda de la O
 *
 */
public interface UsuarioDAO extends GenericDAO<Usuarios_w, Serializable> {

	/**
	 * Metodo para obtener un usuario en bd por su username
	 *
	 * @param username
	 * @return
	 */
	Usuarios_w obtenerUsuarioPorUsername(final String username);

}
