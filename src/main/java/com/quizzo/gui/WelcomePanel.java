package com.quizzo.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.quizzo.Main;

public class WelcomePanel extends JPanel {
    public WelcomePanel(Main main) {
        setLayout(null);
        setBackground(new java.awt.Color(30, 30, 30));

        // Heading
        JLabel title = new JLabel("Quizzo");
        title.setBounds(250, 80, 300, 60);
        title.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 48));
        title.setForeground(java.awt.Color.ORANGE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        // Sub‑heading / details
        JLabel subtitle = new JLabel("<html><div style='text-align:center;'>"
            + "Welcome to Quizzo!<br>"
            + "Test your knowledge, Track your progress!<br>"
            + "</div></html>");
        subtitle.setBounds(150, 160, 500, 80);
        subtitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));
        subtitle.setForeground(java.awt.Color.LIGHT_GRAY);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(subtitle);

        // Login button
        JButton loginBtn = new JButton("Log In");
        loginBtn.setBounds(275, 280, 120, 40);
        loginBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        loginBtn.addActionListener(e -> main.setLogin());
        add(loginBtn);

        // Register button
        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(415, 280, 120, 40);
        registerBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        registerBtn.addActionListener(e -> main.setRegister());
        add(registerBtn);
    }
}
