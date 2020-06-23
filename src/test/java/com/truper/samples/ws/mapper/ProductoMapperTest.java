package com.truper.samples.ws.mapper;

import org.junit.Assert;
import org.junit.Test;

import com.truper.samples.ws.dto.ProductoW;
import com.truper.sinv.entity.Producto_w;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author José René Miranda de la O
 *
 */
public class ProductoMapperTest {

	private final ProductoMapper mapper = new ProductoMapper();

	private final PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void testMapToInner() {
		final ProductoW expected = factory.manufacturePojo(ProductoW.class);
		final Producto_w actual = mapper.mapToInner(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getNombre(), actual.getNombre());
		Assert.assertEquals(expected.getSku(), actual.getSku());
		Assert.assertEquals(expected.getExistencia(), actual.getExistencia());
		Assert.assertEquals(expected.getPrice(), actual.getPrice());
	}

	@Test
	public void testMapToInnerNull() {
		final Producto_w actual = mapper.mapToInner(null);
		Assert.assertNull(actual);
	}

	@Test
	public void testMapToOuter() {
		final Producto_w expected = factory.manufacturePojo(Producto_w.class);
		final ProductoW actual = mapper.mapToOuter(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getNombre(), actual.getNombre());
		Assert.assertEquals(expected.getSku(), actual.getSku());
		Assert.assertEquals(expected.getExistencia(), actual.getExistencia());
		Assert.assertEquals(expected.getPrice(), actual.getPrice());
	}

	@Test
	public void testMapToOuterNull() {
		final ProductoW actual = mapper.mapToOuter(null);
		Assert.assertNull(actual);
	}

}
