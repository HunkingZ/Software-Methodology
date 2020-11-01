package Management;

import java.util.ArrayList;

public class Chicken extends Sandwich{
    private double price = 8.99;
    private ArrayList<String> basicIngredient = new ArrayList<>();
    private ArrayList<Object> extraArray = new ArrayList<>();


    @Override
    public double price() {
        return price;
    }

    @Override
    ArrayList<String> getBasic() {
        basicIngredient.add("Fried Chicken");
        basicIngredient.add("Spicy Sauce");
        basicIngredient.add("Pickles");
        return basicIngredient;
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
        if (extraArray.remove(obj)) {
            price -= 1.99;
            return true;
        }
        return false;
    }
}
