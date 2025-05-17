package com.quizzo.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.quizzo.Main;

public class QuizStartPanel extends JPanel {
    public QuizStartPanel(Main main) {
        setLayout(null);
        setBackground(new java.awt.Color(30, 30, 30));

        JLabel label = new JLabel("Enter Quiz Topic ID:");
        label.setBounds(200, 200, 300, 30);
        label.setFont(new java.awt.Font("Segoe UI", 0, 24));
        label.setForeground(java.awt.Color.WHITE);
        add(label);

        JTextField topicIdField = new JTextField();
        topicIdField.setBounds(200, 250, 300, 40);
        add(topicIdField);

        JButton startQuizBtn = new JButton("Start Quiz");
        startQuizBtn.setBounds(270, 310, 150, 40);
        add(startQuizBtn);
        JButton exitBtn = new JButton("Home");
exitBtn.setBounds(650, 20, 100, 30);
exitBtn.addActionListener(e -> main.setWelcome());
add(exitBtn);


        startQuizBtn.addActionListener(e -> {
            String topicId = topicIdField.getText();
            main.startQuiz(topicId);
        });
    }
}
