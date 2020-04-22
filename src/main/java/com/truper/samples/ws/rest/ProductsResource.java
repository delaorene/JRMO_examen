package com.truper.samples.ws.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truper.samples.service.ProductService;
import com.truper.sinv.entity.Producto_w;

import lombok.extern.log4j.Log4j;

/**
 * Controlador que publica un servicio web con distintos paths
 * usando IoC para acceder a los services
 * @author cgarcias
 *
 */
@Log4j
@Controller
@RequestMapping("/ws/products")
public class ProductsResource {
	
	@Autowired
	private ProductService psProdService;
	
	/**
	 * @author cgarcias
	 * Responde un objeto JSON con el producto encontrado si aplica
	 * @param strSku
	 * @return JSON Object
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Producto_w obtenerProduct(@RequestParam("sku") String strSku){
		return psProdService.getBySKU(strSku);
	}
	
	/**
	 * @author cgarcias
	 * Devuelve todos los productos que encuentra el service
	 * @return JSON Object
	 */
	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	@ResponseBody
	public List<Producto_w> iniciarStock(){
		return psProdService.getAll();
	}
	
	/**
	 * @author cgarcias
	 * @param strSku
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public int delete(@RequestParam("sku") String strSku){
		log.info("Eliminando producto " + strSku + " por REST");
		return this.psProdService.deleteBySKU(strSku);
	}
	
}
