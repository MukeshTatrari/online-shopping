package com.mukesh.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mukesh.onlineshopping.model.UserModel;
import com.mukesh.shoppingbackend.dao.CartLineDAO;
import com.mukesh.shoppingbackend.dto.Cart;
import com.mukesh.shoppingbackend.dto.CartLine;
import com.mukesh.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private HttpSession session;

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

}
