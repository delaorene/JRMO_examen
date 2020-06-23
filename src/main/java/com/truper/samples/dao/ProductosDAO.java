package com.truper.samples.dao;

import java.io.Serializable;
import java.util.List;

import com.truper.sinv.entity.Producto_w;

public interface ProductosDAO extends GenericDAO<Producto_w, Serializable> {

	List<Producto_w> getAll();

	Producto_w getBySKU(final String strSku);

}
