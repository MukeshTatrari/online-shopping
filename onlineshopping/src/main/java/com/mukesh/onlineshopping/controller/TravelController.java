package com.mukesh.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/travel")
public class TravelController {

	@RequestMapping("/places")
	public ModelAndView showPlaces() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Places");
		mv.addObject("userClickTravel", true);
		return mv;
	}

	@RequestMapping("/places/placeDetails")
	public ModelAndView showPlaceDetails() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Place Details");
		mv.addObject("UserClickedPlaceDetails", true);
		return mv;
	}

}
