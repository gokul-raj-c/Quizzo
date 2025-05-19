package com.quizzo.gui;

import com.quizzo.Main;
import com.quizzo.service.UserService;
import com.quizzo.service.UserService.UserInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewUsersPanel extends JPanel {

    private JTable userTable;
    private DefaultTableModel tableModel;

    public ViewUsersPanel(Main main) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        // Title
        JLabel titleLabel = new JLabel("Registered Users");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Name", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(tableModel);
        userTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        // Back Button
        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        backBtn.addActionListener(e -> main.setAdminPanel());
        add(backBtn, BorderLayout.SOUTH);

        loadUserData();
    }

    private void loadUserData() {
        UserService userService = new UserService();
        List<UserInfo> users = userService.getAllUsers();

        tableModel.setRowCount(0);
        for (UserInfo user : users) {
            tableModel.addRow(new Object[]{user.getName(), user.getEmail()});
        }
    }
}
