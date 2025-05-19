package com.quizzo;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.quizzo.db.MongoConnection;
import com.quizzo.gui.AddQuestionPanel;
import com.quizzo.gui.AdminPanel;
import com.quizzo.gui.Login;
import com.quizzo.gui.QuizPanel;
import com.quizzo.gui.QuizStartPanel;
import com.quizzo.gui.Register;
import com.quizzo.gui.ResultPanel;
import com.quizzo.gui.ViewResultsPanel;
import com.quizzo.gui.ViewUsersPanel;
import com.quizzo.gui.WelcomePanel;


public class Main extends JFrame {

    private JPanel contentPanel;
    private CardLayout layout;
    private String userEmail;

    public Main() {
        setTitle("Quizzo Application");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        layout = new CardLayout();
        contentPanel = new JPanel(layout);
        setContentPane(contentPanel);

        // Show welcome screen first
        setWelcome();

        setVisible(true);
    }

    /**
     * Welcome screen with branding and Login/Register buttons
     */
    public void setWelcome() {
        contentPanel.removeAll();
        contentPanel.add(new WelcomePanel(this), "welcome");
        layout.show(contentPanel, "welcome");
        repaint();
        revalidate();
    }

    /**
     * Display Login form
     */
    public void setLogin() {
        contentPanel.removeAll();
        contentPanel.add(new Login(this), "login");
        layout.show(contentPanel, "login");
        repaint();
        revalidate();
    }

    /**
     * Display Registration form
     */
    public void setRegister() {
        contentPanel.removeAll();
        contentPanel.add(new Register(this), "register");
        layout.show(contentPanel, "register");
        repaint();
        revalidate();
    }

    /**
     * After login, decide admin vs user
     */
    public void setUserDashboard(String email) {
        this.userEmail = email;
        if ("adminquizzo@gmail.com".equalsIgnoreCase(email)) {
            setAdminPanel();
        } else {
            setQuizStartPanel(email);
        }
    }

    /**
     * Admin main panel
     */
    public void setAdminPanel() {
        contentPanel.removeAll();
        contentPanel.add(new AdminPanel(this), "admin");
        layout.show(contentPanel, "admin");
        repaint();
        revalidate();
    }

    /**
     * Panel to add new questions
     */
    public void setAddQuestionPanel() {
        contentPanel.removeAll();
        contentPanel.add(new AddQuestionPanel(this), "addq");
        layout.show(contentPanel, "addq");
        repaint();
        revalidate();
    }

    /**
     * Panel for user to enter topic ID
     */
    public void setQuizStartPanel(String userEmail) {
        this.userEmail = userEmail;
        contentPanel.removeAll();
        contentPanel.add(new QuizStartPanel(this), "quizstart");
        layout.show(contentPanel, "quizstart");
        repaint();
        revalidate();
    }

    /**
     * Launch the quiz panel with questions
     */
    public void startQuiz(String topicId) {
        contentPanel.removeAll();
        contentPanel.add(new QuizPanel(this, topicId, userEmail), "quiz");
        layout.show(contentPanel, "quiz");
        repaint();
        revalidate();
    }

    /**
     * Show result summary to user
     */
    public void showResult(int score, int total) {
        contentPanel.removeAll();
        contentPanel.add(new ResultPanel(this, score, total), "result");
        layout.show(contentPanel, "result");
        repaint();
        revalidate();
    }
    
    /**
     * Show all users' quiz results (admin)
    
    public void setViewResultsPanel() {
        contentPanel.removeAll();
        contentPanel.add(new ViewResultsPanel(this), "viewResults");
        layout.show(contentPanel, "viewResults");
        repaint();
        revalidate();
    } */

   public void setViewResultsPanel() {
    contentPanel.removeAll();
    contentPanel.add(new ViewResultsPanel(this), "viewresults");
    layout.show(contentPanel, "viewresults");
    repaint();
    revalidate();
}

public void setViewUsersPanel() {
    contentPanel.removeAll();
    contentPanel.add(new ViewUsersPanel(this), "viewusers");
    layout.show(contentPanel, "viewusers");
    repaint();
    revalidate();
}




    public static void main(String[] args) {
        // Ensure MongoDB connection is initialized (optional)
        MongoConnection.getDatabase();
        SwingUtilities.invokeLater(Main::new);
    }
}
