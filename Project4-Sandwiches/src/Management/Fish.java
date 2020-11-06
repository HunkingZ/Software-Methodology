package Management;

import java.io.File;
import java.util.ArrayList;

public class Fish extends Sandwich{
    private double price = 12.99;
    private ArrayList<String> basicIngredient = new ArrayList<>();
    private final String basic1 = "Grilled Snappe";
    private final String basic2 = "Cilantro";
    private final String basic3 = "Lime";
    
    @Override
    public double price() {
        return price;
    }

    @Override
    public ArrayList<String> getBasic() {
        ArrayList<String> basicIngredient = new ArrayList<>();
        basicIngredient.add("Grilled Snapper");
        basicIngredient.add("Cilantro");
        basicIngredient.add("Lime");
        return basicIngredient;
        //return basic1 + ","+ basic2 + "," + basic3;
    }

    @Override
    public String getType() {
        return "Fish";
    }

    //mustard
    //pickles
    @Override
    public boolean add(Object obj) {
        extras.add((Extra) obj);
        price += 1.99;
        return false;
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
        for (Extra extra: extras) {
            System.out.println(extra.toString());
        }
        price -= 1.99;
        return true;
    }
}
