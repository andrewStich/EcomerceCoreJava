package com.nagazlabs;

import com.nagazlabs.models.User;
import com.nagazlabs.util.Menus;

public class MainApp {

	private static User activeUser = null;
	public static boolean exit = false;
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		while(true) {
			int choice = Menus.mainMenu();
			
			switch (choice) {
			case 1: {
				activeUser = Menus.registration();
				break;
			}
			case 2: {
				activeUser = Menus.Login();
				break;
			}
			case 3: {
				if(activeUser == null) {
					System.out.println("\n\nYou must login or create an account first!");
				} else {
					Menus.buyMenu(activeUser);
				}
				break;
			}
			case 4: {
				if(activeUser == null) {
					System.out.println("\n\nYou must login or create an account first!");
				} else {
					Menus.replace(activeUser);
				}
				break;
			}
			case 5: {
				if(activeUser == null) {
					System.out.println("\n\nYou must login or create an account first!");
				} else {
					Menus.showInvoices(activeUser);
				}
				break;
			}
			case 6: {
				exit = true;
				break;
			}
			default:
				System.out.println();
				break;
			}
			
			if(exit) {
				break;
			}
		}
		
		System.out.println("Thanks for shopping!");
		
		
	}
}
