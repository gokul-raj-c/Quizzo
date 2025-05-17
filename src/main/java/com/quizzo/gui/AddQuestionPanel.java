package com.quizzo.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.quizzo.Main;
import com.quizzo.model.Question;
import com.quizzo.service.AdminService;

public class AddQuestionPanel extends JPanel {
    private final Main main;
    private String topicId;
    private JTextField questionField;
    private JTextField[] optionFields;
    private JTextField correctField;
    private JButton nextBtn;
    private JButton finishBtn;
    private JButton exitBtn;
    private JLabel idLabel;

    public AddQuestionPanel(Main main) {
        this.main = main;
        initPanel();
    }

    private void initPanel() {
        setLayout(null);
        setBackground(new java.awt.Color(30, 30, 30));

        // Generate unique topic ID
        topicId = UUID.randomUUID().toString().substring(0, 8);
        idLabel = new JLabel("Topic ID: " + topicId);
        idLabel.setBounds(150, 20, 300, 25);
        idLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        idLabel.setForeground(java.awt.Color.ORANGE);
        add(idLabel);

        // Exit button
        exitBtn = new JButton("Home");
        exitBtn.setBounds(650, 20, 100, 30);
        exitBtn.addActionListener(e -> main.setWelcome());
        add(exitBtn);

        JLabel title = new JLabel("Add Questions for Topic");
        title.setBounds(250, 50, 400, 30);
        title.setFont(new java.awt.Font("Segoe UI", 0, 24));
        title.setForeground(java.awt.Color.WHITE);
        add(title);

        // Question field
        JLabel qLabel = new JLabel("Question:");
        qLabel.setBounds(100, 100, 100, 25);
        qLabel.setForeground(java.awt.Color.WHITE);
        add(qLabel);
        questionField = new JTextField();
        questionField.setBounds(200, 100, 500, 30);
        add(questionField);

        // Options
        optionFields = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            JLabel optLabel = new JLabel("Option " + (i+1) + ":");
            optLabel.setBounds(100, 150 + i*50, 100, 25);
            optLabel.setForeground(java.awt.Color.WHITE);
            add(optLabel);

            optionFields[i] = new JTextField();
            optionFields[i].setBounds(200, 150 + i*50, 400, 30);
            add(optionFields[i]);
        }

        // Correct answer
        JLabel cLabel = new JLabel("Correct Answer:");
        cLabel.setBounds(100, 350, 120, 25);
        cLabel.setForeground(java.awt.Color.WHITE);
        add(cLabel);
        correctField = new JTextField();
        correctField.setBounds(230, 350, 200, 30);
        add(correctField);

        // Next Question button
        nextBtn = new JButton("Next Question");
        nextBtn.setBounds(200, 400, 160, 40);
        add(nextBtn);
        nextBtn.addActionListener(e -> saveAndClear());

        // Finish button
        finishBtn = new JButton("Finish Topic");
        finishBtn.setBounds(400, 400, 160, 40);
        add(finishBtn);
        finishBtn.addActionListener(e -> {
            saveAndClear();
            JOptionPane.showMessageDialog(this, "Topic created with ID: " + topicId);
            main.setAdminPanel();
        });
    }

    private void saveAndClear() {
        String qText = questionField.getText().trim();
        String correct = correctField.getText().trim();
        List<String> opts = new ArrayList<>();
        for (JTextField f : optionFields) {
            opts.add(f.getText().trim());
        }
        if (qText.isEmpty() || correct.isEmpty() || opts.stream().anyMatch(String::isEmpty)) {
            JOptionPane.showMessageDialog(this, "Please fill in question, all options, and correct answer.");
            return;
        }
        Question q = new Question(topicId, qText, opts, correct);
        new AdminService().addQuestion(q);

        // Clear for next question
        questionField.setText("");
        for (JTextField f : optionFields) f.setText("");
        correctField.setText("");
    }
}