package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane pane = FXMLLoader.load(Main.class.getResource("sample.fxml"));


        Scene scene = new Scene(pane);
        primaryStage.setTitle("Project59");
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();


    }



    public static void main(String[] args) {

        launch(args);
    }
}
