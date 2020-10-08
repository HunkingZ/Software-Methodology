import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

/**
 * UI for I/O handling
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class TransactionManager {
    public AccountDatabase database = new AccountDatabase();
    public void run() {
        System.out.println("Transaction processing starts.....");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        Stream:
        while (input != null) {
            Scanner arguments = new Scanner(input); //scanner to help parse the current user input
            String command = "\0"; //in case of no command, sends to default switch case

            if (arguments.hasNext()) { command = arguments.next(); }

            switch (command) {
                case "OC": { //open a checking account
                    String check = "Checking";
                    Account ac = createAccount(arguments, check);
                    if (ac != null) {
                        if (!database.add(ac)) {
                            System.out.println("Account is already in the database.");
                        } else {
                            System.out.println("Account opened and added to the database.");
                        }
                    }
                    break;
                }
                case "OS": { //open a savings account
                    String saving = "Savings";
                    Account ac = createAccount(arguments, saving);
                    if (ac != null) {
                        if (!database.add(ac)) {
                            System.out.println("Account is already in the database.");
                        } else {
                            System.out.println("Account opened and added to the database.");
                        }
                    }
                    break;
                }
                case "OM": { //open a money market account
                    String moneymarket = "Money Market";
                    Account ac = createAccount(arguments, moneymarket);
                    if (ac != null) {
                        if (!database.add(ac)) {
                            System.out.println("Account is already in the database.");
                        } else {
                            System.out.println("Account opened and added to the database.");
                        }
                    }
                    break;
                }
                case "CC": { //close a checking account associated with the name
                    closeAccount(arguments, "Checking");
                    break;
                }
                case "CS": { //close a savings account associated with the name
                    closeAccount(arguments, "Savings");
                    break;
                }
                case "CM": { //close a savings account associated with the name
                    closeAccount(arguments, "Money Market");
                    break;
                }
                case "DC": { //deposit money to a checking account associated with the name
                    depositAccount(arguments, "Checking");
                    break;
                }
                case "DS": { //deposit money to a savings account associated with the name
                    depositAccount(arguments, "Savings");
                    break;
                }
                case "DM": { //deposit money to a money market account associated with the name
                    depositAccount(arguments, "Money Market");
                    break;
                }
                case "WC": { //withdraw money from a checking account associated with the name
                    withdrawAccount(arguments, "Checking");
                    break;
                }
                case "WS": { //withdraw  money from a saving account associated with the name
                    withdrawAccount(arguments, "Savings");
                    break;
                }
                case "WM": { //withdraw money from a market account associated with the name
                    withdrawAccount(arguments, "Money Market");
                    break;
                }
                case "PA": { //print the list of accounts in the database
                    database.printAccounts();
                    break;
                }
                case "PD": { //calculate the monthly interests and fees, and print the account statements,
                                // sorted by the dates opened in ascending order
                    database.printByDateOpen();
                    break;
                }
                case "PN": { //same with PD, but sorted by the last names in ascending order
                    database.printByLastName();
                    break;
                }
                case "Q": { //transaction complete
                    System.out.println("Transaction processing completed.");
                    break Stream;
                }
                default: {
                    System.out.println("Command " + "'" + command + "'" + " not supported!");
                    break;
                }
            }
            input = scan.nextLine();
        }

    }

    private Profile parseProfile(String forename, String surname) {
        if (forename == null || surname == null) {
            return null;
        }
        return new Profile(forename, surname);
    }

    public static double parseBalance(String balance) {
        if (balance == null) {
            return -1;
        }
        try {
            return Double.parseDouble(balance);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean isDateFormat(String date) {
        String[] pDate = date.split("/");
        final int DATE_FORMAT = 3;
        if (pDate.length != DATE_FORMAT) {
            return false;
        }
        if (isNumeric(pDate[0]) && isNumeric(pDate[1]) && isNumeric(pDate[2])) {
            return true;
        }
        return false;
    }

    private Date parseDate(String date) {
        if (date == null) {
            return null;
        }
        if (isDateFormat(date)) {
            String[] pDate = date.split("/");
            int month = Integer.parseInt(pDate[0]);
            int day = Integer.parseInt(pDate[1]);
            int year = Integer.parseInt(pDate[2]);

            Date newDate = new Date(month, day, year);
            if (newDate.isValid()) {
                return newDate;
            }
        }
        System.out.println(date + " is not a valid date!");
        return null;
    }

    private boolean isNumeric(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isDouble(String balance) {
        try {
            Double.parseDouble(balance);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }


    private boolean isBoolean(String bool) {
        boolean result = Boolean.parseBoolean(bool);
        if (result == false) {
            if (!bool.toLowerCase().equals("false")) { return false; }
        }
        return true;
    }


    private Account createAccount(Scanner arguments, String type) {
        if (!arguments.hasNext()) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        //Profile holder = parseProfile(arguments.next(), arguments.next());
        String firstName = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        String lastName = arguments.next();

        Profile personalProfile = parseProfile(firstName, lastName);

        if (!arguments.hasNext()) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        String moneyInput = arguments.next();
        if (!isDouble(moneyInput)) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        double balance = parseBalance(moneyInput);

        String dateInput = arguments.next();
        if (!isDateFormat(dateInput)) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        Date date = parseDate(dateInput);
        if (date == null) {
            return null;
        }

        Account newAccount;
        switch (type) {
            case "Checking": {
                String directDeposit = arguments.next();
                if (!isBoolean(directDeposit)) {
                    System.out.println("Input data type mismatch.");
                    return null;
                }
                Boolean pDirectDeposit = Boolean.parseBoolean(directDeposit);

                return new Checking(personalProfile, balance, date, pDirectDeposit);

            }
            case "Savings": {
                String isLoyal = arguments.next();
                if (!isBoolean(isLoyal)) {
                    System.out.println("Input data type mismatch.");
                    return null;
                }
                Boolean pIsLoyal = Boolean.parseBoolean(isLoyal);

                return new Savings(personalProfile, balance, date, pIsLoyal);

            }
            case "Money Market": {
                return new MoneyMarket(personalProfile, balance, date);

            }
            default: {
                return null;
            }
        }
    }

    private void closeAccount(Scanner arguments, String type) {
        Account target = findAccount(arguments, type);
        if (target == null) {
            return;
        }

        if (database.remove(target)) {
            System.out.println("Account closed and removed from the database.");
        } else {
            System.out.println("Account does not exist.");
        }
        return;
    }

    private Account findAccount(Scanner arguments, String type) {
        if (database.getSize() == 0) {
            System.out.println("Account does not exist.");
            return null;
        }
        if (!arguments.hasNext()) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        //Profile holder = parseProfile(arguments.next(), arguments.next());
        String firstName = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        String lastName = arguments.next();

        Profile personalProfile = parseProfile(firstName, lastName);
        Account target;
        switch (type) {
            case "Checking": {
                target = new Checking(personalProfile, -1, null, false);
                break;
            }
            case "Savings": {
                target = new Savings(personalProfile, -1, null, false);
                break;
            }
            case "Money Market": {
                target = new MoneyMarket(personalProfile, -1, null);
                break;
            }
            default: return null;
        }
        return target;
    }

    private void depositAccount(Scanner arguments, String type) {
        Account target = findAccount(arguments, type);
        if (target == null) { return; }
        String moneyInput = arguments.next();
        if (!isDouble(moneyInput)) {
            System.out.println("Input data type mismatch.");
            return;
        }
        double amount = parseBalance(moneyInput);
        if (database.deposit(target, amount)) {
            System.out.println(amount + "deposited to account.");
        } else {
            System.out.println("Account does not exist.");
        }
    }

    private void withdrawAccount(Scanner arguments, String type) {
        Account target = findAccount(arguments, type);
        if (target == null) { return; }
        String moneyInput = arguments.next();
        if (!isDouble(moneyInput)) {
            System.out.println("Input data type mismatch.");
            return;
        }
        double amount = parseBalance(moneyInput);
        if (database.withdrawal(target, amount) == 0) {
            System.out.println(amount + " withdrawn from account.");
        } else if (database.withdrawal(target, amount) == 1) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Account does not exist.");
        }
    }
}