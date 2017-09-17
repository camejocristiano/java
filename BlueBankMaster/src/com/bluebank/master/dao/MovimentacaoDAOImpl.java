package com.bluebank.master.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bluebank.master.model.Conta;
import com.bluebank.master.model.Movimentacao;

@Repository
public class MovimentacaoDAOImpl implements MovimentacaoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	//setter for sessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Movimentacao findById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Movimentacao conta = new Movimentacao();
		try {
			conta = (Movimentacao) session.get(Movimentacao.class, id);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return conta;
	}

	public Movimentacao findByNumero(String numero) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Movimentacao conta = new Movimentacao();
		String hql = "from com.bluebank.master.model.Movimentacao where numero = ?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, numero);
			conta = (Movimentacao) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return conta;
	}

	public void saveMovimentacao(Movimentacao Movimentacao) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (Movimentacao != null) {
			try {
				session.save(Movimentacao);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
		}
		session.close();
	}
	
	public void depositar(Movimentacao Movimentacao, Conta Conta) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (Movimentacao != null) {
			try {
				session.update(Conta);
				session.save(Movimentacao);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
		}
		session.close();
	}
	
	public void sacar(Movimentacao Movimentacao, Conta Conta) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (Movimentacao != null) {
			try {
				session.update(Conta);
				session.save(Movimentacao);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
		}
		session.close();
	}
	
	public void transferir(Movimentacao Movimentacaoo, Conta Contao, Movimentacao Movimentacaod, Conta Contad) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (Movimentacaoo != null && Movimentacaod != null) {
			try {
				session.update(Contao);
				session.save(Movimentacaoo);
				session.update(Contad);
				session.save(Movimentacaod);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
		}
		session.close();
	}

	public void updateMovimentacao(Movimentacao Movimentacao) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (Movimentacao != null) {
			try {
				session.update(Movimentacao);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void deleteMovimentacaoById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Movimentacao conta = new Movimentacao();
		try {
			conta = (Movimentacao) session.get(Movimentacao.class, id);
			session.delete(conta);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Movimentacao> findAllMovimentacoes() {
		List<Movimentacao> conta = new ArrayList<Movimentacao>();
		Session session = sessionFactory.openSession();
		conta = session.createQuery("From com.bluebank.master.model.Movimentacao").list();
		return conta;
	}

	
	public void deleteAllMovimentacoes() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.createQuery("delete from Movimentacao").executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	public boolean isMovimentacaoExist(Movimentacao Movimentacao) {
		
			return findById(Movimentacao.getId())!=null;
	}

}

