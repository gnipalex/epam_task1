package com.epam.hnyp.shop.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 1691415738115382419L;
	
	private int id;
	private OrderStatus status;
	private String description;
	private Date date;
	private int userId;
	private PayType payType;
	private String creditCardCode;
	private DeliveryType deliveryType;
	private String address;

	//not persistent
	private List<OrderItem> items;
	private int itemCount;
	private long totalPrice;
	
	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(final OrderStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(final int userId) {
		this.userId = userId;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public DeliveryType getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCreditCardCode() {
		return creditCardCode;
	}

	public void setCreditCardCode(String creditCardCode) {
		this.creditCardCode = creditCardCode;
	}
	
}
