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
	
	private static final Logger LOG = Logger.getLogger(PrepareOrderServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher(PREPARE_ORDER_JSP).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
