package com.epam.hnyp.task9.form;

import java.util.List;


public class ProductFilterBean {	
	private Double priceLow;
	private Double priceHigh;
	
	private List<Integer> categoryIds;
	private List<Integer> manufacturerIds;
	private ProductSortMode sortMode;
	private ProductElementsOnPageMode elementsOnPage;
	
	private Integer currentPage;
	private Integer pagesCount;

	public Double getPriceLow() {
		return priceLow;
	}

	public void setPriceLow(Double priceLow) {
		this.priceLow = priceLow;
	}

	public Double getPriceHigh() {
		return priceHigh;
	}

	public void setPriceHigh(Double priceHigh) {
		this.priceHigh = priceHigh;
	}

	public List<Integer> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Integer> categoryIds) {
		this.categoryIds = categoryIds;
	}
	
	public ProductSortMode getSortMode() {
		return sortMode;
	}

	public ProductElementsOnPageMode getElementsOnPage() {
		return elementsOnPage;
	}

	public void setElementsOnPage(ProductElementsOnPageMode elementsOnPage) {
		this.elementsOnPage = elementsOnPage;
	}

	public void setSortMode(ProductSortMode sortMode) {
		this.sortMode = sortMode;
	}

	public List<Integer> getManufacturerIds() {
		return manufacturerIds;
	}

	public void setManufacturerIds(List<Integer> manufacturerIds) {
		this.manufacturerIds = manufacturerIds;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(Integer pagesCount) {
		this.pagesCount = pagesCount;
	}
}
