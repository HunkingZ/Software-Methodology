package Management;

import java.util.ArrayList;

/**
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Order implements Customizable{
    public static int lineNumber;
    private ArrayList<OrderLine> orderLines;

    /**
     *
     */
    public Order() {
        orderLines = new ArrayList<>();
        this.lineNumber = 1;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override public boolean add(Object obj) {
        if (obj == null || !(obj instanceof OrderLine)) { return false; }
        orderLines.add((OrderLine) obj);
        return true;
    }

    /**
     *
     * @param obj
     * @return
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
     *
     * @return
     */
    public int size() {
        return orderLines.size();
    }

    /**
     *
     * @param index
     * @return
     */
    public OrderLine get(int index) {
        if (index < 0 || index >= orderLines.size()) { return null; }
        return orderLines.get(index);
    }

    /**
     *
     * @param index
     * @return
     */
    private void reorganize(int index) {
        for (int i = index; i < orderLines.size(); i++) {
            orderLines.get(i).setLineNumber(++index);
        }
    }
}
