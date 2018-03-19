package com.mukesh.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mukesh.shoppingbackend.dao.CartLineDAO;
import com.mukesh.shoppingbackend.dao.ProductDAO;
import com.mukesh.shoppingbackend.dao.UserDAO;
import com.mukesh.shoppingbackend.dto.Cart;
import com.mukesh.shoppingbackend.dto.CartLine;
import com.mukesh.shoppingbackend.dto.Product;
import com.mukesh.shoppingbackend.dto.User;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;

	private CartLine cartLine = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.mukesh.shoppingbackend");
		context.refresh();
		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
		productDAO = (ProductDAO) context.getBean("productDAO");
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	@Test
	public void testAddCartLine() {

		// fetch the user and then cart of that user
		User user = userDAO.getUserByEmail("muk@gmail.com");
		Cart cart = user.getCart();

		// fetch the product
		Product product = productDAO.getProduct(2);

		// Create a new CartLine
		cartLine = new CartLine();
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		cartLine.setProductCount(1);

		double oldTotal = cartLine.getTotal();

		cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());

		cart.setCartLines(cart.getCartLines() + 1);
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
		cartLine.setAvailable(true);

		assertEquals("Failed to add the CartLine!", true, cartLineDAO.addCartLine(cartLine));
		assertEquals("Failed to update the cart!", true, userDAO.updateCart(cart));

	}

	// @Test
	// public void testUpdateCartLine() {
	//
	// // fetch the user and then cart of that user
	// User user = userDAO.getUserByEmail("absr@gmail.com");
	// Cart cart = user.getCart();
	//
	// cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), 2);
	//
	// cartLine.setProductCount(cartLine.getProductCount() + 1);
	//
	// double oldTotal = cartLine.getTotal();
	//
	// cartLine.setTotal(cartLine.getProduct().getUnitPrice() *
	// cartLine.getProductCount());
	//
	// cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() -
	// oldTotal));
	//
	// assertEquals("Failed to update the CartLine!", true,
	// cartLineDAO.updateCartLine(cartLine));
	//
	// }

}
