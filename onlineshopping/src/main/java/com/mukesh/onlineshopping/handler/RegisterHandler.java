package com.mukesh.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.Message;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.message.MessageResolver;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mukesh.onlineshopping.model.RegisterModel;
import com.mukesh.onlineshopping.util.ObjectUtils;
import com.mukesh.shoppingbackend.dao.UserDAO;
import com.mukesh.shoppingbackend.dto.Address;
import com.mukesh.shoppingbackend.dto.Cart;
import com.mukesh.shoppingbackend.dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public RegisterModel init() {
		return new RegisterModel();
	}

	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}

	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}

	public String saveAll(RegisterModel model) {
		String transitionValue = "success";
		try {
			User user = model.getUser();
			if (user.getRole().equals("USER")) {
				Cart cart = new Cart();
				cart.setUser(user);
				user.setCart(cart);
			}

			// encode the Password
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			// save the user
			userDAO.addUser(user);

			// get the Address
			Address address = model.getBilling();
			address.setUserId(user.getId());
			address.setBilling(true);

			// save the Address
			userDAO.addAddress(address);

			return transitionValue;
		} catch (Exception ex) {
			return null;
		}
	}

	public String validateUser(User user, MessageContext error) {

		String transitionValues = "success";

		// if password matches the confirm password
		if (!(user.getPassword().equals(user.getConfirmPassword()))) {

			error.addMessage(new MessageBuilder().error().source("confirmPassword")
							.defaultText("Password does not match the Confirm Password!").build());

			transitionValues = "failure";
		}

		// check the uniqueness of the Email ID

		if (!ObjectUtils.isEmpty(userDAO.getUserByEmail(user.getEmail()))) {

			error.addMessage(new MessageBuilder().error().source("email").defaultText("Email Already Exist !").build());

			transitionValues = "failure";

		}

		return transitionValues;

	}
}
