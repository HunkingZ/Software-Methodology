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
        sb.append(getType());
        for (String basic : getBasic()) {
            sb.append(basic).append(",");
        }
        for (Extra extra : extras) {
            sb.append(extra.toString()).append(",");
        }
        sb.append(price());
        return sb.toString();
    }
}
