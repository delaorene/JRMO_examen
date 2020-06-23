package com.truper.samples.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.truper.samples.dao.PedidoDetalleDAO;
import com.truper.sinv.entity.Pedidos_Detalle_w;

/**
 * @author José René Miranda de la O
 *
 */
@Repository
public class PedidoDetalleDAOImpl extends GenericDAOImpl<Pedidos_Detalle_w, Serializable> implements PedidoDetalleDAO {

	@Override
	public List<Pedidos_Detalle_w> obtenerDetallePorPedido(final Integer idPedido) {
		final String strQry = "FROM Pedidos_Detalle_w WHERE idPedido=:idPedido";
		final Query qry = getSession().createQuery(strQry);
		qry.setInteger("idPedido", idPedido);
		return qry.list();
	}

	@Override
	public List<Pedidos_Detalle_w> obtenerDetalles() {
		final String strQry = "FROM Pedidos_Detalle_w";
		final Query qry = getSession().createQuery(strQry);
		return qry.list();
	}

}
