package com.quizzo.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.quizzo.Main;
import com.quizzo.service.UserService;

public class Register extends JPanel {
    private final Main main;

    public Register(Main main) {
        this.main = main;
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setBackground(new java.awt.Color(30, 30, 30));

        JLabel title = new JLabel("User Registration");
        title.setBounds(250, 50, 300, 30);
        title.setFont(new java.awt.Font("Segoe UI", 0, 24));
        title.setForeground(java.awt.Color.WHITE);
        add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(200, 100, 100, 25);
        nameLabel.setForeground(java.awt.Color.WHITE);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(300, 100, 250, 30);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 150, 100, 25);
        emailLabel.setForeground(java.awt.Color.WHITE);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(300, 150, 250, 30);
        add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(200, 200, 100, 25);
        passLabel.setForeground(java.awt.Color.WHITE);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(300, 200, 250, 30);
        add(passField);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(300, 250, 120, 40);
        add(registerBtn);

        JButton exitBtn = new JButton("Home");
exitBtn.setBounds(650, 20, 100, 30);
exitBtn.addActionListener(e -> main.setWelcome());
add(exitBtn);


        registerBtn.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());

            UserService userService = new UserService();
            if (userService.register(name, email, password)) {
                JOptionPane.showMessageDialog(this, "Registered successfully!");
                main.setLogin(); // method in Main.java to switch back
            } else {
                JOptionPane.showMessageDialog(this, "Email already exists.");
            }
        });
    }
}
