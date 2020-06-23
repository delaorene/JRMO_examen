package com.truper.samples.ws.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truper.samples.service.ProductService;
import com.truper.samples.ws.dto.ProductoW;

/**
 * Controlador que publica un servicio web con distintos paths usando IoC para
 * acceder a los services
 *
 * @author cgarcias
 *
 */
@Controller
@RequestMapping("/ws/products")
public class ProductsResource {

	@Autowired
	private ProductService psProdService;

	/**
	 * @author cgarcias Responde un objeto JSON con el producto encontrado si aplica
	 * @param strSku
	 * @return JSON Object
	 */
	@RequestMapping(value = "/{sku}", method = RequestMethod.GET)
	@ResponseBody
	public ProductoW obtenerProduct(@PathVariable("sku") final String strSku) {
		return psProdService.getBySKU(strSku);
	}

	/**
	 * @author cgarcias Devuelve todos los productos que encuentra el service
	 * @return JSON Object
	 */
	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductoW> iniciarStock() {
		return psProdService.getAll();
	}

	/**
	 * @author cgarcias
	 * @param strSku
	 */
	@RequestMapping(value = "/{sku}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("sku") final String strSku) {
		psProdService.deleteBySKU(strSku);
	}
}
