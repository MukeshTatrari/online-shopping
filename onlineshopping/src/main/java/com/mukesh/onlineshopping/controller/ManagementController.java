package com.mukesh.onlineshopping.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mukesh.shoppingbackend.dao.CategoryDAO;
import com.mukesh.shoppingbackend.dao.ProductDAO;
import com.mukesh.shoppingbackend.dto.Category;
import com.mukesh.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ManagementController.class);
	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	ProductDAO productDAO;

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("page");

		LOGGER.info("inside showManageProducts");

		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");

		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mv.addObject("product", nProduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product Added Successfully !");
			}
		}

		return mv;
	}

	/**
	 * 
	 * 
	 * 
	 * handling product submission
	 */
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mproduct, BindingResult results,
					Model model) {

		LOGGER.info("inside handleProductSubmission");
		if (results.hasErrors()) {

			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation Failed for Product Submission");

			LOGGER.info("product validation failed ");

			LOGGER.info(mproduct.toString());
			return "page";

		}

		LOGGER.info(mproduct.toString());
		// create a new product on form submission
		productDAO.addProduct(mproduct);
		LOGGER.info("inside handleProductSubmission");
		return "redirect:/manage/products?operation=product";

	}

	/**
	 * 
	 * @return
	 * 
	 *         returning list of categories
	 */
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.getAllCategories();

	}

}
