package com.mukesh.onlineshopping.controller;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	
	@RequestMapping(value = {"/","/home","/index"})
	public ModelAndView index (){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", "Welcome to Srping Web Mvc");
		return mv;
		
	}
	
//	@RequestMapping(value = {"/test"})
//	public ModelAndView test (@RequestParam(value = "greeting" , required = false) String greeting ){
//		if(greeting==null)
//		{
//			greeting = "Hello Welcome";
//		}
//		
//		ModelAndView mv = new ModelAndView("page");
//		mv.addObject("greeting", greeting);
//		return mv;
//		
//	}
	
	
	@RequestMapping(value = {"/test/{greeting}"})
	public ModelAndView test (@PathVariable("greeting") String greeting ){
		if(greeting==null)
		{
			greeting = "Hello Welcome";
		}
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
		
	}
	

}
