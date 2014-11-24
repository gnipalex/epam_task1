package com.epam.hnyp.shop.customtag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.hnyp.shop.form.ProductFilterBean;
import com.epam.hnyp.shop.servlet.ProductsServlet;

public class PrepareUrlTag extends SimpleTagSupport {
	private ProductFilterBean filterBean;
	private Integer pageNum;
	private String url;
	
	@Override
	public void doTag() throws JspException, IOException {
		List<String> parts = new ArrayList<>();
		if (filterBean.getCategoryIds() != null) {
			for (Integer i : filterBean.getCategoryIds()) {
				parts.add(ProductsServlet.FORM_CATEGORIES_PARAM + "=" + i);
			}
		}
		if (filterBean.getManufacturerIds() != null) {
			for (Integer i : filterBean.getManufacturerIds()) {
				parts.add(ProductsServlet.FORM_MANUFACTURES_PARAM + "=" + i);
			}
		}
//		if (filterBean.getItemsOnPage() != null) {
//			parts.add(ProductsServlet.FORM_ELEMENTS_ON_PAGE_PARAM + "=" + 
//					filterBean.getItemsOnPage());
//		}
		if (filterBean.getElementsOnPage() != null) {
			parts.add(ProductsServlet.FORM_ELEMENTS_ON_PAGE_PARAM + "=" + filterBean.getElementsOnPage());
		}
		if (filterBean.getPriceHigh() != null) {
			parts.add(ProductsServlet.FORM_PRICE_HIGH_PARAM + "=" + 
					filterBean.getPriceHigh());
		}
		if (filterBean.getPriceLow() != null) {
			parts.add(ProductsServlet.FORM_PRICE_LOW_PARAM + "=" + 
					filterBean.getPriceLow());
		}
		if (filterBean.getSortMode() != null) {
			parts.add(ProductsServlet.FORM_SORT_BY_PARAM + "=" + 
					filterBean.getSortMode());
		}
		if (pageNum != null) {
			parts.add(ProductsServlet.FORM_PAGE_NUMBER_PARAM + "=" + pageNum);
		}
		JspWriter writer = getJspContext().getOut();
		if (url != null) {
			writer.write(url);
		}
		writer.write('?');
		for (int i=0; i<parts.size(); i++) {
			writer.write(parts.get(i));
			if (i < parts.size() - 1) {
				writer.write('&');
			}
		}
	}

	public ProductFilterBean getFilterBean() {
		return filterBean;
	}

	public void setFilterBean(ProductFilterBean filterBean) {
		this.filterBean = filterBean;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
