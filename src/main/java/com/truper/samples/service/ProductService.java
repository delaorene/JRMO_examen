package com.truper.samples.service;

import java.util.List;

import com.truper.samples.ws.dto.ProductoW;

public interface ProductService {

	List<ProductoW> getAll();

	ProductoW getBySKU(final String strSku);

	void deleteBySKU(final String strSku);

	void createProduct(final ProductoW product);

	void updateProduct(final ProductoW product);
}
