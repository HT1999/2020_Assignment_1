package sample;
/* Author: Hassan Tariq
   Course: CSCI 2020U
   Question 3: Dynamic triangle change with angles
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a pane
        Pane pane = new Pane();
        Circle circle = new Circle(150, 150, 100);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);


        //create the three points of the circle
        //set circles stroke/fill to black/red
        Circle p1 = new Circle(250, 150, 5);
        p1.setFill(Color.RED);
        p1.setStroke(Color.BLACK);
        Circle p2 = new Circle(50, 150, 5);
        p2.setFill(Color.RED);
        p2.setStroke(Color.BLACK);
        Circle p3 = new Circle(150, 50, 5);
        p3.setFill(Color.RED);
        p3.setStroke(Color.BLACK);

        //array list for storing of points
        ArrayList<Circle> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);

        // Place nodes in the pane
        drawTriangle(pane, points);
        pane.getChildren().addAll(p1, p2, p3);
        placeText(pane, points);

        // Create and register the handle
        pane.setOnMouseDragged(e -> {
            for (int i = 0; i < points.size(); i++) {
                if (points.get(i).contains(e.getX(), e.getY())) {
                    pane.getChildren().clear();
                    points.get(i).setCenterX(e.getX());
                    points.get(i).setCenterY(e.getY());
                    drawTriangle(pane, points);
                    pane.getChildren().addAll(p1, p2, p3);
                    pane.getChildren().add(circle);
                    placeText(pane, points);
                }
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("Q3"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }


    //to update the text points on the vertices
    static void placeText(Pane pane, ArrayList<Circle> points) {
        double[] sides = getSides(points);
        double[] angles = getAngles(sides);
        pane.getChildren().add(new Text(points.get(0).getCenterX(),
                points.get(0).getCenterY() - 5, String.format("%.1f", angles[0])));
        pane.getChildren().add(new Text(points.get(1).getCenterX(),
                points.get(1).getCenterY() - 5, String.format("%.1f", angles[1])));
        pane.getChildren().add(new Text(points.get(2).getCenterX(),
                points.get(2).getCenterY() - 5, String.format("%.1f", angles[2])));
    }

    //array list of sides of triangles
    static double[] getSides(ArrayList<Circle> points) {
        double[] sides = new double[3];
        sides[0] = Math.sqrt(Math.pow(points.get(0).getCenterX() -
                points.get(1).getCenterX(), 2) + Math.pow(points.get(0).getCenterY() -
                points.get(1).getCenterY(), 2));
        sides[1] = Math.sqrt(Math.pow(points.get(1).getCenterX() -
                points.get(2).getCenterX(), 2) + Math.pow(points.get(1).getCenterY() -
                points.get(2).getCenterY(), 2));
        sides[2] = Math.sqrt(Math.pow(points.get(2).getCenterX() -
                points.get(0).getCenterX(), 2) + Math.pow(points.get(2).getCenterY() -
                points.get(0).getCenterY(), 2));
        return sides;
    }

    //array of angles
    static double[] getAngles(double[] s) {
        double[] angles = new double[3];

        //use given formula with some modification
        angles[0] = Math.toDegrees(Math.acos((s[0] * s[0] - s[1] *
                s[1] - s[2] * s[2]) / (-2 * s[1] * s[2])));
        angles[1] = Math.toDegrees(Math.acos((s[1] * s[1] - s[0] *
                s[0] - s[2] * s[2]) / (-2 * s[0] * s[2])));
        angles[2] = Math.toDegrees(Math.acos((s[2] * s[2] - s[1] *
                s[1] - s[0] * s[0]) / (-2 * s[0] * s[1])));
        return angles;
    }

    //create shape of triangle (polygon)
    private void drawTriangle(Pane pane, ArrayList<Circle> point) {
        Polygon polygon = new Polygon();
        pane.getChildren().add(polygon);
        ObservableList<Double> points = polygon.getPoints();

        for (int i = 0; i < point.size(); i++) {
            points.add(point.get(i).getCenterX());
            points.add(point.get(i).getCenterY());
        }
        polygon.setFill(Color.WHITE);
        polygon.setStroke(Color.BLACK);
    }
}