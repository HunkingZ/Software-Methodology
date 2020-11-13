package SceneB;

import Management.*;
import SceneA.SceneAController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class SceneBController{

    private ObservableList<String> orderList = FXCollections.observableArrayList();
    private SceneAController controllerA;
    @FXML
    private ListView<String> OD_ListView;
    @FXML
    private TextArea OD_textArea;
    @FXML
    private Label OD_orderTotal;

    @FXML
    void OD_Copy() {
        try {
            int index = OD_ListView.getSelectionModel().getSelectedIndex();
            Order database = controllerA.getDatabase();
            OrderLine orderLine = new OrderLine(database.get(index));
            database.add(orderLine);
            controllerA.setDatabase(reformatOrder(database));
            OD_textArea.setText("Successfully Added The Same OrderLine!");
            start();
        } catch (Exception e) {
            OD_textArea.setText("Please Select a OrderLine to Add.");
        }
    }

    @FXML
    void OD_Remove() {
        try {
            int index = OD_ListView.getSelectionModel().getSelectedIndex();
            Order database = controllerA.getDatabase();
            OrderLine orderLine = new OrderLine(database.get(index));
            database.remove(orderLine);
            controllerA.setDatabase(reformatOrder(database));
            start();
        } catch (Exception e) {
            OD_textArea.setText("Please Select a OrderLine to Remove.");
        }
    }

    @FXML
    void back() throws IOException {
        this.controllerA.closeSceneB();
    }

    @FXML
    void clear() {
        controllerA.setDatabase(new Order());
        start();
    }

    @FXML
    void export() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open target File for the export");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File targetFile = chooser.showSaveDialog(stage);

            BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile, false));

            Order data = controllerA.getDatabase();
            for (int i = 0; i < data.size(); i++) {
                OrderLine orderLine = data.get(i);
                String sandwichInfo = orderLine.toString();
                writer.append(sandwichInfo + "\n");
            }

            writer.close();
            OD_textArea.setText("Successfully Export the File.");
        } catch (IOException e) {
            OD_textArea.setText("Export File Failed.");
        } catch (NullPointerException e) {
            OD_textArea.setText("Please Select a File to Export.");
        }
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
        double total = 0;
        for (int i = 0; i < controllerA.getDatabase().size(); i++)  {
            total += controllerA.getDatabase().get(i).getPrice();
        }
        OD_orderTotal.setText(String.format("%.2f", total));
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
