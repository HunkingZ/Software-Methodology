package Management;

import java.util.ArrayList;

/**
 * Order database
 * Holds all order information
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Order implements Customizable{
    public static int lineNumber;
    private ArrayList<OrderLine> orderLines;

    /**
     * Constructor for the database
     */
    public Order() {
        orderLines = new ArrayList<>();
        this.lineNumber = 1;
    }

    /**
     * Adds an order to the order database
     *
     * @param obj OrderLine to be added
     * @return True if successful, False if otherwise
     */
    @Override public boolean add(Object obj) {
        if (obj == null || !(obj instanceof OrderLine)) { return false; }
        orderLines.add((OrderLine) obj);
        return true;
    }

    /**
     * Removes an order from the order database
     *
     * @param obj OrderLine to be removed
     * @return True if successful, False if otherwise
     */
    @Override public boolean remove(Object obj) {
        if (obj == null || !(obj instanceof OrderLine)) { return false; }
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
                Order.lineNumber--;
                reorganize(index);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the size of the database
     *
     * @return Size of the database
     */
    public int size() {
        return orderLines.size();
    }

    /**
     * Gets a specific order from the order database
     *
     * @param index Index of the order in the database
     * @return An order in the database
     */
    public OrderLine get(int index) {
        if (index < 0 || index >= orderLines.size()) { return null; }
        return orderLines.get(index);
    }

    /**
     * Reformats the order database
     * Used when removing an order from the order database
     *
     * @param index Index of the removed order
     */
    private void reorganize(int index) {
        for (int i = index; i < orderLines.size(); i++) {
            orderLines.get(i).setLineNumber(++index);
        }
    }
}
