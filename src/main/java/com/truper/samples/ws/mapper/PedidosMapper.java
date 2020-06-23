package com.truper.samples.ws.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.truper.samples.util.mapper.Mapper;
import com.truper.samples.ws.dto.PedidosW;
import com.truper.samples.ws.dto.UsuariosW;
import com.truper.sinv.entity.Pedidos_w;
import com.truper.sinv.entity.Usuarios_w;

/**
 * Clase para mapear entre un bean expuesto al front y un bean de persistencia,
 * con el fin de evitar exponer al front el bean de persistencia
 *
 * @author José René Miranda de la O
 *
 */
@Component("pedidos-mapper")
public class PedidosMapper implements Mapper<Pedidos_w, PedidosW> {

	@Autowired
	@Qualifier("usuarios-mapper")
	private Mapper<Usuarios_w, UsuariosW> usuariosMapper;

	@Override
	public Pedidos_w mapToInner(final PedidosW outer) {
		if (outer == null) {
			return null;
		}
		final Pedidos_w pedidos = new Pedidos_w();
		pedidos.setDateSale(outer.getDateSale());
		pedidos.setId(outer.getId());
		pedidos.setTotal(outer.getTotal());
		pedidos.setUsername(usuariosMapper.mapToInner(outer.getUsername()));
		return pedidos;
	}

	@Override
	public PedidosW mapToOuter(final Pedidos_w inner) {
		if (inner == null) {
			return null;
		}
		final PedidosW pedidos = new PedidosW();
		pedidos.setDateSale(inner.getDateSale());
		pedidos.setId(inner.getId());
		pedidos.setTotal(inner.getTotal());
		pedidos.setUsername(usuariosMapper.mapToOuter(inner.getUsername()));
		return pedidos;
	}
}
