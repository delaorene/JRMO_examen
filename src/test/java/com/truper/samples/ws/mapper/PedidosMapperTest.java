package com.truper.samples.ws.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.truper.samples.util.mapper.Mapper;
import com.truper.samples.ws.dto.PedidosW;
import com.truper.samples.ws.dto.UsuariosW;
import com.truper.sinv.entity.Pedidos_w;
import com.truper.sinv.entity.Usuarios_w;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author José René Miranda de la O
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PedidosMapperTest {

	@InjectMocks
	private PedidosMapper mapper;

	@Mock
	private Mapper<Usuarios_w, UsuariosW> usuariosMapper;

	private final PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void testMapToInner() {
		final PedidosW expected = factory.manufacturePojo(PedidosW.class);
		Mockito.when(usuariosMapper.mapToInner(expected.getUsername())).thenReturn(new Usuarios_w());
		final Pedidos_w actual = mapper.mapToInner(expected);
		Mockito.verify(usuariosMapper).mapToInner(expected.getUsername());
		Assert.assertNotNull(actual);
		Assert.assertNotNull(actual.getUsername());
		Assert.assertEquals(expected.getDateSale(), actual.getDateSale());
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getTotal(), actual.getTotal());
	}

	@Test
	public void testMapToInnerNull() {
		final Pedidos_w actual = mapper.mapToInner(null);
		Assert.assertNull(actual);
	}

	@Test
	public void testMapToOuter() {
		final Pedidos_w expected = factory.manufacturePojo(Pedidos_w.class);
		Mockito.when(usuariosMapper.mapToOuter(expected.getUsername())).thenReturn(new UsuariosW());
		final PedidosW actual = mapper.mapToOuter(expected);
		Mockito.verify(usuariosMapper).mapToOuter(expected.getUsername());
		Assert.assertNotNull(actual);
		Assert.assertNotNull(actual.getUsername());
		Assert.assertEquals(expected.getDateSale(), actual.getDateSale());
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getTotal(), actual.getTotal());
	}

	@Test
	public void testMapToOuterNull() {
		final PedidosW actual = mapper.mapToOuter(null);
		Assert.assertNull(actual);
	}
}
