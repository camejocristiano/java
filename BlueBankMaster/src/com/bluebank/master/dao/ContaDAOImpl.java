package com.bluebank.master.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.bluebank.master.model.Conta;

public class ContaDAOImpl implements ContaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	//setter for sessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Conta findById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Conta conta = new Conta();
		try {
			conta = (Conta) session.get(Conta.class, id);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return conta;
	}

	public Conta findByAgenciaNumero(String agencia, String numero) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Conta conta = new Conta();
		String hql = "FROM com.bluebank.master.model.Conta WHERE agencia = ? AND numero = ?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, agencia);
			query.setParameter(1, numero);
			conta = (Conta) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return conta;
	}
	
	public Conta findByAgenciaNumeroCpf(String agencia, String numero, String cpf) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Conta conta = new Conta();
		String hql = "FROM com.bluebank.master.model.Conta WHERE agencia = ? AND numero = ? AND cpf = ?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, agencia);
			query.setParameter(1, numero);
			query.setParameter(2, cpf);
			conta = (Conta) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return conta;
	}

	public void saveConta(Conta Conta) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (Conta != null) {
			try {
				session.save(Conta);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void updateConta(Conta Conta) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (Conta != null) {
			try {
				session.update(Conta);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void deleteContaById(long id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Conta conta = new Conta();
		try {
			conta = (Conta) session.get(Conta.class, id);
			session.delete(conta);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Conta> findAllContas() {
		List<Conta> conta = new ArrayList<Conta>();
		Session session = sessionFactory.openSession();
		conta = session.createQuery("From com.bluebank.master.model.Conta").list();
		return conta;
	}

	
	public void deleteAllContas() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.createQuery("delete from Conta").executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	public boolean isContaExist(Conta Conta) {
		
			return findByAgenciaNumero(Conta.getAgencia(), Conta.getNumero())!=null;
	}

}

