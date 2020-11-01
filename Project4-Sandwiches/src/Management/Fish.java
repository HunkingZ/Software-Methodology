package Management;

import java.io.File;
import java.util.ArrayList;

public class Fish extends Sandwich{
    private double price = 12.99;
    private ArrayList<String> basicIngredient = new ArrayList<>();

    
    @Override
    public double price() {
        return price;
    }

    @Override
    ArrayList<String> getBasic() {
        basicIngredient.add("Grilled Snapper");
        basicIngredient.add("Cilantro");
        basicIngredient.add("Lime");
        return basicIngredient;
    }

    @Override
    public String getType() {
        return "Fish";
    }

    @Override
    public boolean add(Object obj) {
        extras.add((Extra) obj);
        price += 1.99;
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if (extras.remove(obj)) {
            price -= 1.99;
            return true;
        }
        return false;
    }
}
