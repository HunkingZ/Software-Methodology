package SceneB;

import Management.*;
import SceneA.SceneAController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;


public class SceneBController{

    private ObservableList<String> orderList = FXCollections.observableArrayList();
    private SceneAController controllerA;
    @FXML
    private ListView<String> OD_ListView;

    @FXML
    void OD_Copy() {
        int index = OD_ListView.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        Order database = controllerA.getDatabase();
        OrderLine orderLine = new OrderLine(database.get(index));
        System.out.println(orderLine.toString() + "\n");
        database.add(orderLine);
        for (int i = 0; i < database.size(); i++) {
            System.out.println(database.get(i).toString());
        }
        controllerA.setDatabase(reformatOrder(database));
        start();
    }

    @FXML
    void OD_Remove() {

    }

    @FXML
    void back() {

    }

    @FXML
    void clear() {

    }

    @FXML
    void export() {

    }


    public void setController(SceneAController controller) {
        controllerA = controller;
    }

    public void start() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> list = getOrderDetails(controllerA.getDatabase());
        for (String st : list) {
            observableList.add(st);
        }
        OD_ListView.setItems(observableList);
    }

    private ArrayList<String> getOrderDetails(Order database) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < database.size(); i++) {
            result.add(database.get(i).toString());
        }
        return result;
    }

    private Order reformatOrder(Order database) {
        for (int i = 0; i < database.size(); i++) {
            database.get(i).setLineNumber(i + 1);
        }
        return database;
    }
}
