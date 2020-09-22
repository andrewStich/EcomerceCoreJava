package com.nagazlabs.models;

import java.util.Date;
import java.util.List;

public class Invoice {

	private int id;
	private String userId;
	private List<Item> items;
	private float total;
	private Date timeStamp;

	public Invoice() {
	}

	public Invoice(int id, String userId, List<Item> items, float total, Date timeStamp) {
		super();
		this.id = id;
		this.userId = userId;
		this.items = items;
		this.total = total;
		this.timeStamp = timeStamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", userId=" + userId + ", items=" + items + ", total=" + total + ", timeStamp="
				+ timeStamp + "]";
	}

}
