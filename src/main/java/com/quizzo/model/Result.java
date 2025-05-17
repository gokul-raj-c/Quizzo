
package com.quizzo.model;

import java.util.List;

public class Result {
    private String userId;
    private String topicId;
    private int score;
    private List<String> userAnswers;
    private List<String> correctAnswers;

    public Result(String userId, String topicId, int score, List<String> userAnswers, List<String> correctAnswers) {
        this.userId = userId;
        this.topicId = topicId;
        this.score = score;
        this.userAnswers = userAnswers;
        this.correctAnswers = correctAnswers;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
