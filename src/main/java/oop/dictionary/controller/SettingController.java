package oop.dictionary.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oop.dictionary.model.DictionaryManagement;

import java.io.File;
import java.net.URL;
import java.util.Dictionary;
import java.util.ResourceBundle;

public class SettingController implements Initializable {

    @FXML
    private Button importButton;

    @FXML
    private Button exportButton;

    @FXML
    private AnchorPane anchorPane;

    private DictionaryManagement dictionaryManagement;

    public void importData() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        File file = fileChooser.showOpenDialog(stage);
        dictionaryManagement.insertFromFile(file.getPath());
    }

    public void exportData() {
        dictionaryManagement.dictionaryExportToFile();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryManagement = DictionaryManagement.getInstance();
    }
}
