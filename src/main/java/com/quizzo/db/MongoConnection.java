package com.quizzo.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "quizzo";

    private static MongoClient client = MongoClients.create(URI);

    public static MongoDatabase getDatabase() {
        return client.getDatabase(DB_NAME);
    }
}
