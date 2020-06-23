package com.truper.samples.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.truper.samples.dao.GenericDAO;

/**
 * Clase para tener los metodos que comparten todos los DAO y asñi evitar
 * repetir código
 *
 * @author José René Miranda de la O
 *
 */
@Repository
public class GenericDAOImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveOrUpdate(final T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final T entity) {
		getSession().delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(final T entity) {
		getSession().update(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object findById(final Class<T> entity, final int id) {
		return getSession().get(entity, id);
	}

	@Override
	public void saveAll(final List<T> entities) {
		for (final T entity : entities) {
			getSession().saveOrUpdate(entity);
		}
	}

}
