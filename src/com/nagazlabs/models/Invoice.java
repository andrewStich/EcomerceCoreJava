package com.nagazlabs.models;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Invoice {

	private int id;
	private int userId;
	private List<String> items;
	private float total;
	private String timeStamp;

	public Invoice() {
		SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy H:m:s aa");
		this.timeStamp = df.format(new Date());
	}

	public Invoice(int id, int userId, List<String> items, float total) {
		super();
		SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy H:mm:ss aa");
		this.id = id;
		this.userId = userId;
		this.items = items;
		this.total = total;
		this.timeStamp = df.format(LocalDateTime.now());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", userId=" + userId + ", items=" + items + ", total=" + total + ", timeStamp="
				+ timeStamp + "]";
	}

}
