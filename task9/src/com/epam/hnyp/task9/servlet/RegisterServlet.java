package com.epam.hnyp.task9.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.hnyp.task9.Constants;
import com.epam.hnyp.task9.capcha.Capcha;
import com.epam.hnyp.task9.capcha.CapchaAwtJpegImpl;
import com.epam.hnyp.task9.capcha.provider.AbstractCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.AbstractCapchaProvider.CapchaUidMissedException;
import com.epam.hnyp.task9.listener.ContextInitializer;
import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.service.UserService;
import com.epam.hnyp.task9.validation.UserFormBean;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String POSTREDIRECT_REGISTER_CONVSCOPE_KEY = "com.epam.hnyp.task9.servlet.POSTREDIRECT_REGISTER_CONVSCOPE_KEY";
	public static final String CONVSCOPE_USERBEAN_KEY = "postre:registerUserFormBean";
	public static final String CONVSCOPE_ERRORMESSAGES_KEY = "postre:registerErrorMessages";
	
	public static final String ERRORMESSAGES_KEY = "errorMessages";
	public static final String USERBEAN_KEY = "userFormBean";
	
	public static final String CAPCHA_ERROR_KEY = "capchaError";
	
	public static final String REGISTER_FORM_NAME_PARAM = "name";
	public static final String REGISTER_FORM_LASTNAME_PARAM = "lastName";
	public static final String REGISTER_FORM_LOGIN_PARAM = "login";
	public static final String REGISTER_FORM_PWD_PARAM = "password";
	public static final String REGISTER_FORM_REPWD_PARAM = "rePassword";
	public static final String REGISTER_FORM_RECEIVELETTERS_PARAM = "receiveLetters";
	public static final String REGISTER_FORM_CAPCHA_PARAM = "capcha";
	
	private AbstractCapchaProvider capchaProvider;
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		capchaProvider = (AbstractCapchaProvider)getServletContext().getAttribute(ContextInitializer.INIT_CAPCHA_PROVIDER_KEY);
		userService = (UserService)getServletContext().getAttribute(ContextInitializer.INIT_USER_SERVICE_KEY);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		capchaProvider.clearAllExpiredCapcha(request);
		
		Capcha capcha = new CapchaAwtJpegImpl(Constants.CAPCHA_TEXT_LENGTH);
		capchaProvider.saveCapcha(request, response, capcha);
		
		Map<String, Object> conversationMap = (Map<String, Object>)session.getAttribute(POSTREDIRECT_REGISTER_CONVSCOPE_KEY);
		session.removeAttribute(POSTREDIRECT_REGISTER_CONVSCOPE_KEY);
		if (conversationMap != null) {
			request.setAttribute(ERRORMESSAGES_KEY, conversationMap.get(CONVSCOPE_ERRORMESSAGES_KEY));
			request.setAttribute(USERBEAN_KEY, conversationMap.get(CONVSCOPE_USERBEAN_KEY));
		}
		
		request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserFormBean userFormBean = extractRegisterForm(request);
		Map<String, String> errorMessages = userFormBean.validate();
		
		if (errorMessages.isEmpty()) {
			if (userService.userExists(userFormBean.getLogin())) {
				errorMessages.put(UserFormBean.USERBEAN_LOGIN_ERROR_KEY, "this login is already in use");
			}
		}
		
		String capchaText = request.getParameter(REGISTER_FORM_CAPCHA_PARAM);		
		Capcha originalCapcha = null;
		try {
			originalCapcha = capchaProvider.getCapcha(request);
		} catch (CapchaUidMissedException e) {
			//log
		}
		if (originalCapcha == null) {
			errorMessages.put(CAPCHA_ERROR_KEY, "capcha expired or not found, try again");
		} else if (capchaProvider.isCapchaExpired(originalCapcha)) {
			errorMessages.put(CAPCHA_ERROR_KEY, "capcha expired, try again");
		} else if (!originalCapcha.getCapcha().equals(capchaText)) {
			errorMessages.put(CAPCHA_ERROR_KEY, "wrong capcha, try again");
		}
		
		if (!errorMessages.isEmpty()) {
			HttpSession session = request.getSession();
			Map<String, Object> conversationMap = new HashMap<>();
			conversationMap.put(CONVSCOPE_ERRORMESSAGES_KEY, errorMessages);
			conversationMap.put(CONVSCOPE_USERBEAN_KEY, userFormBean);
			
			session.setAttribute(POSTREDIRECT_REGISTER_CONVSCOPE_KEY, conversationMap);
			
			response.sendRedirect(getServletContext().getContextPath() + "/register");
			return;
		}
		
		User user = userFormBean.buildUser();
		userService.add(user);
		
		response.sendRedirect(getServletContext().getContextPath() + "/main");
	}
	
	private UserFormBean extractRegisterForm(HttpServletRequest request) {
		UserFormBean form = new UserFormBean();
		form.setLastName(request.getParameter(REGISTER_FORM_LASTNAME_PARAM));
		form.setLogin(request.getParameter(REGISTER_FORM_LOGIN_PARAM));
		form.setName(request.getParameter(REGISTER_FORM_NAME_PARAM));
		form.setPassword(request.getParameter(REGISTER_FORM_PWD_PARAM));
		form.setReceiveLetters(request.getParameter(REGISTER_FORM_RECEIVELETTERS_PARAM));
		form.setRePassword(request.getParameter(REGISTER_FORM_REPWD_PARAM));
		return form;
	}
}
