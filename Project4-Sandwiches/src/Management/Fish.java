package Management;

import java.util.ArrayList;

/**
 *
 */
public class Fish extends Sandwich {
    private double price = 12.99;

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
        basicIngredient.add("Grilled Snapper");
        basicIngredient.add("Cilantro");
        basicIngredient.add("Lime");
        return basicIngredient;
        //return basic1 + ","+ basic2 + "," + basic3;
    }

    /**
     *
     * @return
     */
    @Override public String getType() {
        return "Fish";
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override public boolean add(Object obj) {
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
     *
     * @return
     */
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fish/Grilled Snapper,Cilantro,Lime");
        sb.append(super.toString());

        return sb.toString();
    }
}
