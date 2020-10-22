package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

//import java.awt.event.ActionEvent;

public class Controller {
    @FXML
    private TextField oca_fName, oca_lName, oca_balance, oca_month, oca_day, oca_year,
                        dw_fName, dw_lName, dw_amount;
    //private RadioButton oca_Checking, oca_Savings, oca_MoneyMarket, dw_Checking, dw_Savings, dw_MoneyMarket;
    //private CheckBox oca_DirectDeposit, oca_LoyalCustomer;
    @FXML
    void openAccount(ActionEvent event) {
        String fName = oca_fName.getText();
        String lName = oca_lName.getText();
        double balance = Double.parseDouble(oca_balance.getText());
        int month = Integer.parseInt(oca_month.getText());
        int day = Integer.parseInt(oca_day.getText());
        int year = Integer.parseInt(oca_year.getText());
        //boolean isChecking = Boolean.parseBoolean(oca_Checking.getText());
        //boolean isSavings = Boolean.parseBoolean(oca_Savings.getText());

    }

    @FXML
    void closeAccount(ActionEvent event) {

    }

    @FXML
    void clear(ActionEvent event) {

    }

    @FXML
    void deposit(ActionEvent event) {

    }

    @FXML
    void withdraw(ActionEvent event) {

    }
}
