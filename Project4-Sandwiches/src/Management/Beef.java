package Management;

import java.util.ArrayList;

/**
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Beef extends Sandwich {
    private double price = 10.99;

    /**
     *
     * @return
     */
    @Override public double price() {
        return price;
    }

    /**
     *
     * @return
     */
    @Override public ArrayList<String> getBasic() {
        ArrayList<String> basicIngredient = new ArrayList<>();
        basicIngredient.add("Roast Beef");
        basicIngredient.add("Provolone Cheese");
        basicIngredient.add("Mustard");

        return basicIngredient;
    }

    /**
     * @return
     */
    @Override public String getType() {
        return "Beef";
    }

    /**
     * @param obj
     * @return
     */
    @Override public boolean add(Object obj) {
        if (obj == null || !(obj instanceof Extra)) { return false; }

        if (extras.size() >= Sandwich.MAX_EXTRAS) { return false; }

        extras.add((Extra) obj);
        price += Sandwich.PER_EXTRA;
        return true;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override public boolean remove(Object obj) {
        if (obj == null || !(obj instanceof Extra)) { return false; }

        int index;
        for (int i = 0; i < extras.size(); i++) {
            Extra extra = extras.get(i);
            if (extra.name.equals(((Extra) obj).name)) {
                index = i;
                int j = index;
                for (; j + 1 < extras.size(); j++) {
                    extras.set(j, extras.get(j + 1));
                }
                extras.remove(j);
                break;
            }
        }

        price -= Sandwich.PER_EXTRA;
        return false;
    }

    /**
     *
     * @return
     */
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Beef/Roast Beef,Provolone Cheese,Mustard");
        sb.append(super.toString());

        return sb.toString();
    }
}
