package com.epam.hnyp.task9.form;

import java.util.List;


public class ProductFilterBean {	
	private Integer priceLow;
	private Integer priceHigh;
	private List<Integer> categoryIds;
	private List<Integer> manufacturerIds;
	private ProductSortMode sortMode;
	
	private Integer itemsOnPage;
	private Integer currentPage;
	private Integer pagesCount;

	public Integer getPriceLow() {
		return priceLow;
	}

	public void setPriceLow(Integer priceLow) {
		this.priceLow = priceLow;
	}

	public Integer getPriceHigh() {
		return priceHigh;
	}

	public void setPriceHigh(Integer priceHigh) {
		this.priceHigh = priceHigh;
	}

	public List<Integer> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Integer> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public Integer getItemsOnPage() {
		return itemsOnPage;
	}

	public void setItemsOnPage(Integer itemsOnPage) {
		this.itemsOnPage = itemsOnPage;
	}

	public ProductSortMode getSortMode() {
		return sortMode;
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
