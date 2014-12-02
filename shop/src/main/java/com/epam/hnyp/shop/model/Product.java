package com.epam.hnyp.shop.model;

import java.io.Serializable;


public class Product implements Serializable {
	
	private static final long serialVersionUID = 163293855381109658L;
	
	
	private int id;
	private int categoryId;
	private String name;
	private int manufacturerId;
	private long price;
	private double weight;
	private String description;
	private String imgFile;
	private boolean available;
	
	private Category category;
	private Manufacturer manufacturer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		if (price < 0) {
			throw new IllegalArgumentException();
		}
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		if (weight < 0) {
			throw new IllegalArgumentException();
		}
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgFile() {
		return imgFile;
	}
	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

}
