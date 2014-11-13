package com.epam.hnyp.task9.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hnyp.task9.form.ProductFilterBean;
import com.epam.hnyp.task9.listener.ContextInitializer;
import com.epam.hnyp.task9.service.ProductsService;

public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String FORM_PRICE_LOW_PARAM = "priceLow";
	public static final String FORM_PRICE_HIGH_PARAM = "priceHigh";
	public static final String FORM_SORT_BY_PARAM = "sortBy";
	public static final String FORM_ELEMENTS_ON_PAGE_PARAM = "elementsOnPage";
	public static final String FORM_CATEGORIES_PARAM = "cat";
	
	private ProductsService productsService; 
	
	@Override
	public void init() throws ServletException {
		this.productsService = (ProductsService)getServletContext().getAttribute(ContextInitializer.INIT_PRODUCTS_SERVICE_KEY);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	private ProductFilterBean extractProductFilter(HttpServletRequest request) {
		ProductFilterBean bean = new ProductFilterBean();
		
	}
}
