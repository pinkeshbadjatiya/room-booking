package com.adobe.prj.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.adobe.prj.entity.FoodDrink;
import com.adobe.prj.exceptions.InvalidParameterOrMissingValue;

@Repository // All 'Dao' classes should have repository
public class FoodDrinkDaoJpaImpl implements FoodDrinkDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public FoodDrink getFoodDrink(int id) {
		// Limitation of find is that it can only find based on the primary key
		// 'find' basically looks at the annotations @Table and @Id to generate
		// the SQL
		FoodDrink ans = em.find(FoodDrink.class, id);
		if (ans == null) {
			throw new InvalidParameterOrMissingValue("No foodDrink with the given id found.");
		}
		return em.find(FoodDrink.class, id);
	}

	@Override
	public List<FoodDrink> getFoodDrinks() {
		String JPQL = "SELECT fd FROM FoodDrink fd";
		TypedQuery<FoodDrink> query = em.createQuery(JPQL, FoodDrink.class);
		if (query == null) {
			throw new InvalidParameterOrMissingValue("No foodDrinks found.");
		}
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateFoodDrink(FoodDrink fd) {

		FoodDrink _fd = em.find(FoodDrink.class, fd.getId());

		if (_fd == null) {
			throw new InvalidParameterOrMissingValue("No foodDrink with the given Id found. So, updation not possible.");
		}
		_fd.setTitle(fd.getTitle());
		em.persist(_fd);

	}

	@Override
	@Transactional
	public void deleteFoodDrink(FoodDrink fd) {
		FoodDrink _fd = em.find(FoodDrink.class, fd.getId());
		if (_fd == null) {
			throw new InvalidParameterOrMissingValue("No foodDrink with the given Id found. So, deletion not possible.");
		}
		em.remove(_fd);
	}

	@Override
	@Transactional // Ensures the whole function gets executed in an atomic
					// fashion. If not, then it rollsback the whole operation.
	public void addFoodDrink(FoodDrink fd) {
		em.persist(fd);
	}

}
