package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.dao.MovimentacaoDao;
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

		MovimentacaoDao dao = new MovimentacaoDao(em);
//		dao.setEm(em);
		List<Double> medias = dao.getMediasPorDiaETipo(TipoMovimentacao.SAIDA, conta);

//		System.out.println("A soma é : "+ soma);
		System.out.println("A média é : "+ medias.get(0));
		System.out.println("A média é : "+ medias.get(1));
		System.out.println("A média é : "+ medias.get(2));
		em.getTransaction().commit();
		em.close();
	}
}
