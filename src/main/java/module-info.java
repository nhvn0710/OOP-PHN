module oop.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires voicerss.tts;
    requires java.desktop;
    requires java.sql;
    requires unirest.java;
    requires org.json;

    requires org.kordamp.bootstrapfx.core;

    opens oop.dictionary to javafx.fxml;
    opens oop.dictionary.controller to javafx.fxml;
    opens oop.dictionary.model to javafx.fxml;

    exports oop.dictionary;
    exports oop.dictionary.model;
    exports oop.dictionary.controller;
}