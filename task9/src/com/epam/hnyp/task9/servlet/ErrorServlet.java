package com.epam.hnyp.task9.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class ErrorServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(ErrorServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Exception ex = (Exception)request.getAttribute("javax.servlet.error.exception");
		LOG.error(ex);
		request.getRequestDispatcher("WEB-INF/jsp/error/error.jsp").forward(request, response);
	}

}
