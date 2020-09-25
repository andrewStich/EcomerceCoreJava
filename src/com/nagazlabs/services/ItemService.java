package com.nagazlabs.services;

import java.util.List;

import com.nagazlabs.models.Item;
import com.nagazlabs.mongo.ItemDB;

public class ItemService {

	private static ItemDB db = new ItemDB();
	
	public static Item getItem(String code) {
		return db.getItemByItemCode(code);
	}
	
	public static List<Item> getItemList() {
		return db.getAllItems();
	}
	
	public static boolean itemExists(String code) {
		if(db.getItemByItemCode(code) != null) {
			return true;
		}
		return false;
	}
}
