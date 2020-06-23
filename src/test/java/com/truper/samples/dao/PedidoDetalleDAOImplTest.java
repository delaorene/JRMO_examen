package com.truper.samples.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.truper.samples.dao.impl.PedidoDetalleDAOImpl;
import com.truper.sinv.entity.Pedidos_Detalle_w;

/**
 * @author José René Miranda de la O
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PedidoDetalleDAOImplTest {

	@InjectMocks
	private PedidoDetalleDAOImpl pedidoDetalleDAOImpl;

	@Mock
	private SessionFactory sessionFactory;

	@Mock
	private Session session;

	@Mock
	private Query query;

	@Test
	public void testObtenerPorPedido() {
		final Integer idPedido = 1;
		final String strQry = "FROM Pedidos_Detalle_w WHERE idPedido=:idPedido";
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		Mockito.when(session.createQuery(strQry)).thenReturn(query);
		Mockito.when(query.list()).thenReturn(new ArrayList<>());
		final List<Pedidos_Detalle_w> response = pedidoDetalleDAOImpl.obtenerDetallePorPedido(idPedido);
		Mockito.verify(sessionFactory).getCurrentSession();
		Mockito.verify(session).createQuery(strQry);
		Mockito.verify(query).list();
		Assert.assertNotNull(response);
	}
}
