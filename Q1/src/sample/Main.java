package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5);
        int random1 = (int)(Math.random() * 54 + 1);
        int random2 = (int)(Math.random() * 54 + 1);
        int random3 = (int)(Math.random() * 54 + 1);

        String url1 = "image/card/" + String.valueOf(random1) + ".png";
        String url2 = "image/card/" + String.valueOf(random2) + ".png";
        String url3 = "image/card/" + String.valueOf(random3) + ".png";


        ImageView imageView1 = new ImageView(url1);
        ImageView imageView2 = new ImageView(url2);
        ImageView imageView3 = new ImageView(url3);

        pane.add(imageView1, 0, 0);
        pane.add(imageView2, 1, 0);
        pane.add(imageView3, 2, 0);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Question1"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }



    public static void main(String[] args) {
        launch(args);
    }
}
