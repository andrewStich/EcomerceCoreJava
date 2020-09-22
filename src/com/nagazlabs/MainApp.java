package com.nagazlabs;

import com.nagazlabs.mongo.UserDB;

public class MainApp {

	private static UserDB userDB = new UserDB();
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		System.out.println(userDB.getUserByPass("me", "pass").toString());
		
	}
}
