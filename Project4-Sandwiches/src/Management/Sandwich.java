package Management;

import java.util.ArrayList;

public abstract class Sandwich implements Customizable{
    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras;

    public Sandwich() {
        extras = new ArrayList<>();
    }
    public abstract double price();

    abstract ArrayList<String> getBasic();

    public abstract String getType();
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sandwich type : ").append(getType()).append("\n");
        sb.append("Basic Ingredients : ");
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
}
