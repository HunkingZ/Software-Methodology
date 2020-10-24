package sample;

import javafx.beans.binding.BooleanBinding;
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
    private Button oca_open, oca_close;

    @FXML
    void openAccount(ActionEvent event) {
        String fName = oca_fName.getText();
        String lName = oca_lName.getText();
        String inputBalance = oca_balance.getText();
        String month = oca_month.getText();
        String day = oca_day.getText();
        String year = oca_year.getText();

        if (!oca_checkValid(fName, lName, inputBalance, month, day, year)) {
            resultArea.appendText("Invalid Input\n");
            return;
        }
        double balance;
        if (validateBalance(inputBalance)) {
            balance = Double.parseDouble(inputBalance);
        } else {
            return;
        }
        Profile holder = new Profile(fName, lName);
        Date openDate = parseDate(month, day, year);
        if (openDate == null) {
            return;
        }

        String selectedAccountType = ((RadioButton) accountType.getSelectedToggle()).getText();

        switch (selectedAccountType) {
            case "Checking": {
                boolean isDD = oca_DirectDeposit.isSelected();
                Account newChecking = new Checking(holder, balance, openDate, isDD);
                if (newChecking != null) {
                    checkExist(newChecking);
                }
                break;
            }
            case "Savings": {
                boolean isLC = oca_LoyalCustomer.isSelected();
                Account newSaving = new Savings(holder, balance, openDate, isLC);
                if (newSaving != null) {
                    checkExist(newSaving);
                }
                break;
            }
            case "Money Market": {
                Account newMoneyMarket = new MoneyMarket(holder, balance, openDate);
                if (newMoneyMarket != null) {
                    checkExist(newMoneyMarket);
                }
                break;
            }

        }


    }

    @FXML
    void closeAccount(ActionEvent event) {
        String fName = oca_fName.getText();
        String lName = oca_lName.getText();


        if (!oca_checkValid(fName, lName, "0", "0", "0", "0")) {
            resultArea.appendText("Invalid Input\n");
            return;
        }
        Profile holder = new Profile(fName, lName);
        String selectedAccountType = ((RadioButton) accountType.getSelectedToggle()).getText();

        Account target = findAccount(selectedAccountType, holder);

        if (target == null) {
            resultArea.appendText("Account does not exits.\n");
            return;
        }
        if (database.remove(target)) {
            resultArea.appendText("Account closed and removed from the database.\n");
        } else {
            resultArea.appendText("Account does not exist.\n");
        }
    }

    @FXML
    void clear(ActionEvent event) {
        resultArea.clear();
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

        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(oca_Checking.textProperty(),
                        oca_Savings.textProperty(),
                        oca_MoneyMarket.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (!oca_Checking.isSelected()
                        && !oca_Savings.isSelected()
                        && !oca_MoneyMarket.isSelected());
            }
        };

        oca_open.disableProperty().bind(bb);
        oca_close.disableProperty().bind(bb);

        /*
        oca_open.setDisable(oca_Checking.isSelected() || oca_Savings.isSelected()
                || oca_MoneyMarket.isSelected());
        oca_close.setVisible(oca_Checking.isSelected() || oca_Savings.isSelected()
                || oca_MoneyMarket.isSelected());
         */

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

    @FXML
    void resP(ActionEvent event) {
        String printResult = database.printAccounts();
        if (printResult == null) {
            resultArea.appendText("Database is empty.\n");
            return;
        }
        resultArea.appendText(printResult + "\n");
    }

    @FXML
    void resPName(ActionEvent event) {
        String printResult = database.printByLastName();
        if (printResult == null) {
            resultArea.appendText("Database is empty.\n");
            return;
        }
        resultArea.appendText(printResult + "\n");
    }

    @FXML
    void resPDate(ActionEvent event) {
        String printResult = database.printByDateOpen();
        if (printResult == null) {
            resultArea.appendText("Database is empty.\n");
            return;
        }
        resultArea.appendText(printResult + "\n");
    }

    public Account findAccount(String type, Profile holder) {
        if (database.getSize() == 0) {
            return null;
        }

        switch (type) {
            case "Checking" -> { return new Checking(holder, -1, null, false); }
            case "Savings" -> { return new Savings(holder, -1, null, false); }
            case "Money Market" -> { return new MoneyMarket(holder, -1, null); }
            default -> { return null; }
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
            resultArea.appendText(date.toString() + " is not a valid date!\n");
            return null;
        } catch (NumberFormatException e) {
            resultArea.appendText("Invalid date input\n");
            return null;
        }
    }

    private boolean validateBalance(String stringBalance) {
        try {
            double balance = Double.parseDouble(stringBalance);
            return true;
        } catch (NumberFormatException e) {
            resultArea.appendText("Invalid balance input\n");
            return false;
        }
    }

    private void checkExist(Account account) {
        if (!database.add(account)) {
            resultArea.appendText("Account is already in the database.\n");
        } else {
            resultArea.appendText("Account opened and added to the database.\n");
        }
    }

    private boolean oca_checkValid(String fName, String lName, String inputBalance,
                                   String month, String day, String year) {
        if (fName.equals("") || lName.equals("") || inputBalance.equals("")
                || month.equals("") || day.equals("") || year.equals("")) {
            return false;
        }
        return true;
    }
}
