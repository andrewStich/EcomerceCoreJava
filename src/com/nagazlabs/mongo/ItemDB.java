package com.nagazlabs.mongo;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.nagazlabs.models.Item;

public class ItemDB {

	private static MongoCollection<Document> coll = Connection.getItemsCollection();
	private static Gson gson = new Gson();
	
	public boolean addItem(Item item) {
		try {
			InsertHandler.insert(item, coll);
			return true;
		} catch (Exception e) {
			System.out.println("error adding item");
		}
		return false;
	}
	
	public Item getItemByItemCode(String code) {
		try {
			Document itemDoc = coll.find(Filters.eq("itemCode", code)).first();
			Item item = gson.fromJson(itemDoc.toJson(), Item.class);
			return item;
		} catch (Exception e) {
			System.out.println("error retrieving item");
		}
		return null;
	}
	
	public boolean updateItem(Item item) {
		try {
			Document itemDoc = Document.parse(gson.toJson(item));
			coll.replaceOne(Filters.eq("itemCode", item.getItemCode()), itemDoc);
			return true;
		} catch (Exception e) {
			System.out.println("error updating Item");
		}
		return false;
	}
	
	public boolean deleteItemById(int id) {
		try {
			coll.deleteOne(Filters.eq("id", id));
			return true;
		} catch (Exception e) {
			System.out.println("Error deleting user");
		}
		return false;
	}
}
