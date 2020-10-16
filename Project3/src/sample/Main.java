package sample;
//package Project2-TranscationSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Layout.fxml"));
        primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();

        Label label = new Label("Account Type :");
        ToggleGroup tb = new ToggleGroup();
        RadioButton checkbutton = new RadioButton("Checking");
        RadioButton savingbutton = new RadioButton("Saving");
        RadioButton moneyMarketbutton = new RadioButton("Money Market");

        checkbutton.setToggleGroup(tb);
        savingbutton.setToggleGroup(tb);
        moneyMarketbutton.setToggleGroup(tb);
        HBox hbox = new HBox(label, checkbutton, savingbutton, moneyMarketbutton);
        primaryStage.setScene(new Scene(hbox, 400, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
