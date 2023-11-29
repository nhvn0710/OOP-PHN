package oop.dictionary.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class QuizzController implements Initializable {

    @FXML
    private Label questionLabel;

    @FXML
    private RadioButton option1;

    @FXML
    private RadioButton option2;

    @FXML
    private RadioButton option3;

    @FXML
    private RadioButton option4;

    @FXML
    private Button confirmButton;

    @FXML
    private Label correctCount;

    @FXML
    private Label incorrectCount;

    @FXML
    private Label explanationBox;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int currentCorrectCount = 0;
    private int currentInCorrectCount = 0;

    private boolean explanationShown = false;

    private void loadQuestions() {
        questions = new ArrayList<>();
        try {
            //Insert questions' path here.
            File inFile = new File("QuizzQuestions.txt");
            FileReader fileReader = new FileReader(inFile);

            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            while ((line = reader.readLine()) != null) {
                String[] word = line.split("\t");
                Question q = new Question(word[0], word[1], word[2], word[3], word[4], word[5], word[6]);
                questions.add(q);
            }
            Collections.shuffle(questions);
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /*
        questions.add(new Question("First question?", "Option 1", "Option 2", "Option 3", "Option 4"));
        questions.add(new Question("Second question?", "Option2 1", "Option 2", "Option 3", "Option 4", "Option 2"));
        questions.add(new Question("Third question?", "Option3 1", "Option 2", "Option 3", "Option 4", "Option 3"));
        questions.add(new Question("Forth question?", "Option4 1", "Option 2", "Option 3", "Option 4", "Option 2"));
        */
    }

    private void loadQuestion(int index) {
        Question currentQuestion = questions.get(index);
        questionLabel.setText(currentQuestion.getQuestion());
        option1.setText(currentQuestion.getOption1());
        option2.setText(currentQuestion.getOption2());
        option3.setText(currentQuestion.getOption3());
        option4.setText(currentQuestion.getOption4());
        explanationBox.setText("");
    }

    public void submitAnswer() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (!explanationShown) {
            explanationBox.setText(currentQuestion.getExplanation());
            System.out.println(currentQuestion.getExplanation());
            explanationShown = true;
            confirmButton.setText("Next");
        } else {
            explanationShown = false;
            confirmButton.setText("Confirm");
            RadioButton answer;
            if (option1.getText().equals(currentQuestion.getAnswer())) {
                answer = option1;
            } else if (option2.getText().equals(currentQuestion.getAnswer())) {
                answer = option2;
            } else if (option3.getText().equals(currentQuestion.getAnswer())) {
                answer = option3;
            } else
                answer = option4;
            if (answer.isSelected()) {
                currentCorrectCount++;
                correctCount.setText("Correct: " + currentCorrectCount);
            } else {
                currentInCorrectCount++;
                incorrectCount.setText("Incorrect: " + currentInCorrectCount);
            }
            currentQuestionIndex++;
            explanationBox.setText(currentQuestion.getExplanation());
            if (currentQuestionIndex < questions.size()) {
                loadQuestion(currentQuestionIndex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadQuestions();
        loadQuestion(currentQuestionIndex);

    }
}

class Question {

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String explanation;

    public Question(String question, String option1, String option2, String option3, String option4) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = option1;
        this.explanation = "";
    }

    public Question(String question, String option1, String option2, String option3, String option4, String answer, String explanation) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.explanation = explanation;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getAnswer() {
        return answer;
    }

    public String getExplanation() {
        return explanation;
    }
}