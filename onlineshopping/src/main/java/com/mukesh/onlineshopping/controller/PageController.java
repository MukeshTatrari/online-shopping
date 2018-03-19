package com.mukesh.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mukesh.onlineshopping.exception.ProductNotFoundException;
import com.mukesh.shoppingbackend.dao.CategoryDAO;
import com.mukesh.shoppingbackend.dao.ProductDAO;
import com.mukesh.shoppingbackend.daoimpl.CategoryDAOImpl;
import com.mukesh.shoppingbackend.dto.Category;
import com.mukesh.shoppingbackend.dto.Product;
import com.mukesh.shoppingbackend.dto.User;

@Controller
public class PageController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	/**
	 * 
	 * @return display home page with Categories
	 */
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		// passing the list of categories
		mv.addObject("categories", categoryDAO.getAllCategories());
		mv.addObject("userClickHome", true);
		return mv;

	}

	/**
	 * 
	 * @return display about us page
	 */
	@RequestMapping(value = { "/about" })
	public ModelAndView about() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;

	}

	/**
	 * 
	 * @return display contact us page
	 */
	@RequestMapping(value = { "/contact" })
	public ModelAndView contact() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;

	}

	/**
	 * 
	 * @return To load all the Products based on category
	 */
	@RequestMapping(value = { "/show/all/products" })
	public ModelAndView showAllProducts() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("categories", categoryDAO.getAllCategories());
		mv.addObject("userClickAllProducts", true);
		return mv;

	}

	/**
	 * 
	 * @return To load all the Products based on category
	 */
	@RequestMapping(value = { "/login" })
	public ModelAndView login(@RequestParam(name="error", required = false)	String error,
					@RequestParam(name="logout", required = false)	String logout){

		ModelAndView mv = new ModelAndView("login");
		if (error != null) {
			LOGGER.info("error ::"+error);
			LOGGER.info("error ::"+error);
			mv.addObject("message", "Invalid Username and Password !");
		}
		
		if(logout!=null)
		{
			mv.addObject("message", "User has Successfully logged out!");
		}

		mv.addObject("title", "Login");
		return mv;

	}

	/**
	 * 
	 * @return To load all the Products based on category
	 */
	@RequestMapping(value = { "/show/category/{id}/products" })
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {

		ModelAndView mv = new ModelAndView("page");

		// CategoryDAO to fetch the single category

		Category category = categoryDAO.getCategory(id);

		mv.addObject("title", category.getName());
		// passing the list of categories
		mv.addObject("categories", categoryDAO.getAllCategories());

		// passing a single category
		mv.addObject("category", category);
		mv.addObject("userClickCategoryProducts", true);
		return mv;

	}

	/**
	 * 
	 * @return To load all the Products based on category
	 */
	@RequestMapping(value = { "/show/{id}/product" })
	public ModelAndView showProductDetail(@PathVariable("id") int id) throws ProductNotFoundException {

		ModelAndView mv = new ModelAndView("page");

		// ProductDAO to fetch the single Product

		Product product = productDAO.getProduct(id);

		if (product == null) {
			throw new ProductNotFoundException();
		}
		// update the view count of the product
		product.setViews(product.getViews() + 1);
		productDAO.updateProduct(product);

		mv.addObject("title", product.getName());
		// passing the product to View
		mv.addObject("product", product);

		mv.addObject("userClickShowProduct", true);
		return mv;

	}

	/**
	 * 
	 * @return access-denied
	 */
	@RequestMapping(value = { "/access-denied" })
	public ModelAndView accessDenied() {

		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 Access - Denied !");
		mv.addObject("errorTitle", "opppssss something is wrong!");
		mv.addObject("errorDescription", "You are not Authorized to view this page !");
		return mv;

	}
	
	/**
	 * 
	 * @return display contact us page
	 */
	@RequestMapping(value = { "/perform-logout" })
	public String logout(HttpServletRequest request,HttpServletResponse response) {

		//first we will fetch the Authentication object
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null)
		{
			new SecurityContextLogoutHandler().logout(request, response, authentication);;
		}

		return "redirect:/login?logout";
	}
}
