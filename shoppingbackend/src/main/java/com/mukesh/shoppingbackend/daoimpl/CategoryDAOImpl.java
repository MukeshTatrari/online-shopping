package com.mukesh.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mukesh.shoppingbackend.dao.CategoryDAO;
import com.mukesh.shoppingbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<Category>();
	static {

		Category category = new Category();

		// adding 1st category

		category.setId(1);
		category.setName("Television");
		category.setDescription("This is Some description for Television!");
		category.setImageURL("CAT_1.png");

		categories.add(category);

		// adding 2nd category
		Category category1 = new Category();
		category1.setId(2);
		category1.setName("Mobile");
		category1.setDescription("This is Some description for Mobile!");
		category1.setImageURL("CAT_2.png");

		categories.add(category1);

		// adding 2nd category
		Category category2 = new Category();
		category2.setId(3);
		category2.setName("Laptop");
		category2.setDescription("This is Some description for Laptop!");
		category2.setImageURL("CAT_3.png");

		categories.add(category2);
	}

	@Override
	public List<Category> list() {
		
		return categories;
	}

	@Override
	public Category getCategory(int categoryId) {
		// TODO Auto-generated method stub
		for(Category category: categories)
		{
			if(category.getId()==categoryId)
			{
				return category;
			}
		}
		
		return null;
	}

}
