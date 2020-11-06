package Management;

import java.util.ArrayList;

public abstract class Sandwich implements Customizable{
    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras;
    //protected ArrayList<String> basics;
    public Sandwich() {
        extras = new ArrayList<>();
    }
    public abstract double price();

    public abstract ArrayList<String> getBasic();

    public abstract String getType();

    /*
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sandwich type :").append(getType()).append("\n");
        sb.append("Basic Ingredients :");
        for (String basic : getBasic()) {
            sb.append(basic).append(",");
        }
        sb.append("\n").append("Extra ingredients :");
        for (Extra extra : extras) {
            sb.append(extra.toString()).append(",");
        }
        sb.append("\n");
        sb.append("Price: ").append(price());
        return sb.toString();
    }
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getType());
        sb.append("/");

        for (String basic : getBasic()) {
            sb.append(basic).append(",");
        }

        //sb.append(getBasic());
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
