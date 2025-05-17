// AdminService.java
package com.quizzo.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.quizzo.db.MongoConnection;
import com.quizzo.model.Question;
import org.bson.Document;

public class AdminService {
    private final MongoCollection<Document> questionCollection;

    public AdminService() {
        MongoDatabase db = MongoConnection.getDatabase();
        questionCollection = db.getCollection("questions");
    }

    public void addQuestion(Question q) {
        Document doc = new Document("topicId", q.getTopicId())
                .append("question", q.getQuestion())
                .append("options", q.getOptions())
                .append("correctAnswer", q.getCorrectAnswer());
        questionCollection.insertOne(doc);
    }
}
