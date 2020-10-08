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
                    String check = "CHECKING";
                    Account ac = createAccount(arguments, check);
                    if (ac != null) {
                        database.add(ac);
                    }
                    break;
                    //isparseDate == null, break;
                }
                case "OS": { //open a savings account
                    String saving = "SAVING";
                    Account ac = createAccount(arguments, saving);
                    if (ac != null) {
                        database.add(ac);
                    }
                    break;
                }
                case "OM": { //open a money market account
                    String moneymarket = "MONEYMARKET";
                    Account ac = createAccount(arguments, moneymarket);
                    if (ac != null) {
                        database.add(ac);
                    }
                    break;
                }
                case "CC": { //close a checking account associated with the name

                }
                case "CS": { //close a savings account associated with the name

                }
                case "CM": { //close a savings account associated with the name

                }
                case "DC": { //deposit money to a checking account associated with the name

                }
                case "DS": { //deposit money to a savings account associated with the name

                }
                case "DM": { //deposit money to a money market account associated with the name

                }
                case "WC": { //withdraw money from a checking account associated with the name

                }
                case "WS": { //withdraw  money from a saving account associated with the name

                }
                case "WM": { //withdraw money from a market account associated with the name

                }
                case "PA": { //print the list of accounts in the database

                }
                case "PD": { //calculate the monthly interests and fees, and print the account statements,
                                // sorted by the dates opened in ascending order

                }
                case "PN": { //same with PD, but sorted by the last names in ascending order

                }
                case "Q": { //transaction complete
                    System.out.println("Transaction processing completed");
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
        return new Profile(forename, surname);
    }

    public static double parseBalance(String balance) {
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

    private boolean isNumeric(String date) {
        for (int i = 0; i < date.length(); i++) {
            if (!Character.isDigit(date.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    private boolean isBoolean(String bool) {
        boolean result = Boolean.parseBoolean(bool);
        if (result == false) {
            if (!bool.equals("false")) { return false; }
        }
        return true;
    }

    private Account createAccount(Scanner arguments, String type) {
        if (!arguments.hasNext()) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        String firstName = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        String lastName = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        Profile personalProfile = parseProfile(firstName, lastName);

        String moneyInput = arguments.next();
        if (!isNumeric(moneyInput)) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        double balance = Double.parseDouble(moneyInput);

        String dateInput = arguments.next();
        if (!isDateFormat(dateInput)) {
            System.out.println("Input data type mismatch.");
            return null;
        }
        Date date = parseDate(dateInput);
        if (date == null) {
            return null;
        }
        Account.Type accountType = Account.Type.valueOf("CHECKING");

        switch (accountType) {
            case CHECKING: {
                String boolInput = arguments.next();
                if (!isBoolean(boolInput)) {
                    System.out.println("Input data type mismatch.");
                    return null;
                }
                Boolean bool = Boolean.parseBoolean(boolInput);

                Account newAccount = new Checking(personalProfile, balance, date, bool, accountType);
                return newAccount;
            }
            case SAVING: {
                String boolInput = arguments.next();
                if (!isBoolean(boolInput)) {
                    System.out.println("Input data type mismatch.");
                    return null;
                }
                Boolean bool = Boolean.parseBoolean(boolInput);

                Account newAccount = new Savings(personalProfile, balance, date, bool, accountType);
                return newAccount;
            }
            case MONEYMARKET: {
                Account newAccount = new MoneyMarket(personalProfile, balance, date, accountType);
                return newAccount;
            }
        }

        /*
        if (type.equals("C") || type.equals("S")) {
            String boolInput = arguments.next();
            if (!isBoolean(boolInput)) {
                System.out.println("Input data type mismatch.");
                return null;
            }
            Boolean bool = Boolean.parseBoolean(boolInput);

            if (type.equals("C")) {
                Account.Type account = Account.Type.valueOf("CHECKING");
                Account newAccount = new Checking(personalProfile, balance, date, bool, account);
            } else {
                Account.Type saving = Account.Type.valueOf("SAVING");
                Account newAccount = new Savings(personalProfile, balance, date, bool, saving);
            }
        }
         */
        return null;
    }

}
