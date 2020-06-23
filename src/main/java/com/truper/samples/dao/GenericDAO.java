package com.truper.samples.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, PK extends Serializable> {

	/**
	 * Metodo para guardar o actualizar una entidad
	 *
	 * @param entity
	 */
	void saveOrUpdate(T entity);

	/**
	 * Metodo para borrar una entidad
	 *
	 * @param entity
	 */
	void delete(T entity);

	/**
	 * Metodo para actualizar una entidad
	 *
	 * @param entity
	 */
	void update(T entity);

	/**
	 * Metodo para buscar por id
	 *
	 * @param o
	 * @param id
	 * @return
	 */
	Object findById(Class<T> entity, int id);

	/**
	 * MEtodo para guardar una lista de entidades
	 * 
	 * @param entities
	 */
	void saveAll(List<T> entities);

}
