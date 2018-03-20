package com.mukesh.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mukesh.shoppingbackend.dao.ProductDAO;
import com.mukesh.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDAOImpl.class);

	@Override
	public Product getProduct(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> getProducts() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	@Override
	public boolean addProduct(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception ex) {
			LOGGER.error("error in adding the products " + ex);
		}
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception ex) {
			LOGGER.error("Error in updating the products " + ex);
		}
		return false;
	}

	@Override
	public boolean deleteProduct(Product product) {
		try {

			product.setActive(false);
			// call the updateProduct method
			return this.updateProduct(product);
		} catch (Exception ex) {
			LOGGER.error("Error in deleting the products " + ex);
		}
		return false;
	}

	@Override
	public List<Product> getActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE is_active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProducts);
		query.setParameter("active", true);
		return query.getResultList();
	}

	@Override
	public List<Product> getActiveProductByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE is_active = :active  AND categoryId = :categoryId";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory);
		query.setParameter("active", true);
		query.setParameter("categoryId", categoryId);
		return query.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String selectActiveProductsByCategory = "FROM Product WHERE is_active = :active ORDER BY ID ";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory);
		query.setParameter("active", true);
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.getResultList();
	}

	@Override
	public List<Product> getProductsByParam(String param, int count) {

		String query = "FROM Product WHERE active = true ORDER BY " + param + " DESC";

		return sessionFactory.getCurrentSession().createQuery(query, Product.class).setFirstResult(0)
						.setMaxResults(count).getResultList();

	}

}
