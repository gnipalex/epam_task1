package com.epam.hnyp.task9.servlet;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.epam.hnyp.task9.capcha.Capcha;
import com.epam.hnyp.task9.capcha.provider.AbstractCapchaProvider;
import com.epam.hnyp.task9.capcha.provider.AbstractCapchaProvider.CapchaUidMissedException;
import com.epam.hnyp.task9.form.UserFormBean;
import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.service.UserService;
import com.epam.hnyp.task9.util.convscope.ConversationScopeFactory;
import com.epam.hnyp.task9.util.convscope.ConversationScopeProvider;
import com.epam.hnyp.task9.util.img.ImgProvider;

public class RegisterServletTest {

	private final String fakeContextPath = "fakeContextPath";
	private final String fakeStringParam = "AAA";
	private final String fakeEmailParam = "1@ya.ru";
	private final String fakeCapcha = "12345";

	private HttpSession mockSession;
	private Part mockPart;
	private HttpServletRequest mockRequest;
	private RequestDispatcher mockDispatcher;
	private HttpServletResponse mockResponse;
	private Capcha mockCapcha;
	private AbstractCapchaProvider mockCapchaProvider;
	private UserService mockUserService;
	private ServletContext mockServletContext;
	private ConversationScopeFactory mockConversationScopeFactory;
	private ConversationScopeProvider mockConvScopeProvider;
	private ImgProvider mockImageProvider;

	private RegisterServlet testServlet = new RegisterServlet();

	@Before
	public void prepare() throws CapchaUidMissedException {
		mockSession = Mockito.mock(HttpSession.class);
		mockPart = Mockito.mock(Part.class);
		mockDispatcher = Mockito.mock(RequestDispatcher.class);

		mockRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
		Mockito.when(
				mockRequest
						.getRequestDispatcher(RegisterServlet.REGISTER_JSP_URL))
				.thenReturn(mockDispatcher);
		mockResponse = Mockito.mock(HttpServletResponse.class);

		mockCapcha = Mockito.mock(Capcha.class);
		Mockito.when(mockCapcha.getCapcha()).thenReturn(fakeCapcha);
		mockCapchaProvider = Mockito.mock(AbstractCapchaProvider.class);
		Mockito.when(mockCapchaProvider.getCapcha(mockRequest)).thenReturn(
				mockCapcha);

		mockUserService = Mockito.mock(UserService.class);

		mockConvScopeProvider = Mockito.mock(ConversationScopeProvider.class);
		mockConversationScopeFactory = Mockito
				.mock(ConversationScopeFactory.class);
		Mockito.when(
				mockConversationScopeFactory.newConversationScopeProvider(
						Mockito.any(HttpServletRequest.class),
						Mockito.anyString())).thenReturn(mockConvScopeProvider);

		mockServletContext = Mockito.mock(ServletContext.class);
		Mockito.when(mockServletContext.getContextPath()).thenReturn(
				fakeContextPath);
		Mockito.when(mockRequest.getServletContext()).thenReturn(
				mockServletContext);

		mockImageProvider = Mockito.mock(ImgProvider.class);

		testServlet.setCapchaProvider(mockCapchaProvider);
		testServlet.setUserService(mockUserService);
		testServlet.setConvScopeFactory(mockConversationScopeFactory);
		testServlet.setAvatarProvider(mockImageProvider);
	}

	@Test
	public void testDoGetClearAllExpiredCapcha() throws ServletException,
			IOException {
		testServlet.doGet(mockRequest, mockResponse);
		Mockito.verify(mockCapchaProvider).clearAllExpiredCapcha(mockRequest);
	}

	@Test
	public void testDoGetSaveCapcha() throws ServletException, IOException {
		testServlet.doGet(mockRequest, mockResponse);
		Mockito.verify(mockCapchaProvider).saveCapcha(
				Mockito.any(HttpServletRequest.class),
				Mockito.any(HttpServletResponse.class),
				Mockito.any(Capcha.class));
	}

