package com.epam.hnyp.task9.form;

public enum ProductElementsOnPageMode {
	FIVE(5), TEN(10), TWENTY(20);
	
	private final int count;

	private ProductElementsOnPageMode(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
}
