package sample;
//package Project2-TranscationSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
        primaryStage.setTitle("Transaction Manager");
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
