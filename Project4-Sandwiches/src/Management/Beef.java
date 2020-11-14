package Management;

import java.util.ArrayList;

/**
 * Information about a beef sandwich
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Beef extends Sandwich {
    private double price = 10.99;

    /**
     * Gets the total price of the sandwich
     * (Basic Ingredients) + (Extra Ingredients)
     *
     * @return The totla price
     */
    @Override public double price() {
        return price;
    }

    /**
     * Gets the basic ingredients of a beef sandwich
     *
     * @return List of the basic ingredients
     */
    @Override public ArrayList<String> getBasic() {
        ArrayList<String> basicIngredient = new ArrayList<>();
        basicIngredient.add("Roast Beef");
        basicIngredient.add("Provolone Cheese");
        basicIngredient.add("Mustard");

        return basicIngredient;
    }

    /**
     * Gets the type of the sandwich
     *
     * @return String of "Beef"
     */
    @Override public String getType() {
        return "Beef";
    }

    /**
     * Adds an item to the sandwich
     *
     * @param obj Item to be added
     * @return True if added, False if otherwise
     */
    @Override public boolean add(Object obj) {
        if (obj == null || !(obj instanceof Extra)) { return false; }

        if (extras.size() >= Sandwich.MAX_EXTRAS) { return false; }

        extras.add((Extra) obj);
        price += Sandwich.PER_EXTRA;
        return true;
    }

    /**
     * Removes an item from the sandwich
     *
     * @param obj Item to be removed
     * @return True if removed, False if otherwise
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
     * Basic information of a beef sandwich
     * (Sandwich Type) + (Basic Ingredients)
     *
     * @return String of the basic information
     */
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Beef/Roast Beef,Provolone Cheese,Mustard");
        sb.append(super.toString());

        return sb.toString();
    }
}
