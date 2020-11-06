package Management;

import java.util.ArrayList;

public class Beef extends Sandwich{
    private double price = 10.99;
    private final ArrayList<String> basicIngredient = new ArrayList<>();
    private final String basic1 = "Roast Beef";
    private final String basic2 = "Provolone Cheese";
    private final String basic3 = "Mustard";

    @Override
    public double price() {
        return price;
    }

    @Override
    public ArrayList<String> getBasic() {
        ArrayList<String> basicIngredient = new ArrayList<>();
        basicIngredient.add("Roast Beef");
        basicIngredient.add("Provolone Cheese");
        basicIngredient.add("Mustard");


        //return basic1 + ","+ basic2 + "," + basic3;
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
