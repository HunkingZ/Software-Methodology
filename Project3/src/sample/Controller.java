package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import management.*;

import java.io.*;
import java.util.Scanner;

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

        if (!ocaCheckValid(fName, lName, inputBalance, month, day, year)) {
            return;
        }

        Date openDate = parseDate(month, day, year);

        double balance;
        if (validateBalance(inputBalance) && openDate != null) {
            balance = Double.parseDouble(inputBalance);
        } else {
            return;
        }
        Profile holder = new Profile(fName, lName);


        String selectedAccountType = ((RadioButton) accountType.getSelectedToggle()).getText();

        switch (selectedAccountType) {
            case "Checking" -> {
                boolean isDD = oca_DirectDeposit.isSelected();
                Account newChecking = new Checking(holder, balance, openDate, isDD);
                if (newChecking != null) {
                    checkExist(newChecking);
                }
                break;
            }
            case "Savings" -> {
                boolean isLC = oca_LoyalCustomer.isSelected();
                Account newSaving = new Savings(holder, balance, openDate, isLC);
                if (newSaving != null) {
                    checkExist(newSaving);
                }
                break;
            }
            case "Money Market" -> {
                Account newMoneyMarket = new MoneyMarket(holder, balance, openDate, 0);
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


        if (!ocaCheckValid(fName, lName, "0", "0", "0", "0")) {
            return;
        }
        Profile holder = new Profile(fName, lName);
        String selectedAccountType = ((RadioButton) accountType.getSelectedToggle()).getText();

        Account target = findAccount(selectedAccountType, holder);

        if (target == null) {
            resultArea.setText("Account does not exist.\n");
            return;
        }
        if (database.remove(target)) {
            resultArea.setText("Account closed and removed from the database.\n");
        } else {
            resultArea.setText("Account does not exist.\n");
        }
    }

    @FXML
    void clear(ActionEvent event) {
        oca_fName.clear();
        oca_lName.clear();
        oca_month.clear();
        oca_day.clear();
        oca_year.clear();
        oca_balance.clear();
        dw_lName.clear();
        dw_fName.clear();
        dw_amount.clear();
        resultArea.clear();
    }

    @FXML
    void deposit(ActionEvent event) {
        String fName = dw_fName.getText();
        String lName = dw_lName.getText();
        String inputAmount = dw_amount.getText();

        if (!dwCheckValid(fName, lName, inputAmount)) {
            return;
        }

        Profile holder = new Profile(fName, lName);

        double amount;
        if (validateBalance(inputAmount)) {
            amount = Double.parseDouble(inputAmount);
        } else {
            return;
        }

        String selectedAccountType = ((RadioButton) accountManage.getSelectedToggle()).getText();
        Account target = findAccount(selectedAccountType, holder);
        if (database.deposit(target, amount)) {
            String out = String.format("%.2f deposited to account.\n", amount);
            resultArea.setText(out);
        } else {
            resultArea.setText("Account does not exist.\n");
        }

    }

    @FXML
    void withdraw(ActionEvent event) {
        String fName = dw_fName.getText();
        String lName = dw_lName.getText();
        String inputAmount = dw_amount.getText();

        if (!dwCheckValid(fName, lName, inputAmount)) {
            return;
        }

        Profile holder = new Profile(fName, lName);

        double amount;
        if (validateBalance(inputAmount)) {
            amount = Double.parseDouble(inputAmount);
        } else {
            return;
        }

        String selectedAccountType = ((RadioButton) accountManage.getSelectedToggle()).getText();
        Account target = findAccount(selectedAccountType, holder);
        if (database.withdrawal(target, amount) == 0) {
            String out = String.format("%.2f withdrawn from account.\n", amount);
            resultArea.setText(out);
        } else if (database.withdrawal(target, amount) == 1) {
            resultArea.setText("Insufficient funds.\n");
        } else { resultArea.setText("Account does not exist.\n"); }
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

    @FXML
    void resP(ActionEvent event) {
        String printResult = database.printAccounts();
        if (printResult == null) {
            resultArea.setText("Database is empty.\n");
            return;
        }
        resultArea.setText(printResult + "\n");
    }

    @FXML
    void resPName(ActionEvent event) {
        String printResult = database.printByLastName();
        if (printResult == null) {
            resultArea.setText("Database is empty.\n");
            return;
        }
        resultArea.setText(printResult + "\n");
    }

    @FXML
    void resPDate(ActionEvent event) {
        String printResult = database.printByDateOpen();
        if (printResult == null) {
            resultArea.setText("Database is empty.\n");
            return;
        }
        resultArea.setText(printResult + "\n");
    }

    @FXML
    void importFile(ActionEvent event) {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Source File for the import");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File sourceFile = chooser.showOpenDialog(stage);

            Scanner databaseParser = new Scanner(sourceFile);
            AccountDatabase acc = database;
            database = new AccountDatabase();

            while (databaseParser.hasNextLine()) {
                String inputLine = databaseParser.nextLine();
                if (inputLine.split(",").length != 6) {
                    resultArea.setText("Import File Format is Wrong. Remain previous Database.");
                    database = acc;
                    return;
                }
                String[] accInfo = inputLine.split(",");
                String type = accInfo[0];
                String fName = accInfo[1];
                String lName = accInfo[2];
                Profile holder = new Profile(fName, lName);
                double balance = Double.parseDouble(accInfo[3]);
                String[] dateInfo = accInfo[4].split("/");
                String month = dateInfo[0];
                String day = dateInfo[1];
                String year = dateInfo[2];
                Date dateOpen = parseDate(month, day, year);

                switch (type) {
                    case "S" -> {
                        boolean isLoyal = Boolean.parseBoolean(accInfo[5]);
                        database.add(new Savings(holder, balance, dateOpen, isLoyal));
                        break;
                    }
                    case "C" -> {
                        boolean isDeposit = Boolean.parseBoolean(accInfo[5]);
                        database.add(new Checking(holder, balance, dateOpen, isDeposit));
                        break;
                    }
                    case "M" -> {
                        int withdrawals = Integer.parseInt(accInfo[5]);
                        database.add(new MoneyMarket(holder, balance, dateOpen, withdrawals));
                        break;
                    }
                }
            }
            databaseParser.close();
            resultArea.setText("Successfully Imported File.");
        } catch (FileNotFoundException e) {
            resultArea.setText("Import File Failed.");
        } catch (NullPointerException e) {
            resultArea.setText("Please Select a Import File.");
        }
    }

    @FXML
    void exportFile(ActionEvent event) {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open target File for the export");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File targetFile = chooser.showSaveDialog(stage);

            BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile, true));

            for (int i = 0; i < database.getSize(); i++) {
                Account account = database.getAccountByIndex(i);
                String type = account.getType();
                String fName = account.getfName();
                String lName = account.getlName();
                Double balance = account.getBalance();
                Date accDate = account.getDate();
                String special = account.getSpecialValue();
                switch (type) {
                    case "Savings" -> {
                        writer.append("S,");
                        break;
                    }
                    case "Checking" -> {
                        writer.append("C,");
                        break;
                    }
                    case "Money Market" -> {
                        writer.append("M,");
                        break;
                    }
                }
                writer.append(String.format("%s,%s,%.2f,%s,%s\n", fName, lName, balance, accDate, special));
            }
            writer.close();
            resultArea.setText("Successfully Export the File.");
        } catch (IOException e) {
            resultArea.setText("Export File Failed.");
        } catch (NullPointerException e) {
            resultArea.setText("Please Select a File to Export.");
        }
    }

    @FXML
    void clearDatabase() {
        database = new AccountDatabase();
        resultArea.setText("Database cleared!");
    }

    public Account findAccount(String type, Profile holder) {
        if (database.getSize() == 0) {
            return null;
        }

        switch (type) {
            case "Checking" -> { return new Checking(holder, -1, null, false); }
            case "Savings" -> { return new Savings(holder, -1, null, false); }
            case "Money Market" -> { return new MoneyMarket(holder, -1, null, 0); }
            default -> { return null; }
        }
    }

    private Account makeAccount(String type, Profile holder, double balance, Date openDate) {
        switch (type) {
            case "Checking" -> {
                boolean isDD = oca_DirectDeposit.isSelected();
                return new Checking(holder, balance, openDate, isDD);
            }
            case "Savings" -> {
                boolean isLC = oca_LoyalCustomer.isSelected();
                return new Savings(holder, balance, openDate, isLC);
            }
            case "Money Market" -> {
                return new MoneyMarket(holder, balance, openDate, 0);
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
            resultArea.appendText("Balance/Amount is Invalid!\n");
            return false;
        }
    }

    private void checkExist(Account account) {
        if (!database.add(account)) {
            resultArea.setText("Account is already in the database.\n");
        } else {
            resultArea.setText("Account opened and added to the database.\n");
        }
    }

    private boolean ocaCheckValid(String fName, String lName, String inputBalance,
                                  String month, String day, String year) {
        resultArea.clear();
        boolean result = true;
        if (fName.equals("")) {
            resultArea.appendText("First Name is Empty!\n");
            result = false;
        }
        if (lName.equals("")) {
            resultArea.appendText("Last Name is Empty!\n");
            result = false;
        }
        if (month.equals("") || day.equals("") || year.equals("")) {
            resultArea.appendText("Date is Invalid!\n");
            result = false;
        }
        if (inputBalance.equals("")) {
            resultArea.appendText("Balance is Empty!\n");
            result = false;
        }
        return result;
    }

    private boolean dwCheckValid(String fName, String lName, String Amount) {
        boolean result = true;

        resultArea.clear();
        if (fName.equals("")) {
            resultArea.appendText("First Name is Empty!\n");
            result = false;
        }
        if (lName.equals("")) {
            resultArea.appendText("Last Name is Empty!\n");
            result = false;
        }
        if (Amount.equals("")) {
            resultArea.appendText("Amount is Empty!\n");
            result = false;
        }
        return result;
    }
}