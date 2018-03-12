package com.mukesh.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mukesh.shoppingbackend.dao.CategoryDAO;
import com.mukesh.shoppingbackend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private static Category category;
	
	@BeforeClass
	public static void init()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.mukesh.shoppingbackend");
		context.refresh();
		categoryDAO=(CategoryDAO) context.getBean("categoryDAO");
	}
	
////	@Test
	public void testAddCategory(String categoryName)
	{
		category = new Category();
		category.setName(categoryName);
		category.setDescription("This is Some description for "+categoryName);
		category.setImageURL("CAT_"+categoryName+".png");
		
		assertEquals("Something went wrong while inserting Category inside Table!",true,categoryDAO.addCategory(category));
	}
	
//	@Test
//	public void testAddCategory()
//	{
//		String categoryName ="Television";
//		category = new Category();
//		category.setName(categoryName);
//		category.setDescription("This is Some description for "+categoryName);
//		category.setImageURL("CAT_"+categoryName+".png");
//		
//		assertEquals("Something went wrong while inserting Category inside Table!",true,categoryDAO.addCategory(category));
//	}
	
	
	
//	@Test
	public void testGetCategory()
	{
		category = categoryDAO.getCategory(1);
		assertEquals("Something went wrong while fetching the Category from Table!","Television",category.getName());
		
	}
	
//	@Test
	public void testUpdateCategory()
	{
		category = categoryDAO.getCategory(1);
		category.setName("TV");
		assertEquals("Something went wrong while updating the Category in Table!",true,categoryDAO.updateCategory(category));
		
	}

//	@Test
	public void testDeleteCategory()
	{
		category = categoryDAO.getCategory(1);
		assertEquals("Something went wrong while deleting the Category from Table!",true,categoryDAO.deleteCategory(category));
		
	}
	
////	@Test
//	public void testgetAllCategories()
//	{
//		
//		assertEquals("Something went wrong while fetching the Categories from Table!",3,categoryDAO.getAllCategories().size());
//		
//	}
	
	
	@Test
	public void testCRUDCategory()
	{
		testAddCategory("Television");
		testAddCategory("Mobile");
		testAddCategory("Laptop");
		testGetCategory();
		testUpdateCategory();
		testDeleteCategory();
		
	}
}
