package com.epam.hnyp.task9.form;

public enum ProductSortMode {
	NAME_ASC("name ascending", true, "p.name"),
	NAME_DESC("name descending", false, "p.name"),
	PRICE_ASC("price ascending", true, "p.price"),
	PRICE_DESC("price descending", false, "p.price"), 
	MANUFACTURER_ASC("manufacturers ascending", true, "m.name"),
	MANUFACTURER_DESC("manufacturers descending", false, "m.name");

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
