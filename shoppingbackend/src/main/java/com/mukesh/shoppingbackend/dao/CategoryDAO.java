package com.mukesh.shoppingbackend.dao;

import java.util.List;

import com.mukesh.shoppingbackend.dto.Category;


public interface CategoryDAO {
	
	List<Category> list();
	Category getCategory(int categoryId);

}
