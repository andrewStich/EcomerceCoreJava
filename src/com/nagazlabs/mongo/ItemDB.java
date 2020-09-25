package com.nagazlabs.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.nagazlabs.models.Item;
import com.nagazlabs.util.DBUtil;

public class ItemDB {

	private static MongoCollection<Document> coll = Connection.getItemsCollection();
	private static Gson gson = new Gson();
	
	public boolean addItem(Item item) {
		try {
			DBUtil.insert(item, coll);
			return true;
		} catch (Exception e) {
			//System.out.println("error adding item");
		}
		return false;
	}
	
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		try {
			MongoCursor<Document> cur = coll.find().cursor();
			while(cur.hasNext()) {
				Item item = gson.fromJson(cur.next().toJson(), Item.class);
				items.add(item);
			}
			return items;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
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
	
	public int getMaxId() {
		try {
			MongoCursor<Document> cur = coll.find().sort(new BasicDBObject("id", -1)).limit(1).iterator();
			while(cur.hasNext()) {
				return (int) cur.next().get("id");
			}
		} catch (Exception e) {
			
		}
		return 0;
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
