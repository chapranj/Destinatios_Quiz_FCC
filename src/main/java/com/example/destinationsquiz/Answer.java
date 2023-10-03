package com.example.destinationsquiz;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Answer {
    BufferedReader reader;
    private ArrayList<String> correctAnswers = new ArrayList<>();
    public Answer() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader("TextFiles/answers.txt"));
    }

    public int checkAnswer(ArrayList<String> chosenAnswers, Stage primaryStage) throws IOException {
        int score = 0;
        String line = reader.readLine();
        while (!(line.equals(Quiz.countrySelected))){
            line = reader.readLine();
        }
        for (int i = 0; i< Quiz.NUMBEROFQUESTIONS;i++){
            correctAnswers.add(reader.readLine());
        }
        for (int i = 0; i< Quiz.NUMBEROFQUESTIONS;i++){
            String correctAnswer = correctAnswers.get(i);
            String chosenAnswer = chosenAnswers.get(i);
            System.out.println("CorrectAnswer: "+correctAnswer);
            System.out.println("ChosenAnswer: "+chosenAnswer);
            if (correctAnswer.equals(chosenAnswer)){
                score++;
            }
        }

        return score;
    }

    public Scene scoreCheck(int score, Stage primaryStage) throws IOException {
        VBox finalVBOX = new VBox();
        if (score != 5){
            Label lbl1 = new Label("Sorry but you didnt score in full");
            Label lbl2 = new Label("We can't add you in lucky draw");
            Label lbl3 = new Label("But chill you did well");
            Label lbl4 = new Label("Your score was : " + score);
            lbl1.setFont(Font.font(20));
            lbl2.setFont(Font.font(20));
            lbl3.setFont(Font.font(20));
            lbl4.setFont(Font.font(20));
//            Button btn = new Button("Back");
            finalVBOX.getChildren().addAll(lbl1,lbl2,lbl3,lbl4);
//            btn.setOnAction(actionEvent -> {
//                primaryStage.setScene(Runner.theFirstScene);
//            });

        }
        else{
            Label hurrah = new Label("Hurrayy...you scored 5/5!");
            hurrah.setFont(Font.font(20));
            HBox nameHbox = new HBox();
            Label nameLabel = new Label("Name : ");
            nameLabel.setFont(Font.font(20));
            TextField nameTfield = new TextField();
            nameHbox.getChildren().addAll(nameLabel,nameTfield);
            HBox numberHbox = new HBox();
            Label numberLabel = new Label("Student Id : ");
            numberLabel.setFont(Font.font(20));
            TextField numberTfield = new TextField();
            numberHbox.getChildren().addAll(numberLabel,numberTfield);

            Button addToFile = new Button("ADD NAME TO DRAW");
            addToFile.setFont(Font.font(20));

            addToFile.setOnAction(e->{
                try {
                    addToFile(nameTfield.getText(),numberTfield.getText());
                    primaryStage.setScene(Runner.theFirstScene);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            finalVBOX.getChildren().addAll(hurrah,nameHbox,numberHbox,addToFile);
            System.out.println("yeh");

        }
        return new Scene(finalVBOX,1000,1000);
    }

    public void addToFile(String name, String number) throws IOException {
        BufferedWriter writer;

        writer = new BufferedWriter(new FileWriter("TextFiles/luckyDraw.txt",true));
        writer.newLine();
        writer.write("Name : " + name + " ; Number : " + number);

        writer.close();
    }
}
