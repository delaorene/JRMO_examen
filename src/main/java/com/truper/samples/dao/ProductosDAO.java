package com.truper.samples.dao;

import java.util.List;

import com.truper.sinv.entity.Producto_w;

public interface ProductosDAO {
	
	public List<Producto_w> getAll();
	
	public Producto_w getBySKU(String strSku);
	
	public int deleteBySKU(String strSku);
	
	public int createProduct(Producto_w product);
	
	public int updateProduct(Producto_w product);
}
