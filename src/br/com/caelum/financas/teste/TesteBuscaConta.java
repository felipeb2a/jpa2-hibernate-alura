package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteBuscaConta {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		Conta conta = em.find(Conta.class, 1);
		conta.setTitular("João");
		conta.setAgencia("456");
		em.getTransaction().commit();
		
		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();
				
		conta.setTitular("Leonardo");
		//transformar uma conta em managed novamente
		em2.merge(conta);
		em2.getTransaction().commit();
		
		
	}
}
