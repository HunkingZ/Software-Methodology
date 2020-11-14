package SceneA;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 */
public class Main extends Application {

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sceneALayout.fxml"));
        primaryStage.setTitle("Sandwich Order");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
