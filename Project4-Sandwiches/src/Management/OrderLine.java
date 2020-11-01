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
    
    public int getLineNumber() {
        return lineNumber;
    }
    
    public String getSandwichInfo() {
        String sb = "Line Number :" + lineNumber + "\n" +
                sandwich.toString();
        return sb;
    }
}
