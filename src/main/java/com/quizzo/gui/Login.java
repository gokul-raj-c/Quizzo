
package com.quizzo.gui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.quizzo.Main;
import com.quizzo.service.AuthService;

public class Login extends JPanel {

    private final Main main;
    private JTextField emailField;
    private JPasswordField passwordField;

    public Login(Main main) {
        this.main = main;
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setBackground(new java.awt.Color(30, 30, 30));

        JLabel titleLabel = new JLabel("Log In");
        titleLabel.setBounds(300, 80, 200, 30);
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(java.awt.Color.WHITE);
        add(titleLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 140, 100, 30);
        emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
        emailLabel.setForeground(java.awt.Color.WHITE);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(300, 140, 250, 30);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200, 200, 100, 30);
        passwordLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
        passwordLabel.setForeground(java.awt.Color.WHITE);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(300, 200, 250, 30);
        add(passwordField);

        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(300, 260, 100, 30);
        add(loginButton);
        loginButton.addActionListener(this::loginAction);

JButton exitBtn = new JButton("Home");
exitBtn.setBounds(650, 20, 100, 30);
exitBtn.addActionListener(e -> main.setWelcome());
add(exitBtn);

    }

    private void loginAction(ActionEvent evt) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password");
            return;
        }

        boolean isAuthenticated = AuthService.authenticate(email, password);
        if (isAuthenticated) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            main.setUserDashboard(email); // This method should be defined in Main.java
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password");
        }
    }
}
