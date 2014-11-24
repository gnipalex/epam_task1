package com.epam.hnyp.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.hnyp.shop.capcha.Capcha;
import com.epam.hnyp.shop.capcha.provider.AbstractCapchaProvider;
import com.epam.hnyp.shop.capcha.provider.AbstractCapchaProvider.CapchaUidMissedException;
import com.epam.hnyp.shop.listener.ContextInitializer;


public class CapchaDrawerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CapchaDrawerServlet.class);
			
	public static final int CAPCHA_PICTURE_WIDTH = 200;
	public static final int CAPCHA_PICTURE_HEIGHT = 100;
	public static final int CAPCHA_PICTURE_FONT_SZ = 40;
	
	private AbstractCapchaProvider capchaProvider;
	
	@Override
	public void init() throws ServletException {
		capchaProvider = (AbstractCapchaProvider)getServletContext().getAttribute(ContextInitializer.INIT_CAPCHA_PROVIDER_KEY);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Capcha capcha = null;
		try {
			capcha = capchaProvider.getCapcha(request);
		} catch (CapchaUidMissedException e) {
			LOG.error("missed capcha uid from client", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		if (capcha != null) {
			response.setContentType(capcha.getMimeType());
			capcha.drawCapcha(response.getOutputStream(), CAPCHA_PICTURE_HEIGHT,
					CAPCHA_PICTURE_WIDTH, CAPCHA_PICTURE_FONT_SZ);
			if (LOG.isInfoEnabled()) {
				LOG.info("capcha successfuly drawn");
			}
		} else {
			LOG.error("capcha to draw not found");
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	public AbstractCapchaProvider getCapchaProvider() {
		return capchaProvider;
	}

	public void setCapchaProvider(AbstractCapchaProvider capchaProvider) {
		this.capchaProvider = capchaProvider;
	}
	
}
