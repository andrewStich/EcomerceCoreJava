package com.nagazlabs.mongo;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.nagazlabs.models.User;

public class UserDB {

	private static MongoCollection<Document> coll = Connection.getUsersCollection();
	private static Gson gson = new Gson();
	
	public boolean addUser(User user) {
		try {
			InsertHandler.insert(user, coll);
			return true;
		} catch (Exception e) {
			System.out.println("error adding user");
		}
		 return false;
	}
	
	public User getUserById(int id) {
		try {
			Document userDoc = coll.find(Filters.eq("id", id)).first();
			User user = gson.fromJson(userDoc.toJson(), User.class);
			return user;
		} catch (Exception e) {
			System.out.println("Error retrieving user");
		}
		return null;
	}
	
	public User getUserByPass(String uname, String pass) {
		try {
			Document userDoc = coll.find(Filters.and(Filters.eq("userName", uname), Filters.eq("password", pass))).first();
			User user = gson.fromJson(userDoc.toJson(), User.class);
			return user;
		} catch (Exception e) {
			System.out.println("Error retrieving user");
		}
		return null;
	}
	
	public boolean deleteUserById(int id) {
		try {
			coll.deleteOne(Filters.eq("id", id));
			return true;
		} catch (Exception e) {
			System.out.println("Error deleting user");
		}
		return false;
	}
}
