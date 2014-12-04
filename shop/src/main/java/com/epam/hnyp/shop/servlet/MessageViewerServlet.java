package com.epam.hnyp.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageViewerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String PARAM_TITLE = "title";
	public static final String PARAM_MESSAGE = "message";
	
	public static final String MESSAGE_JSP = "WEB-INF/jsp/messageView.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(PARAM_TITLE, request.getParameter(PARAM_TITLE));
		request.setAttribute(PARAM_MESSAGE, request.getParameter(PARAM_MESSAGE));
		request.getRequestDispatcher(MESSAGE_JSP).forward(request, response);
	}
}
