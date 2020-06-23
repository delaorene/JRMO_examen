package com.truper.samples.dao;

import static org.mockito.Mockito.atLeastOnce;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.truper.samples.dao.impl.GenericDAOImpl;
import com.truper.sinv.entity.Pedidos_w;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author José René Miranda de la O
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GeneticDAOImplTest {

	@InjectMocks
	private GenericDAOImpl<Object, ?> genericDAOImpl;

	@Mock
	private SessionFactory sessionFactory;

	@Mock
	private Session session;

	private final PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void testFindById() {
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		Mockito.when(session.get(Object.class, 1)).thenReturn(new Pedidos_w());
		final Pedidos_w response = (Pedidos_w) genericDAOImpl.findById(Object.class, 1);
		Mockito.verify(sessionFactory).getCurrentSession();
		Mockito.verify(session).get(Object.class, 1);
		Assert.assertNotNull(response);
	}

	@Test
	public void testSaveOrUpdate() {
		final Pedidos_w pedido = factory.manufacturePojo(Pedidos_w.class);
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		Mockito.doNothing().when(session).saveOrUpdate(pedido);
		genericDAOImpl.saveOrUpdate(pedido);
		Mockito.verify(sessionFactory).getCurrentSession();
		Mockito.verify(session).saveOrUpdate(pedido);
	}

	@Test
	public void testDelete() {
		final Pedidos_w pedido = factory.manufacturePojo(Pedidos_w.class);
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		Mockito.doNothing().when(session).delete(pedido);
		genericDAOImpl.delete(pedido);
		Mockito.verify(sessionFactory).getCurrentSession();
		Mockito.verify(session, atLeastOnce()).delete(pedido);
	}

	@Test
	public void testUpdate() {
		final Pedidos_w pedido = factory.manufacturePojo(Pedidos_w.class);
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		Mockito.doNothing().when(session).update(pedido);
		genericDAOImpl.update(pedido);
		Mockito.verify(sessionFactory).getCurrentSession();
		Mockito.verify(session, atLeastOnce()).update(pedido);
	}
}
