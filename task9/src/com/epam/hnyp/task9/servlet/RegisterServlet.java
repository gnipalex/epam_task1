package com.epam.hnyp.task9.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		capchaUidService = (CapchaUidService)getServletContext().getAttribute("init:capchaUidService");
		capchaService = (AbstractCapchaService)getServletContext().getAttribute("init:capchaService");
		capchaLength = (Integer)getServletContext().getAttribute("init:capchaLength");
		userService = (UserService)getServletContext().getAttribute("init:userService");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		capchaService.clearAllExpiredCapcha(request);
		
		Capcha capcha = new CapchaAwtJpegImpl(capchaLength);
		session.setAttribute("currentCapcha", capcha);
		String capchaUid = session.getId();
		capchaService.saveCapcha(request, capchaUid, capcha);/////exception
		capchaUidService.setUid(request, response, capchaUid);
		
		Map<String, String> errors = (Map<String, String>)session.getAttribute("registerErrorMessages");
		session.removeAttribute("registerErrorMessages");
		if (errors == null) {
			errors = new HashMap<String, String>();
		}
		request.setAttribute("errorMessages", errors);
		UserFormBean userFormBean = (UserFormBean)session.getAttribute("registerUserFormBean");
		session.removeAttribute("registerUserFormBean");
		if (userFormBean == null) {
			userFormBean = new UserFormBean();
		}
		request.setAttribute("userFormBean", userFormBean);
		
		request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserFormBean userFormBean = extractRegisterForm(request);
		Map<String, String> errorMessages = userFormBean.validate();
		
		if (errorMessages.isEmpty()) {
			if (userService.userExists(userFormBean.getLogin())) {
				errorMessages.put(UserFormBean.LOGIN_ERROR_KEY, "this login is already in use");
			}
		}
		
		String capchaText = request.getParameter("capcha");
		String capchaUid = capchaUidService.readUid(request);
		
		Capcha originalCapcha = capchaService.getCapcha(request, capchaUid);
		if (originalCapcha == null) {
			errorMessages.put("capchaError", "capcha expired or not found, try again");
		} else if (capchaService.isCapchaExpired(originalCapcha)) {
			errorMessages.put("capchaError", "capcha expired, try again");
		} else if (!originalCapcha.getCapcha().equals(capchaText)) {
			errorMessages.put("capchaError", "wrong capcha, try again");
		}
		
		if (!errorMessages.isEmpty()) {
			HttpSession session = request.getSession();
			session.setAttribute("registerUserFormBean", userFormBean);
			session.setAttribute("registerErrorMessages", errorMessages);
			response.sendRedirect(getServletContext().getContextPath() + "/register");
			return;
		}
		
		User user = userFormBean.buildUser();
		userService.add(user);
		
		response.sendRedirect(getServletContext().getContextPath() + "/main");
	}
	
	private UserFormBean extractRegisterForm(HttpServletRequest request) {
		UserFormBean form = new UserFormBean();
		form.setLastName(request.getParameter("lastName"));
		form.setLogin(request.getParameter("login"));
		form.setName(request.getParameter("name"));
		form.setPassword(request.getParameter("password"));
		form.setReceiveLetters(request.getParameter("receiveLetters"));
		form.setRePassword(request.getParameter("rePassword"));
		return form;
	}
}
