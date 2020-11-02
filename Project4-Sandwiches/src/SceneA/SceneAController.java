package SceneA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneAController implements Initializable {

    private double price;

    @FXML
    private ComboBox sandwichType;

    @FXML
    private ImageView image;

    @FXML
    private ListView<String> extraProvided, extraSelected;


    @FXML
    void SandwichSelect() {
        String type = sandwichType.getSelectionModel().getSelectedItem().toString();
        switch (type) {
            case "Beef" : {
                Image beef = new Image("/Images/Beef.jpg");
                image.setImage(beef);
                break;
            }
            case "Fish" : {
                Image fish = new Image("/Images/Fish.jpg");
                image.setImage(fish);
                break;
            }
            default:{
                Image chicken = new Image("/Images/Chicken.jpg");
                image.setImage(chicken);
                break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Chicken", "Beef", "Fish");
        sandwichType.setItems(list);
        sandwichType.setValue(list.get(0));


        ObservableList<String> extraList = FXCollections.observableArrayList("Bacon", "Pesto", "Pepper Jelly", "Pepper",
                "Mayonnaise", "Pickled Pepper", "Ranch", "Avocado", "Mozzarella", "Tomatoes", "walnuts");
        extraProvided.getItems().addAll(extraList);

        SandwichSelect();
    }


}
