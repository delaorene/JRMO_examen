package com.truper.samples.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.truper.samples.dao.ProductosDAO;
import com.truper.sinv.entity.Producto_w;

/**
 * 
 * @author cgarcias
 * @since 06/06/2017
 * Acceso a datos para productos en la base de datos configurada en el XML de spring
 */
@Repository
public class ProductosDAOImpl implements ProductosDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @author cgarcias
	 * Obtiene el listado de todos los productos, por ahora limitado a 100 registros
	 * @return List<ProductoVO>
	 */
	@Override
	public List<Producto_w> getAll() {
		String strQry = "FROM Producto_w";
		Query qry = sessionFactory.getCurrentSession().createQuery(strQry);
		return qry.list();
	}

	/**
	 * @author cgarcias
	 * Obtiene el registro de un producto en específico con el SKU indicado
	 * @return ProductoVO o NULL si no existen datos
	 */
	@Override
	public Producto_w getBySKU(String sku) {
		String strQry = "FROM Producto_w WHERE sku=:skuParam";
		Query qry = sessionFactory.getCurrentSession().createQuery(strQry);
		qry.setString("skuParam", sku);
		
		return (Producto_w) qry.uniqueResult();
	}
	
	/**
	 * @author cgarcias
	 * Elimina un producto por su SKU
	 */
	@Override
	public int deleteBySKU(String strSku){
		String strQuery = "DELETE FROM Producto_w WHERE sku=:skuParam";
		Query qry = sessionFactory.getCurrentSession().createQuery(strQuery);
		qry.setString("skuParam", strSku);
		return qry.executeUpdate();
	}
	
	/**
	 * @author cgarcias
	 * @param product
	 * @return
	 * Crea un producto
	 */
	@Override
	public int createProduct(Producto_w product){
		sessionFactory.getCurrentSession().save(product);
		return 1;
	}

	/**
	 * @author cgarcias
	 * Actualiza el nombre y la existencia de un producto
	 */
	@Override
	public int updateProduct(Producto_w product) {
		sessionFactory.getCurrentSession().update(product);
		return 1;
	}

}
