package com.truper.samples.ws.mapper;

import org.springframework.stereotype.Component;

import com.truper.samples.util.mapper.Mapper;
import com.truper.samples.ws.dto.UsuariosW;
import com.truper.sinv.entity.Usuarios_w;

/**
 * Clase para mapear entre un bean expuesto al front y un bean de persistencia,
 * con el fin de evitar exponer al front el bean de persistencia
 *
 * @author José René Miranda de la O
 *
 */
@Component("usuarios-mapper")
public class UsuariosMapper implements Mapper<Usuarios_w, UsuariosW> {

	@Override
	public Usuarios_w mapToInner(final UsuariosW outer) {
		if (outer == null) {
			return null;
		}
		final Usuarios_w usuarios = new Usuarios_w();
		usuarios.setApellidos(outer.getApellidos());
		usuarios.setNombre(outer.getNombre());
		usuarios.setPassword(outer.getPassword());
		usuarios.setRole(outer.getRole());
		usuarios.setUsername(outer.getUsername());
		return usuarios;
	}

	@Override
	public UsuariosW mapToOuter(final Usuarios_w inner) {
		if (inner == null) {
			return null;
		}
		final UsuariosW usuarios = new UsuariosW();
		usuarios.setApellidos(inner.getApellidos());
		usuarios.setNombre(inner.getNombre());
		usuarios.setPassword(inner.getPassword());
		usuarios.setRole(inner.getRole());
		usuarios.setUsername(inner.getUsername());
		return usuarios;
	}
}
