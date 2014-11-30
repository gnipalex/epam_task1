package com.epam.hnyp.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.service.UserService;

public class UserLoginCheckerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String PARAM_LOGIN = "login";

	private UserService userService;

	@Override
	public void init() throws ServletException {
		userService = (UserService) getServletContext().getAttribute(
				ContextInitializer.INIT_USER_SERVICE_KEY);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(PARAM_LOGIN);
		String resultJson = null;
		if (login == null || login.isEmpty()) {
			resultJson = prepareJsonAnswer(false, true);
		} else {
			resultJson = prepareJsonAnswer(userService.userExists(login), false);
		}
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.write(resultJson);
	}

	private String prepareJsonAnswer(boolean userExists, boolean empty) {
		StringBuilder str = new StringBuilder();
		str.append("{ ").append("\"exist\" : ").append(userExists)
				.append(", \"empty\" : ").append(empty).append(" }");
		return str.toString();
	}
}
