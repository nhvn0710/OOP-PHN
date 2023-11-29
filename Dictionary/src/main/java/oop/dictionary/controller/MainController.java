package oop.dictionary.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import oop.dictionary.HelloApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane mainContent;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane gamePane;
    @FXML
    private AnchorPane dictionaryPane;

    @FXML
    private SearchController searchController;

    @FXML
    private HBox searchButton;
    @FXML
    private HBox translateButton;
    @FXML
    private HBox gameButton;
    @FXML
    private HBox dictionaryButton;

    private void setMainContent(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }

    public void resetStyle() {
        searchButton.getStyleClass().removeAll("selected");
        translateButton.getStyleClass().removeAll("selected");
    }

    @FXML
    public void showSearchPane() {
        setMainContent(searchPane);
        resetStyle();
        searchButton.getStyleClass().add("selected");
    }

    @FXML
    public void showTranslatePane() {
        setMainContent(translatePane);
        resetStyle();
        translateButton.getStyleClass().add("selected");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("view/search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("view/translate.fxml"));
            translatePane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        showSearchPane();
    }
}
