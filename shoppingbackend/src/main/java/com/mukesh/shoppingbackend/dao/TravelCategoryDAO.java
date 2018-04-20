package com.mukesh.shoppingbackend.dao;

import java.util.List;
import com.mukesh.shoppingbackend.dto.TravelCategory;

public interface TravelCategoryDAO {
	
	List<TravelCategory> getAllTravelCategory();
	TravelCategory getTravelCategory(int TravelCateogyId);
	boolean addTravelCateogy(TravelCategory travelCategory);

	boolean updateTravelCateogy(TravelCategory travelCategory);
	boolean deleteTravelCateogy(TravelCategory travelCategory);

}
