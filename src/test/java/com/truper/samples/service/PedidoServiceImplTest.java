package com.truper.samples.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.truper.samples.dao.PedidoDAO;
import com.truper.samples.service.impl.PedidoServiceImpl;
import com.truper.samples.util.mapper.Mapper;
import com.truper.samples.ws.dto.PedidosW;
import com.truper.sinv.entity.Pedidos_w;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author José René Miranda de la O
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PedidoServiceImplTest {

	@InjectMocks
	private PedidoServiceImpl pedidoServiceImpl;

	@Mock
	private PedidoDAO pedidoDAO;

	@Mock
	private Mapper<Pedidos_w, PedidosW> pedidoMapper;

	private final PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void testObtenerPedidosPorUsuario() {
		final String username = "admin";
		final Pedidos_w pedido = factory.manufacturePojo(Pedidos_w.class);
		final List<Pedidos_w> pedidos = new ArrayList<>();
		pedidos.add(pedido);
		Mockito.when(pedidoDAO.obtenerPedidosPorUsuario(username)).thenReturn(pedidos);
		Mockito.when(pedidoMapper.mapToOuter(pedido)).thenReturn(new PedidosW());
		final List<PedidosW> list = pedidoServiceImpl.obtenerPedidosPorUsuario(username);
		Mockito.verify(pedidoDAO).obtenerPedidosPorUsuario(username);
		Mockito.verify(pedidoMapper).mapToOuter(pedido);
		Assert.assertNotNull(list);
	}

}
