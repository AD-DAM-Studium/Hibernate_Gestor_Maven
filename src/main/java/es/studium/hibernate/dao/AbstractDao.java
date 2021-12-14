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
	//Este m�todo nos va a permitir despu�s implementar los m�todos CRUD con expresiones lambda
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
	//M�todo guardar con expresion Lambda

	@Override
	public void save(T t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));

	}
	//M�todo actualizar con expresion Lambda
	@Override
	public void update(T t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));

	}
	//M�todo eliminar con Lambda
	@Override
	public void delete(T t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));

	}

}
