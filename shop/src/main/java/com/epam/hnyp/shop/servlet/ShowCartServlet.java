package com.epam.hnyp.shop.servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hnyp.shop.cart.Cart;
import com.epam.hnyp.shop.cart.Cart.CartItem;
import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.listener.SessionListener;
import com.epam.hnyp.shop.model.Product;
import com.epam.hnyp.shop.service.ProductService;


public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String CART_JSP_URL = "WEB-INF/jsp/cart.jsp";
	
	private ProductService prodService;
	
	@Override
	public void init() throws ServletException {
		prodService = (ProductService)getServletContext().getAttribute(ContextInitializer.INIT_PRODUCTS_SERVICE_KEY);
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Cart cart = (Cart)request.getSession().getAttribute(SessionListener.SESSION_CART_KEY);
//		refreshCart(cart);
//		request.getRequestDispatcher(CART_JSP_URL).forward(request, response);
//	}

}
