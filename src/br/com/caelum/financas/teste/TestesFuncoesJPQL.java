package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
				+ " group by day(m.data), month(m.data), year(m.data)"; // com named parameter
		
		TypedQuery<Double> query = em.createQuery(jpql, Double.class); // adiciona a jpql na query
		query.setParameter("pConta", conta); // adiciona o parametro de busca
		query.setParameter("pTipoMovimentacao", TipoMovimentacao.SAIDA); // adiciona o parametro de busca

//		BigDecimal soma = (BigDecimal) query.getSingleResult();
		List<Double> medias = (List<Double>) query.getResultList();

//		System.out.println("A soma � : "+ soma);
		System.out.println("A m�dia � : "+ medias.get(0));
		System.out.println("A m�dia � : "+ medias.get(1));
		System.out.println("A m�dia � : "+ medias.get(2));
		em.getTransaction().commit();
		em.close();
	}
}