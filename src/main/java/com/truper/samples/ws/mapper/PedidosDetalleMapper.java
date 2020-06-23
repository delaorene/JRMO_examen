package com.truper.samples.ws.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.truper.samples.util.mapper.Mapper;
import com.truper.samples.ws.dto.PedidosDetalleW;
import com.truper.samples.ws.dto.PedidosW;
import com.truper.samples.ws.dto.ProductoW;
import com.truper.sinv.entity.Pedidos_Detalle_w;
import com.truper.sinv.entity.Pedidos_w;
import com.truper.sinv.entity.Producto_w;

/**
 * Clase para mapear entre un bean expuesto al front y un bean de persistencia,
 * con el fin de evitar exponer al front el bean de persistencia
 *
 * @author José René Miranda de la O
 *
 */
@Component("pedidos-detalle-mapper")
public class PedidosDetalleMapper implements Mapper<Pedidos_Detalle_w, PedidosDetalleW> {

	@Autowired
	@Qualifier("product-mapper")
	private Mapper<Producto_w, ProductoW> productoMapper;

	@Autowired
	@Qualifier("pedidos-mapper")
	private Mapper<Pedidos_w, PedidosW> pedidosMapper;

	@Override
	public Pedidos_Detalle_w mapToInner(final PedidosDetalleW outer) {
		if (outer == null) {
			return null;
		}
		final Pedidos_Detalle_w pedidosDetalle = new Pedidos_Detalle_w();
		pedidosDetalle.setAmout(outer.getAmout());
		pedidosDetalle.setId(outer.getId());
		pedidosDetalle.setPrice(outer.getPrice());
		pedidosDetalle.setIdPedido(pedidosMapper.mapToInner(outer.getIdPedido()));
		pedidosDetalle.setSku(productoMapper.mapToInner(outer.getSku()));
		return pedidosDetalle;
	}

	@Override
	public PedidosDetalleW mapToOuter(final Pedidos_Detalle_w inner) {
		if (inner == null) {
			return null;
		}
		final PedidosDetalleW pedidosDetalle = new PedidosDetalleW();
		pedidosDetalle.setAmout(inner.getAmout());
		pedidosDetalle.setId(inner.getId());
		pedidosDetalle.setPrice(inner.getPrice());
		pedidosDetalle.setIdPedido(pedidosMapper.mapToOuter(inner.getIdPedido()));
		pedidosDetalle.setSku(productoMapper.mapToOuter(inner.getSku()));
		return pedidosDetalle;
	}

}
