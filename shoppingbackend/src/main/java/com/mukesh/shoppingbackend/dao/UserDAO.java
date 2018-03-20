package com.mukesh.shoppingbackend.dao;

import java.util.List;

import com.mukesh.shoppingbackend.dto.Address;
import com.mukesh.shoppingbackend.dto.Cart;
import com.mukesh.shoppingbackend.dto.User;

public interface UserDAO {

	//add an User
	public boolean addUser(User user);
	
	//get User by Email
	
	public User getUserByEmail(String email);
	
	//add an Address
	public boolean addAddress(Address address);
	
	public Address getAddress(int addressId);
	
	public Address getBillingAddress(int useId);
	
	public List<Address> getShippingAddresses(int userId);
	
	// update a Cart
	
	public boolean updateCart(Cart cart);
	
}
