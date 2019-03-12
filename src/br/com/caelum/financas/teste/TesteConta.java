package br.com.caelum.financas.teste;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by leonardocordeiro on 24/02/17.
 */
public class TesteConta {

    public static void main(String[] args) {

        Conta conta = new Conta();
        conta.setTitular("João");
        conta.setBanco("Banco do Brasil");
        conta.setAgencia("123");
        conta.setNumero("456");        
        
        EntityManager em = new JPAUtil().getEntityManager();

        //inicia a transação
        em.getTransaction().begin();
        //persiste a conta
        em.persist(conta);
        //finalina
        em.getTransaction().commit();
        //fecha a entiryManager
        em.close();
    }
}
