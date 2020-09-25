package com.nagazlabs.services;

import java.util.List;

import com.nagazlabs.models.Invoice;
import com.nagazlabs.models.Item;
import com.nagazlabs.models.User;
import com.nagazlabs.mongo.InvoiceDB;
import com.nagazlabs.util.DBUtil;

public class InvoiceService {

	private static InvoiceDB db = new InvoiceDB();
	
	public static Invoice createInvoice(User user, List<String> cartItemCodes) {
		Invoice invoice = new Invoice();
		List<Item> cart = DBUtil.convertInvoiceList(cartItemCodes);
		float total = 0;
		
		for(Item i: cart) {
			total += i.getPrice();
		}
		
		invoice.setId(DBUtil.getMaxId("invoice") + 1);
		invoice.setUserId(user.getId());
		invoice.setItems(cartItemCodes);
		invoice.setTotal(total);
		
		db.addInvoice(invoice);
		
		return invoice;
	}
	
	public static List<Invoice> getUserInvoices(int userId) {
		return db.getUserInvoices(userId);
	}
	
	public static boolean updateInvoice(Invoice invoice) {
		return db.updateInvoice(invoice);
	}
}
