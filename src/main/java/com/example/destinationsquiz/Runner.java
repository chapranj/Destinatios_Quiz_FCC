package com.example.destinationsquiz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.example.destinationsquiz.SelectCountries.*;

public class Runner extends Application {
    public static Scene theFirstScene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Destinations Quiz");
        primaryStage.show();
        theFirstScene = CreateSelectCountriesScene(primaryStage);
        theFirstScene.getStylesheets().add("styles.css");

        primaryStage.setScene(theFirstScene);
    }
}