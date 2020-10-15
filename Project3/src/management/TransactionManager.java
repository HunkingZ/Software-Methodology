package management;

import java.util.Scanner;

/**
 * UI for I/O handling
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class TransactionManager {
    private AccountDatabase database = new AccountDatabase();

    /**
     * I/O Handler interface for user inputs
     */
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
                    System.out.println("\nTransaction processing completed.");
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

    /**
     * Validates and creates a Profile type based on given input
     *
     * @param forename String of first name
     * @param surname String of last name
     * @return The constructed profile based on forename and surname or null if invalid input
     */
    private Profile parseProfile(String forename, String surname) {
        if (forename == null || surname == null) {
            return null;
        }
        return new Profile(forename, surname);
    }

    /**
     * Validates and creates an a double value based on given input
     *
     * @param balance String to validate and construct as double
     * @return The string input as a double or -1 if invalid input
     */
    private double parseBalance(String balance) {
        if (balance == null) { return -1; }

        try { return Double.parseDouble(balance); }
        catch (NumberFormatException e) { return -1; }
    }

    /**
     * Validates that an input is in the proper format of Date type (int/int/int)
     *
     * @param date String of input to validate
     * @return True if input is proper (int/int/int) or False if invalid input
     */
    private boolean isDateFormat(String date) {
        String[] pDate = date.split("/");
        final int DATE_FORMAT = 3;
        if (pDate.length == DATE_FORMAT
                && isInteger(pDate[0])
                && isInteger(pDate[1])
                && isInteger(pDate[2])) {
            return true;
        }

        System.out.println("Input data type mismatch.");
        return false;
    }

    /**
     * Validates and creates a date based on given input
     *
     * @param date String of input to validate and convert into a Date type
     * @return The parsed date or null if invalid input
     */
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

    /**
     * Validates an input to be purely numeric
     *
     * @param num String of input to check
     * @return True if input is an integer or False if invalid input
     */
    private boolean isInteger(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates an input to be of double
     *
     * @param balance String of input to check
     * @return True if input is double or False if invalid input
     */
    private boolean isDouble(String balance) {
        try {
            Double.parseDouble(balance);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input data type mismatch.");
            return false;
        }

    }

    /**
     * Validates an input to be of boolean
     *
     * @param bool String of input to check
     * @return True if input is boolean (true|false) or False if invalid input
     */
    private boolean isBoolean(String bool) {
        boolean result = Boolean.parseBoolean(bool);
        if (!result) {
            if (!bool.toLowerCase().equals("false")) {
                System.out.println("Input data type mismatch.");
                return false;
            }
        }
        return true;
    }

    /**
     * Validates an input to exist
     *
     * @param arguments Input command
     * @return True if there is an input or False if there is no input
     */
    private boolean validateArgs(Scanner arguments) {
        if(arguments.hasNext()) { return true; }
        else {
            System.out.println("Input data type mismatch.");
            return false;
        }
    }

    /**
     * Creates an account based on the given input
     *
     * @param arguments Input commands
     * @param type Account type (Checking|Savings|Money Market)
     * @return New account specified by the parameters
     */
    private Account createAccount(Scanner arguments, String type) {
        //confirm valid name
        if (!validateArgs(arguments)) { return null; }
        String firstName = arguments.next();
        if (!validateArgs(arguments)) { return null; }
        String lastName = arguments.next();
        Profile personalProfile = parseProfile(firstName, lastName);

        //confirm valid balance
        if (!validateArgs(arguments)) { return null; }
        String balance = arguments.next();
        if (!isDouble(balance)) { return null; }
        double pBalance = parseBalance(balance);

        //confirm valid date
        if (!validateArgs(arguments)) { return null; }
        String dateInput = arguments.next();
        if (!isDateFormat(dateInput)) { return null; }
        Date date = parseDate(dateInput);
        if (date == null) { return null; }

        switch (type) {
            case "Checking": {
                //confirm valid direct deposit
                if (!validateArgs(arguments)) { return null; }
                String directDeposit = arguments.next();
                if (!isBoolean(directDeposit)) { return null; }
                boolean pDirectDeposit = Boolean.parseBoolean(directDeposit);

                //create and return checking account
                return new Checking(personalProfile, pBalance, date, pDirectDeposit);
            }
            case "Savings": {
                //confirm valid loyalty program
                if (!validateArgs(arguments)) { return null; }
                String isLoyal = arguments.next();
                if (!isBoolean(isLoyal)) { return null; }
                boolean pIsLoyal = Boolean.parseBoolean(isLoyal);

                //create and return savings account
                return new Savings(personalProfile, pBalance, date, pIsLoyal);
            }
            case "Money Market": {
                //create and return money market account
                return new MoneyMarket(personalProfile, pBalance, date);
            }
            default: {
                return null;
            }
        }
    }

    /**
     * Find the account based on the given input
     *
     * @param arguments Input command
     * @param type Account type (Checking|Savings|Money Market)
     * @return Account based on strictly the name and type
     */
    private Account findAccount(Scanner arguments, String type) {
        if (database.getSize() == 0) {
            System.out.println("Account does not exist.");
            return null;
        }

        if (!validateArgs(arguments)) { return null; }
        String firstName = arguments.next();
        if (!arguments.hasNext()) { return null; }
        String lastName = arguments.next();

        Profile personalProfile = parseProfile(firstName, lastName);

        switch (type) {
            case "Checking" -> { return new Checking(personalProfile, -1, null, false); }
            case "Savings" -> { return new Savings(personalProfile, -1, null, false); }
            case "Money Market" -> { return new MoneyMarket(personalProfile, -1, null); }
            default -> { return null; }
        }
    }

    /**
     * Find the account and remove the account
     *
     * @param arguments Input command
     * @param type Account type (Checking|Savings|Money Market)
     */
    private void closeAccount(Scanner arguments, String type) {
        Account target = findAccount(arguments, type);
        if (target == null) { return; }

        if (database.remove(target)) { System.out.println("Account closed and removed from the database."); }
        else { System.out.println("Account does not exist."); }
    }

    /**
     * Deposit a specified amount into an account
     *
     * @param arguments Input command
     * @param type Account type (Checking|Savings|Money Market)
     */
    private void depositAccount(Scanner arguments, String type) {
        Account target = findAccount(arguments, type);
        if (target == null) { return; }

        if (!validateArgs(arguments)) { return; }
        String amount = arguments.next();
        if (!isDouble(amount)) { return; }
        double pAmount = parseBalance(amount);

        if (database.deposit(target, pAmount)) { System.out.printf("%.2f deposited to account.\n", pAmount); }
        else { System.out.println("Account does not exist."); }
    }

    /**
     * Withdraw a specified amount from an account
     * Will not succeed if account does not have the specified balance
     *
     * @param arguments Input command
     * @param type Account type (Checking|Savings|Money Market)
     */
    private void withdrawAccount(Scanner arguments, String type) {
        Account target = findAccount(arguments, type);
        if (target == null) { return; }
        String amount = arguments.next();
        if (!isDouble(amount)) { return; }
        double pAmount = parseBalance(amount);

        if (database.withdrawal(target, pAmount) == 0) { System.out.printf("%.2f withdrawn from account.\n", pAmount); }
        else if (database.withdrawal(target, pAmount) == 1) { System.out.println("Insufficient funds."); }
        else { System.out.println("Account does not exist."); }
    }
}