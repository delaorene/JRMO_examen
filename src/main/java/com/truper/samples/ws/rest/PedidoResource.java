package com.truper.samples.ws.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.truper.samples.service.PedidoDetalleService;
import com.truper.samples.service.PedidoService;
import com.truper.samples.ws.dto.Pedido;
import com.truper.samples.ws.dto.PedidosW;

/**
 * @author José René Miranda de la O
 *
 */
@Controller
@RequestMapping("/ws/pedido")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private PedidoDetalleService pedidoDetalleService;

	/**
	 * @author cgarcias Devuelve todos los productos que encuentra el service
	 * @return JSON Object
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<PedidosW> obtenerPedidos() {
		return pedidoService.obtenerPedidos();
	}

	/**
	 * @author Metodo para obtener los pedidos por usuario
	 * @return JSON Object
	 */
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	@ResponseBody
	public List<PedidosW> obtenerPedidosPorUsuario(@PathVariable("username") final String username) {
		return pedidoService.obtenerPedidosPorUsuario(username);
	}

	/**
	 * @author Metodo para obtener el detalle del pedido
	 * @return JSON Object
	 */
	@RequestMapping(value = "/detalle/{idPedido}", method = RequestMethod.GET)
	@ResponseBody
	public Pedido obtenerDetallePorPedido(@PathVariable("idPedido") final Integer idPedido) {
		return pedidoDetalleService.obtenerDetallePorPedido(idPedido);
	}

	/**
	 * @author Metodo para obtener el detalle del pedido
	 * @return JSON Object
	 */
	@RequestMapping(value = "/detalles", method = RequestMethod.GET)
	@ResponseBody
	public List<Pedido> obtenerDetalles() {
		return pedidoDetalleService.obtenerDetalles();
	}

	/**
	 * @author Metodo para crear un pedido
	 * @return JSON Object
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void crearPedido(@RequestBody final Pedido pedidio) {
		pedidoDetalleService.crearPedidoDetalle(pedidio);
	}
}
