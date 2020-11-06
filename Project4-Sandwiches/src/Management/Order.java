package Management;

import java.util.ArrayList;

public class Order implements Customizable{

    public static int lineNumber;
    private ArrayList<OrderLine> orderLines;

    public Order() {
        orderLines = new ArrayList<>();
        this.lineNumber = 1;
    }

    @Override
    public boolean add(Object obj) {
        orderLines.add((OrderLine) obj);
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        int index;
        for (int i = 0; i < orderLines.size(); i++) {
            OrderLine orderLine = orderLines.get(i);
            if (orderLine.getLineNumber() == (((OrderLine) obj).getLineNumber())) {
                index = i;
                int j = index;

                for (; j + 1 < orderLines.size(); j++) {
                    orderLines.set(j, orderLines.get(j + 1));

                }
                orderLines.remove(j);
                return true;
            }
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

    public int size() {
        return orderLines.size();
    }

    public OrderLine get(int index) {
        return orderLines.get(index);
    }

}
