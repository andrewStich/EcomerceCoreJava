package com.nagazlabs.models;

public class Item {

	private String itemCode;
	private String name;
	private float price;

	public Item() {
	}

	public Item(String itemCode, String name, float price) {
		super();
		this.itemCode = itemCode;
		this.name = name;
		this.price = price;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "\t" + name + "\t" + itemCode + "\t" + price;
	}

}
