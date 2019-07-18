package com.adobe.prj.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.adobe.prj.entity.Equipment;
import com.adobe.prj.exceptions.InvalidId;
import com.adobe.prj.exceptions.InvalidParameterOrMissingValue;

//implementation of EquipmentDao interface
@Repository // All 'DAO' classes should have repository
public class EquipmentDaoJpaImpl implements EquipmentDao {

	@PersistenceContext
	private EntityManager em;

	// returns a single equipment based on id
	@Override
	public Equipment getEquipment(int id) {
		// Limitation of find is that it can only find based on the primary key
		// 'find' basically looks at the annotations @Table and @Id to generate
		// the SQL
		Equipment ans = em.find(Equipment.class, id);
		if (ans == null) {
			throw new InvalidParameterOrMissingValue("No equipment with the given id found.");
		}
		return ans;
	}

	// returns list of equipments
	@Override
	public List<Equipment> getEquipments() {
		String JPQL = "SELECT e FROM Equipment e";
		TypedQuery<Equipment> query = em.createQuery(JPQL, Equipment.class);
		if (query == null) {
			// error: invalid id for item.
			throw new InvalidId("No equipment found. Please check the Id given for the equipment.");
		}
		return query.getResultList();
	}

	// updates equipment
	@Override
	@Transactional
	public void updateEquipment(Equipment e) {

		Equipment _e = em.find(Equipment.class, e.getId());
		if (_e == null) {
			throw new InvalidParameterOrMissingValue(
					"No equipment with the given id found. So, updation not possible.");
		}
		_e.setTitle(e.getTitle());
		_e.setPrice(e.getPrice());
		_e.setPrice_type(e.getPrice_type());
		em.persist(_e);
	}

	// deletes equipment
	@Override
	@Transactional
	public void deleteEquipment(Equipment e) {
		Equipment _e = em.find(Equipment.class, e.getId());
		if (_e == null) {
			throw new InvalidParameterOrMissingValue(
					"No equipment with the given id found. So, deletion not possible.");
		}
		em.remove(_e);
	}

	// adds an equipment
	@Override
	@Transactional // Ensures the whole function gets executed in an atomic
					// fashion. If not, then it rolls back the whole operation.
	public void addEquipment(Equipment e) {
		em.persist(e);
	}

}
