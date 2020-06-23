package com.truper.samples.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.truper.samples.dao.PedidoDAO;
import com.truper.sinv.entity.Pedidos_w;

/**
 * @author José René Miranda de la O
 *
 */
@Repository
public class PedidoDAOImpl extends GenericDAOImpl<Pedidos_w, Serializable> implements PedidoDAO {

	@Override
	public List<Pedidos_w> obtenerPedidosPorUsuario(final String username) {
		final String strQry = "FROM Pedidos_w WHERE username=:username";
		final Query qry = getSession().createQuery(strQry);
		qry.setString("username", username);
		return qry.list();
	}

	@Override
	public List<Pedidos_w> obtenerPedidos() {
		final String strQry = "FROM Pedidos_w";
		final Query qry = getSession().createQuery(strQry);
		return qry.list();
	}
}
