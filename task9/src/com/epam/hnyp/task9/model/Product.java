package com.epam.hnyp.task9.model;


public class Product {
	private int id;
	private int categoryId;
	private String name;
	private int vendorId;
	private long price;
	private double weight;
	private String description;
	private String imgFile;
	private boolean available;
	private Category category;
	private Manufacturer vendor;
	
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
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public Manufacturer getVendor() {
		return vendor;
	}
	public void setVendor(Manufacturer vendor) {
		this.vendor = vendor;
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
