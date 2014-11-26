package com.epam.hnyp.shop.model;

public class OrderItem {
	private int id;
	private int orderId;
	private int productId;
	private long actualPrice;
	private int count;
	
	private Product product;
	
	public OrderItem(int id, int orderId, int productId, long actualPrice,
			int count) {
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.actualPrice = actualPrice;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getProductId() {
		return productId;
	}

	public long getActualPrice() {
		return actualPrice;
	}

	public int getCount() {
		return count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
