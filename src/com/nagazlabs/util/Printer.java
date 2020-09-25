package com.nagazlabs.util;

import java.util.List;

import com.nagazlabs.models.Invoice;
import com.nagazlabs.models.Item;
import com.nagazlabs.services.ItemService;

public class Printer {

	public static void title( ) {
		System.out.println("\n\tStandalone Ecomerce App");
	}
	
	public static void bars() {
		System.out.println("+===============================================+");
	}
	
	public static void mainMenu() {
		bars();
		System.out.println("|\t1. REGISTER\t\t\t\t|");
		System.out.println("|\t2. LOGIN\t\t\t\t|");
		System.out.println("|\t3. BUY AN ITEM\t\t\t\t|");
		System.out.println("|\t4. REPLACE AN ITEM\t\t\t|");
		System.out.println("|\t5. VIEW ORDERS\t\t\t\t|");
		System.out.println("|\t6. EXIT\t\t\t\t\t|");
		bars();
	}
	
	public static void invoice(String uname, Invoice invoice) {
		List<Item> items = DBUtil.convertInvoiceList(invoice.getItems());
		bars();
		System.out.println("| Customer: " + uname + "\t\t\t\t|");
		System.out.println("| Order Date: " + invoice.getTimeStamp().toString() + "\t\t|");
		System.out.println("| Invoice #: " + invoice.getId() + "\t\t\t\t\t|");
		System.out.println("|\tItems\tCode\tPrice\t\t\t|");
		for(Item item: items) {
			System.out.println("|" + item.toString() + "\t\t\t|");
		}
		System.out.println("|\t\t\t\t\t\t|");
		System.out.println("|\tTotal = " + invoice.getTotal() + "\t\t\t\t|");
		bars();
	}
	
	public static void itemList() {
		List<Item> items = ItemService.getItemList();
		
		System.out.println("\n\n\n\tItem List");
		bars();
		System.out.println("\tItem\tCode\tPrice");
		for(Item i: items) {
			System.out.println(i.toString());
		}
		bars();
	}
	
	public static void login() {
		System.out.println("\n\n\n\n\tLogin Menu");
		bars();
	}
	
	public static void register() {
		System.out.println("\n\n\n\n\tUser Registration");
		bars();
	}
}
