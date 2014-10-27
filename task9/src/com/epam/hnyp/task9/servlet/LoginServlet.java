package com.epam.hnyp.task9.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.hnyp.task9.Constants;
import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.service.UserService;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = (UserService)getServletContext().getAttribute(Constants.INIT_USER_SERVICE_KEY);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Constants.SESSION_AUT_USER_KEY);
		if (user != null) {
			response.sendRedirect(getServletContext().getContextPath() + "/main");
			return;
		}
		//postredirect errors
		String login = (String)session.getAttribute(Constants.LOGIN_POSTREDIRECT_LOGIN_KEY);
		session.removeAttribute(Constants.LOGIN_POSTREDIRECT_LOGIN_KEY);
		if (login != null) {
			request.setAttribute(Constants.LOGIN_LOGIN_KEY, login);
		}
		String errorMessage = (String)session.getAttribute(Constants.LOGIN_POSTREDIRECT_ERROR_MESSAGE_KEY);
		session.removeAttribute(Constants.LOGIN_POSTREDIRECT_ERROR_MESSAGE_KEY);
		if (errorMessage != null) {
			request.setAttribute(Constants.LOGIN_ERROR_MESSAGE_KEY, errorMessage);
		}
		
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String loginParam = request.getParameter(Constants.LOGIN_LOGIN_PARAM);
		String passwordParam = request.getParameter(Constants.LOGIN_PWD_PARAM);
		
		if (loginParam == null || loginParam.isEmpty()) {
			session.setAttribute(Constants.LOGIN_POSTREDIRECT_ERROR_MESSAGE_KEY, "login is empty");
			response.sendRedirect(getServletContext().getContextPath() + "/login");
			return;
		}
		
		User user = userService.getByLogin(loginParam);
		if (user == null || !user.getPassword().equals(passwordParam)) {
			session.setAttribute(Constants.LOGIN_POSTREDIRECT_LOGIN_KEY, loginParam);
			session.setAttribute(Constants.LOGIN_POSTREDIRECT_ERROR_MESSAGE_KEY, "bad credentials");
			response.sendRedirect(getServletContext().getContextPath() + "/login");
			return;
		}
		
		session.setAttribute(Constants.SESSION_AUT_USER_KEY, user);
		response.sendRedirect(getServletContext().getContextPath() + "/main");
	}
	
	

}
