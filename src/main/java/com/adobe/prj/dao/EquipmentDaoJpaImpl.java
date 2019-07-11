package com.adobe.prj.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.adobe.prj.entity.Equipment;

@Repository // All 'Dao' classes should have repository
public class EquipmentDaoJpaImpl implements EquipmentDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Equipment getEquipment(int id) {
		// Limitation of find is that it can only find based on the primary key
		// 'find' basically looks at the annotations @Table and @Id to generate
		// the SQL
		return em.find(Equipment.class, id);
	}

	@Override
	public List<Equipment> getEquipments() {
		String JPQL = "SELECT e FROM Equipment e";
		TypedQuery<Equipment> query = em.createQuery(JPQL, Equipment.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateEquipment(Equipment e) {

		Equipment _e = em.find(Equipment.class, e.getId());
		// _l.setImage(l.getImage());
		// _l.setTitle(l.getTitle());
		_e.setTitle(e.getTitle());
		em.persist(_e);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
		System.out.println(_e);

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
	}

	@Override
	@Transactional
	public void deleteEquipment(Equipment e) {
		Equipment _e = em.find(Equipment.class, e.getId());
		em.remove(_e);
	}

	@Override
	@Transactional // Ensures the whole function gets executed in an atomic fashion. If not, then it rollsback the whole operation.
	public void addEquipment(Equipment e) {
		em.persist(e);
	}

}
