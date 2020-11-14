package SceneA;

import Management.*;
import SceneB.SceneBController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 */
public class SceneAController implements Initializable {

    private Stage stageB = new Stage();
    private Sandwich sandwich;
    private Order database = new Order();
    private OrderLine orderLine;
    private double price;
    private ArrayList<String> selectedExtra;
    private final int MAX_EXTRA_INGREDIENTS = 6;

    private final ObservableList<String> initializedExtraList = FXCollections.observableArrayList("Bacon", "Pesto",
            "Pepper Jelly", "Pepper", "Mayonnaise", "Pickled Pepper",
            "Ranch", "Avocado", "Mozzarella", "Tomatoes", "walnuts");
    private ObservableList<String> availableIngredientList = FXCollections.observableArrayList(initializedExtraList);
    private ObservableList<String> selectedIngredientList = FXCollections.observableArrayList();

    @FXML private ComboBox sandwichType;
    @FXML private ImageView image;
    @FXML private ListView<String> extraProvided, extraSelected;
    @FXML private TextArea select_textArea;
    @FXML private Label select_basic, select_price;

    /**
     *
     */
    @FXML void SandwichSelect() {
        String type = sandwichType.getSelectionModel().getSelectedItem().toString();
        switch (type) {
            case "Beef" : {
                selectInitialize();
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
                selectInitialize();
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
                selectInitialize();
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

    /**
     *
     */
    @FXML void addExtra() {
        String selectExtra = extraProvided.getSelectionModel().getSelectedItem();

        if (selectedExtra.contains(selectExtra)) {
            select_textArea.setText(selectExtra + " Can Not Be Added More Than Once.");
        } else {
            if (selectedExtra.size() >= MAX_EXTRA_INGREDIENTS) {
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
        selectedIngredientList.add(selectExtra);
        availableIngredientList.remove(selectExtra);
        extraProvided.setItems(availableIngredientList);
        extraSelected.setItems(selectedIngredientList);
    }

    /**
     *
     */
    @FXML void removeExtra() {
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
            select_textArea.setText("There is no extra could be removed.");
            return;
        }
        selectedIngredientList.remove(delete);
        availableIngredientList.add(delete);
        extraSelected.setItems(selectedIngredientList);
        extraProvided.setItems(availableIngredientList);
    }

    /**
     *
     */
    @FXML void select_add() {
        if (sandwich == null) {
            select_textArea.setText("Please Select Your Sandwich.");
            return;
        }

        orderLine = new OrderLine(database.lineNumber, sandwich, sandwich.price());
        database.lineNumber++;
        database.add(orderLine);
        select_textArea.setText(sandwich.getType() + " Sandwich Added to Your Shopping Cart.");
        SandwichSelect();
    }

    /**
     *
     * @throws IOException
     */
    @FXML void openSceneB() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../SceneB/sceneBLayout.fxml"));
        Parent orderDetails = loader.load();
        SceneBController controllerB = loader.getController();
        controllerB.setController(this);
        Scene scene = new Scene(orderDetails, 600, 400);

        stageB.setTitle("Order Details");
        stageB.setScene(scene);
        stageB.show();
        controllerB.start();
    }

    /**
     *
     * @throws IOException
     */
    public void closeSceneB() throws IOException {
        stageB.close();
    }

    /**
     *
     * @param sand
     * @return
     */
    private String getBasic(Sandwich sand) {
        ArrayList<String> basicList = sand.getBasic();
        StringBuilder sb = new StringBuilder();

        for (String basic : basicList) {
            sb.append(basic + "\n");
        }
        return sb.toString();
    }

    /**
     *
     */
    private void selectInitialize() {
        selectedExtra = new ArrayList<>();
        extraSelected.getItems().clear();
        availableIngredientList = FXCollections.observableArrayList(initializedExtraList);
        extraProvided.setItems(availableIngredientList);
    }

    /**
     *
     * @return
     */
    public Order getDatabase() {
        return database;
    }

    /**
     *
     * @param order
     */
    public void setDatabase(Order order) {
        database = order;
    }

    /**
     *
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Chicken", "Beef", "Fish");
        sandwichType.setItems(list);
        sandwichType.setValue(list.get(0));
        extraProvided.getItems().addAll(availableIngredientList);
        SandwichSelect();
    }
}
