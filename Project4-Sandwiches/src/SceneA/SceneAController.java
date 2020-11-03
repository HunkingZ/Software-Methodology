package SceneA;

import Management.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SceneAController implements Initializable {

    private Sandwich sandwich;
    private Order database = new Order();
    private OrderLine orderLine;
    private int lineNum = 0;
    private double price;
    private ArrayList<String> selectedExtra;

    @FXML
    private ComboBox sandwichType;

    @FXML
    private ImageView image;

    @FXML
    private ListView<String> extraProvided, extraSelected;

    @FXML
    private TextArea select_textArea, select_basic, select_price;

    @FXML
    void SandwichSelect() {
        String type = sandwichType.getSelectionModel().getSelectedItem().toString();
        switch (type) {
            case "Beef" : {
                selectedExtra = new ArrayList<>();
                extraSelected.getItems().clear();
                Image beef = new Image("/Images/Beef.jpg");
                image.setImage(beef);
                sandwich = new Beef();
                price = sandwich.price();
                String textPrice = String.format("$%.2f", price);
                select_price.setText(textPrice);
                String basics = getBasic(sandwich);
                select_basic.setText(basics);
                break;
            }
            case "Fish" : {
                selectedExtra = new ArrayList<>();
                extraSelected.getItems().clear();
                Image fish = new Image("/Images/Fish.jpg");
                image.setImage(fish);
                sandwich = new Fish();
                price = sandwich.price();
                String textPrice = String.format("$%.2f", price);
                select_price.setText(textPrice);
                String basics = getBasic(sandwich);
                select_basic.setText(basics);
                break;
            }
            default:{
                selectedExtra = new ArrayList<>();
                extraSelected.getItems().clear();
                Image chicken = new Image("/Images/Chicken.jpg");
                image.setImage(chicken);
                sandwich = new Chicken();
                price = sandwich.price();
                String textPrice = String.format("$%.2f", price);
                select_price.setText(textPrice);
                String basics = getBasic(sandwich);
                select_basic.setText(basics);
                break;
            }
        }
    }

    @FXML
    void addExtra() {
        ObservableList<String> list = FXCollections.observableArrayList();
        String selectExtra = extraProvided.getSelectionModel().getSelectedItem();

        if (selectedExtra.contains(selectExtra)) {
            /*
            selectedExtra.remove(selectExtra);
            Extra extra = new Extra(selectExtra);
            sandwich.remove(extra);
            price = sandwich.price();
            String textPrice = String.format("$%.2f", price);
            select_price.setText(textPrice);
            select_textArea.setText(selectExtra + " Removed!");
             */
            select_textArea.setText(selectExtra + " Can Not Be Added More Than Once.");
        } else {
            if (selectedExtra.size() >= 6) {
                select_textArea.setText("You Can Not Add More Than 6 Extra Ingredients.");
                return;
            }
            selectedExtra.add(selectExtra);
            Extra extra = new Extra(selectExtra);
            sandwich.add(extra);
            price = sandwich.price();
            String textPrice = String.format("$%.2f", price);
            select_price.setText(textPrice);
            select_textArea.setText(selectExtra + " Added!");
        }
        for (String s : selectedExtra) {
            list.add(s);
        }
        extraSelected.setItems(list);
    }

    @FXML
    void removeExtra() {
        ObservableList<String> list = FXCollections.observableArrayList();
        String delete = extraSelected.getSelectionModel().getSelectedItem();
        if (selectedExtra.contains(delete)) {
            selectedExtra.remove(delete);
            Extra extra = new Extra(delete);
            sandwich.remove(extra);
            price = sandwich.price();
            String textPrice = String.format("$%.2f", price);
            select_price.setText(textPrice);
            select_textArea.setText(delete + " Removed!");
        } else {
            select_textArea.setText(delete + " Is Not In the List.");
        }
        for (String s : selectedExtra) {
            list.add(s);
        }
        extraSelected.setItems(list);
    }

    @FXML
    void select_add() {
        if (sandwich == null) {
            select_textArea.setText("Please Select Your Sandwich.");
            return;
        }
        orderLine = new OrderLine(lineNum, sandwich, sandwich.price());
        database.add(orderLine);
        select_textArea.setText(sandwich.getType() + " Added to Your Shopping Cart.");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Chicken", "Beef", "Fish");
        sandwichType.setItems(list);
        sandwichType.setValue(list.get(0));

        ObservableList<String> extraList = FXCollections.observableArrayList("Bacon", "Pesto",
                "Pepper Jelly", "Pepper", "Mayonnaise", "Pickled Pepper",
                "Ranch", "Avocado", "Mozzarella", "Tomatoes", "walnuts");
        extraProvided.getItems().addAll(extraList);
        SandwichSelect();
    }

    private String getBasic(Sandwich sand) {
        ArrayList<String> basicList = sand.getBasic();
        StringBuilder sb = new StringBuilder();
        for (String basic : basicList) {
            sb.append(basic + "\n");
        }
        return sb.substring(0, sb.length());
    }
}
