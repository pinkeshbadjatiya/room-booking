package com.adobe.prj.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.adobe.prj.entity.FoodDrink;

@Repository // All 'Dao' classes should have repository
public class FoodDrinkDaoJpaImpl implements FoodDrinkDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public FoodDrink getFoodDrink(int id) {
		// Limitation of find is that it can only find based on the primary key
		// 'find' basically looks at the annotations @Table and @Id to generate
		// the SQL
		return em.find(FoodDrink.class, id);
	}

	@Override
	public List<FoodDrink> getFoodDrinks() {
		String JPQL = "SELECT fd FROM FoodDrink fd";
		TypedQuery<FoodDrink> query = em.createQuery(JPQL, FoodDrink.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateFoodDrink(FoodDrink fd) {

		FoodDrink _fd = em.find(FoodDrink.class, fd.getId());
		// _l.setImage(l.getImage());
		// _l.setTitle(l.getTitle());
		_fd.setTitle(fd.getTitle());
		em.persist(_fd);

	}

	@Override
	@Transactional
	public void deleteFoodDrink(FoodDrink fd) {
		FoodDrink _fd = em.find(FoodDrink.class, fd.getId());
		em.remove(_fd);
	}

	@Override
	@Transactional // Ensures the whole function gets executed in an atomic fashion. If not, then it rollsback the whole operation.
	public void addFoodDrink(FoodDrink fd) {
		em.persist(fd);
	}

}
