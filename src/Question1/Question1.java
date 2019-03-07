package Question1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;


public class Question1 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a list of cards
        int card1 = (int)(Math.random()*54);
        int card2 = (int)(Math.random()*54);
        int card3 = (int)(Math.random()*54);
        // Create a HBox pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);

        String image1 = "image/Cards/" + String.valueOf(card1) + ".png";
        String image2 = "image/Cards/" + String.valueOf(card2) + ".png";
        String image3 = "image/Cards/" + String.valueOf(card3) + ".png";

        //Image view
        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);

        //add to gridpane
        gridPane.add(imageView1, 0, 0);
        gridPane.add(imageView2, 1, 0);
        gridPane.add(imageView3, 2, 0);

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Question 1"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

}
