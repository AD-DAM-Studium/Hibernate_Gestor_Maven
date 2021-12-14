package es.studium.hibernate.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.studium.hibernate.utiles.EntityManagerUtil;

public class AbstractDao<T> implements Dao<T> {

	private EntityManager entityManager = EntityManagerUtil.getEntityManager();
	private Class<T> clazz;

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Optional<T> get(long id) {

		return Optional.ofNullable(entityManager.find(clazz, id));
	}

	@Override
	public List<T> getAll() {
		String qlString = "FROM " + clazz.getName();
		Query query = entityManager.createQuery(qlString);
		return query.getResultList();
	}
	//Este método nos va a permitir después implementar los métodos CRUD con expresiones lambda
	private void executeInsideTransaction(Consumer<EntityManager> action) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			action.accept(entityManager);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}
	}
	//Método guardar con expresion Lambda

	@Override
	public void save(T t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));

	}
	//Método actualizar con expresion Lambda
	@Override
	public void update(T t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));

	}
	//Método eliminar con Lambda
	@Override
	public void delete(T t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));

	}

}
