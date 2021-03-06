package com.mukesh.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mukesh.shoppingbackend.dao.CategoryDAO;
import com.mukesh.shoppingbackend.dto.Category;

@Transactional
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	 private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * fetch the list of active categories
	 */
	@Override
	public List<Category> getAllCategories() {

		String selectActiveCategory = "FROM Category WHERE is_active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(
				selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}

	/**
	 * getting the Single category based on categoryId
	 */
	@Override
	public Category getCategory(int id) {

		return sessionFactory.getCurrentSession().get(Category.class,
				Integer.valueOf(id));

	}

	@Override
	public boolean addCategory(Category category) {

		try {
			// Code to add category in database
			sessionFactory.getCurrentSession().persist(category);
			return true;

		} catch (Exception ex) {
			 LOGGER.info("Exception in adding the Category ::" + ex);
			return false;
		}
	}

	/**
	 * updating a single category
	 */
	@Override
	public boolean updateCategory(Category category) {
		try {
			// Code to update category in database
			sessionFactory.getCurrentSession().update(category);
			return true;

		} catch (Exception ex) {
			 LOGGER.info("Exception in updating the Category ::" + ex);
			return false;
		}
	}

	@Override
	public boolean deleteCategory(Category category) {
		category.setActive(false);
		try {
			// Code to update category in database
			sessionFactory.getCurrentSession().update(category);
			return true;

		} catch (Exception ex) {
			 LOGGER.info("Exception in removing the Category ::" + ex);
			return false;
		}
	}

}
