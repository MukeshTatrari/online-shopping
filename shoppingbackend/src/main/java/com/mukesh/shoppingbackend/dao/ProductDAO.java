package com.mukesh.shoppingbackend.dao;

import java.util.List;

import com.mukesh.shoppingbackend.dto.Product;

public interface ProductDAO {
	
	
	Product getProduct(int productId);
	public List<Product> getProducts();
	public boolean addProduct(Product product);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(Product product);
	
	//methods to fetch the products
	
	public List<Product>getActiveProducts();
	public List<Product>getActiveProductByCategory(int categoryId);
	
	//get Latest Products by Count
	public List<Product>getLatestActiveProducts(int count);
	
	List<Product> getProductsByParam(String param, int count);	
	

}
