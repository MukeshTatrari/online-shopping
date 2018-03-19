package com.mukesh.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mukesh.onlineshopping.util.FileUploadUtility;
import com.mukesh.onlineshopping.validator.ProductValidator;
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

	@RequestMapping(value = "/products", method = RequestMethod.GET)
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
			else if (operation.equals("category")) {
				mv.addObject("message", "Category Added Successfully !");
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
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mproduct, BindingResult results,
					Model model, HttpServletRequest request) {

		LOGGER.info("inside handleProductSubmission");

		if (mproduct.getId() == 0) {
			LOGGER.info("inside mProduct if " +mproduct.getId());
			new ProductValidator().validate(mproduct, results);
		}
		else
		{
			LOGGER.info("inside mProduct else file name " +mproduct.getFile().getOriginalFilename());
			// edit check only when the file has been selected
			if(!mproduct.getFile().getOriginalFilename().equals("")) {
				LOGGER.info("inside mProduct elseee " +mproduct.getId());
				new ProductValidator().validate(mproduct, results);
			}		
		}

		if (results.hasErrors()) {
			LOGGER.info("product validation failed " + results);

			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation Failed for Product Submission");

			LOGGER.info(mproduct.toString());
			return "page";

		}

		LOGGER.info(mproduct.toString());

		if (mproduct.getId() == 0) {
			// create a new product on form submission
			productDAO.addProduct(mproduct);
		} else {
			// udpate a new product if the id is not zero
			productDAO.updateProduct(mproduct);
		}
		LOGGER.info("inside handleProductSubmission");

		// file upload logic

		if (!mproduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mproduct.getFile(), mproduct.getCode());

		}

		return "redirect:/manage/products?operation=product";

	}

	@RequestMapping(value = "/products/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {

		// get the product from the database
		Product product = productDAO.getProduct(id);
		boolean isActive = product.isActive();
		// Activate or deactivate product based on its field value
		product.setActive(!product.isActive());

		// update the product
		productDAO.updateProduct(product);

		return (isActive) ? "You have Successfully Deactivated the Product with id " + product.getId()
						: "You have Successfully Activated  the Product with id " + product.getId();

	}

	@RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");

		LOGGER.info("inside showManageProducts");

		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");

		// get the product from database
		Product nProduct = productDAO.getProduct(id);
		// set the product from database
		mv.addObject("product", nProduct);
		return mv;
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

	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();

	}
	
	// to handle Category Submission
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		
		//add a new category
		categoryDAO.addCategory(category);
		
		return "redirect:/manage/products?operation=category";
	}
}
