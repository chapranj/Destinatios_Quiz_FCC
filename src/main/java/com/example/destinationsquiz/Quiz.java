package com.example.destinationsquiz;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Quiz {
    final static int NUMBEROFQUESTIONS = 5;
    final static int NUMBEROFOPTIONS = 4;
    final static int QUESTIONLENGTH = NUMBEROFQUESTIONS * (NUMBEROFOPTIONS +1);

    static String countrySelected = null;

    private ArrayList<ToggleGroup> questionGroup = new ArrayList<ToggleGroup>();
    private Scene scene;
//    private static ScrollPane rootScroll = new ScrollPane();
    private static VBox root = new VBox();

    public void CreateQuizScene(Stage primaryStage){
        Button submitButton = new Button("SUBMIT");
        ScrollPane rootScroll = new ScrollPane();

        submitButton.setOnAction(e->{
            try {
                onSubmit(primaryStage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        System.out.println("Clicked Checkpoint #4");
        root.getChildren().add(submitButton);

        rootScroll.setContent(root);

//        primaryStage.setScene();
        System.out.println("Clicked Checkpoint #5");

        primaryStage.setScene(new Scene(rootScroll,1200,700));

    }

    public void loadQuiz(Button btn, Stage primaryStage) throws IOException {
        if (!root.getChildren().isEmpty()){
            root.getChildren().clear();
        }
        System.out.println("Clicked Checkpoint #2");
        BufferedReader reader;
        countrySelected = btn.getText();
        reader = new BufferedReader(new FileReader("TextFiles/questions.txt"));
        String line = reader.readLine();
        while (!(line.equals(countrySelected))){
            line = reader.readLine();
        }
            for (int j =0; j < NUMBEROFQUESTIONS; j++){
                VBox questionVBOX = new VBox();
                ToggleGroup question = new ToggleGroup();
                Text questionText = new Text(reader.readLine());
                questionVBOX.getChildren().add(questionText);
                for (int k = 0; k < NUMBEROFOPTIONS;k++){
                    RadioButton radioButton = new RadioButton();
                    radioButton.setText(reader.readLine());
                    radioButton.setToggleGroup(question);
                    questionVBOX.getChildren().add(radioButton);
                }
                questionGroup.add(question);
                root.getChildren().add(questionVBOX);
        }
    CreateQuizScene(primaryStage);
    System.out.println("Clicked Checkpoint #3");
    }

    public void onSubmit(Stage primaryStage) throws IOException {
        ArrayList<String> answers = new ArrayList<>();
        System.out.println("IN OON SUBMIT");
        for (ToggleGroup question : questionGroup){
            RadioButton selectedAns = (RadioButton) question.getSelectedToggle();
            answers.add(selectedAns.getText());
        }
        Answer ans = new Answer();
        int Score = ans.checkAnswer(answers, primaryStage);
        Scene finalScene = ans.scoreCheck(Score, primaryStage);
        primaryStage.setScene(finalScene);
    }
}
