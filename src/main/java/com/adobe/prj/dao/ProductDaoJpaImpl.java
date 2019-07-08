package com.adobe.prj.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adobe.prj.entity.Product;

@Repository		// All 'Dao' classes should have repository
public class ProductDaoJpaImpl implements ProductDao {

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional		// Ensures the whole function gets executed in an atomic fashion. If not, then it rollsback the whole operation.
	public void addProduct(Product p) {
		System.out.println("Product Added!!");
		em.persist(p);
	}

	@Override
	public List<Product> getProducts() {
		String JPQL = "SELECT p FROM Product p";
		TypedQuery<Product> query = em.createQuery(JPQL, Product.class);
		return query.getResultList();
	}

	@Override
	public Product getProduct(int id) {
		// Limitation of find is that it can only find based on the primary key
		// 'find' basically looks at the annotations @Table and @Id to generate the SQL
		return em.find(Product.class, id);
	}

}
