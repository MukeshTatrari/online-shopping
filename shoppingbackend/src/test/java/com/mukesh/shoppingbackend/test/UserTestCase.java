package com.mukesh.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mukesh.shoppingbackend.dao.UserDAO;
import com.mukesh.shoppingbackend.dto.Address;
import com.mukesh.shoppingbackend.dto.Cart;
import com.mukesh.shoppingbackend.dto.User;

public class UserTestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserTestCase.class);
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private static User user;
	private static Address address;
	private static Cart cart;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.mukesh.shoppingbackend");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	/*
	 * @Test
	 */
	// public void testAdd() {
	// user = new User();
	// user.setFirstName("Hrithik");
	// user.setLastName("Roshan");
	// user.setEmail("hr@gmail.com");
	// user.setContactNumber("1234512345");
	// user.setRole("USER");
	// user.setEnabled(true);
	// user.setPassword("12345");
	//
	// address = new Address();
	// address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
	// address.setAddressLineTwo("Near Kaabil Store");
	// address.setCity("Mumbai");
	// address.setState("Maharashtra");
	// address.setCountry("India");
	// address.setPostalCode("400001");
	// address.setBilling(true);
	// // address.setUserId(user.getId());
	//
	// // linked the address with the user // address.setUser(user);
	//
	// // add the user assertEquals("Failed to add the user!", true,
	// // userDAO.addUser(user)); // add the address
	// assertEquals("Failed to add the billing address!", true,
	// userDAO.addAddress(address));
	//
	// if (user.getRole().equals("USER")) {
	//
	// LOGGER.info("inside Role:::"); // add a cart cart = new Cart(); //
	// // linked
	// // the cart with the
	// // user cart.setUser(user); // link the user with the cart
	// LOGGER.info("inside Role::: Cart " + cart.toString());
	// // assertEquals("Failed to add the Cart !", true,
	// // userDAO.addCart(cart)); //
	// user.setCart(cart);
	//
	// // add the shipping address
	// address = new Address();
	// address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
	// address.setAddressLineTwo("Near Kudrat Store");
	// address.setCity("Mumbai");
	// address.setState("Maharashtra");
	// address.setCountry("India");
	// address.setPostalCode("400001");
	// // address.setUserId(user.getId());
	//
	// assertEquals("Failed to add the shipping address!", true,
	// userDAO.addAddress(address));
	// }
	// }

	// @Test
	// public void testAdd() {
	// user = new User();
	// user.setFirstName("Hrithik");
	// user.setLastName("Roshan");
	// user.setEmail("hr@gmail.com");
	// user.setContactNumber("1234512345");
	// user.setRole("USER");
	// user.setEnabled(true);
	// user.setPassword("12345");
	//
	// if (user.getRole().equals("USER")) {
	// LOGGER.info("inside Role:::");
	// // add a cart
	// cart = new Cart();
	// // linked the cart with the user
	// cart.setUser(user);
	//
	// // attaching cart with user
	// user.setCart(cart);
	// }
	// // add the user
	// assertEquals("Failed to add the user!", true, userDAO.addUser(user));
	// }

	// working for uni-directional

//	@Test
//	public void testAddAddress() {
//		user = new User();
//		user.setFirstName("Hrithik");
//		user.setLastName("Roshan");
//		user.setEmail("hr@gmail.com");
//		user.setContactNumber("1234512345");
//		user.setRole("USER");
//		user.setEnabled(true);
//		user.setPassword("12345");
//
//		// add the user
//		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
//
//		address = new Address();
//		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
//		address.setAddressLineTwo("Near Kaabil Store");
//		address.setCity("Mumbai");
//		address.setState("Maharashtra");
//		address.setCountry("India");
//		address.setPostalCode("400001");
//		address.setBilling(true);
//		// linked the address with the user
//		address.setUser(user);
//
//		// add the address
//		assertEquals("Failed to add the billing address!", true, userDAO.addAddress(address));
//
//		address = new Address();
//		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
//		address.setAddressLineTwo("Near Kudrat Store");
//		address.setCity("Mumbai");
//		address.setState("Maharashtra");
//		address.setCountry("India");
//		address.setPostalCode("400001");
//		address.setShipping(true);
//		address.setUser(user);
//
//		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
//	}
	
	
	
//	@Test
//	public void testAddAddress() {
//		user = userDAO.getUserByEmail("hr@gmail.com");
//
//
//		address = new Address();
//		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
//		address.setAddressLineTwo("Near Kaabil Store");
//		address.setCity("Kolkata");
//		address.setState("kolalalala");
//		address.setCountry("India");
//		address.setPostalCode("400001");
//		address.setShipping(true);
//		// linked the address with the user
//		address.setUser(user);
//
//		// add the address
//		assertEquals("Failed to add the billing address!", true, userDAO.addAddress(address));
//
//		address = new Address();
//		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
//		address.setAddressLineTwo("Near Kudrat Store");
//		address.setCity("Mukeshs");
//		address.setState("Gurgaon");
//		address.setCountry("India");
//		address.setPostalCode("400001");
//		address.setShipping(true);
//		address.setUser(user);
//
//		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
//	}
	
	@Test
	public void testFetchAddress()
	{
		user = userDAO.getUserByEmail("hr@gmail.com");
		assertEquals("Failed to Fetch  the shipping address!", 3, userDAO.getShippingAddresses(user.getId()).size());
		assertEquals("Failed to Fetch  the billing  address!", "Mumbai", userDAO.getBillingAddress(user.getId()).getCity());
		
	}

	// @Test
	// public void testUpdateCart() {
	// user = userDAO.getUserByEmail("hr@gmail.com");
	// cart = user.getCart();
	// cart.setGrandTotal(10000);
	// cart.setCartLines(1);
	// assertEquals("Failed to update the cart!", true,
	// userDAO.updateCart(cart));
	// }

}
