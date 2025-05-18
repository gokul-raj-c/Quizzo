// AdminService.java
package com.quizzo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.quizzo.db.MongoConnection;
import com.quizzo.model.Question;
import com.quizzo.model.Result;

public class AdminService {
    private final MongoCollection<Document> questionCollection;
    private final MongoCollection<Document> resultCollection;

    public AdminService() {
        MongoDatabase db = MongoConnection.getDatabase();
        questionCollection = db.getCollection("questions");
        resultCollection = db.getCollection("results");
    }

    public void addQuestion(Question q) {
        Document doc = new Document("topicId", q.getTopicId())
                .append("question", q.getQuestion())
                .append("options", q.getOptions())
                .append("correctAnswer", q.getCorrectAnswer());
        questionCollection.insertOne(doc);
    }

    public List<Result> getAllResults() {
        List<Result> resultList = new ArrayList<>();
        for (Document doc : resultCollection.find()) {
            String userId = doc.getString("userId");
            String topicId = doc.getString("topicId");
            int score = doc.getInteger("score", 0);
            List<String> userAnswers = (List<String>) doc.get("userAnswers");
            List<String> correctAnswers = (List<String>) doc.get("correctAnswers");

            Result result = new Result(userId, topicId, score, userAnswers, correctAnswers);
            resultList.add(result);
        }
        return resultList;
    }
}