	private void configureMockRequestParams(String capcha, String lastName,
			String login, String name, String pwd, String receiveLetters,
			String rePwd) {
		Mockito.when(
				mockRequest
						.getParameter(RegisterServlet.REGISTER_FORM_CAPCHA_PARAM))
				.thenReturn(capcha);
		Mockito.when(
				mockRequest
						.getParameter(RegisterServlet.REGISTER_FORM_LASTNAME_PARAM))
				.thenReturn(lastName);
		Mockito.when(
				mockRequest
						.getParameter(RegisterServlet.REGISTER_FORM_LOGIN_PARAM))
				.thenReturn(login);
		Mockito.when(
				mockRequest
						.getParameter(RegisterServlet.REGISTER_FORM_NAME_PARAM))
				.thenReturn(name);
		Mockito.when(
				mockRequest
						.getParameter(RegisterServlet.REGISTER_FORM_PWD_PARAM))
				.thenReturn(pwd);
		Mockito.when(
				mockRequest
						.getParameter(RegisterServlet.REGISTER_FORM_RECEIVELETTERS_PARAM))
				.thenReturn(receiveLetters);
		Mockito.when(
				mockRequest
						.getParameter(RegisterServlet.REGISTER_FORM_REPWD_PARAM))
				.thenReturn(rePwd);
	}

	@Test
	public void testDoPostSuccessRegistration() throws ServletException,
			IOException {
		configureMockRequestParams(fakeCapcha, fakeStringParam, fakeEmailParam,
				fakeStringParam, fakeStringParam, fakeStringParam,
				fakeStringParam);

		Mockito.when(mockCapchaProvider.isCapchaExpired(mockCapcha))
				.thenReturn(false);

		Mockito.when(mockUserService.userExists(fakeEmailParam)).thenReturn(
				false);

		testServlet.doPost(mockRequest, mockResponse);

		Mockito.verify(mockUserService).add(Mockito.any(User.class));

		Mockito.verify(mockResponse).sendRedirect(
				fakeContextPath + RegisterServlet.REDIRECT_REGISTER_SUCCESS);
	}

	@Test
	public void testDoPostRegisterFailedCapchaExpired()
			throws ServletException, IOException {
		configureMockRequestParams(fakeCapcha, fakeStringParam, fakeEmailParam,
				fakeStringParam, fakeStringParam, fakeStringParam,
				fakeStringParam);

		Mockito.when(mockCapchaProvider.isCapchaExpired(mockCapcha))
				.thenReturn(true);

		Mockito.when(mockUserService.userExists(fakeEmailParam)).thenReturn(
				false);

		testServlet.doPost(mockRequest, mockResponse);

		ArgumentCaptor<Object> argCaptor = ArgumentCaptor
				.forClass(Object.class);
		Mockito.verify(mockConvScopeProvider).addAttribute(
				Mockito.eq(RegisterServlet.ERRORMESSAGES_KEY),
				argCaptor.capture());
		Map<String, Object> errorsMap = (Map<String, Object>) argCaptor
				.getValue();

		assertTrue("capcha error message was not set",
				errorsMap.containsKey(RegisterServlet.CAPCHA_ERROR_KEY));

		Mockito.verify(mockResponse).sendRedirect(
				fakeContextPath + RegisterServlet.REDIRECT_REGISTER_FAIL);
	}

	@Test
	public void testDoPostRegisterFailedUserExist() throws ServletException,
			IOException {
		configureMockRequestParams(fakeCapcha, fakeStringParam, fakeEmailParam,
				fakeStringParam, fakeStringParam, fakeStringParam,
				fakeStringParam);

		Mockito.when(mockCapchaProvider.isCapchaExpired(mockCapcha))
				.thenReturn(false);

		Mockito.when(mockUserService.userExists(fakeEmailParam)).thenReturn(
				true);

		testServlet.doPost(mockRequest, mockResponse);

		ArgumentCaptor<Object> argCaptor = ArgumentCaptor
				.forClass(Object.class);
		Mockito.verify(mockConvScopeProvider).addAttribute(
				Mockito.eq(RegisterServlet.ERRORMESSAGES_KEY),
				argCaptor.capture());
		Map<String, Object> errorsMap = (Map<String, Object>) argCaptor
				.getValue();

		assertTrue("not found user exist error message",
				errorsMap.containsKey(UserFormBean.USERBEAN_LOGIN_ERROR_KEY));

		Mockito.verify(mockResponse).sendRedirect(
				fakeContextPath + RegisterServlet.REDIRECT_REGISTER_FAIL);
	}

