package com.quizzo.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.quizzo.Main;
import com.quizzo.service.ResultService;
import com.quizzo.service.ResultService.ResultWithUser;

public class ViewResultsPanel extends JPanel {

    private ResultService resultService;
    private Main main;

    public ViewResultsPanel(Main main) {
        this.main = main;
        this.resultService = new ResultService();

        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        // Title
        JLabel titleLabel = new JLabel("User Results Grouped by Quiz ID");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Main scrollable panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(30, 30, 30));

        Map<String, List<ResultWithUser>> groupedResults = resultService.getResultsGroupedByTopic();

        for (Map.Entry<String, List<ResultWithUser>> entry : groupedResults.entrySet()) {
            String topicId = entry.getKey();
            List<ResultWithUser> results = entry.getValue();

            // Topic ID Label
            JLabel quizLabel = new JLabel("Quiz ID: " + topicId);
            quizLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            quizLabel.setForeground(Color.CYAN);
            quizLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
            contentPanel.add(quizLabel);

            // Table for this quiz
            String[] columns = {"User Email", "Score", "Total Marks"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            for (ResultWithUser r : results) {
                model.addRow(new Object[]{r.getUsername(), r.getScore(), r.getTotalMarks()});
            }

            JTable table = new JTable(model);
            JScrollPane tableScroll = new JScrollPane(table);
            tableScroll.setPreferredSize(new Dimension(550, Math.min(results.size() * 20 + 50, 200)));
            contentPanel.add(tableScroll);
        }

        JScrollPane mainScrollPane = new JScrollPane(contentPanel);
        add(mainScrollPane, BorderLayout.CENTER);

        // Back Button
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> main.setAdminPanel());
        add(backBtn, BorderLayout.SOUTH);
    }
}
