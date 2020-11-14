package Management;

/**
 *
 */
public class OrderLine {
    private int lineNumber;
    private Sandwich sandwich;
    private double price;

    /**
     *
     * @param lineNumber
     * @param sandwich
     */
    public OrderLine(int lineNumber, Sandwich sandwich) {
        this.lineNumber = lineNumber;
        this.sandwich = sandwich;
        this.price = sandwich.price();
    }

    /**
     *
     * @param orderLine
     */
    public OrderLine(OrderLine orderLine) {
        this.lineNumber = orderLine.lineNumber;
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
    public String getSandwichInfo() {
        String sb = "Line Number :" + lineNumber + "\n" +
                sandwich.toString();
        return sb;
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
