package br.com.santander.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("santander");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
