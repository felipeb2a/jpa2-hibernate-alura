package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TestesFuncoesJPQL {

	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// cria objeto com e seta o id para query
		Conta conta = new Conta();
		conta.setId(2);

		//soma
//		String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta "
//				+ " and m.tipoMovimentacao = :pTipoMovimentacao " 
//				+ " order by m.valor desc"; // com named parameter
		//media
		String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta "
				+ " and m.tipoMovimentacao = :pTipoMovimentacao " 
				+ " order by m.valor desc"; // com named parameter
		
		Query query = em.createQuery(jpql); // adiciona a jpql na query
		query.setParameter("pConta", conta); // adiciona o parametro de busca
		query.setParameter("pTipoMovimentacao", TipoMovimentacao.SAIDA); // adiciona o parametro de busca

//		BigDecimal soma = (BigDecimal) query.getSingleResult();
		Double media = (Double) query.getSingleResult();

//		System.out.println("A soma é : "+ soma);
		System.out.println("A média é : "+ media);
		em.getTransaction().commit();
		em.close();
	}
}
