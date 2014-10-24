package com.epam.hnyp.task9.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.hnyp.task9.Constants;
import com.epam.hnyp.task9.util.Capcha;


public class CapchaDrawerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Capcha capcha = (Capcha)session.getAttribute(Constants.SESSION_CURRENT_CAPCHA_KEY);
		session.removeAttribute(Constants.SESSION_CURRENT_CAPCHA_KEY);
		if (capcha != null) {
			response.setContentType(capcha.getMimeType());
			capcha.drawCapcha(response.getOutputStream(), Constants.CAPCHA_PICTURE_HEIGHT,
					Constants.CAPCHA_PICTURE_WIDTH, Constants.CAPCHA_PICTURE_FONT_SZ);
		}
	}

}
