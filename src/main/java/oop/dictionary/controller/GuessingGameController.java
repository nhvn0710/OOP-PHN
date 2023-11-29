package oop.dictionary.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import oop.dictionary.model.DictionaryManagement;
import oop.dictionary.model.Word;

import java.net.URL;
import java.util.*;

public class GuessingGameController implements Initializable {

    @FXML
    private VBox choicesBox;

    @FXML
    private WebView targetWordLabel;

    @FXML
    private Button choice1;

    @FXML
    private Button choice2;

    @FXML
    private Button choice3;

    @FXML
    private Button choice4;

    @FXML
    private Button choice5;

    @FXML
    private ProgressBar guessesLeftBar;

    @FXML
    private Label resultLabel;

    @FXML
    private Label guessCountLabel;

    @FXML
    private ProgressIndicator correctCountClock;

    private Word targetWord;
    private List<Word> choices = new ArrayList<>();
    private int guessesLeft = 5;

    private final int rightCount = 5;

    DictionaryManagement dictionaryManagement = DictionaryManagement.getInstance();
    private ObservableList<Word> wordObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordObservableList = FXCollections.observableArrayList();
        wordObservableList.setAll(dictionaryManagement.searchWord(""));
        guessesLeftBar.setProgress(1.0);
        correctCountClock.setProgress(0.0);
        guessCountLabel.setText("Guess Left: "+ guessesLeft);
        startNewGame(dictionaryManagement.getArrayWords());

    }

    public void startNewGame(List<Word> dict) {
        targetWord = pickRandomWord(dict);
        targetWordLabel.getEngine().loadContent(targetWord.getOnlyExplain());
        choices.clear();
        choices.add(targetWord);
        while (choices.size() < 5) {
            Word option = pickRelatedWord(dict, targetWord);
            if (!choices.contains(option)) {
                choices.add(option);
            }
        }
        System.out.println("done");

        Collections.shuffle(choices);
        assignChoicesToButtons();


        resultLabel.setText("Let's start guessing!");

    }

    private Word pickRandomWord(List<Word> dict) {
        int index = new Random().nextInt(dict.size());
        return dict.get(index);
    }

    private Word pickRelatedWord(List<Word> dict, Word target) {
        int randomint;
        int index = dict.indexOf(target);
        do {
            randomint = index + new Random().nextInt(10) - 5;
        } while (randomint==index || randomint<0);
        return dict.get(randomint);
    }

    private void assignChoicesToButtons() {
        choice1.setText(choices.get(0).getWordTarget());
        choice2.setText(choices.get(1).getWordTarget());
        choice3.setText(choices.get(2).getWordTarget());
        choice4.setText(choices.get(3).getWordTarget());
        choice5.setText(choices.get(4).getWordTarget());
    }

    public void onChoiceSelected(ActionEvent event) {
        Button selectedButton = (Button) event.getSource();
        String selectedText = selectedButton.getText();

        Word selectedWord = null;
        for (Word option : choices) {
            if (option.getWordTarget().equals(selectedText)) {
                selectedWord = option;
                break;
            }
        }

        assert selectedWord != null;
        if (selectedWord.equals(targetWord)) {
            resultLabel.setText("Correct!");
            correctCountClock.setProgress(correctCountClock.getProgress()+ (double) 1 /rightCount);
            startNewGame(dictionaryManagement.getArrayWords());
        } else {
            guessesLeft--;
            guessesLeftBar.setProgress(guessesLeft / 5.0);
            guessCountLabel.setText("Guess Left: "+ guessesLeft);

            if (guessesLeft == 0) {
                resultLabel.setText("Incorrect! The word was: " + targetWord.getWordTarget());
            }
        }
    }
}