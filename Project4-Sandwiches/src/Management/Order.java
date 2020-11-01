package Management;

import java.util.ArrayList;

public class Order implements Customizable{

    public static int lineNumber;
    private ArrayList<OrderLine> orderLines;

    public Order() {
        orderLines = new ArrayList<>();
    }
    @Override
    public boolean add(Object obj) {
        orderLines.add((OrderLine) obj);
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        if (orderLines.remove(obj)) {
            return true;
        }
        return false;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (OrderLine orderLine : orderLines) {
            sb.append(orderLine.getSandwichInfo()).append("\n----------\n");
        }
        sb.append("Amount of Order : ").append(orderLines.size());
        return sb.toString();
    }
}
