package com.mukesh.onlineshopping.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.mukesh.onlineshopping.controller.ContactUsController;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView  noHandlerFoundException()
	{
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Page Not Found");
		mv.addObject("errorDescription", "The page you are looking is not available");
		mv.addObject("title","404 Error Page");
		
		return mv;
	}

	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView  handlerProductNotFoundException()
	{
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product not Available");
		mv.addObject("errorDescription", "The Product you are looking is not available");
		mv.addObject("title","Product Not Found");
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView  handlerException(Exception ex)
	{
		ModelAndView mv = new ModelAndView("error");
		LOGGER.info("Error is", ex.getStackTrace().toString());
		
		LOGGER.info("Error is", ex.getMessage());
		mv.addObject("errorTitle", "Contact Your Administrator!!");
		mv.addObject("errorDescription", "Something went Wrong");
		mv.addObject("title","Product Not Found");
		
		return mv;
	}
}
