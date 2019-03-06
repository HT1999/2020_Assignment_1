package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField invest = new TextField ();
        TextField years = new TextField ();
        TextField annualRate = new TextField ();
        TextField futureValue= new TextField ();
        Button calculate = new Button("Calculate");




        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_RIGHT);
        grid.setVgap(4);
        grid.setHgap(10);


        grid.add(new Label("Investment Amount "), 0, 0);
        grid.add(invest, 1, 0);

        grid.add(new Label("Years "), 0, 1);
        grid.add(years, 1, 1);

        grid.add(new Label("Annual Interest Rate "), 0, 2);
        grid.add(annualRate, 1, 2);

        grid.add(new Label("Future Value "), 0, 3);
        grid.add(futureValue, 1, 3);

        grid.add(calculate,1,4);

        Scene scene = new Scene(grid);
        primaryStage.setTitle("Question1"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        calculate.setOnAction(action -> {
            futureValue.setText(Double.parseDouble(invest.getText()) *
                    Math.pow(1 + Double.parseDouble(annualRate.getText())/100, Double.parseDouble(years.getText())) + "");

        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
