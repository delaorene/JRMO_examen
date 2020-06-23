package com.truper.samples.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.truper.samples.dao.ProductosDAO;
import com.truper.samples.service.ProductService;
import com.truper.samples.util.mapper.Mapper;
import com.truper.samples.ws.dto.ProductoW;
import com.truper.sinv.entity.Producto_w;

/**
 * Provee de los datos que se obtienen de los DAO
 *
 * @author cgarcias
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class }, readOnly = true)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductosDAO pdProductoDAO;

	@Autowired
	@Qualifier("product-mapper")
	private Mapper<Producto_w, ProductoW> productoMapper;

	/**
	 * @author cgarcias Da una lista de todos los productos del DAO
	 * @return List<ProductoVO> o NULL
	 */
	@Override
	public List<ProductoW> getAll() {
		List<ProductoW> listProducts = null;
		final List<Producto_w> productos = pdProductoDAO.getAll();
		if (CollectionUtils.isNotEmpty(productos)) {
			listProducts = new ArrayList<>();
			for (final Producto_w producto_w : productos) {
				listProducts.add(productoMapper.mapToOuter(producto_w));
			}
		}
		return listProducts;
	}

	/**
	 * @author cgarcias Devuelve un objeto ProductoVO �nica por SKU/Clase Producto
	 * @return ProductoVO o NULL
	 */
	@Override
	public ProductoW getBySKU(final String strSku) {
		return productoMapper.mapToOuter(pdProductoDAO.getBySKU(strSku));
	}

	/**
	 * @author cgarcias Elimina un producto
	 */
	@Override
	public void deleteBySKU(final String strSku) {
		final Producto_w producto = pdProductoDAO.getBySKU(strSku);
		pdProductoDAO.delete(producto);
	}

	/**
	 * @author cgarcias Crea un producto
	 */
	@Override
	public void createProduct(final ProductoW product) {
		pdProductoDAO.saveOrUpdate(productoMapper.mapToInner(product));
	}

	/**
	 * @author cgarcias Actualiza un producto
	 */
	@Override
	public void updateProduct(final ProductoW product) {
		pdProductoDAO.update(productoMapper.mapToInner(product));
	}
}
