package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPQL {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// cria objeto com e seta o id para query
		Conta conta = new Conta();
		conta.setId(2);

//		String jpql = "select m from Movimentacao m where m.conta.id = 2";
		String jpql = "select m from Movimentacao m where m.conta = :pConta " +
		" and m.tipoMovimentacao = :pTipoMovimentacao " +
		" order by m.valor desc"; // com named
																											// parameter
		Query query = em.createQuery(jpql); // adiciona a jpql na query
		query.setParameter("pConta", conta); // adiciona o parametro de busca
		query.setParameter("pTipoMovimentacao", TipoMovimentacao.ENTRADA); // adiciona o parametro de busca

		List<Movimentacao> resultados = query.getResultList();

		for (Movimentacao m : resultados) {
			System.out.println("Descrição: " + m.getDescricao());
			System.out.println("Valor: " + m.getValor());
			System.out.println("Tipo Movimentacao: " + m.getTipoMovimentacao());
			System.out.println("Conta id: " + m.getConta().getId());
		}

		em.getTransaction().commit();
		em.close();
	}

}
