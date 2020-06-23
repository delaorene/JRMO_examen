package com.truper.samples.ws.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.truper.samples.util.mapper.Mapper;
import com.truper.samples.ws.dto.PedidosDetalleW;
import com.truper.samples.ws.dto.PedidosW;
import com.truper.samples.ws.dto.ProductoW;
import com.truper.sinv.entity.Pedidos_Detalle_w;
import com.truper.sinv.entity.Pedidos_w;
import com.truper.sinv.entity.Producto_w;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author José René Miranda de la O
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PedidosDetalleMapperTest {

	@InjectMocks
	private PedidosDetalleMapper mapper;

	@Mock
	private Mapper<Producto_w, ProductoW> productoMapper;

	@Mock
	private Mapper<Pedidos_w, PedidosW> pedidosMapper;

	private final PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void testMapToInner() {
		final PedidosDetalleW expected = factory.manufacturePojo(PedidosDetalleW.class);
		Mockito.when(productoMapper.mapToInner(expected.getSku())).thenReturn(new Producto_w());
		Mockito.when(pedidosMapper.mapToInner(expected.getIdPedido())).thenReturn(new Pedidos_w());
		final Pedidos_Detalle_w actual = mapper.mapToInner(expected);
		Mockito.verify(productoMapper).mapToInner(expected.getSku());
		Mockito.verify(pedidosMapper).mapToInner(expected.getIdPedido());
		Assert.assertNotNull(actual);
		Assert.assertNotNull(actual.getIdPedido());
		Assert.assertNotNull(actual.getSku());
		Assert.assertEquals(expected.getAmout(), actual.getAmout());
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getPrice(), actual.getPrice());
	}

	@Test
	public void testMapToInnerNull() {
		final Pedidos_Detalle_w actual = mapper.mapToInner(null);
		Assert.assertNull(actual);
	}

	@Test
	public void testMapToOuter() {
		final Pedidos_Detalle_w expected = factory.manufacturePojo(Pedidos_Detalle_w.class);
		Mockito.when(productoMapper.mapToOuter(expected.getSku())).thenReturn(new ProductoW());
		Mockito.when(pedidosMapper.mapToOuter(expected.getIdPedido())).thenReturn(new PedidosW());
		final PedidosDetalleW actual = mapper.mapToOuter(expected);
		Mockito.verify(productoMapper).mapToOuter(expected.getSku());
		Mockito.verify(pedidosMapper).mapToOuter(expected.getIdPedido());
		Assert.assertNotNull(actual);
		Assert.assertNotNull(actual.getIdPedido());
		Assert.assertNotNull(actual.getSku());
		Assert.assertEquals(expected.getAmout(), actual.getAmout());
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getPrice(), actual.getPrice());
	}

	@Test
	public void testMapToOuterNull() {
		final PedidosDetalleW actual = mapper.mapToOuter(null);
		Assert.assertNull(actual);
	}
}
