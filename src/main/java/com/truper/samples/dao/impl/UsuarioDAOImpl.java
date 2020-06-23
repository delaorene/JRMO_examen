package com.truper.samples.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.truper.samples.dao.UsuarioDAO;
import com.truper.sinv.entity.Usuarios_w;

/**
 * @author José René Miranda de la O
 *
 */
@Repository
public class UsuarioDAOImpl extends GenericDAOImpl<Usuarios_w, Serializable> implements UsuarioDAO {

	@Override
	public Usuarios_w obtenerUsuarioPorUsername(final String username) {
		final Criteria criteria = getSession().createCriteria(Usuarios_w.class);
		criteria.add(Restrictions.eq("username", username));
		return (Usuarios_w) criteria.uniqueResult();
	}

}
