import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

/**
 * UI for I/O handling
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class TransactionManager {
    public void run() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        Stream:
        while (input != null) {
            Scanner arguments = new Scanner(input); //scanner to help parse the current user input
            String command = "\0"; //in case of no command, sends to default switch case

            if (arguments.hasNext()) { command = arguments.next(); }

            switch (command) {
                case "OC": { //open a checking account

                    //isparseDate == null, break;
                }
                case "OS": { //open a savings account

                }
                case "OM": { //open a money market account

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
                    System.out.println("Invalid command!");
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

}
