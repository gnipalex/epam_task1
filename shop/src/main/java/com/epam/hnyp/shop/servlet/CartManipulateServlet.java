package com.epam.hnyp.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.hnyp.shop.cart.Cart;
import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.listener.SessionListener;
import com.epam.hnyp.shop.model.Product;
import com.epam.hnyp.shop.service.ProductService;

public class CartManipulateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger
			.getLogger(CartManipulateServlet.class);

	public static final String PARAM_ADD_MODE = "addMode";
	public static final String PARAM_ID = "productId";

	public static final String JSON_TOTAL_COUNT = "totalCount";
	public static final String JSON_TOTAL_PRICE = "totalPrice";
	public static final String JSON_ITEM_COUNT = "itemCount";
	public static final String JSON_ITEM_PRICE = "itemPrice";
	public static final String JSON_SUCCESS = "success";

	private ProductService productService;

	@Override
	public void init() throws ServletException {
		productService = (ProductService) getServletContext().getAttribute(
				ContextInitializer.INIT_PRODUCTS_SERVICE_KEY);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute(
				SessionListener.SESSION_CART_KEY);

		String addModeString = request.getParameter(PARAM_ADD_MODE);
		String prodIdString = request.getParameter(PARAM_ID);

		AddMode addMode = null;
		int prodId = 0;
		try {
			addMode = AddMode.valueOf(addModeString);
		} catch (Exception ex) {
			LOG.error("add mode not specified");
			return;
		}
		try {
			prodId = Integer.parseInt(prodIdString);
		} catch (NumberFormatException e) {
			if (addMode != AddMode.CLEAR_CART) {
				LOG.error("product id is not specified");
				return;
			}
		}
		int totalCount = 0, itemCount = 0;
		long totalPrice = 0, itemPrice = 0;
		boolean success = true;
		switch (addMode) {
		case APPEND_ONE:
			Product product = productService.getProduct(prodId);
			if (product == null) {
				success = false;
			} else {
				itemCount = cart.add(product);
				itemPrice = cart.getItemPrice(prodId);
			}
			break;
		case REMOVE_ONE:
			if (productService.getProduct(prodId) == null) {
				success = false;
			} else {
				itemCount = cart.remove(prodId);
				itemPrice = cart.getItemPrice(prodId);
			}
			break;
		case CLEAR_CART:
			cart.clear();
			break;
		case REMOVE_ALL:
			cart.removeAll(prodId);
			break;
		}
		totalCount = cart.getTotalCount();
		totalPrice = cart.getTotalPrice();
		
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.write(prepareJsonAnswer(totalCount, totalPrice, itemCount, itemPrice, success));
	}

	private String prepareJsonAnswer(int totalCount, long totalPrice,
			int itemCount, long itemPrice, boolean success) {
		StringBuilder result = new StringBuilder();
		result.append("{ \"").append(JSON_ITEM_COUNT).append("\" : ")
				.append(itemCount).append(", ");
		result.append("\"").append(JSON_ITEM_PRICE).append("\" : ")
				.append(itemPrice).append(", ");
		result.append("\"").append(JSON_TOTAL_PRICE).append("\" : ")
				.append(totalPrice).append(", ");
		result.append("\"").append(JSON_TOTAL_COUNT).append("\" : ")
				.append(totalCount).append(", ");
		result.append("\"").append(JSON_SUCCESS).append("\" : ")
				.append(success).append(" }");
		return result.toString();
	}

	public static enum AddMode {
		APPEND_ONE, REMOVE_ONE, REMOVE_ALL, CLEAR_CART
	}

}
