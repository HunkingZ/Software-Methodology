package Management;

public class OrderLine {
    private int lineNumber;
    private Sandwich sandwich;
    private double price;

    public OrderLine(int lineNumber, Sandwich sandwich, double price) {
        this.lineNumber = lineNumber;
        this.sandwich = sandwich;
        this.price = sandwich.price();
    }

    public OrderLine(OrderLine orderLine) {
        this.lineNumber = orderLine.lineNumber;
        this.sandwich = orderLine.sandwich;
        this.price = orderLine.sandwich.price();
    }
    
    public int getLineNumber() {
        return lineNumber;
    }
    
    public String getSandwichInfo() {
        String sb = "Line Number :" + lineNumber + "\n" +
                sandwich.toString();
        return sb;
    }

    public String toString() {
        String sb =  lineNumber + ") " + sandwich.toString();
        return sb;
    }

    public void setLineNumber(int index) {
        lineNumber = index;
    }

    public double getPrice() {
        return price;
    }
}
