package Management;

import java.util.ArrayList;

/**
 *
 */
public abstract class Sandwich implements Customizable {
    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras;

    /**
     *
     */
    public Sandwich() {
        extras = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public abstract double price();

    /**
     *
     * @return
     */
    public abstract ArrayList<String> getBasic();

    /**
     *
     * @return
     */
    public abstract String getType();

    /**
     *
     * @return
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("/");
        if (extras.size() == 0) {
            sb.append("no extras");
        } else {
            for (Extra extra : extras) {
                sb.append(extra.toString()).append(",");
            }
        }
        String pricePart = String.format("%.2f", price());
        sb.append("/").append("Price: $").append(pricePart);
        return sb.toString();
    }
}
