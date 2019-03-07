package Question2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class Question2 extends Application {
    private TextField investment = new TextField();
    private TextField numOfYears = new TextField();
    private TextField interestRate = new TextField();
    private TextField futureValue = new TextField();
    private Button calc = new Button("Calculate");

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create UI
        GridPane pane = new GridPane();
        pane.setVgap(5);
        pane.setHgap(5);
        pane.add(new Label("Investment Amount:"), 0, 0);
        pane.add(investment, 1, 0);
        pane.add(new Label("Number Of Years:"), 0, 1);
        pane.add(numOfYears, 1, 1);
        pane.add(new Label("Annual Interest Rate:"), 0, 2);
        pane.add(interestRate, 1, 2);
        pane.add(new Label("Future value:"), 0, 3);
        pane.add(futureValue, 1, 3);
        pane.add(calc, 1, 4);


        // Process events
        calc.setOnAction(e -> futureValue());

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Question2"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    private void futureValue() {
        double investmentAmount = Double.parseDouble(investment.getText());
        int years = Integer.parseInt(numOfYears.getText());
        double monthlyInterestRate =
                Double.parseDouble(interestRate.getText()) / 1200;
        futureValue.setText(String.format("$%.1f",
                (investmentAmount * Math.pow(1 + monthlyInterestRate, years * 12))));
    }
}
