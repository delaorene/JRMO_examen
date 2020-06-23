package com.truper.samples.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.truper.samples.dao.PedidoDAO;
import com.truper.samples.dao.PedidoDetalleDAO;
import com.truper.samples.dao.ProductosDAO;
import com.truper.samples.dao.UsuarioDAO;
import com.truper.samples.service.PedidoDetalleService;
import com.truper.samples.util.mapper.Mapper;
import com.truper.samples.ws.dto.Pedido;
import com.truper.samples.ws.dto.PedidosDetalleW;
import com.truper.samples.ws.dto.PedidosW;
import com.truper.sinv.entity.Pedidos_Detalle_w;
import com.truper.sinv.entity.Pedidos_w;
import com.truper.sinv.entity.Producto_w;

/**
 * @author José René Miranda de la O
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class }, readOnly = true)
public class PedidoDetalleServiceImpl implements PedidoDetalleService {

	@Autowired
	private PedidoDAO pedidoDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private PedidoDetalleDAO pedidoDetalleDAO;

	@Autowired
	private ProductosDAO productoDAO;

	@Autowired
	@Qualifier("pedidos-mapper")
	private Mapper<Pedidos_w, PedidosW> pedidoMapper;

	@Autowired
	@Qualifier("pedidos-detalle-mapper")
	private Mapper<Pedidos_Detalle_w, PedidosDetalleW> pedidoDetalleMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void crearPedidoDetalle(final Pedido pedido) {
		final Pedidos_w pedido_w = pedidoMapper.mapToInner(pedido.getPedido());
		pedido_w.setUsername(usuarioDAO.obtenerUsuarioPorUsername("admin"));
		pedido_w.setDateSale(new Date());
		pedidoDAO.saveOrUpdate(pedido_w);
		if (CollectionUtils.isNotEmpty(pedido.getDetalle())) {
			for (final PedidosDetalleW detalle : pedido.getDetalle()) {
				final Pedidos_Detalle_w detalle_w = pedidoDetalleMapper.mapToInner(detalle);
				final Producto_w producto_w = productoDAO.getBySKU(detalle.getSku().getSku());
				detalle_w.setIdPedido(pedido_w);
				detalle_w.setSku(producto_w);
				detalle_w.setPrice(producto_w.getPrice());
				final Integer cantidadActual = producto_w.getExistencia();
				producto_w.setExistencia(cantidadActual - detalle.getAmout().intValue());
				pedidoDetalleDAO.saveOrUpdate(detalle_w);
				productoDAO.saveOrUpdate(producto_w);
			}
		}

	}

	@Override
	public Pedido obtenerDetallePorPedido(final Integer idPedido) {
		Pedido detalles = null;
		final Pedidos_w pedido_w = (Pedidos_w) pedidoDAO.findById(Pedidos_w.class, idPedido);
		final List<Pedidos_Detalle_w> detalle = pedidoDetalleDAO.obtenerDetallePorPedido(idPedido);
		if (CollectionUtils.isNotEmpty(detalle)) {
			detalles = new Pedido();
			detalles.setPedido(pedidoMapper.mapToOuter(pedido_w));
			final List<PedidosDetalleW> detallesPedido = new ArrayList<>();
			for (final Pedidos_Detalle_w pedidos_Detalle_w : detalle) {
				detallesPedido.add(pedidoDetalleMapper.mapToOuter(pedidos_Detalle_w));
			}
			detalles.setDetalle(detallesPedido);
		}
		return detalles;
	}

	@Override
	public List<Pedido> obtenerDetalles() {
		List<Pedido> detalles = null;
		final List<Pedidos_w> pedidos = pedidoDAO.obtenerPedidos();
		if (CollectionUtils.isNotEmpty(pedidos)) {
			detalles = new ArrayList<>();
			for (final Pedidos_w pedidos_w : pedidos) {
				final Pedido pedido = new Pedido();
				pedido.setPedido(pedidoMapper.mapToOuter(pedidos_w));
				final List<Pedidos_Detalle_w> detallewList = pedidoDetalleDAO.obtenerDetallePorPedido(pedidos_w.getId());
				if (CollectionUtils.isNotEmpty(detallewList)) {
					final List<PedidosDetalleW> detallesPedido = new ArrayList<>();
					for (final Pedidos_Detalle_w detalle : detallewList) {
						final PedidosDetalleW detalle_w = pedidoDetalleMapper.mapToOuter(detalle);
						detallesPedido.add(detalle_w);
					}
					pedido.setDetalle(detallesPedido);
				}
				detalles.add(pedido);
			}
		}
		return detalles;
	}

}
