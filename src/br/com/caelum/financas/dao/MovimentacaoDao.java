package br.com.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;

public class MovimentacaoDao {

	private EntityManager em;

	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}

	public List<Double> getMediasPorDiaETipo(TipoMovimentacao saida, Conta conta) {
		// media
		String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta "
				+ " and m.tipoMovimentacao = :pTipoMovimentacao "
				+ " group by day(m.data), month(m.data), year(m.data)"; // com named parameter

		TypedQuery<Double> query = em.createQuery(jpql, Double.class); // adiciona a jpql na query
		query.setParameter("pConta", conta); // adiciona o parametro de busca
		query.setParameter("pTipoMovimentacao", TipoMovimentacao.SAIDA); // adiciona o parametro de busca

		return query.getResultList();
	}

}
