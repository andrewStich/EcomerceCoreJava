package com.nagazlabs.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.nagazlabs.models.Item;
import com.nagazlabs.mongo.InvoiceDB;
import com.nagazlabs.mongo.ItemDB;
import com.nagazlabs.mongo.UserDB;

public class DBUtil {

	private static UserDB userDB = new UserDB();
	private static ItemDB itemDB = new ItemDB();
	private static InvoiceDB invDB = new InvoiceDB();
	
	public static int getMaxId(String dbType) {
		if(dbType.equals("user")) {
			return userDB.getMaxId();
		} else if(dbType.equals("item")) {
			return itemDB.getMaxId();
		} else if(dbType.equals("invoice")) {
			return invDB.getMaxId();
		}
		
		return -1;
	}
	
	public static boolean insert(Object obj, MongoCollection<Document> coll) {
		Map<String, Object> objMap = new HashMap<String, Object>();
		Field[] allFields = obj.getClass().getDeclaredFields();
		
		try {
			for(Field field: allFields) {
				field.setAccessible(true);
				Object value = field.get(obj);
				objMap.put(field.getName(), value);
			}
			
			
			coll.insertOne(new Document(objMap));
			
			return true;
		} catch (Exception e) {
			System.out.println("Error inserting Document");
		}
		return false;
	}
	
	public static List<Item> convertInvoiceList(List<String> strList) {
		List<Item> itemList = new ArrayList<Item>();
		
		for(String i: strList) {
			itemList.add(itemDB.getItemByItemCode(i));
		}
		return itemList;
	}
}
