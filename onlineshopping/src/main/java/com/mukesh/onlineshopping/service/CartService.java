package com.mukesh.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mukesh.onlineshopping.model.UserModel;
import com.mukesh.shoppingbackend.dao.CartLineDAO;
import com.mukesh.shoppingbackend.dao.ProductDAO;
import com.mukesh.shoppingbackend.dto.Cart;
import com.mukesh.shoppingbackend.dto.CartLine;
import com.mukesh.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private HttpSession session;

	@Autowired
	private ProductDAO productDAO;

	// return cart of the user who is logged in
	private Cart getCart() {
		return ((UserModel) (session.getAttribute("userModel"))).getCart();
	}

	/**
	 * 
	 * @return
	 * 
	 *         returns the Entire cart Line
	 */
	public List<CartLine> getCartLines() {
		Cart cart = this.getCart();
		return cartLineDAO.listCartLines(cart.getId());
	}

	public String updateCartLine(int cartLineId, int count) {

		// fetch the cartLine

		CartLine cartLine = cartLineDAO.getCartLine(cartLineId);
		if (cartLine == null) {
			return "result=error";

		} else {
			Product product = cartLine.getProduct();
			double old_Total = cartLine.getTotal();

			if (product.getQuantity() <= 3) {
				count = product.getQuantity();
			}

			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			double new_Total = cartLine.getProductCount() * cartLine.getBuyingPrice();
			cartLine.setTotal(new_Total);

			cartLineDAO.updateCartLine(cartLine);

			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - old_Total + cartLine.getTotal());

			cartLineDAO.updateCart(cart);

			return "result=updated";
		}

	}

	public String deleteCartLine(int cartLineId) {

		CartLine cartLine = cartLineDAO.getCartLine(cartLineId);
		if (cartLine == null) {
			return "result=error";

		} else {

			cartLineDAO.updateCartLine(cartLine);
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);

			// remove the cart Line
			cartLineDAO.removeCartLine(cartLine);

			return "result=deleted";
		}
	}

	public String addCartLine(int productId) {
		String response = null;

		Cart cart = this.getCart();

		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);

		if (cartLine == null) {

			cartLine = new CartLine();
			// fecth the product
			Product product = productDAO.getProduct(productId);

			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);

			cartLineDAO.addCartLine(cartLine);

			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());

			cartLineDAO.updateCart(cart);

			response = "result=added";
		} else {
			// check if the cartLine has been already reached to maximum count
			if (cartLine.getProductCount() < 3) {
				// call the manageCartLine method to increase the count
				response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);
			} else {
				response = "result=maximum";
			}
		}

		return response;

	}

	/* to update the cart count */
	public String manageCartLine(int cartLineId, int count) {

		CartLine cartLine = cartLineDAO.getCartLine(cartLineId);

		double oldTotal = cartLine.getTotal();

		Product product = cartLine.getProduct();

		// check if that much quantity is available or not
		if (product.getQuantity() < count) {
			return "result=unavailable";
		}

		// update the cart line
		cartLine.setProductCount(count);
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setTotal(product.getUnitPrice() * count);
		cartLineDAO.updateCartLine(cartLine);

		// update the cart
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
		cartLineDAO.updateCart(cart);

		return "result=updated";
	}

	public String validateCartLine() {
		Cart cart = this.getCart();
		List<CartLine> cartLines = cartLineDAO.listCartLines(cart.getId());
		double grandTotal = 0.0;
		int lineCount = 0;
		String response = "result=success";
		boolean changed = false;
		Product product = null;
		for (CartLine cartLine : cartLines) {
			product = cartLine.getProduct();
			changed = false;
			// check if the product is active or not
			// if it is not active make the availability of cartLine as false
			if ((!product.isActive() && product.getQuantity() == 0) && cartLine.isAvailable()) {
				cartLine.setAvailable(false);
				changed = true;
			}
			// check if the cartLine is not available
			// check whether the product is active and has at least one quantity
			// available
			if ((product.isActive() && product.getQuantity() > 0) && !(cartLine.isAvailable())) {
				cartLine.setAvailable(true);
				changed = true;
			}

			// check if the buying price of product has been changed
			if (cartLine.getBuyingPrice() != product.getUnitPrice()) {
				// set the buying price to the new price
				cartLine.setBuyingPrice(product.getUnitPrice());
				// calculate and set the new total
				cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
				changed = true;
			}

			// check if that much quantity of product is available or not
			if (cartLine.getProductCount() > product.getQuantity()) {
				cartLine.setProductCount(product.getQuantity());
				cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
				changed = true;

			}

			// changes has happened
			if (changed) {
				// update the cartLine
				cartLineDAO.updateCartLine(cartLine);
				// set the result as modified
				response = "result=modified";
			}

			grandTotal += cartLine.getTotal();
			lineCount++;
		}

		cart.setCartLines(lineCount++);
		cart.setGrandTotal(grandTotal);
		cartLineDAO.updateCart(cart);

		return response;
	}
}