	@Test
	public void testDoPostRegisterAvatarIgnoredDueErrors()
			throws IllegalStateException, IOException, ServletException {
		configureMockRequestParams(fakeCapcha, fakeStringParam, fakeEmailParam,
				fakeStringParam, fakeStringParam, fakeStringParam,
				fakeStringParam);

		Mockito.when(mockCapchaProvider.isCapchaExpired(mockCapcha))
				.thenReturn(false);

		// user already exists
		Mockito.when(mockUserService.userExists(fakeEmailParam)).thenReturn(
				true);

		Mockito.when(
				mockRequest.getPart(RegisterServlet.REGISTER_FORM_AVATAR_PARAM))
				.thenReturn(mockPart);

		Mockito.when(mockImageProvider.supportsMimeType(Mockito.anyString()))
				.thenReturn(true);

		testServlet.doPost(mockRequest, mockResponse);

		Mockito.verify(mockUserService, Mockito.never()).add(
				Mockito.any(User.class));

		Mockito.verify(mockImageProvider, Mockito.never()).read(
				Mockito.any(InputStream.class));

		Mockito.verify(mockResponse).sendRedirect(
				fakeContextPath + RegisterServlet.REDIRECT_REGISTER_FAIL);
	}

	@Test
	public void testDoPostRegisterAvatarWrongMimeType()
			throws IllegalStateException, IOException, ServletException {
		configureMockRequestParams(fakeCapcha, fakeStringParam, fakeEmailParam,
				fakeStringParam, fakeStringParam, fakeStringParam,
				fakeStringParam);

		Mockito.when(mockPart.getSize()).thenReturn(1L);
		Mockito.when(
				mockRequest.getPart(RegisterServlet.REGISTER_FORM_AVATAR_PARAM))
				.thenReturn(mockPart);

		Mockito.when(mockCapchaProvider.isCapchaExpired(mockCapcha))
				.thenReturn(false);

		Mockito.when(mockUserService.userExists(fakeEmailParam)).thenReturn(
				false);

		// setting imageProvider NOT to support mime type
		Mockito.when(mockImageProvider.supportsMimeType(Mockito.anyString()))
				.thenReturn(false);

		testServlet.doPost(mockRequest, mockResponse);

		ArgumentCaptor<Object> argCaptor = ArgumentCaptor
				.forClass(Object.class);
		Mockito.verify(mockConvScopeProvider).addAttribute(
				Mockito.eq(RegisterServlet.ERRORMESSAGES_KEY),
				argCaptor.capture());
		Map<String, Object> errorsMap = (Map<String, Object>) argCaptor
				.getValue();

		assertTrue("not found avatar error message",
				errorsMap.containsKey(RegisterServlet.AVATAR_ERROR_KEY));

		Mockito.verify(mockResponse).sendRedirect(
				fakeContextPath + RegisterServlet.REDIRECT_REGISTER_FAIL);
	}

