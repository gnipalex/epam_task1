package com.epam.hnyp.task9.dao;

public enum ProductSortMode {
	NAME_ASC("name ascending", true, "products.name"),
	NAME_DESC("name descending", false, "products.name"),
	PRICE_ASC("price ascending", true, "products.price"),
	PRICE_DESC("price descending", false, "products.price"), 
	VENDOR_ASC("vendor ascending", true, "vendors.name"),
	VENDOR_DESC("vendor descending", false, "vendors.name");

	private String name;
	private String sqlFieldName;
	private boolean ascending;

	private ProductSortMode(String name, boolean ascending, String sqlFieldName) {
		this.name = name;
		this.sqlFieldName = sqlFieldName;
		this.ascending = ascending;
	}

	public String getName() {
		return name;
	}

	public String getSqlFieldName() {
		return sqlFieldName;
	}
	
	public boolean isAscending() {
		return ascending;
	}
}
