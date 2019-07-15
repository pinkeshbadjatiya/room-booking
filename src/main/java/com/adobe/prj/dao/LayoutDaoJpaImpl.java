package com.adobe.prj.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adobe.prj.entity.Layout;

@Repository		// All 'Dao' classes should have repository
public class LayoutDaoJpaImpl implements LayoutDao {

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Layout getLayout(int id) {
		// Limitation of find is that it can only find based on the primary key
		// 'find' basically looks at the annotations @Table and @Id to generate the SQL
		return em.find(Layout.class, id);
	}

	@Override
	public List<Layout> getLayouts() {
		String JPQL = "SELECT l FROM Layout l";
		TypedQuery<Layout> query = em.createQuery(JPQL, Layout.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateLayout(Layout l) {
		Layout _l = em.find(Layout.class, l.getId());
		_l.setImage(l.getImage());
		_l.setTitle(l.getTitle());
		em.persist(_l);
	}

	@Override
	@Transactional
	public void deleteLayout(Layout l) {
		Layout _l = em.find(Layout.class, l.getId());
		em.remove(_l);
	}

	@Override
	@Transactional		// Ensures the whole function gets executed in an atomic fashion. If not, then it rollsback the whole operation.
	public void addLayout(Layout l) {
		System.out.println(l);
		em.persist(l);
	}


}
