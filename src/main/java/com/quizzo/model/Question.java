package com.quizzo.model;

import java.util.List;

public class Question {
    private String topicId;
    private String question;
    private List<String> options;
    private String correctAnswer;

    // FULL constructor matching how you're building it:
    public Question(String topicId,
                    String question,
                    List<String> options,
                    String correctAnswer) {
        this.topicId = topicId;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // No-arg constructor (needed by some frameworks if you ever serialize)
    public Question() { }

    // getters and setters
    public String getTopicId() { return topicId; }
    public void setTopicId(String t) { topicId = t; }

    public String getQuestion() { return question; }
    public void setQuestion(String q) { question = q; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> o) { options = o; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String c) { correctAnswer = c; }
}
