package com.nagazlabs.mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connection {

	private static MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://admin:admin@mycluster1-shard-00-00-shmwc.mongodb.net:27017,mycluster1-shard-00-01-shmwc.mongodb.net:27017,mycluster1-shard-00-02-shmwc.mongodb.net:27017/test?ssl=true&replicaSet=MyCluster1-shard-0&authSource=admin&retryWrites=true&w=majority"));
	private static MongoDatabase db = mongo.getDatabase("store-front");
	
	public static MongoCollection<Document> getUsersCollection() {
		return db.getCollection("users");
	}
	
	public static MongoCollection<Document> getItemsCollection() {
		return db.getCollection("items");
	}
	
	public static MongoCollection<Document> getInvoicesCollection() {
		return db.getCollection("invoices");
	}
}
