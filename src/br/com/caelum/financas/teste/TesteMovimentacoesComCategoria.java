package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

class TesteMovimentacoesComCategoria {

	public static void main(String[] args) {
		Categoria categoria1 = new Categoria("viagem");
		Categoria categoria2 = new Categoria("negocio");
		
		Conta conta = new Conta();
		conta.setId(2);
		
		Movimentacao movimentacao1 = new Movimentacao();
		movimentacao1.setData(Calendar.getInstance()); //hoje
		movimentacao1.setDescricao("Viagem a SP");
		movimentacao1.setTipo(TipoMovimentacao.SAIDA);
		movimentacao1.setValor(new BigDecimal("100.0"));
		movimentacao1.setCategoria(Arrays.asList(categoria1, categoria2));
		
		movimentacao1.setConta(conta);
		
		Movimentacao movimentacao2 = new Movimentacao();
		movimentacao2.setData(Calendar.getInstance()); //hoje
		movimentacao2.setDescricao("Viagem a RJ");
		movimentacao2.setTipo(TipoMovimentacao.SAIDA);
		movimentacao2.setValor(new BigDecimal("300.0"));
		movimentacao2.setCategoria(Arrays.asList(categoria1, categoria2));
		
		movimentacao1.setConta(conta);
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
//		em.persist(categoria1);
//		em.persist(categoria2	);
//		
//		em.persist(movimentacao1);
//		em.persist(movimentacao2);
		
		//buscar movimentacao no banco e alterar o valor
		Movimentacao movimentacao = em.find(Movimentacao.class, 2);
		movimentacao.setValor(new BigDecimal("400.0"));
		
		em.getTransaction().commit();
		em.close();
		
	}
}
