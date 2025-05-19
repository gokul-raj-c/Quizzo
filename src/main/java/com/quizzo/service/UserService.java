package com.quizzo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.quizzo.db.MongoConnection;

public class UserService {

    private final MongoCollection<Document> userCollection;

    public UserService() {
        MongoDatabase db = MongoConnection.getDatabase();
        userCollection = db.getCollection("users");
    }

    // User registration
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

    // User login
    public boolean login(String email, String password) {
        Document doc = userCollection.find(new Document("email", email)).first();
        return doc != null && doc.getString("password").equals(password);
    }

    // ✅ Get all users for admin viewing
    public List<UserInfo> getAllUsers() {
        List<UserInfo> users = new ArrayList<>();
        try (MongoCursor<Document> cursor = userCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String name = doc.getString("name");
                String email = doc.getString("email");
                users.add(new UserInfo(name, email));
            }
        }
        return users;
    }

    // ✅ DTO to hold user info for GUI table
    public static class UserInfo {
        private final String name;
        private final String email;

        public UserInfo(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}
