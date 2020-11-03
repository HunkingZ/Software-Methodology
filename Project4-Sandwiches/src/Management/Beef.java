package Management;

import java.util.ArrayList;

public class Beef extends Sandwich{
    private double price = 10.99;
    private ArrayList<String> basicIngredient = new ArrayList<>();


    @Override
    public double price() {
        return price;
    }

    @Override
    public ArrayList<String> getBasic() {
        basicIngredient.add("Roast Beef");
        basicIngredient.add("Provolone Cheese");
        basicIngredient.add("Mustard");
        return basicIngredient;
    }

    @Override
    public String getType() {
        return "Beef";
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
        if (extras.remove(obj)) {
            price -= 1.99;
            return true;
        }

         */
        extras.remove(obj);
        price -= 1.99;
        return false;
    }
}
