package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteMovimentacaoPorCategoria {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// cria objeto com e seta o id para query
		Categoria categoria = new Categoria();
		categoria.setId(1);

		String jpql = "select m from Movimentacao m join m.categoria c where c = :pCategoria";
																											// parameter
		Query query = em.createQuery(jpql); // adiciona a jpql na query
		query.setParameter("pCategoria", categoria); // adiciona o parametro de busca

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
