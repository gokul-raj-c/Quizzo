package com.quizzo.gui;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.quizzo.Main;
import com.quizzo.model.Question;
import com.quizzo.service.QuizService;

public class QuizPanel extends JPanel {
    private final Main main;
    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;
    private final String userEmail;
    private final String topicId;

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton nextButton, exitButton;

    public QuizPanel(Main main, String topicId, String userEmail) {
        this.main      = main;
        this.topicId   = topicId;
        this.userEmail = userEmail;
        this.questions = new QuizService().getQuestionsByTopic(topicId);

        initComponents();
        loadQuestion();
    }

    private void initComponents() {
        setLayout(null);
        setBackground(new java.awt.Color(30, 30, 30));

        // Question text
        questionLabel = new JLabel();
        questionLabel.setBounds(50, 30, 700, 30);
        questionLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
        questionLabel.setForeground(java.awt.Color.WHITE);
        add(questionLabel);

        // Radio buttons for options
        optionButtons = new JRadioButton[4];
        optionGroup   = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setBounds(50, 80 + i * 40, 600, 30);
            optionButtons[i].setBackground(new java.awt.Color(30, 30, 30));
            optionButtons[i].setForeground(java.awt.Color.WHITE);
            optionGroup.add(optionButtons[i]);
            add(optionButtons[i]);
        }

        // Next button
        nextButton = new JButton("Next");
        nextButton.setBounds(200, 260, 120, 40);
        add(nextButton);
        nextButton.addActionListener(this::onNext);

        // Exit button
        exitButton = new JButton("Exit Quiz");
        exitButton.setBounds(360, 260, 120, 40);
        add(exitButton);
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit the quiz?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                main.setLogin();
            }
        });
    }

    private void loadQuestion() {
        if (currentIndex >= questions.size()) {
            // All done → save & show result
            new QuizService()
                .saveResult(userEmail, topicId, score, questions.size());
            main.showResult(score, questions.size());
            return;
        }

        // Populate question and options
        Question q = questions.get(currentIndex);
        questionLabel.setText(
            String.format("Q%d. %s", currentIndex + 1, q.getQuestion())
        );

        List<String> opts = q.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(opts.get(i));
            optionButtons[i].setSelected(false);
        }

        // Force Swing to redraw updated labels/buttons
        revalidate();
        repaint();
    }

    private void onNext(ActionEvent evt) {
        // Check for a selection
        String selected = null;
        for (JRadioButton btn : optionButtons) {
            if (btn.isSelected()) {
                selected = btn.getText();
                break;
            }
        }
        if (selected == null) {
            JOptionPane.showMessageDialog(
                this,
                "Please select an option before moving on.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Score it
        Question q = questions.get(currentIndex);
        if (q.getCorrectAnswer().equalsIgnoreCase(selected)) {
            score++;
        }

        // Advance index and reload next question
        currentIndex++;
        loadQuestion();
    }
}
