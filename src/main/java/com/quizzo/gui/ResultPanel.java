package com.quizzo.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.quizzo.Main;

public class ResultPanel extends JPanel {
    public ResultPanel(Main main, int score, int total) {
        setLayout(null);
        setBackground(new java.awt.Color(30, 30, 30));

        JLabel resultLabel = new JLabel("Quiz Completed!");
        resultLabel.setBounds(250, 100, 300, 30);
        resultLabel.setFont(new java.awt.Font("Segoe UI", 0, 24));
        resultLabel.setForeground(java.awt.Color.WHITE);
        add(resultLabel);

        JLabel scoreLabel = new JLabel("Your Score: " + score + " out of " + total);
        scoreLabel.setBounds(250, 160, 300, 30);
        scoreLabel.setFont(new java.awt.Font("Segoe UI", 0, 20));
        scoreLabel.setForeground(java.awt.Color.GREEN);
        add(scoreLabel);

        JButton homeButton = new JButton("Log Out");
        homeButton.setBounds(280, 220, 150, 40);
        add(homeButton);

        homeButton.addActionListener(e -> main.setLogin());
    }
}
