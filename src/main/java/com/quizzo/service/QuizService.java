package com.quizzo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.quizzo.db.MongoConnection;
import com.quizzo.model.Question;

public class QuizService {
    private final MongoCollection<Document> questionsCol;
    private final MongoCollection<Document> resultsCol;

    public QuizService() {
        MongoDatabase db = MongoConnection.getDatabase();
        questionsCol = db.getCollection("questions");
        resultsCol   = db.getCollection("results");
    }

    public List<Question> getQuestionsByTopic(String topicId) {
        FindIterable<Document> docs = questionsCol.find(new Document("topicId", topicId));
        return StreamSupport.stream(docs.spliterator(), false)
            .map(d -> new Question(
                d.getString("topicId"),
                d.getString("question"),
                d.getList("options", String.class),
                d.getString("correctAnswer")
            ))
            .collect(Collectors.toList());
    }

    public void saveResult(String userEmail, String topicId, int score, int total) {
        Document doc = new Document("userEmail", userEmail)
            .append("topicId", topicId)
            .append("score", score)
            .append("total", total)
            .append("timestamp", java.time.Instant.now().toString());
        resultsCol.insertOne(doc);
    }
}
