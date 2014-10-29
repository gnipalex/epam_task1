package com.epam.hnyp.task9.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.hnyp.task9.listener.ContextInitializer;
import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.service.UserService;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_AUT_USER_KEY = "SESSION_AUT_USER";
	
	public static final String LOGIN_FORM_LOGIN_PARAM = "login";
	public static final String LOGIN_FORM_PWD_PARAM = "password";
	public static final String LOGIN_FORM_URL_REFERRER_PARAM = "urlReferrer";
	
	public static final String ERROR_MESSAGE_KEY = "loginError";
	public static final String LOGIN_KEY = "login";
	public static final String URL_REFERRER_KEY = "urlReferrer";
	
	public static final String CONVSCOPE_LOGIN_KEY = "postre:login";
	public static final String CONVSCOPE_ERROR_MESSAGE_KEY = "postre:loginLoginError";
	public static final String CONVSCOPE_REFERRER_URL_KEY = "postre:urlReferrer";
	
	public static final String POSTREDIRECT_LOGIN_CONVSCOPE_KEY = "com.epam.hnyp.task9.servlet.POSTREDIRECT_LOGIN_CONVSCOPE_KEY";
	
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = (UserService)getServletContext().getAttribute(ContextInitializer.INIT_USER_SERVICE_KEY);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SESSION_AUT_USER_KEY);
		if (user != null) {
			response.sendRedirect(getServletContext().getContextPath() + "/main");
			return;
		}
		//postredirect errors
		Map<String, Object> conversationMap = (Map<String, Object>)session.getAttribute(POSTREDIRECT_LOGIN_CONVSCOPE_KEY);
		session.removeAttribute(POSTREDIRECT_LOGIN_CONVSCOPE_KEY);
		if (conversationMap != null) {
			request.setAttribute(ERROR_MESSAGE_KEY, conversationMap.get(CONVSCOPE_ERROR_MESSAGE_KEY));
			request.setAttribute(LOGIN_KEY, conversationMap.get(CONVSCOPE_LOGIN_KEY));
			request.setAttribute(URL_REFERRER_KEY, conversationMap.get(CONVSCOPE_REFERRER_URL_KEY));
		} else {
			request.setAttribute(URL_REFERRER_KEY, request.getHeader("referer"));	
		}
		
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String loginParam = request.getParameter(LOGIN_FORM_LOGIN_PARAM);
		String passwordParam = request.getParameter(LOGIN_FORM_PWD_PARAM);
		String urlReferrerParam = request.getParameter(LOGIN_FORM_URL_REFERRER_PARAM);
		
		String errorMessage = null;
		User user = null;
		if (loginParam == null || loginParam.isEmpty()) {
			errorMessage = "login is empty";
		} else {
			user = userService.getByLogin(loginParam);
			if (user == null || !user.getPassword().equals(passwordParam)) {
				errorMessage = "bad credentials";
			}
		}
		if (errorMessage != null) {
			Map<String, Object> conversationMap = new HashMap<String, Object>();
			conversationMap.put(CONVSCOPE_ERROR_MESSAGE_KEY, errorMessage);
			conversationMap.put(CONVSCOPE_LOGIN_KEY, loginParam);
			conversationMap.put(CONVSCOPE_REFERRER_URL_KEY, urlReferrerParam);
			session.setAttribute(POSTREDIRECT_LOGIN_CONVSCOPE_KEY, conversationMap);
			response.sendRedirect(getServletContext().getContextPath()
						+ "/login");
			return;
		}
		session.setAttribute(SESSION_AUT_USER_KEY, user);
		response.sendRedirect(urlReferrerParam);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
