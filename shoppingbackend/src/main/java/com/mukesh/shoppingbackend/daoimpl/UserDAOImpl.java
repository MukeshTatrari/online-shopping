package com.mukesh.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mukesh.shoppingbackend.dao.UserDAO;
import com.mukesh.shoppingbackend.dto.Address;
import com.mukesh.shoppingbackend.dto.Cart;
import com.mukesh.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

	@Override
	public boolean addUser(User user) {

		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception ex) {
			LOGGER.error("error in adding the User " + ex);
		}
		return false;
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception ex) {
			LOGGER.error("error in adding the Address " + ex);
			LOGGER.error("error in adding the Address " + ex.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception ex) {
			LOGGER.error("error in adding the cart " + ex);
		}
		return false;
	}

	@Override
	public User getUserByEmail(String email) {
		String selectQuery = "FROM User where email=:email";

		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email)
							.getSingleResult();
		} catch (Exception ex) {
			LOGGER.error("Error in fetching the user " + ex);
			return null;
		}
	}

	@Override
	public Address getBillingAddress(int userId) {
		String selectQuery = "FROM Address WHERE user_id = :userId AND billing =:billing";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		query.setParameter("userId", userId);
		query.setParameter("billing", true);
		try {

			return (Address) query.getSingleResult();
		} catch (Exception ex) {
			LOGGER.error("Error in fetching the Address " + ex);
			return null;
		}
	}

	@Override
	public List<Address> getShippingAddresses(int userId) {
		String selectQuery = "FROM Address WHERE user_id = :userId AND shipping =:shipping";
		Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
		query.setParameter("userId", userId);
		query.setParameter("shipping", true);
		try {
			return query.getResultList();
			
		} catch (Exception ex) {
			LOGGER.error("Error in fetching the Address " + ex);
			return null;
		}
	}

}
