package com.mukesh.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mukesh.shoppingbackend.dao.TravelCategoryDAO;
import com.mukesh.shoppingbackend.dto.Category;
import com.mukesh.shoppingbackend.dto.TravelCategory;

@Controller
@RequestMapping("/travel")
public class TravelController {

	@Autowired
	private TravelCategoryDAO travelCategoryDAO;

	@RequestMapping("/places")
	public ModelAndView showPlaces() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Places");
		// passing the list of places
		mv.addObject("places",travelCategoryDAO.getAllTravelCategory());
		mv.addObject("userClickTravel", true);
		return mv;
	}


	/**
	 * 
	 * @return To load all the Products based on category
	 */
	@RequestMapping(value = { "show/{id}/PlaceDetails" })
	public ModelAndView showPlaceDetails(@PathVariable("id") int placeId) {

		ModelAndView mv = new ModelAndView("page");

		// CategoryDAO to fetch the single category

		TravelCategory place = travelCategoryDAO.getTravelCategory(placeId);

		mv.addObject("title", place.getName());
		// passing the list of categories
		mv.addObject("places", travelCategoryDAO.getAllTravelCategory());

		// passing a single category
		mv.addObject("place", place);
		mv.addObject("UserClickedPlaceDetails", true);
		return mv;

	}

}
