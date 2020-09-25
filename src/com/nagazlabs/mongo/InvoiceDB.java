package com.nagazlabs.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.nagazlabs.models.Invoice;
import com.nagazlabs.util.DBUtil;

public class InvoiceDB {

	private static MongoCollection<Document> coll = Connection.getInvoicesCollection();
	private static Gson gson = new Gson();
	
	public boolean addInvoice(Invoice invoice) {
		try {
			DBUtil.insert(invoice, coll);
			return true;
		} catch (Exception e) {
			System.out.println("Error creating invoice");
		}
		return false;
	}
	
	public Invoice getInvoiceById(int id) {
		try {
			Document invoiceDoc = coll.find(Filters.eq("id", id)).first();
			Invoice invoice = gson.fromJson(invoiceDoc.toJson(), Invoice.class);
			return invoice;
		} catch (Exception e) {
			System.out.println("error retrieving invoice");
		}
		return null;
	}
	
	public List<Invoice> getUserInvoices(int userId) {
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		try {
			MongoCursor<Document> cur = coll.find(Filters.eq("userId", userId)).iterator();
			
			while(cur.hasNext()) {
				Invoice invoice = gson.fromJson(cur.next().toJson(), Invoice.class);
				invoiceList.add(invoice);
			}
			return invoiceList;
		} catch (Exception e) {
			System.out.println("error retrieving user's invoices");
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
	
	public boolean updateInvoice(Invoice invoice) {
		try {
			Document invoiceDoc = Document.parse(gson.toJson(invoice));
			coll.replaceOne(Filters.eq("id", invoice.getId()), invoiceDoc);
			return true;
		} catch (Exception e) {
			System.out.println("error updating invoice");
		}
		return false;
	}
	
	public boolean deleteInvoiceById(int id) {
		try {
			coll.deleteOne(Filters.eq("id", id));
			return true;
		} catch (Exception e) {
			System.out.println("Error deleting user");
		}
		return false;
	}
}
