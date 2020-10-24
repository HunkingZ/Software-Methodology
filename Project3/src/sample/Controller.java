package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import management.*;

//import java.awt.event.ActionEvent;

public class Controller {
    private AccountDatabase database = new AccountDatabase();

    @FXML
    private TextField oca_fName, oca_lName, oca_balance, oca_month, oca_day, oca_year,
                        dw_fName, dw_lName, dw_amount;

    @FXML
    private ToggleGroup accountType, accountManage;

    @FXML
    private RadioButton oca_Checking, oca_Savings, oca_MoneyMarket, dw_Checking, dw_Savings, dw_MoneyMarket;

    @FXML
    private CheckBox oca_DirectDeposit, oca_LoyalCustomer;

    @FXML
    private TextArea resultArea;




    @FXML
    void openAccount(ActionEvent event) {
        String fName = oca_fName.getText();
        String lName = oca_lName.getText();
        double balance = Double.parseDouble(oca_balance.getText());
        String month = oca_month.getText();
        String day = oca_day.getText();
        String year = oca_year.getText();
        Profile holder = new Profile(fName, lName);
        Date openDate = parseDate(month, day, year);

        /*
        boolean isChecking = Boolean.parseBoolean(oca_Checking.getText());
        boolean isSavings = Boolean.parseBoolean(oca_Savings.getText());
        boolean isDirect = Boolean.parseBoolean(oca_DirectDeposit.getText());
        boolean isLoyal = Boolean.parseBoolean(oca_LoyalCustomer.getText());
         */
        String selectedAccountType = ((RadioButton) accountType.getSelectedToggle()).getText();

        switch (selectedAccountType) {
            case "Checking": {
                boolean isDD = oca_DirectDeposit.isSelected();
                Account newChecking = new Checking(holder, balance, openDate, isDD);
                if (newChecking != null) {
                    checkExist(newChecking);
                }
            }
            case "Savings": {
                boolean isLC = oca_LoyalCustomer.isSelected();
                Account newSaving = new Savings(holder, balance, openDate, isLC);
                if (newSaving != null) {
                    checkExist(newSaving);
                }
            }
            case "Money Market": {
                Account newMoneyMarket = new MoneyMarket(holder, balance, openDate);
                if (newMoneyMarket != null) {
                    checkExist(newMoneyMarket);
                }
            }
        }


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

    @FXML
    void handleBind(ActionEvent event) {
        oca_LoyalCustomer.setDisable(oca_Checking.isSelected());
        oca_DirectDeposit.setDisable(oca_Savings.isSelected());
        if (oca_MoneyMarket.isSelected()) {
            oca_LoyalCustomer.setDisable(true);
            oca_DirectDeposit.setDisable(true);
        }
        if (oca_Checking.isSelected()) {
            oca_LoyalCustomer.setSelected(false);
        } else if (oca_Savings.isSelected()) {
            oca_DirectDeposit.setSelected(false);
        } else {
            oca_DirectDeposit.setSelected(false);
            oca_LoyalCustomer.setSelected(false);
        }
    }

    private Account makeAccount(String type, Profile holder, double balance, Date openDate) {
        switch (type) {
            case "Checking": {
                boolean isDD = oca_DirectDeposit.isSelected();
                return new Checking(holder, balance, openDate, isDD);
            }
            case "Savings": {
                boolean isLC = oca_LoyalCustomer.isSelected();
                return new Savings(holder, balance, openDate, isLC);
            }
            case "Money Market": {
                return new MoneyMarket(holder, balance, openDate);
            }
        }
        return null;
    }

    private Date parseDate(String month, String day, String year) {
        try {
            int intMonth = Integer.parseInt(month);
            int intDay = Integer.parseInt(day);
            int intYear = Integer.parseInt(year);

            Date date = new Date(intMonth, intDay, intYear);
            if (date.isValid()) {
                return date;
            }
            System.out.println(date.toString() + " is not a valid date!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid date input");
        }
        return null;
    }

    private void checkExist(Account account) {
        if (!database.add(account)) {
            resultArea.appendText("Account is already in the database.\n");
        } else {
            resultArea.appendText("Account opened and added to the database.\n");
        }
    }
}
