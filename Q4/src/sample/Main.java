package sample;
/* Author: Hassan Tariq
   Course: CSCI 2020U
   Question 4: Histogram of letter frequency
 */

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.Scanner;


public class Main extends Application{
    public void start(Stage primaryStage) {

            //Create textfield and button
            TextField fileName = new TextField();
            Label labelFile = new Label("Filename: ", fileName);
            Button view = new Button("View");

            labelFile.setContentDisplay(ContentDisplay.RIGHT);
            fileName.setPrefColumnCount(20);
            //add hbox to vbox to have separate non dynamic boxes
            //so this hbox will not interfere with spacing of gridpane
            HBox hBox = new HBox(labelFile, view);

            VBox vBox = new VBox();
            //create action event for inputting file name
            view.setOnAction(e-> {
                java.io.File file = new java.io.File(fileName.getText());
                //error check for file existence
                if (file.exists()) {
                    System.out.println(readFile(file));
                    int[] count = count(readFile(file));
                    char[] chars = new char[26];
                    Rectangle[] rectangles = new Rectangle[26];
                    for (int i = 0; i < 26; i++) {
                        chars[i] = (char) ('A' + i);
                        System.out.print(chars[i] + ": ");
                        System.out.print(count[i] + " ");
                    }

                    GridPane grid = new GridPane();
                    grid.setHgap(2);

                    for (int i = 0; i < 26; i++) {
                        rectangles[i] = new Rectangle(10, count[i]*10);
                        rectangles[i].setStroke(Color.BLACK);
                        rectangles[i].setFill(Color.WHITE);
                        Label label = new Label(chars[i] + "", rectangles[i]);
                        label.setContentDisplay(ContentDisplay.TOP);
                        grid.add(label, i , 0);
                        GridPane.setValignment(label, VPos.BASELINE);
                    }

                    vBox.getChildren().add(grid);
                } else {
                    System.out.println("File does not exist!");
                }
            });
            //add all remaining boxes
            vBox.getChildren().add(hBox);
            Scene scene = new Scene(vBox, 400, 1000);
            primaryStage.setTitle("Q4"); // Set the stage title
            primaryStage.setScene(scene); // Place the scene in the stage
            primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
    //readFile function that reads file and creates single string to count frequency
    public static String readFile(java.io.File file) {

        Scanner scanner;
        String s = "";
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {

                s += scanner.nextLine();
            }
        } catch (IOException ex) {
        }

        s = s.toUpperCase();

        return s;

    }

    //count function that reads the single string created by readFile to get frequency
    public static int[] count (String s) {
        int count[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            if (Character.isLetter(character)) {
                count[character-'A']++;
            }
        }
        return count;
    }
}


