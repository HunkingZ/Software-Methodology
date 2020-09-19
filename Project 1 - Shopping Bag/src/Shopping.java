import java.util.*;

/**
 * Shopping Class
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Shopping {
    public ShoppingBag bag = new ShoppingBag();

    /**
     * Interface for handling I/O
     */
    public void run() {
        Scanner scan = new Scanner(System.in); // Scanner to read user input
        String input = scan.nextLine();   // String to store user input information

        // Full I/O handling input arguments in an indefinite loop ended if under the right condition
        IO:
        while (input != null) {
            Scanner arguments = new Scanner(input); // Scanner to help parse the current user input
            String command = "\0"; // In case of no command, sends to default switch case
            if (arguments.hasNext()) { command = arguments.next(); }

            switch (command) {
                case "A": { // Perform add command
                    GroceryItem item = createItem(arguments);
                    if (item != null) { add(item); }
                    break;
                }
                case "R": { // Perform remove command
                    GroceryItem item = createItem(arguments);
                    if (item != null) { remove(item); }
                    break;
                }
                case "P": { // Perform display command
                    display();
                    break;
                }
                case "C": { // Perform check-out command
                    checking_out();
                    break;
                }
                case "Q": { // Perform quit command
                    break IO;
                }
                default: {
                    System.out.println("Invalid command!");
                    break;
                }
            }

            input = scan.nextLine();
        }
        System.out.println("Thanks for shopping with us!");

    }

    // Methods used for I/O parsing

    /**
     * Checks to see if a string can be parsed as a numeric value
     *
     * @param value The string to be checked
     * @return Whether the value may be parsed as a number
     */
    private boolean isNumeric(String value) {
        try { Double.parseDouble(value); }
        catch (NumberFormatException e) { return false; }

        return true;
    }

    /**
     * Checks to see if a string contains only letters
     *
     * @param word The string to be checked
     * @return Whether the word is fully alphabetic
     */
    private boolean isAlphabetic(String word) {
        try {
            for(int letter = 0; letter < word.length(); letter++) {
                if (!Character.isLetter(word.charAt(letter))) { return false; }
            }
        }
        catch (IllegalFormatException e) { return false; }

        return true;
    }

    /**
     * Confirms that the input has the correct format
     *
     * @param priceInput The input to be checked
     * @return Whether the input is able to be parsed correctly
     */
    private boolean confirmPrice(String priceInput) {
        if (priceInput == null || !isNumeric(priceInput)) {
            System.out.println("Invalid command!");
            return false;
        }
        return true;
    }

    /**
     * Confirms that the input for taxable can be parsed properly as a boolean
     *
     * @param taxableInput The boolean input initialized and read as a string
     * @return Whether the input is able to be parsed correctly
     */
    private boolean confirmTaxable(String taxableInput) {
        // taxableInput must be either TRUE or FALSE
        if (taxableInput == null || !isAlphabetic(taxableInput) && (!taxableInput.toUpperCase().equals("TRUE") && !taxableInput.toUpperCase().equals("FALSE"))) {
            System.out.println("Invalid command!");
            return false;
        }
        return true;
    }

    /**
     * Creates an item based on the user's input arguments
     *
     * @param arguments The user's input command and arguments
     * @return A GroceryItem based on user input
     */
    private GroceryItem createItem(Scanner arguments) {
        // Confirm item existence (anything not empty)
        if (!arguments.hasNext()) {
            System.out.println("Invalid command!");
            return null;
        }
        String itemName = arguments.next();

        // Confirm proper numeric input
        if (!arguments.hasNext()) {
            System.out.println("Invalid command!");
            return null;
        }
        String priceInput = arguments.next();
        if(!confirmPrice(priceInput)) { return null; }
        double price = Double.parseDouble(priceInput);

        // Confirm proper boolean input
        if (!arguments.hasNext()) {
            System.out.println("Invalid command!");
            return null;
        }
        String taxableInput = arguments.next().toUpperCase();
        if(!confirmTaxable(taxableInput)) { return null; }
        boolean taxable = Boolean.parseBoolean(taxableInput);

        return new GroceryItem(itemName, price, taxable);
    }

    // - - - - -

    // Methods for performing identified commands

    public static final int MIN_BAG_SIZE = 0;

    private void add(GroceryItem item) {
        bag.add(item);
        System.out.println(item.name + " added to the bag.");
    }

    private void remove(GroceryItem item) {
        if (bag.getSize() == MIN_BAG_SIZE) {
            System.out.println("Unable to remove, this item is not in the bag.");
        } else {
            bag.remove(item);
            System.out.println(item.name + " $" + item.price + " removed.");
        }
    }

    private void display() {
        if (bag.getSize() == MIN_BAG_SIZE) {
            System.out.println("The bag is empty!");
        } else {
            System.out.println("**You have " + bag.getSize() + " item(s) in the bag.");
            bag.print();
            System.out.println("**End of list");
        }
    }

    private void checking_out() {
        if (bag.getSize() == MIN_BAG_SIZE) {
            System.out.println("Unable to check out, the bag is empty!");
        } else {
            System.out.println("**Checking out " + bag.getSize() + " items.");
            bag.print();

            System.out.println("*Sales total : $" + bag.salesPrice());
            System.out.println("*Sales tax : $" + bag.salesTax());
            System.out.println("*Total amount paid : $" + (bag.salesPrice() + bag.salesTax()));

            bag = new ShoppingBag();
        }
    }
}
