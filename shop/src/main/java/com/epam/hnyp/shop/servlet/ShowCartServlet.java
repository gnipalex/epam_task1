package com.epam.hnyp.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hnyp.shop.cart.Cart;
import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.listener.SessionListener;
import com.epam.hnyp.shop.service.ProductService;


public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductService prodService;
	
	@Override
	public void init() throws ServletException {
		prodService = (ProductService)getServletContext().getAttribute(ContextInitializer.INIT_PRODUCTS_SERVICE_KEY);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute(SessionListener.SESSION_CART_KEY);
		
	}


}
