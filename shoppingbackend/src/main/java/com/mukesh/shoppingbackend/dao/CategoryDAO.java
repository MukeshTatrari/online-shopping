package com.mukesh.shoppingbackend.dao;

import java.util.List;

import com.mukesh.shoppingbackend.dto.Category;


public interface CategoryDAO {
	
	List<Category> getAllCategories();
	Category getCategory(int id);
	boolean addCategory(Category category);

	boolean updateCategory(Category category);
	boolean deleteCategory(Category category);
}
