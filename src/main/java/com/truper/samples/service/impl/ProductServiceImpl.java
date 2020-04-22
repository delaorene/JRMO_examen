package com.truper.samples.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.truper.samples.dao.ProductosDAO;
import com.truper.samples.service.ProductService;
import com.truper.sinv.entity.Producto_w;

/**
 * Provee de los datos que se obtienen de los DAO
 * @author cgarcias
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class}, readOnly=true)
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductosDAO pdProductoDAO;
	
	/**
	 * @author cgarcias
	 * Da una lista de todos los productos del DAO
	 * @return List<ProductoVO> o NULL
	 */
	@Override
	public List<Producto_w> getAll() {
		return pdProductoDAO.getAll();
	}
	
	/**
	 * @author cgarcias
	 * Devuelve un objeto ProductoVO única por SKU/Clase Producto
	 * @return ProductoVO o NULL
	 */
	@Override
	public Producto_w getBySKU(String strSku){
		return pdProductoDAO.getBySKU(strSku);
	}

	/**
	 * @author cgarcias
	 * Elimina un producto
	 */
	@Override
	public int deleteBySKU(String strSku) {
		return pdProductoDAO.deleteBySKU(strSku);
	}

	/**
	 * @author cgarcias
	 * Crea un producto
	 */
	@Override
	public int createProduct(Producto_w product) {
		return pdProductoDAO.createProduct(product);
	}

	/**
	 * @author cgarcias
	 * Actualiza un producto
	 */
	@Override
	public int updateProduct(Producto_w product) {
		return pdProductoDAO.updateProduct(product);
	}

}
