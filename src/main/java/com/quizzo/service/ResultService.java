package com.quizzo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.quizzo.db.MongoConnection;

public class ResultService {

    private final MongoCollection<Document> resultCollection;
    private final MongoCollection<Document> userCollection;
    private final MongoCollection<Document> questionCollection;

    public ResultService() {
        MongoDatabase db = MongoConnection.getDatabase();
        resultCollection = db.getCollection("results");
        userCollection = db.getCollection("users");
        questionCollection = db.getCollection("questions");
    }

    // Returns a map with topicId as key and list of results (username, score, totalMarks)
    public Map<String, List<ResultWithUser>> getResultsGroupedByTopic() {
        Map<String, List<ResultWithUser>> groupedResults = new HashMap<>();

        try (MongoCursor<Document> cursor = resultCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                String topicId = doc.getString("topicId");
                int score = doc.getInteger("score", 0);
                String userEmail = doc.getString("userEmail");

                // Dynamically fetch total marks by counting questions with this topicId
                int totalMarks = getTotalQuestionsForTopic(topicId);

                ResultWithUser result = new ResultWithUser(userEmail, topicId, score, totalMarks);
                groupedResults.computeIfAbsent(topicId, k -> new ArrayList<>()).add(result);
            }
        }

        return groupedResults;
    }

    // Returns number of questions for a given topicId
    private int getTotalQuestionsForTopic(String topicId) {
        Document filter = new Document("topicId", topicId);
        return (int) questionCollection.countDocuments(filter);
    }

    // Helper class to store result + user email for GUI display
    public static class ResultWithUser {
        private final String username;
        private final String topicId;
        private final int score;
        private final int totalMarks;

        public ResultWithUser(String username, String topicId, int score, int totalMarks) {
            this.username = username;
            this.topicId = topicId;
            this.score = score;
            this.totalMarks = totalMarks;
        }

        public String getUsername() {
            return username;
        }

        public String getTopicId() {
            return topicId;
        }

        public int getScore() {
            return score;
        }

        public int getTotalMarks() {
            return totalMarks;
        }
    }
}
