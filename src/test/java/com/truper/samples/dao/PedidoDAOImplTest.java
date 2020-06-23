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

import com.truper.samples.dao.impl.PedidoDAOImpl;
import com.truper.sinv.entity.Pedidos_w;

/**
 * @author José René Miranda de la O
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PedidoDAOImplTest {

	@InjectMocks
	private PedidoDAOImpl pedidoDAOImpl;

	@Mock
	private SessionFactory sessionFactory;

	@Mock
	private Session session;

	@Mock
	private Query query;

	@Test
	public void testObtenerPedidosPorUsuario() {
		final String strQry = "FROM Pedidos_w WHERE username=:username";
		final String username = "admin";
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		Mockito.when(session.createQuery(strQry)).thenReturn(query);
		Mockito.when(query.list()).thenReturn(new ArrayList<>());
		final List<Pedidos_w> response = pedidoDAOImpl.obtenerPedidosPorUsuario(username);
		Mockito.verify(sessionFactory).getCurrentSession();
		Mockito.verify(session).createQuery(strQry);
		Mockito.verify(query).list();
		Assert.assertNotNull(response);
	}
}
