package br.com.caelum.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	/**
	 * Criar a entidade de persistência ao banco FINANCAS
	 */
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
	
	
	/** 
	 * @return a entity manager
	 */
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
}
