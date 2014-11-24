package com.epam.hnyp.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.model.User;
import com.epam.hnyp.shop.util.img.ImageInfo;
import com.epam.hnyp.shop.util.img.ImgProvider;

public class AvatarDrawerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ImgProvider avatarProvider;
	
	@Override
	public void init() throws ServletException {
		avatarProvider = (ImgProvider)getServletContext().getAttribute(ContextInitializer.INIT_AVATAR_PROVIDER_KEY);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute(LoginServlet.SESSION_AUT_USER_KEY);
		if (user != null) {
			if (user.getAvatarFile() == null || user.getAvatarFile().isEmpty()) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				ImageInfo imgInfo = avatarProvider.read(user.getAvatarFile());
				resp.setContentType(imgInfo.getMimeType());
				avatarProvider.write(imgInfo.getImage(), resp.getOutputStream());
			}	
		} else {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

}
