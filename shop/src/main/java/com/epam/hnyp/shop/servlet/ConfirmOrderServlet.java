package com.epam.hnyp.shop.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.hnyp.shop.cart.Cart;
import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.listener.SessionListener;
import com.epam.hnyp.shop.model.Order;
import com.epam.hnyp.shop.model.OrderStatus;
import com.epam.hnyp.shop.model.User;
import com.epam.hnyp.shop.service.OrderService;


public class ConfirmOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String MESSAGE_URL = "/message";
	public static final String MESSAGE_TITLE = "Order create status";
	public static final String MESSAGE_MESSAGE_ERROR = "error creating order, order process was corrupted";
	public static final String MESSAGE_MESSAGE_OK = "order successful created";
	
	private OrderService orderService;
	
	@Override
	public void init() throws ServletException {
		orderService = (OrderService)getServletContext().getAttribute(ContextInitializer.INIT_ORDER_SERVIVE_KEY);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Order preparedOrder = (Order)session.getAttribute(PrepareOrderServlet.PREPARED_ORDER_KEY);
		if (preparedOrder == null) {
			response.sendRedirect(getServletContext().getContextPath() + MESSAGE_URL + "?title=" + MESSAGE_TITLE + "&message=" + MESSAGE_MESSAGE_ERROR);
			return;
		}
		User user = (User)session.getAttribute(LoginServlet.SESSION_AUT_USER_KEY);
		preparedOrder.setDate(new Date());
		preparedOrder.setUserId(user.getId());
		preparedOrder.setStatus(OrderStatus.CONFIRMED);
		orderService.createOrder(preparedOrder);
		session.removeAttribute(PrepareOrderServlet.PREPARED_ORDER_KEY);
		session.removeAttribute(PrepareCartServlet.PREPARED_CART_KEY);
		Cart cart = (Cart)session.getAttribute(SessionListener.SESSION_CART_KEY);
		cart.clear();
		response.sendRedirect(getServletContext().getContextPath() + MESSAGE_URL + "?title=" + MESSAGE_TITLE + "&message=" + MESSAGE_MESSAGE_OK);
	}
}
