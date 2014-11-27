package com.epam.hnyp.shop.model;

import java.util.Collection;
import java.util.Date;

public class Order {
	private int id;
	private OrderStatus status;
	private String description;
	private Date date;
	private int userId;
	private PayType payType;
	private DeliveryType deliveryType;
	private String address;

	private Collection<OrderItem> items;

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

	public Collection<OrderItem> getItems() {
		return items;
	}

	public void setItems(final Collection<OrderItem> items) {
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
	
}
