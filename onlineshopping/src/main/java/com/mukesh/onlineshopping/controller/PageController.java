package com.mukesh.onlineshopping.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mukesh.shoppingbackend.dao.CategoryDAO;
import com.mukesh.shoppingbackend.dto.Category;


@Controller
public class PageController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	/**
	 * 
	 * @return
	 * display home page with Categories
	 */
	@RequestMapping(value = {"/","/home","/index"})
	public ModelAndView index (){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		//passing the list of  categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickHome", true);
		return mv;
		
	}

	/**
	 * 
	 * @return
	 * display about us page
	 */
	@RequestMapping(value = {"/about"})
	public ModelAndView about(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
		
	}
	
	/**
	 * 
	 * @return
	 * display contact us page
	 */
	@RequestMapping(value = {"/contact"})
	public ModelAndView contact(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
		
	}

	
	/**
	 * 
	 * @return
	 *  To load all the Products based on category
	 */
	@RequestMapping(value = {"/show/all/products"})
	public ModelAndView showAllProducts(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickAllProducts", true);
		return mv;
		
	}
	
	/**
	 * 
	 * @return
	 *  To load all the Products based on category
	 */
	@RequestMapping(value = {"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id") int id){
		
		ModelAndView mv = new ModelAndView("page");
		
		//CategoryDAO to fetch the single category
		
		Category category = categoryDAO.getCategory(id);
		
		mv.addObject("title", category.getName());
		//passing the list of  categories
		mv.addObject("categories", categoryDAO.list());
		
		//passing a single category
		mv.addObject("category", category);
		mv.addObject("userClickCategoryProducts", true);
		return mv;
		
	}
}
