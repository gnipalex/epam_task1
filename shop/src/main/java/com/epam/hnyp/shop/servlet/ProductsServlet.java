package com.epam.hnyp.shop.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.hnyp.shop.form.ProductElementsOnPageMode;
import com.epam.hnyp.shop.form.ProductFilterBean;
import com.epam.hnyp.shop.form.ProductSortMode;
import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.model.Category;
import com.epam.hnyp.shop.model.Manufacturer;
import com.epam.hnyp.shop.model.Product;
import com.epam.hnyp.shop.service.ProductsService;

public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(ProductsServlet.class);

	public static final ProductElementsOnPageMode DEFAULT_ELEMENTS_ON_PAGE = ProductElementsOnPageMode.FIVE;
	public static final int DEFAULT_CURRENT_PAGE = 1;
	public static final ProductSortMode DEFAULT_SORT_MODE = ProductSortMode.NAME_ASC;
	
	public static final String FORM_PRICE_LOW_PARAM = "priceLow";
	public static final String FORM_PRICE_HIGH_PARAM = "priceHigh";
	public static final String FORM_SORT_BY_PARAM = "sortBy";
	public static final String FORM_ELEMENTS_ON_PAGE_PARAM = "elementsOnPage";
	public static final String FORM_CATEGORIES_PARAM = "cat";
	public static final String FORM_MANUFACTURES_PARAM = "manuf";
	public static final String FORM_PAGE_NUMBER_PARAM = "pageNumber";

	private ProductsService productsService;

	@Override
	public void init() throws ServletException {
		this.productsService = (ProductsService) getServletContext()
				.getAttribute(ContextInitializer.INIT_PRODUCTS_SERVICE_KEY);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ProductFilterBean filter = extractProductFilter(req);
		int totalCount = productsService.getProductsCountWithoutLimit(filter);
		int pagesCount = (int)Math.ceil((double)totalCount / filter.getElementsOnPage().getCount());
		filter.setPagesCount(pagesCount);
		if (filter.getCurrentPage() > pagesCount) {
			filter.setCurrentPage(1);
		}
		
		Collection<Product> products = productsService.getProductsByFilter(filter);
		Collection<Category> categories = productsService.getAllCategories();
		Collection<Manufacturer> manufacturers = productsService.getAllManufacturers();
		
		req.setAttribute("filterBean", filter);
		req.setAttribute("products", products);
		req.setAttribute("categories", categories);
		req.setAttribute("manufacturers", manufacturers);
		req.setAttribute("sortModes", ProductSortMode.values());
		req.setAttribute("elementsOnPageModes", ProductElementsOnPageMode.values());
		
		req.getRequestDispatcher("WEB-INF/jsp/products.jsp").forward(req, resp);
	}

	private ProductFilterBean extractProductFilter(HttpServletRequest request) {
		ProductFilterBean bean = new ProductFilterBean();
		bean.setCurrentPage(DEFAULT_CURRENT_PAGE);
		bean.setElementsOnPage(DEFAULT_ELEMENTS_ON_PAGE);
		bean.setSortMode(DEFAULT_SORT_MODE);
		
		String priceLow = request.getParameter(FORM_PRICE_LOW_PARAM);
		String priceHigh = request.getParameter(FORM_PRICE_HIGH_PARAM);
		String sortBy = request.getParameter(FORM_SORT_BY_PARAM);
		String elementsOnPage = request
				.getParameter(FORM_ELEMENTS_ON_PAGE_PARAM);
		String pageNumber = request.getParameter(FORM_PAGE_NUMBER_PARAM);
		String[] categories = request.getParameterValues(FORM_CATEGORIES_PARAM);
		String[] manufacturers = request
				.getParameterValues(FORM_MANUFACTURES_PARAM);

		bean.setCategoryIds(readIntegers(categories));
		bean.setManufacturerIds(readIntegers(manufacturers));
		
		bean.setPriceHigh(readDouble(priceHigh));
		bean.setPriceLow(readDouble(priceLow));
		
		if (elementsOnPage != null) {
			try {
				bean.setElementsOnPage(ProductElementsOnPageMode.valueOf(elementsOnPage));
			} catch (IllegalArgumentException e) {}
		}
		Integer curPage = readInteger(pageNumber);
		if (curPage != null && curPage.intValue() > 0) {
			bean.setCurrentPage(curPage);
		}
		if (sortBy != null) {
			try {
				bean.setSortMode(ProductSortMode.valueOf(sortBy));
			} catch (IllegalArgumentException e) {}
		}
		
		return bean;
	}
	
	private Double readDouble(String val) {
		try {
			return Double.parseDouble(val);
		} catch (NumberFormatException | NullPointerException e) {	}
		return null;
	}
	
	private Integer readInteger(String val) {
		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {	}
		return null;
	}

	private List<Integer> readIntegers(String[] values) {
		List<Integer> list = new ArrayList<>();
		if (values != null) {
			for (String s : values) {
				try {
					list.add(Integer.parseInt(s));
				} catch (NumberFormatException e) {	}
			}
		}
		return list;
	}
}
