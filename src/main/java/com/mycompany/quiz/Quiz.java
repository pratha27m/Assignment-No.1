package com.mycompany.quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;


class Question {
    String questionText;
    String[] options;
    int correctAnswer;

    Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public final class Quiz extends JFrame implements ActionListener {
    JLabel label;
    JRadioButton radioButtons[] = new JRadioButton[4];
    JButton btnext, btnresult;
    ButtonGroup bg;
    int score = 0, currentQuestion = 0;
    ArrayList<Question> questions;

    Quiz(String s) {
        super(s);
        label = new JLabel();
        add(label);
        bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton();
            add(radioButtons[i]);
            bg.add(radioButtons[i]);
        }
        btnext = new JButton("Next");
        btnresult = new JButton("Result");
        btnresult.setVisible(false);
        btnresult.addActionListener(this);
        btnext.addActionListener(this);
        add(btnext);
        add(btnresult);

        // Initialize questions
        questions = new ArrayList<>();
        questions.add(new Question("India is a federal union comprising twenty-eight states and how many union territories?",new String[]{"6", "7", "8", "9"},2));
        questions.add(new Question("Which of the following is the capital of Arunachal Pradesh?",new String[]{"Itanagar", "Dispur", "Imphal", "Panaji"},0));
        questions.add(new Question("What are the major languages spoken in Andhra Pradesh?",new String[]{"Odia and Telugu", "Telugu and Urdu", "Telugu and Kannada", "All of the above languages"},1));
        questions.add(new Question("What is the state flower of Haryana?",new String[]{"Lotus", "Rhododendron", "Golden Shower", "Not Declared"},0));
        questions.add(new Question("Which of the following states is not located in the North?",new String[]{"Jharkhand", "Jammu and Kashmir", "Himachal Pradesh", "Haryana"},0));
        questions.add(new Question("In which of the following states, the main language is Khasi?",new String[]{"Mizoram", "Nagaland", "Meghalaya", "Tripura"},2));
        questions.add(new Question("Which is the largest coffee-producing state of India?",new String[]{"Kerala", "Tamil Nadu", "Karnataka", "Arunachal Pradesh"},2));
        questions.add(new Question("Which state has the largest area?",new String[]{"Maharashtra", "Madhya Pradesh", "Uttar Pradesh", "Rajasthan"},3));
        questions.add(new Question("Which state has the largest population?",new String[]{"Uttar Pradesh", "Maharashtra", "Bihar", "Andhra Pradesh"},0));
        questions.add(new Question("In what state is Elephant Falls located?",new String[]{"Mizoram", "Orissa", "Manipur", "Meghalaya"},3));
            
        setData();

        label.setBounds(30, 40, 450, 20);
        for (int i = 0; i < 4; i++) {
            radioButtons[i].setBounds(50, 80 + (i * 30), 450, 20);
        }
        btnext.setBounds(100, 240, 100, 30);
        btnresult.setBounds(270, 240, 100, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
    }

    void setData() {
        if (currentQuestion < questions.size()) {
            Question q = questions.get(currentQuestion);
            label.setText(q.questionText);
            for (int i = 0; i < 4; i++) {
                radioButtons[i].setText(q.options[i]);
            }
            bg.clearSelection();
        }
    }

    boolean check() {
        return radioButtons[questions.get(currentQuestion).correctAnswer].isSelected();
    }

    String getFeedback() {
        if (score >= 9) {
            return "Excellent!";
        } else if (score >= 7) {
            return "Good job!";
        } else if (score >= 5) {
            return "Fair!";
        } else {
            return "Better luck next time!";
        }
    }

    public static void main(String[] args) {
        new Quiz("Simple Quiz App");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnext) {
            if (check()) score++;
            currentQuestion++;
            if (currentQuestion < questions.size()) {
                setData();
            } else {
                btnext.setEnabled(false);
                btnresult.setVisible(true);
                btnresult.setText("Result");
            }
        } else if (e.getSource() == btnresult) {
            JOptionPane.showMessageDialog(this, "Correct Answers: " + score + "\n" + getFeedback());
            System.exit(0);
        }
    }
}
