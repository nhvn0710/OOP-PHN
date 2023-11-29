package oop.dictionary.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import oop.dictionary.model.TranslateApiController;

public class TranslateController {

    @FXML
    private Button viButton;
    @FXML
    private Button enButton;
    @FXML
    private Button frButton;
    @FXML
    private Button koButton;
    @FXML
    private Button zhButton;
    @FXML
    private Button viSourceButton;
    @FXML
    private Button enSourceButton;


    @FXML
    private Button translateButton;

    @FXML
    private TextArea targetText;
    @FXML
    private TextArea sourceText;

    private String sourceLanguage;
    private String targetLanguage;
    
    private void setTargetLanguage(String language) {
        this.targetLanguage = language;
    }
    private void setSourceLanguage(String language) {
        this.sourceLanguage = language;
    }

    public void translate() {
        TranslateApiController translator = new TranslateApiController(sourceText.getText(), sourceLanguage, targetLanguage);
        targetText.setText(translator.getTranslatation());
    }

    public void removeTargetButtonStyle(String style) {
        viButton.getStyleClass().remove(style);
        enButton.getStyleClass().remove(style);
        frButton.getStyleClass().remove(style);
        koButton.getStyleClass().remove(style);
        zhButton.getStyleClass().remove(style);
    }

    public void removeSourceButtonStyle(String style) {
        viSourceButton.getStyleClass().remove(style);
        enSourceButton.getStyleClass().remove(style);
    }


    public void viButtonClick() {
        removeTargetButtonStyle("button-transparent-focused");
        viButton.getStyleClass().add("button-transparent-focused");
        setTargetLanguage("vi");
    }

    public void enButtonClick() {
        removeTargetButtonStyle("button-transparent-focused");
        enButton.getStyleClass().add("button-transparent-focused");
        setTargetLanguage("en");
    }

    public void frButtonClick() {
        removeTargetButtonStyle("button-transparent-focused");
        frButton.getStyleClass().add("button-transparent-focused");
        setTargetLanguage("fr");
    }

    public void koButtonClick() {
        removeTargetButtonStyle("button-transparent-focused");
        koButton.getStyleClass().add("button-transparent-focused");
        setTargetLanguage("ko");
    }

    public void zhButtonClick() {
        removeTargetButtonStyle("button-transparent-focused");
        zhButton.getStyleClass().add("button-transparent-focused");
        setTargetLanguage("zh-CN");
    }

    public void viSourceButtonClick() {
        removeSourceButtonStyle("button-transparent-focused");
        viSourceButton.getStyleClass().add("button-transparent-focused");
        setSourceLanguage("vi");
    }
    public void enSourceButtonClick() {
        removeSourceButtonStyle("button-transparent-focused");
        enSourceButton.getStyleClass().add("button-transparent-focused");
        setSourceLanguage("en");
    }


}
