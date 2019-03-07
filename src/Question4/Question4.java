package Question4;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Question4 extends Application {

    Pane pane = new Pane();
    TextField textField = new TextField();
    VBox vBox = new VBox();

    @Override
    public void start(Stage stage) throws Exception{
        Label fileName = new Label("File name:", textField);
        fileName.setContentDisplay(ContentDisplay.RIGHT);
        textField.setPrefColumnCount(20);
        Button viewBtn = new Button("View");
        HBox hbox = new HBox(fileName,viewBtn);
        vBox.getChildren().addAll(pane,hbox);

        viewBtn.setOnAction(e ->{
           File file = new File(textField.getText());
            updateHistogram();
            vBox.setTranslateY(10);
            stage.sizeToScene();
            stage.setTitle("Question4");
        });
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setTitle("Question4");
        stage.sizeToScene();
        stage.show();
    }
    private void updateHistogram(){
        Histogram histogram = new Histogram(textField.getText());
        pane.getChildren().add(histogram);
    }
    public static void main(String[]args){
        launch(args);
    }
    private static class Histogram extends Pane{
        private char[] chars = new char[26];
        private int counts[]  = new int[26];
        private Rectangle[] rectangles = new Rectangle[26];
        private File file;
        GridPane gridPane;
        double width = 350;
        double height = 350;

        public Histogram(String filename){
            this.file = new File(filename.trim());
            setWidth(width);
            setHeight(height);
            readFile();
            drawHistogram();
        }
        private void readFile(){

            Scanner scanner;
            String string = "";
            try{
                scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    string+=scanner.nextLine();
                }
            }catch (IOException e){
            }
            string = string.toUpperCase();
            for (int i=0; i<string.length(); i++){
                char character = string.charAt(i);
                if(Character.isLetter(character)){
                    counts[character-'A']++;
                }
            }
        }

        private double getTotalCount(){
            double tot = 0;
            for(int count: counts){
                tot+=count;
            }
            return tot;
        }
        private void drawHistogram(){
            gridPane = new GridPane();
            double barWidth = width/chars.length;
            double tot = getTotalCount();
            for(int i = 0; i <counts.length; i++){
                chars[i]= (char)('A'+i);
                double percent = counts[i]/tot;
                double barHeight = height*percent;
                rectangles[i]= new Rectangle(barWidth, barHeight);
                Label label = new Label(chars[i]+ "", rectangles[i]);
                label.setContentDisplay(ContentDisplay.TOP);
                gridPane.add(label, i , 0);
                gridPane.setValignment(label, VPos.BASELINE);
            }
            getChildren().addAll(gridPane);
        }
        public int[] getCount(){
            return counts;
        }
        public void setCount(int[] counts)
        {
            this.counts= counts;
            drawHistogram();
        }

    }
}
