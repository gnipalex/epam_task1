package com.epam.hnyp.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.model.Product;
import com.epam.hnyp.shop.service.ProductService;
import com.epam.hnyp.shop.util.img.ImageInfo;
import com.epam.hnyp.shop.util.img.ImgProvider;

public class ProductImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(ProductImageServlet.class);
	
	public static final String PRODUCT_ID_PARAM = "prodId";
	public static final int CACHE_TIME_SECONDS = 600;
	
	private ImgProvider prodImageProvider;
	private ProductService productsService;
	
	@Override
	public void init() throws ServletException {
		prodImageProvider = (ImgProvider)getServletContext().getAttribute(ContextInitializer.INIT_PRODUCT_IMAGES_PROVIDER_KEY);
		productsService = (ProductService)getServletContext().getAttribute(ContextInitializer.INIT_PRODUCTS_SERVICE_KEY);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String prodId = req.getParameter(PRODUCT_ID_PARAM);
		int id = 0;
		if (prodId != null && !prodId.isEmpty()) {
			try {
				id = Integer.parseInt(prodId);
			} catch (NumberFormatException e) {
				LOG.info("id is not specified");
				return;
			}
		}
		Product product = productsService.getProduct(id);
		if (product != null && product.getImgFile() != null) {
			try {
				ImageInfo prodInfo = prodImageProvider.read(product.getImgFile());
				resp.setContentType(prodInfo.getMimeType());
				resp.setHeader("Cache-Control", "public, max-age=" + CACHE_TIME_SECONDS);
				prodImageProvider.write(prodInfo.getImage(), resp.getOutputStream());
			} catch (IOException e) {
				LOG.error("image file not found " + product.getImgFile());
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
