package com.mukesh.shoppingbackend.dao;

import java.util.List;

import com.mukesh.shoppingbackend.dto.Cart;
import com.mukesh.shoppingbackend.dto.CartLine;
import com.mukesh.shoppingbackend.dto.OrderDetail;

public interface CartLineDAO {

	
	public List<CartLine> listCartLines(int cartId);
	public CartLine getCartLine(int id);	
	public boolean addCartLine(CartLine cartLine);
	public boolean updateCartLine(CartLine cartLine);
	public boolean removeCartLine(CartLine cartLine);
	
	// fetch the CartLine based on cartId and productId
	public CartLine getByCartAndProduct(int cartId, int productId);		
		
	// updating the cart
	boolean updateCart(Cart cart);
	
	// list of available cartLine
	public List<CartLine> listAvailable(int cartId);
	
	// adding order details
	boolean addOrderDetail(OrderDetail orderDetail);
}
