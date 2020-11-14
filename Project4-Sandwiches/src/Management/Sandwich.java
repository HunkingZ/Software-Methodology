package Management;

import java.util.ArrayList;

/**
 * Sandwich information
 *
 * @author Hanqing Zhao, Richard Xu
 */
public abstract class Sandwich implements Customizable {
    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras;

    /**
     * Generic constructor for information that all sandwiches will have
     */
    public Sandwich() {
        extras = new ArrayList<>();
    }

    /**
     * Gets the total price of the sandwich
     * (Basic Ingredients) + (Extra Ingredients)
     *
     * @return The totla price
     */
    public abstract double price();

    /**
     * Gets the basic ingredients of a sandwich type
     *
     * @return List of the basic ingredients
     */
    public abstract ArrayList<String> getBasic();

    /**
     * Gets the type of the sandwich
     *
     * @return String of the sandwich type
     */
    public abstract String getType();

    /**
     * Generic information that all sandwich types will share
     *
     * @return String of the generic sandwich information
     */
    @Override public String toString() {
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
