package com.truper.samples.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.truper.samples.dao.PedidoDAO;
import com.truper.samples.service.PedidoService;
import com.truper.samples.util.mapper.Mapper;
import com.truper.samples.ws.dto.PedidosW;
import com.truper.sinv.entity.Pedidos_w;

/**
 * @author José René Miranda de la O
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class }, readOnly = true)
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;

	@Autowired
	@Qualifier("pedidos-mapper")
	private Mapper<Pedidos_w, PedidosW> pedidoMapper;

	@Override
	public List<PedidosW> obtenerPedidosPorUsuario(final String username) {
		List<PedidosW> lista = null;
		final List<Pedidos_w> pedidos = pedidoDAO.obtenerPedidosPorUsuario(username);
		if (CollectionUtils.isNotEmpty(pedidos)) {
			lista = new ArrayList<>();
			for (final Pedidos_w pedidos_w : pedidos) {
				lista.add(pedidoMapper.mapToOuter(pedidos_w));
			}
		}
		return lista;
	}

	@Override
	public List<PedidosW> obtenerPedidos() {
		List<PedidosW> lista = null;
		final List<Pedidos_w> pedidos = pedidoDAO.obtenerPedidos();
		if (CollectionUtils.isNotEmpty(pedidos)) {
			lista = new ArrayList<>();
			for (final Pedidos_w pedidos_w : pedidos) {
				lista.add(pedidoMapper.mapToOuter(pedidos_w));
			}
		}
		return lista;
	}

}
