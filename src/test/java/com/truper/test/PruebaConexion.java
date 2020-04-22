package com.truper.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.truper.samples.dao.ProductosDAO;
import com.truper.samples.dao.UserDAO;
import com.truper.sinv.entity.Producto_w;
import com.truper.sinv.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
public class PruebaConexion {

	@Autowired
	private ProductosDAO productoDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void readXML(){
		UserVO u = userDAO.getByUser("app");
		System.out.println(u.toString());
	}
	
	@Test
	public void getProducts(){
		List<Producto_w> productos = this.productoDAO.getAll();
		if(productos != null) {
			for(Producto_w prod : productos) {
				System.out.println(prod.getSku() + " - " + prod.getNombre());
			}
		}
	}
	
}
