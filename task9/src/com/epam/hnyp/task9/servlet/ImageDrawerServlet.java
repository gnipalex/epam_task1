package com.epam.hnyp.task9.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hnyp.task9.listener.ContextInitializer;
import com.epam.hnyp.task9.util.img.ImgProvider;

public class ImageDrawerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ImgProvider prodImageProvider;
	
	@Override
	public void init() throws ServletException {
		prodImageProvider = (ImgProvider)getServletContext().getAttribute(ContextInitializer.INIT_PRODUCT_IMAGES_PROVIDER_KEY);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pictureFileName = req.getParameter("file");
		if (pictureFileName != null && !pictureFileName.isEmpty()) {
			req.getRequestDispatcher(prodImageProvider.getDirectoryLocation() + File.separator + pictureFileName).forward(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
