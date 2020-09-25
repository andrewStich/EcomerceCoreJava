package com.nagazlabs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.nagazlabs.models.Invoice;
import com.nagazlabs.models.User;
import com.nagazlabs.services.InvoiceService;
import com.nagazlabs.services.ItemService;
import com.nagazlabs.services.UserService;

public class Menus {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static int mainMenu() {
		Printer.title();
		Printer.mainMenu();
		int choice = 0;
		try {
			while(true) {
				System.out.print("Enter selection (1-6): ");
				choice = Integer.parseInt(scan.nextLine());
				if(choice < 1 || choice > 6) {
					System.out.println("Enter valid number!");
				}
				else {
					break;
				}
			}
		} catch (Exception e) {
		}
		return choice;
	}
	
	public static User Login() {
		Printer.login();
		User user = null;
		try {
			while(true) {
				System.out.print("Username: ");
				String userName = scan.nextLine();
				System.out.print("Password: ");
				String password = scan.nextLine();
				
				user = UserService.login(userName, password);
				
				if(user != null) {
					break;
				} else {
					//System.out.println("invalid credentials");
					System.out.print("Try again? (1 = yes, 0 = no): ");
					int choice = Integer.parseInt(scan.nextLine());
					
					if(choice == 0) {
						return null;
					}
				}
			}
			Printer.bars();
			System.out.println("\nLog In Successfull");
			enter();
			return user;
		} catch (Exception e) {
			System.out.println("error loggin in user (Menus.java)");
		}
		return null;
	}

	public static User registration() {
		Printer.register();
		User user = new User();
		
		try {
			while(true) {
				user.setId(DBUtil.getMaxId("user") + 1);
				System.out.print("Username: ");
				user.setUserName(scan.nextLine());
				System.out.print("Password: ");
				user.setPassword(scan.nextLine());
				System.out.print("Email: ");
				user.setEmail(scan.nextLine());
				System.out.print("Address: ");
				user.setAddress(scan.nextLine());
				
				if(UserService.register(user)) {
					break;
				}
			}
			
			System.out.println("User created Successfully");
			enter();
			return user;
			
		} catch (Exception e) {
			System.out.println("Error registering User (Menus.java)");
		}
		return null;
	}
	
	public static Invoice buyMenu(User user) throws IllegalArgumentException, IllegalAccessException {
		Invoice invoice = null;
		
		while(true) {
			List<String> cart = new ArrayList<String>();
			
			System.out.println("\tBuy Menu");
			Printer.bars();
			Printer.itemList();
			
			System.out.println("\nEnter an item code and press enter.");
			System.out.println("Do this for as many items as you would like to purchase.");
			System.out.println("type \'done\' and press enter when you're finished.");
			while(true) {
				String in = scan.nextLine();
				
				if(in.equals("done")) {
					break;
				}
				
				if(ItemService.itemExists(in)) {
					cart.add(in);
					System.out.println(in + " added to cart");
				} else {
					System.out.println("Item does not exist!");
				}
			}
			
			invoice = InvoiceService.createInvoice(user, cart);
			Printer.invoice(user.getUserName(), invoice);
			
			int choice = -1;
			while(true) {
				System.out.print("\n\nDoes your order look correct? (no = 0, yes = 1) ");
				choice = Integer.parseInt(scan.nextLine());
				if(choice == 1 || choice == 0) {
					break;
				}
			}
			if(choice == 1) {
				break;
			} else {
				System.out.println("\n\n\nLets restart then");
				cart = null;
				invoice = null;
			}
		}
		return invoice;
	}
	
	public static void replace(User user) {
		int credit = 0;
		List<Invoice> userInvoices = InvoiceService.getUserInvoices(user.getId());
		Invoice activeInvoice = null;
		
		System.out.println("\n\nList of your invoices");
		for(Invoice i: userInvoices) {
			Printer.invoice(user.getUserName(), i);
		}
		
		while(true) {
			System.out.print("\nEnter Invoice # to edit: ");
			int choice = Integer.parseInt(scan.nextLine());
			
			for(Invoice i: userInvoices) {
				if(choice == i.getId()) {
					activeInvoice = i;
				}
			}
			if(activeInvoice == null) {
				System.out.println("Please enter valid Invoice #");
				continue;
			}
			
			while(true) {
				String code = null;
				Printer.invoice(user.getUserName(), activeInvoice);
				System.out.print("Enter Item code for Item you would like to return: ");
				code = scan.nextLine();
				
				if(!ItemService.itemExists(code)) {
					System.out.println("Please enter valid item code");
					continue;
				}
				
				for(String i: activeInvoice.getItems()) {
					if(i.equals(code)) {
						activeInvoice.getItems().remove(code);
						activeInvoice.setTotal(activeInvoice.getTotal() - ItemService.getItem(code).getPrice());
						credit += ItemService.getItem(code).getPrice();
						System.out.println("Item removed");
						break;
					}
				}
				
				Printer.invoice(user.getUserName(), activeInvoice);
				InvoiceService.updateInvoice(activeInvoice);
				
				System.out.print("Would you like to return another item? (1 = yes, 0 = no) ");
				int done = Integer.parseInt(scan.nextLine());
				
				if(done == 0) {
					break;
				}
			}
			
			System.out.print("Would you like to edit another invoice? (1 = yes, 0 = no) ");
			int done = Integer.parseInt(scan.nextLine());
			
			if(done == 0) {
				break;
			}
		}
		
		System.out.println("\nYou now have $" + credit + " of store credit.");
		System.out.println("A voucher code will be sent to " + user.getEmail());
		enter();
	}
	
	public static void showInvoices(User user) {
		List<Invoice> userInvoices = InvoiceService.getUserInvoices(user.getId());
		System.out.println("\n\nList of your invoices");
		for(Invoice i: userInvoices) {
			Printer.invoice(user.getUserName(), i);
		}
		enter();
	}
	
	public static void enter() {
		System.out.print("Press Enter to Continue...");
		scan.nextLine();
	}
}
