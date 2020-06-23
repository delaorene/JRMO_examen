package com.truper.samples.dao.impl;

import java.io.Serializable;
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
 * @since 06/06/2017 Acceso a datos para productos en la base de datos
 *        configurada en el XML de spring
 */
@Repository
public class ProductosDAOImpl extends GenericDAOImpl<Producto_w, Serializable> implements ProductosDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @author cgarcias Obtiene el listado de todos los productos, por ahora
	 *         limitado a 100 registros
	 * @return List<ProductoVO>
	 */
	@Override
	public List<Producto_w> getAll() {
		final String strQry = "FROM Producto_w";
		final Query qry = sessionFactory.getCurrentSession().createQuery(strQry);
		return qry.list();
	}

	/**
	 * @author cgarcias Obtiene el registro de un producto en espec�fico con el SKU
	 *         indicado
	 * @return ProductoVO o NULL si no existen datos
	 */
	@Override
	public Producto_w getBySKU(final String sku) {
		final String strQry = "FROM Producto_w WHERE sku=:skuParam";
		final Query qry = sessionFactory.getCurrentSession().createQuery(strQry);
		qry.setString("skuParam", sku);

		return (Producto_w) qry.uniqueResult();
	}

}
