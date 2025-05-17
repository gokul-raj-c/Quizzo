package com.quizzo.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.quizzo.Main;

public class AdminPanel extends JPanel {
    public AdminPanel(Main main) {
        setLayout(null);
        setBackground(new java.awt.Color(30, 30, 30));

        JLabel label = new JLabel("Admin Panel");
        label.setBounds(300, 50, 300, 30);
        label.setFont(new java.awt.Font("Segoe UI", 0, 24));
        label.setForeground(java.awt.Color.WHITE);
        add(label);

        JButton addQuestionBtn = new JButton("Add Question");
        addQuestionBtn.setBounds(300, 120, 200, 50);
        add(addQuestionBtn);

        JButton exitBtn = new JButton("Home");
        exitBtn.setBounds(650, 20, 100, 30);
        exitBtn.addActionListener(e -> main.setWelcome());
        add(exitBtn);


        addQuestionBtn.addActionListener(e -> main.setAddQuestionPanel());
    }
}
