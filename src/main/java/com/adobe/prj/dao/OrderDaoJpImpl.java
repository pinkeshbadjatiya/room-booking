package com.adobe.prj.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.adobe.prj.entity.Order;

@Repository
public class OrderDaoJpImpl implements OrderDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void placeOrder(Order o) {
		em.persist(o);
	}

	@Override
	public List<Order> getOrders() {
		String JPQL = "select o from Order o";
		TypedQuery<Order> query = em.createQuery(JPQL, Order.class);
		return query.getResultList();
	}

}
