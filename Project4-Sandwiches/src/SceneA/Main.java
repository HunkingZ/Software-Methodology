package SceneA;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Primary window initialization class
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Main extends Application {

    /**
     * Initial window loading method
     *
     * @param primaryStage Stage to load on launch
     * @throws Exception if failed to load fxml information
     */
    @Override public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sceneALayout.fxml"));
        primaryStage.setTitle("Sandwich Order");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    /**
     * Program application executable
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
