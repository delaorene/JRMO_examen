package com.truper.samples.ws.mapper;

import org.junit.Assert;
import org.junit.Test;

import com.truper.samples.ws.dto.UsuariosW;
import com.truper.sinv.entity.Usuarios_w;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author José René Miranda de la O
 *
 */
public class UsuariosMapperTest {

	private final UsuariosMapper mapper = new UsuariosMapper();

	private final PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void testMapToInner() {
		final UsuariosW expected = factory.manufacturePojo(UsuariosW.class);
		final Usuarios_w actual = mapper.mapToInner(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getApellidos(), actual.getApellidos());
		Assert.assertEquals(expected.getNombre(), actual.getNombre());
		Assert.assertEquals(expected.getPassword(), actual.getPassword());
		Assert.assertEquals(expected.getRole(), actual.getRole());
		Assert.assertEquals(expected.getUsername(), actual.getUsername());
	}

	@Test
	public void testMapToInnerNull() {
		final Usuarios_w actual = mapper.mapToInner(null);
		Assert.assertNull(actual);
	}

	@Test
	public void testMapToOuter() {
		final Usuarios_w expected = factory.manufacturePojo(Usuarios_w.class);
		final UsuariosW actual = mapper.mapToOuter(expected);
		Assert.assertNotNull(actual);
		Assert.assertEquals(expected.getApellidos(), actual.getApellidos());
		Assert.assertEquals(expected.getNombre(), actual.getNombre());
		Assert.assertEquals(expected.getPassword(), actual.getPassword());
		Assert.assertEquals(expected.getRole(), actual.getRole());
		Assert.assertEquals(expected.getUsername(), actual.getUsername());
	}

	@Test
	public void testMapToOuterNull() {
		final UsuariosW actual = mapper.mapToOuter(null);
		Assert.assertNull(actual);
	}

}
