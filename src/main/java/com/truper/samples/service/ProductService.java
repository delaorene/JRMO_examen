package com.truper.samples.service;

import java.util.List;

import com.truper.sinv.entity.Producto_w;

public interface ProductService {
	
	public List<Producto_w> getAll();
	
	public Producto_w getBySKU(String strSku);
	
	public int deleteBySKU(String strSku);
	
	public int createProduct(Producto_w product);
	
	public int updateProduct(Producto_w product);
}
