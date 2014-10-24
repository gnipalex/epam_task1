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
import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.service.AbstractCapchaService;
import com.epam.hnyp.task9.service.CapchaUidService;
import com.epam.hnyp.task9.service.UserService;
import com.epam.hnyp.task9.util.Capcha;
import com.epam.hnyp.task9.util.CapchaAwtJpegImpl;
import com.epam.hnyp.task9.validation.UserFormBean;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AbstractCapchaService capchaService;
	private CapchaUidService capchaUidService;
	private UserService userService;
	private int capchaLength;
	
	@Override
	public void init() throws ServletException {
		capchaUidService = (CapchaUidService)getServletContext().getAttribute(Constants.INIT_CAPCHAUID_SERVICE_KEY);
		capchaService = (AbstractCapchaService)getServletContext().getAttribute(Constants.INIT_CAPCHA_SERVICE_KEY);
		capchaLength = (Integer)getServletContext().getAttribute(Constants.INIT_CAPCHA_LENGTH_KEY);
		userService = (UserService)getServletContext().getAttribute(Constants.INIT_USER_SERVICE_KEY);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		capchaService.clearAllExpiredCapcha(request);
		
		Capcha capcha = new CapchaAwtJpegImpl(capchaLength);
		session.setAttribute(Constants.SESSION_CURRENT_CAPCHA_KEY, capcha);
		String capchaUid = session.getId();
		capchaService.saveCapcha(request, capchaUid, capcha);/////exception
		capchaUidService.setUid(request, response, capchaUid);
		
		Map<String, String> errors = (Map<String, String>)session.getAttribute(Constants.REGISTER_POSTREDIRECT_ERRORMESSAGES_KEY);
		session.removeAttribute(Constants.REGISTER_POSTREDIRECT_ERRORMESSAGES_KEY);
		if (errors == null) {
			errors = new HashMap<String, String>();
		}
		request.setAttribute(Constants.REGISTER_ERRORMESSAGES_KEY, errors);
		UserFormBean userFormBean = (UserFormBean)session.getAttribute(Constants.REGISTER_POSTREDIRECT_USERBEAN_KEY);
		session.removeAttribute(Constants.REGISTER_POSTREDIRECT_USERBEAN_KEY);
		if (userFormBean == null) {
			userFormBean = new UserFormBean();
		}
		request.setAttribute(Constants.REGISTER_USERBEAN_KEY, userFormBean);
		
		request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserFormBean userFormBean = extractRegisterForm(request);
		Map<String, String> errorMessages = userFormBean.validate();
		
		if (errorMessages.isEmpty()) {
			if (userService.userExists(userFormBean.getLogin())) {
				errorMessages.put(Constants.USERBEAN_LOGIN_ERROR_KEY, "this login is already in use");
			}
		}
		
		String capchaText = request.getParameter(Constants.REGISTER_FORM_CAPCHA_PARAM);
		String capchaUid = capchaUidService.readUid(request);
		
		Capcha originalCapcha = capchaService.getCapcha(request, capchaUid);
		if (originalCapcha == null) {
			errorMessages.put(Constants.CAPCHA_ERROR_KEY, "capcha expired or not found, try again");
		} else if (capchaService.isCapchaExpired(originalCapcha)) {
			errorMessages.put(Constants.CAPCHA_ERROR_KEY, "capcha expired, try again");
		} else if (!originalCapcha.getCapcha().equals(capchaText)) {
			errorMessages.put(Constants.CAPCHA_ERROR_KEY, "wrong capcha, try again");
		}
		
		if (!errorMessages.isEmpty()) {
			HttpSession session = request.getSession();
			session.setAttribute(Constants.REGISTER_POSTREDIRECT_USERBEAN_KEY, userFormBean);
			session.setAttribute(Constants.REGISTER_POSTREDIRECT_ERRORMESSAGES_KEY, errorMessages);
			response.sendRedirect(getServletContext().getContextPath() + "/register");
			return;
		}
		
		User user = userFormBean.buildUser();
		userService.add(user);
		
		response.sendRedirect(getServletContext().getContextPath() + "/main");
	}
	
	private UserFormBean extractRegisterForm(HttpServletRequest request) {
		UserFormBean form = new UserFormBean();
		form.setLastName(request.getParameter(Constants.REGISTER_FORM_LASTNAME_PARAM));
		form.setLogin(request.getParameter(Constants.REGISTER_FORM_LOGIN_PARAM));
		form.setName(request.getParameter(Constants.REGISTER_FORM_NAME_PARAM));
		form.setPassword(request.getParameter(Constants.REGISTER_FORM_PWD_PARAM));
		form.setReceiveLetters(request.getParameter(Constants.REGISTER_FORM_RECEIVELETTERS_PARAM));
		form.setRePassword(request.getParameter(Constants.REGISTER_FORM_REPWD_PARAM));
		return form;
	}
}
