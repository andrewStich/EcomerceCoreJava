package com.nagazlabs.services;

import com.nagazlabs.models.User;
import com.nagazlabs.mongo.UserDB;

public class UserService {

	private static UserDB db = new UserDB();
	
	public static User login(String uname, String pass) {
		User user = db.getUserByPass(uname, pass);
		if(user != null) {
			return user;
		}
		
		System.out.println("Invalid username or password!");
		return null;
	}
	
	public static boolean register(User user) {
		if(db.getUserByUserName(user.getUserName()) != null) {
			System.out.println("\nUser already exists!\n");
			return false;
		}
		
		db.addUser(user);
		return true;
	}
}
