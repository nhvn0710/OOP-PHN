package oop.dictionary.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import oop.dictionary.model.DictionaryManagement;
import oop.dictionary.model.TtsApiController;
import oop.dictionary.model.Word;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    DictionaryManagement dictionaryManagement = DictionaryManagement.getInstance();
    @FXML
    private ListView<Word> listWordView;
    @FXML
    private TextField searchField;
    @FXML
    private TextField targetField;
    @FXML
    private Label wordLabel;
    @FXML
    private WebView definitionView;
    @FXML
    private HTMLEditor editor;
    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button usSoundButton;
    @FXML
    private Button ukSoundButton;
    @FXML
    private AnchorPane definitionPane;


    private ObservableList<Word> wordObservableList;

    private Word tempWord = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wordObservableList = FXCollections.observableArrayList();
        wordObservableList.setAll(dictionaryManagement.searchWord(""));

        listWordView.setItems(wordObservableList);
        listWordView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Word>() {
            @Override
            public void changed(ObservableValue<? extends Word> observable, Word oldValue, Word newValue) {
                tempWord = newValue;
                showDefinition(newValue);
            }
        });
    }

    private void showDefinition(Word word) {
        if (word != null) {
            wordLabel.setText(word.getWordTarget());
            definitionView.getEngine().loadContent(word.getWordExplain(), "text/html");
        }
        else {
            wordLabel.setText("");
            definitionView.getEngine().loadContent("");
        }
    }

    public void search() {
        String s = searchField.getText();
        wordObservableList.setAll(dictionaryManagement.searchWord(s));

        listWordView.setItems(wordObservableList);
    }

    public void playUkSound() {
        TtsApiController tts = TtsApiController.getInstance();
        tts.setUkSound(tempWord.getWordTarget());
        tts.playUkSound(tempWord.getWordTarget());
    }

    public void playUsSound() {
        TtsApiController tts = TtsApiController.getInstance();
        tts.setUsSound(tempWord.getWordTarget());
        tts.playUsSound(tempWord.getWordTarget());
    }

    public void edit() {
        definitionView.setVisible(false);
        editor.setVisible(true);
        saveButton.setVisible(true);
        editor.setHtmlText(tempWord.getWordExplain());
    }

    public void delete() {
        dictionaryManagement.delete(tempWord);
        dictionaryManagement.dictionaryExportToFile();
        listWordView.setItems(wordObservableList);
        tempWord = null;
        showDefinition(tempWord);
        search();
    }

    public void add() {
        definitionView.setVisible(false);
        saveButton.setVisible(true);
        targetField.setVisible(true);
        editor.setVisible(true);
    }

    public void save() {
        if (targetField.isVisible()) {
            dictionaryManagement.add(new Word(targetField.getText(), editor.getHtmlText()));
        }
        else {
            tempWord.setWordExplain(editor.getHtmlText());
        }
        dictionaryManagement.dictionaryExportToFile();
        editor.setVisible(false);
        targetField.setVisible(false);
        saveButton.setVisible(false);
        definitionView.setVisible(true);
        showDefinition(tempWord);
    }

}
