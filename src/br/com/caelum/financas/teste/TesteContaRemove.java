package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteContaRemove {
	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setId(1);
        conta.setTitular("Danilo");
        conta.setBanco("Bano do Brasil");
        conta.setAgencia("123");
        conta.setNumero("456");

        EntityManager em = new JPAUtil().getEntityManager();

        //inicia a transação
        em.getTransaction().begin();
        //busca a conta para remover
        conta = em.find(Conta.class, 1); 
        //remove a conta
        em.remove(conta);
        //finaliza
        em.getTransaction().commit();
        //fecha a entiryManager
        em.close();
	}
}
