package com.truper.samples.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.truper.samples.dao.PedidoDAO;
import com.truper.samples.dao.PedidoDetalleDAO;
import com.truper.samples.dao.ProductosDAO;
import com.truper.samples.dao.UsuarioDAO;
import com.truper.samples.service.impl.PedidoDetalleServiceImpl;
import com.truper.samples.util.mapper.Mapper;
import com.truper.samples.ws.dto.Pedido;
import com.truper.samples.ws.dto.PedidosDetalleW;
import com.truper.samples.ws.dto.PedidosW;
import com.truper.sinv.entity.Pedidos_Detalle_w;
import com.truper.sinv.entity.Pedidos_w;
import com.truper.sinv.entity.Producto_w;
import com.truper.sinv.entity.Usuarios_w;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author José René Miranda de la O
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PedidoDetalleServiceImplTest {

	@InjectMocks
	private PedidoDetalleServiceImpl pedidoDetalleServiceImpl;

	@Mock
	private PedidoDAO pedidoDAO;

	@Mock
	private UsuarioDAO usuarioDAO;

	@Mock
	private PedidoDetalleDAO pedidoDetalleDAO;

	@Mock
	private ProductosDAO productoDAO;

	@Mock
	private Mapper<Pedidos_w, PedidosW> pedidoMapper;

	@Mock
	private Mapper<Pedidos_Detalle_w, PedidosDetalleW> pedidoDetalleMapper;

	private final PodamFactory factory = new PodamFactoryImpl();
	private static final String SKU = "76655";

	@Test
	public void testCrearPedidoDetalle() {
		final Pedido pedido = new Pedido();
		pedido.setDetalle(new ArrayList<PedidosDetalleW>());
		final PedidosDetalleW detalle = factory.manufacturePojo(PedidosDetalleW.class);
		detalle.getSku().setSku(SKU);
		final PedidosW pedidoW = factory.manufacturePojo(PedidosW.class);
		pedido.getDetalle().add(detalle);
		pedido.setPedido(pedidoW);
		final Producto_w producto_w = factory.manufacturePojo(Producto_w.class);
		producto_w.setSku(SKU);
		producto_w.setExistencia(20);
		final Usuarios_w usuario = factory.manufacturePojo(Usuarios_w.class);
		final Pedidos_w pedido_w = factory.manufacturePojo(Pedidos_w.class);
		final Pedidos_Detalle_w detalle_w = new Pedidos_Detalle_w();
		detalle_w.setIdPedido(pedido_w);
		detalle_w.setSku(producto_w);
		detalle_w.setAmout(new BigDecimal("234.23"));
		detalle_w.setId(2);
		detalle_w.setPrice(new BigDecimal("12345.23"));
		Mockito.when(pedidoMapper.mapToInner(pedido.getPedido())).thenReturn(pedido_w);
		Mockito.when(usuarioDAO.obtenerUsuarioPorUsername("admin")).thenReturn(usuario);
		Mockito.doNothing().when(pedidoDAO).saveOrUpdate(pedido_w);
		Mockito.when(pedidoDetalleMapper.mapToInner(pedido.getDetalle().get(0))).thenReturn(detalle_w);
		Mockito.when(productoDAO.getBySKU(SKU)).thenReturn(producto_w);
		Mockito.doNothing().when(pedidoDetalleDAO).saveOrUpdate(detalle_w);
		Mockito.doNothing().when(productoDAO).saveOrUpdate(producto_w);
		pedidoDetalleServiceImpl.crearPedidoDetalle(pedido);
		Mockito.verify(pedidoMapper).mapToInner(pedido.getPedido());
		Mockito.verify(pedidoDAO).saveOrUpdate(pedido_w);
		Mockito.verify(usuarioDAO).obtenerUsuarioPorUsername("admin");
		Mockito.verify(pedidoDetalleMapper).mapToInner(pedido.getDetalle().get(0));
		Mockito.verify(productoDAO).getBySKU(SKU);
		Mockito.verify(pedidoDetalleDAO).saveOrUpdate(detalle_w);
		Mockito.verify(productoDAO).saveOrUpdate(producto_w);
		Assert.assertTrue(producto_w.getExistencia() == 19);
	}
}
