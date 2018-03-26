package com.mukesh.onlineshopping.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactUsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsController.class);

	/**
	 * 
	 * @return display contact us page
	 */
	@RequestMapping(value =  "/contact" , method = RequestMethod.GET)
	public ModelAndView contact() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;

	}


	/**
	 * 
	 * 
	 * 
	 * handling product submission
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String handleContactUsSubmission() {
		
	

		return "ou have Successfully Deactivated the Product with id";
	}

}
