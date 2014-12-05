package com.epam.hnyp.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.hnyp.shop.cart.Cart;
import com.epam.hnyp.shop.listener.SessionListener;

public class PrepareCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(PrepareCartServlet.class);

	public static final String PREPARED_CART_KEY = "PREPARED_CART_KEY";
	public static final String PREPARE_ORDER_URL = "/prepareOrder";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session
				.getAttribute(SessionListener.SESSION_CART_KEY);
		if (cart.getTotalCount() == 0) {
			LOG.info("cart is empty");
			response.sendRedirect(getServletContext().getContextPath() + "/message?title=Cart prepare status&message=Cart is empty, add some products first");
			return;
		}
		session.setAttribute(PREPARED_CART_KEY, new Cart(cart));
		response.sendRedirect(getServletContext().getContextPath() + PREPARE_ORDER_URL);
	}

}