	@Test
	public void testDoPostRegisterAvatarSuccesfulySavedWithOriginalResolution()
			throws IllegalStateException, IOException, ServletException {
		configureMockRequestParams(fakeCapcha, fakeStringParam, fakeEmailParam,
				fakeStringParam, fakeStringParam, fakeStringParam,
				fakeStringParam);

		Mockito.when(mockPart.getSize()).thenReturn(1L);
		Mockito.when(
				mockRequest.getPart(RegisterServlet.REGISTER_FORM_AVATAR_PARAM))
				.thenReturn(mockPart);

		Mockito.when(mockCapchaProvider.isCapchaExpired(mockCapcha))
				.thenReturn(false);

		Mockito.when(mockUserService.userExists(fakeEmailParam)).thenReturn(
				false);

		// setting imageProvider TO support mime type
		Mockito.when(mockImageProvider.supportsMimeType(Mockito.anyString()))
				.thenReturn(true);
		// setting imageProvider TO return normal resolution image
		BufferedImage mockImage = Mockito.mock(BufferedImage.class);
		Mockito.when(mockImage.getHeight()).thenReturn(
				RegisterServlet.AVATAR_MAX_HEIGHT);
		Mockito.when(mockImage.getWidth()).thenReturn(
				RegisterServlet.AVATAR_MAX_WIDTH);

		Mockito.when(mockImageProvider.read(mockPart.getInputStream()))
				.thenReturn(mockImage);

		testServlet.doPost(mockRequest, mockResponse);

		Mockito.verify(mockImageProvider).write(Mockito.eq(mockImage),
				Mockito.anyString());

		Mockito.verify(mockResponse).sendRedirect(
				fakeContextPath + RegisterServlet.REDIRECT_REGISTER_SUCCESS);
	}

	@Test
	public void testDoPostRegisterAvatarSuccesfulySavedResized()
			throws IllegalStateException, IOException, ServletException {
		configureMockRequestParams(fakeCapcha, fakeStringParam, fakeEmailParam,
				fakeStringParam, fakeStringParam, fakeStringParam,
				fakeStringParam);

		Mockito.when(mockPart.getSize()).thenReturn(1L);
		Mockito.when(
				mockRequest.getPart(RegisterServlet.REGISTER_FORM_AVATAR_PARAM))
				.thenReturn(mockPart);

		Mockito.when(mockCapchaProvider.isCapchaExpired(mockCapcha))
				.thenReturn(false);

		Mockito.when(mockUserService.userExists(fakeEmailParam)).thenReturn(
				false);

		// setting imageProvider TO support mime type
		Mockito.when(mockImageProvider.supportsMimeType(Mockito.anyString()))
				.thenReturn(true);
		// setting imageProvider TO return bigger than normal resolution image
		BufferedImage mockImage = Mockito.mock(BufferedImage.class);
		Mockito.when(mockImage.getHeight()).thenReturn(
				RegisterServlet.AVATAR_MAX_HEIGHT + 1);
		Mockito.when(mockImage.getWidth()).thenReturn(
				RegisterServlet.AVATAR_MAX_WIDTH);

		Mockito.when(mockImageProvider.read(mockPart.getInputStream()))
				.thenReturn(mockImage);

		// mocking resized image
		BufferedImage mockImageResized = Mockito.mock(BufferedImage.class);
		Mockito.when(
				mockImageProvider.resizeImage(Mockito.eq(mockImage),
						Mockito.anyInt(), Mockito.anyInt())).thenReturn(
				mockImageResized);

		testServlet.doPost(mockRequest, mockResponse);

		Mockito.verify(mockImageProvider).write(Mockito.eq(mockImageResized),
				Mockito.anyString());

		Mockito.verify(mockResponse).sendRedirect(
				fakeContextPath + RegisterServlet.REDIRECT_REGISTER_SUCCESS);
	}
	
	@Test
	public void testDoPostRegisterAvatarIgnoredZeroLength()
			throws IllegalStateException, IOException, ServletException {
		configureMockRequestParams(fakeCapcha, fakeStringParam, fakeEmailParam,
				fakeStringParam, fakeStringParam, fakeStringParam,
				fakeStringParam);

		Mockito.when(mockPart.getSize()).thenReturn(0L);
		Mockito.when(
				mockRequest.getPart(RegisterServlet.REGISTER_FORM_AVATAR_PARAM))
				.thenReturn(mockPart);

		Mockito.when(mockCapchaProvider.isCapchaExpired(mockCapcha))
				.thenReturn(false);

		Mockito.when(mockUserService.userExists(fakeEmailParam)).thenReturn(
				false);

		testServlet.doPost(mockRequest, mockResponse);

		Mockito.verify(mockImageProvider, Mockito.never()).read(mockPart.getInputStream());

		Mockito.verify(mockResponse).sendRedirect(
				fakeContextPath + RegisterServlet.REDIRECT_REGISTER_SUCCESS);
	}
}
