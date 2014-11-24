package com.epam.hnyp.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.model.User;
import com.epam.hnyp.shop.service.UserService;
import com.epam.hnyp.shop.util.convscope.ConversationScopeFactory;
import com.epam.hnyp.shop.util.convscope.ConversationScopeProvider;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LoginServlet.class);
	
	public static final String SESSION_AUT_USER_KEY = "SESSION_AUT_USER";
	
	public static final String LOGIN_FORM_LOGIN_PARAM = "login";
	public static final String LOGIN_FORM_PWD_PARAM = "password";
	public static final String LOGIN_FORM_URL_REFERRER_PARAM = "urlReferrer";
	
	public static final String ERROR_MESSAGE_KEY = "loginError";
	public static final String LOGIN_KEY = "login";
	public static final String URL_REFERRER_KEY = "urlReferrer";
	
	public static final String POSTREDIRECT_LOGIN_CONVSCOPE_KEY = "com.epam.hnyp.task9.servlet.POSTREDIRECT_LOGIN_CONVSCOPE_KEY";
	
	private UserService userService;
	private ConversationScopeFactory convScopeFactory;
	
	@Override
	public void init() throws ServletException {
		userService = (UserService)getServletContext().getAttribute(ContextInitializer.INIT_USER_SERVICE_KEY);
		convScopeFactory = (ConversationScopeFactory)getServletContext().getAttribute(
				ContextInitializer.INIT_CONVERSATION_SCOPE_FACTORY_KEY);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(SESSION_AUT_USER_KEY);
		if (user != null) {
			response.sendRedirect(getServletContext().getContextPath() + "/main");
			return;
		}
		//postredirect errors
		ConversationScopeProvider convScope = convScopeFactory.newConversationScopeProvider(request, POSTREDIRECT_LOGIN_CONVSCOPE_KEY);
		convScope.restore();
		if (request.getAttribute(URL_REFERRER_KEY) == null) {
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
			if (LOG.isInfoEnabled()) {
				LOG.info("login failed, saving conversation scope before redirect");
			}
			ConversationScopeProvider convScope = convScopeFactory.newConversationScopeProvider(request, POSTREDIRECT_LOGIN_CONVSCOPE_KEY);
			convScope.addAttribute(ERROR_MESSAGE_KEY, errorMessage);
			convScope.addAttribute(LOGIN_KEY, loginParam);
			convScope.addAttribute(URL_REFERRER_KEY, urlReferrerParam);
			convScope.save();
			
			if (LOG.isInfoEnabled()) {
				LOG.info("redirect to /login");
			}
			response.sendRedirect(getServletContext().getContextPath()
						+ "/login");
			return;
		}
		
		session.setAttribute(SESSION_AUT_USER_KEY, user);
		if (LOG.isInfoEnabled()) {
			LOG.info("successful login, redirecting to '" + urlReferrerParam + "'");
		}
		response.sendRedirect(urlReferrerParam);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ConversationScopeFactory getConvScopeFactory() {
		return convScopeFactory;
	}

	public void setConvScopeFactory(ConversationScopeFactory convScopeFactory) {
		this.convScopeFactory = convScopeFactory;
	}

}
