package Management;

import java.util.ArrayList;

/**
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Chicken extends Sandwich {
    private double price = 8.99;

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
     * Gets the basic ingredients of a chicken sandwich
     *
     * @return List of the basic ingredients
     */
    @Override public ArrayList<String> getBasic() {
        ArrayList<String> basicIngredient = new ArrayList<>();
        basicIngredient.add("Fried Chicken");
        basicIngredient.add("Spicy Sauce");
        basicIngredient.add("Pickles");
        return basicIngredient;


        //return basic1 + ","+ basic2 + "," + basic3;
    }

    /**
     * Gets the type of the sandwich
     *
     * @return String of "Chicken"
     */
    @Override public String getType() {
        return "Chicken";
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
        return true;
    }

    /**
     * Basic information of a chicken sandwich
     * (Sandwich Type) + (Basic Ingredients)
     *
     * @return String of the basic information
     */
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chicken/Fried Chicken,Spicy Sauce,Pickles");
        sb.append(super.toString());

        return sb.toString();
    }
}
