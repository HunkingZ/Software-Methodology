package Management;

/**
 * A specific order
 * Compiles of serial number, sandwich information, and cost
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class OrderLine {
    private int lineNumber;
    private Sandwich sandwich;
    private double price;

    /**
     * Constructor for an order
     *
     * @param sandwich Sandwich to copy data from
     */
    public OrderLine(Sandwich sandwich) {
        this.lineNumber = Order.lineNumber++;
        this.sandwich = sandwich;
        this.price = sandwich.price();
    }

    /**
     * Copy constructor for an order
     *
     * @param orderLine The order to copy data from
     */
    public OrderLine(OrderLine orderLine) {
        this.lineNumber = Order.lineNumber++;
        this.sandwich = orderLine.sandwich;
        this.price = orderLine.sandwich.price();
    }

    /**
     * Gets the serial number of the particular order
     *
     * @return The serial number
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Gets the order details of the order
     *
     * @return The order details as (Serial Number) (Sandwich Type) (Basic Ingredients) (Extra Ingredients) (Cost)
     */
    public String toString() {
        String sb =  lineNumber + ") " + sandwich.toString();
        return sb;
    }

    /**
     * Sets the serial number to a specific index
     *
     * @param index The index to be re-initialized to
     */
    public void setLineNumber(int index) {
        lineNumber = index;
    }

    /**
     * Gets the price of the order
     *
     * @return Price of the order
     */
    public double getPrice() {
        return price;
    }
}
