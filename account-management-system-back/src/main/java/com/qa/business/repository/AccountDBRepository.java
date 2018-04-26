package com.qa.business.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;

	@Transactional(Transactional.TxType.SUPPORTS)
public class AccountDBRepository {

	@PersistenceContext(unitName = "accountManagementSystemPU")
	private EntityManager em;
	
	@Transactional(Transactional.TxType.REQUIRED)
	public Account create(Account account) {
		em.persist(account);
		return account;
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void delete(Long id) {
		em.remove(em.getReference(Account.class, id));
	}
	
	public Account find(Long id) {
		return em.find(Account.class, id);
	}
	
	public List<Account> findAll(){
		TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a order by a.accountNumber", Account.class);
		return query.getResultList();
	}

	public Long countAll() {
		TypedQuery<Long> query = em.createQuery("SELECT count(a) from Account a", Long.class);
		return query.getSingleResult();
	}
	

	
	
}
