// UserService.java
package com.quizzo.service;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.quizzo.db.MongoConnection;

public class UserService {
    private final MongoCollection<Document> userCollection;

    public UserService() {
        MongoDatabase db = MongoConnection.getDatabase();
        userCollection = db.getCollection("users");
    }

    public boolean register(String name, String email, String password) {
        if (userCollection.find(new Document("email", email)).first() != null) {
            return false; // already exists
        }
        Document doc = new Document("name", name)
                .append("email", email)
                .append("password", password);
        userCollection.insertOne(doc);
        return true;
    }

    public boolean login(String email, String password) {
        Document doc = userCollection.find(new Document("email", email)).first();
        return doc != null && doc.getString("password").equals(password);
    }
}
