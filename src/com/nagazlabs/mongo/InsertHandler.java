package com.nagazlabs.mongo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class InsertHandler {

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
}
