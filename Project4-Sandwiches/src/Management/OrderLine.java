package Management;

/**
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class OrderLine {
    private int lineNumber;
    private Sandwich sandwich;
    private double price;

    /**
     *
     * @param sandwich
     */
    public OrderLine(Sandwich sandwich) {
        this.lineNumber = Order.lineNumber++;
        this.sandwich = sandwich;
        this.price = sandwich.price();
    }

    /**
     *
     * @param orderLine
     */
    public OrderLine(OrderLine orderLine) {
        this.lineNumber = Order.lineNumber++;
        this.sandwich = orderLine.sandwich;
        this.price = orderLine.sandwich.price();
    }

    /**
     *
     * @return
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     *
     * @return
     */
    public String toString() {
        String sb =  lineNumber + ") " + sandwich.toString();
        return sb;
    }

    /**
     *
     * @param index
     */
    public void setLineNumber(int index) {
        lineNumber = index;
    }

    /**
     *
     * @return
     */
    public double getPrice() {
        return price;
    }
}
