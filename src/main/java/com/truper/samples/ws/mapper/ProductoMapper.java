package com.truper.samples.ws.mapper;

import org.springframework.stereotype.Component;

import com.truper.samples.util.mapper.Mapper;
import com.truper.samples.ws.dto.ProductoW;
import com.truper.sinv.entity.Producto_w;

/**
 * Clase para mapear entre un bean expuesto al front y un bean de persistencia,
 * con el fin de evitar exponer al front el bean de persistencia
 *
 * @author José René Miranda de la O
 *
 */
@Component("product-mapper")
public class ProductoMapper implements Mapper<Producto_w, ProductoW> {

	@Override
	public Producto_w mapToInner(final ProductoW outer) {
		if (outer == null) {
			return null;
		}
		final Producto_w producto = new Producto_w();
		producto.setExistencia(outer.getExistencia());
		producto.setNombre(outer.getNombre());
		producto.setPrice(outer.getPrice());
		producto.setSku(outer.getSku());
		return producto;
	}

	@Override
	public ProductoW mapToOuter(final Producto_w inner) {
		if (inner == null) {
			return null;
		}
		final ProductoW producto = new ProductoW();
		producto.setExistencia(inner.getExistencia());
		producto.setNombre(inner.getNombre());
		producto.setPrice(inner.getPrice());
		producto.setSku(inner.getSku());
		return producto;
	}
}
