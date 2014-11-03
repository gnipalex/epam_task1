package com.epam.hnyp.task9.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.hnyp.task9.capcha.Capcha;
import com.epam.hnyp.task9.capcha.CapchaAwtJpegImpl;
import com.epam.hnyp.task9.capcha.provider.AbstractCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.AbstractCapchaProvider.CapchaUidMissedException;
import com.epam.hnyp.task9.listener.ContextInitializer;
import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.service.UserService;
import com.epam.hnyp.task9.util.ConversationScopeFactory;
import com.epam.hnyp.task9.util.ConversationScopeProvider;
import com.epam.hnyp.task9.validation.UserFormBean;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(RegisterServlet.class);

	public static final String REGISTER_JSP_URL = "WEB-INF/jsp/register.jsp";
	public static final String REDIRECT_REGISTER_FAIL = "/register";
	public static final String REDIRECT_REGISTER_SUCCESS = "/main";

	public static final String POSTREDIRECT_REGISTER_CONVSCOPE_KEY = "com.epam.hnyp.task9.servlet.POSTREDIRECT_REGISTER_CONVSCOPE_KEY";

	public static final String ERRORMESSAGES_KEY = "errorMessages";
	public static final String USERBEAN_KEY = "userFormBean";

	public static final String CAPCHA_ERROR_KEY = "capchaError";
	public static final int CAPCHA_TEXT_LENGTH = 5;

	public static final String REGISTER_FORM_NAME_PARAM = "name";
	public static final String REGISTER_FORM_LASTNAME_PARAM = "lastName";
	public static final String REGISTER_FORM_LOGIN_PARAM = "login";
	public static final String REGISTER_FORM_PWD_PARAM = "password";
	public static final String REGISTER_FORM_REPWD_PARAM = "rePassword";
	public static final String REGISTER_FORM_RECEIVELETTERS_PARAM = "receiveLetters";
	public static final String REGISTER_FORM_CAPCHA_PARAM = "capcha";

	private AbstractCapchaProvider capchaProvider;
	private UserService userService;
	private ConversationScopeFactory convScopeFactory;

	@Override
	public void init() throws ServletException {
		capchaProvider = (AbstractCapchaProvider) getServletContext()
				.getAttribute(ContextInitializer.INIT_CAPCHA_PROVIDER_KEY);
		userService = (UserService) getServletContext().getAttribute(
				ContextInitializer.INIT_USER_SERVICE_KEY);
		convScopeFactory = (ConversationScopeFactory)getServletContext().getAttribute(
				ContextInitializer.INIT_CONVERSATION_SCOPE_FACTORY_KEY);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		capchaProvider.clearAllExpiredCapcha(request);

		Capcha capcha = new CapchaAwtJpegImpl(CAPCHA_TEXT_LENGTH);
		capchaProvider.saveCapcha(request, response, capcha);

		ConversationScopeProvider convScope = convScopeFactory
				.newConversationScopeProvider(request, POSTREDIRECT_REGISTER_CONVSCOPE_KEY);
		convScope.restore();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("register(GET)");
		}

		request.getRequestDispatcher(REGISTER_JSP_URL).forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserFormBean userFormBean = extractRegisterForm(request);
		Map<String, String> errorMessages = userFormBean.validate();
		
		if (errorMessages.isEmpty()) {
			try {
			if (userService.userExists(userFormBean.getLogin())) {
				errorMessages.put(UserFormBean.USERBEAN_LOGIN_ERROR_KEY, "this login is already in use");
				if (LOG.isInfoEnabled()) {
					LOG.info("register(POST) user already exist");
				}
			}
			} catch (SQLException e) {
				LOG.error(e);
				throw new SqlRuntimeException(e);
			}
		}
		
		String capchaText = request.getParameter(REGISTER_FORM_CAPCHA_PARAM);		
		Capcha originalCapcha = null;
		try {
			originalCapcha = capchaProvider.getCapcha(request);
		} catch (CapchaUidMissedException e) {
			LOG.error("capcha uid from client missed", e);
		}
		if (originalCapcha == null) {
			errorMessages.put(CAPCHA_ERROR_KEY, "capcha expired or not found, try again");
			if (LOG.isInfoEnabled()) {
				LOG.info("register(POST) originalCapcha == null");
			}
		} else if (capchaProvider.isCapchaExpired(originalCapcha)) {
			errorMessages.put(CAPCHA_ERROR_KEY, "capcha expired, try again");
			if (LOG.isInfoEnabled()) {
				LOG.info("register(POST) capcha expired");
			}
		} else if (!originalCapcha.getCapcha().equals(capchaText)) {
			errorMessages.put(CAPCHA_ERROR_KEY, "wrong capcha, try again");
			if (LOG.isInfoEnabled()) {
				LOG.info("register(POST) capcha mismatch");
			}
		}
		
		if (!errorMessages.isEmpty()) {
			ConversationScopeProvider convScope = convScopeFactory
					.newConversationScopeProvider(request, POSTREDIRECT_REGISTER_CONVSCOPE_KEY);
			convScope.addAttribute(ERRORMESSAGES_KEY, errorMessages);
			convScope.addAttribute(USERBEAN_KEY, userFormBean);
			convScope.save();
			if (LOG.isInfoEnabled()) {
				LOG.info("register(POST) errors due register, redirecting to " + REDIRECT_REGISTER_FAIL);
			}
			response.sendRedirect(request.getServletContext().getContextPath() + REDIRECT_REGISTER_FAIL);
			return;
		}
		
		User user = userFormBean.buildUser();
		try {
			userService.add(user);
		} catch (SQLException e) {
			LOG.error("user is not saved", e);
			throw new SqlRuntimeException(e);
		}
		
		if (LOG.isInfoEnabled()) {
			LOG.info("register(POST) redirecting to " + REDIRECT_REGISTER_SUCCESS);
		}
		
		response.sendRedirect(request.getServletContext().getContextPath() + REDIRECT_REGISTER_SUCCESS);
	}

	private UserFormBean extractRegisterForm(HttpServletRequest request) {
		UserFormBean form = new UserFormBean();
		form.setLastName(request.getParameter(REGISTER_FORM_LASTNAME_PARAM));
		form.setLogin(request.getParameter(REGISTER_FORM_LOGIN_PARAM));
		form.setName(request.getParameter(REGISTER_FORM_NAME_PARAM));
		form.setPassword(request.getParameter(REGISTER_FORM_PWD_PARAM));
		form.setReceiveLetters(request
				.getParameter(REGISTER_FORM_RECEIVELETTERS_PARAM));
		form.setRePassword(request.getParameter(REGISTER_FORM_REPWD_PARAM));
		return form;
	}

	public AbstractCapchaProvider getCapchaProvider() {
		return capchaProvider;
	}

	public void setCapchaProvider(AbstractCapchaProvider capchaProvider) {
		this.capchaProvider = capchaProvider;
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
