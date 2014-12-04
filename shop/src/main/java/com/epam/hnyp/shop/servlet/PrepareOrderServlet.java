package com.epam.hnyp.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class PrepareOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String PREPARE_ORDER_JSP = "WEB-INF/jsp/prepareOrder.jsp"; 
	
	public static final String PARAM_PAY_TYPE = "payType";
	public static final String PARAM_CREDIT_CARD = "creditCard";
	public static final String PARAM_DELIVERY_TYPE = "deliveryType";
	public static final String PARAM_ADDRESS = "address";
	
	
	private static final Logger LOG = Logger.getLogger(PrepareOrderServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//errors post redirect
		req.getRequestDispatcher(PREPARE_ORDER_JSP).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//validate
	}
}
