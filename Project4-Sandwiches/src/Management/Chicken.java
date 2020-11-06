package Management;

import java.util.ArrayList;

public class Chicken extends Sandwich{
    private double price = 8.99;

    private ArrayList<Object> extraArray = new ArrayList<>();

    private final String basic1 = "Fried Chicken";
    private final String basic2 = "Spicy Sauce";
    private final String basic3 = "Pickles";

    @Override
    public double price() {
        return price;
    }

    @Override
    public ArrayList<String> getBasic() {
        ArrayList<String> basicIngredient = new ArrayList<>();
        basicIngredient.add("Fried Chicken");
        basicIngredient.add("Spicy Sauce");
        basicIngredient.add("Pickles");
        return basicIngredient;


        //return basic1 + ","+ basic2 + "," + basic3;
    }

    @Override
    public String getType() {
        return "Chicken";
    }

    @Override
    public boolean add(Object obj) {
        extras.add((Extra) obj);
        price += 1.99;
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        /*
        if (extraArray.remove(obj)) {
            price -= 1.99;
            return true;
        }

         */
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
        //extras.remove(obj);
        price -= 1.99;
        return true;
    }
}
