package com.mukesh.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mukesh.shoppingbackend.dao.TravelCategoryDAO;
import com.mukesh.shoppingbackend.dto.TravelCategory;

@Transactional
@Repository("travelCategoryDAO")
public class TravelCategoryDAOImpl implements TravelCategoryDAO {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TravelCategoryDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<TravelCategory> getAllTravelCategory() {
		String selectActivePlaces = "FROM TravelCategory WHERE is_active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(
				selectActivePlaces);
		query.setParameter("active", true);
		return query.getResultList();
	}

	@Override
	public TravelCategory getTravelCategory(int placeId) {
		return sessionFactory.getCurrentSession().get(TravelCategory.class,
				Integer.valueOf(placeId));

	}

	@Override
	public boolean addTravelCateogy(TravelCategory place) {
		try {
			// Code to add place in database
			sessionFactory.getCurrentSession().persist(place);
			return true;

		} catch (Exception ex) {
			LOGGER.info("Exception in adding the Place ::" + ex);
			return false;
		}
	}

	@Override
	public boolean updateTravelCateogy(TravelCategory place) {
		try {
			// Code to update category in database
			sessionFactory.getCurrentSession().update(place);
			return true;

		} catch (Exception ex) {
			LOGGER.info("Exception in updating places ::" + ex);
			return false;
		}
	}

	@Override
	public boolean deleteTravelCateogy(TravelCategory place) {
		place.setActive(false);
		try {
			// Code to update category in database
			sessionFactory.getCurrentSession().update(place);
			return true;

		} catch (Exception ex) {
			LOGGER.info("Exception in removing the place ::" + ex);
			return false;
		}
	}

}
