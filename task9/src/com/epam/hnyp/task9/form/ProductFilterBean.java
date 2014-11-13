package com.epam.hnyp.task9.form;

import java.util.List;

import com.epam.hnyp.task9.dao.ProductSortMode;

public class ProductFilterBean {
	private String name;
	private Integer priceLow;
	private Integer priceHigh;
	private Integer itemsOnPage;
	private Integer from;
	private Integer to;
	private Integer totalCount;
	
	private List<Integer> categoryIds;
	private List<Integer> manufacturerIds;
	
	private ProductSortMode sortMode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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
}
