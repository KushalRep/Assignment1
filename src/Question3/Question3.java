package Question3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Question3 extends Application {

    Pane pane = new Pane();

    @Override
    public void start(Stage stage) {
        Circle circle = new Circle(200, 200, 100);
        pane.getChildren().add(circle);

        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        Circle[] circlePoints = new Circle[3]; // 3 points
        Line[] line = new Line[3];//3 lines
        Text[] text = new Text[3];//text for angle

        for (int i = 0; i < circlePoints.length; i++) {
            text[i] = new Text();
            circlePoints[i] = new Circle(0, 0, 5);
            setRandomLocation(circlePoints[i], circle);
            final int index = i;
            circlePoints[i].setOnMouseDragged(e -> {
                double radValue = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());
                double x = circle.getCenterX() + circle.getRadius() * Math.cos(radValue);
                double y = circle.getCenterY() + circle.getRadius() * Math.sin(radValue);
                circlePoints[index].setCenterX(x);
                circlePoints[index].setCenterY(y);
                updateLineLocation(line,circlePoints,text);
            });
        }
        for (int i = 0; i < line.length; i++) {

            int circleIndex = (i + 1 >= circlePoints.length) ? 0 : i + 1;
            line[i] = new Line(circlePoints[i].getCenterX(), circlePoints[i].getCenterY(),
                    circlePoints[circleIndex].getCenterX(), circlePoints[circleIndex].getCenterY());
        }
        updateLineLocation(line, circlePoints, text);

        pane.getChildren().addAll(line);
        pane.getChildren().addAll(text);
        pane.getChildren().addAll(circlePoints);
        stage.setScene(new Scene(pane, 200, 200)); // place scene
        stage.setTitle("Question3");//stage title
        stage.show();//display stage
    }

    private void updateLineLocation(Line[] line, Circle[] k, Text[] angle) {

        for (int i = 0; i < line.length; i++) {
            int circleIndex = (i + 1 >= k.length) ? 0 : i + 1;
            line[i].setStartX(k[i].getCenterX());
            line[i].setStartY(k[i].getCenterY());
            line[i].setEndX(k[circleIndex].getCenterX());
            line[i].setEndY(k[circleIndex].getCenterY());
            angle[i].setX(k[i].getCenterX() + 5);
            angle[i].setY(k[i].getCenterY() - 5);
        }
        //calc angles
        double a = distanceCalc(line[0]);
        double b = distanceCalc(line[1]);
        double c = distanceCalc(line[2]);
        double A = Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
        angle[2].setText(String.format("%.1f", A));
        double B = Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
        angle[0].setText(String.format("%.1f", B));
        double C = Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b)));
        angle[1].setText(String.format("%.1f", C));
    }

    private void setRandomLocation(Circle cPoint, Circle c) {
        double angle = Math.random() * 360;
        double x = c.getCenterX() + c.getRadius() * Math.cos(Math.toRadians(angle));
        double y = c.getCenterY() + c.getRadius() * Math.sin(Math.toRadians(angle));
        cPoint.setCenterX(x);
        cPoint.setCenterY(y);
    }

    private static double distanceCalc(Line line) {
        double x1 = line.getStartX();
        double y1 = line.getStartY();
        double x2 = line.getEndX();
        double y2 = line.getEndY();
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
