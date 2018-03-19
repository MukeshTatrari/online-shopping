package com.mukesh.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mukesh.onlineshopping.model.UserModel;
import com.mukesh.shoppingbackend.dao.UserDAO;
import com.mukesh.shoppingbackend.dto.User;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private UserDAO userDAO;
	
	private UserModel userModel =null;

	@ModelAttribute("userModel")
	private UserModel getUserModel() {
		if (httpSession.getAttribute("userModel") == null) {
			// add the user Model

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = userDAO.getUserByEmail(authentication.getName());

			if (user != null) {
				// create a new user Model
				
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName() + " "+ user.getLastName());
				
				if(userModel.getRole().equalsIgnoreCase("USER"))
				{
					userModel.setCart(user.getCart());
				}
				
				//set the user model in the session.
				
				httpSession.setAttribute("userModel", userModel);
				
				return userModel;
			}
		}

		return (UserModel) httpSession.getAttribute("userModel");
	}

}
