/**
 * ShoppingBag Class
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class GroceryItem {
    private String name;
    private double price;
    private boolean taxable;

    /**
     * Compares two items to see if they match exactly
     *
     * @param obj The second item to compare
     * @return Whether the items match
     */
    @Override
    public boolean equals(Object obj) {
        // Edge cases that the obj is null or is not a instance of GroceryItem
        if (obj == null || !(obj instanceof GroceryItem)) {
            return false;
        }
        return name.equals(((GroceryItem)obj).name)
                && price == (((GroceryItem)obj).price)
                && taxable == (((GroceryItem)obj).taxable);
    }

    /**
     * Constructor to create an item
     *
     * @param name The name of the item
     * @param price The price of the item
     * @param taxable Whether the item is taxable or not
     */
    public GroceryItem(String name, double price, boolean taxable) {
        this.name = name;
        this.price = price;
        this.taxable = taxable;
    }

    /**
     * Prints the item's name, price (to the cent), and whether it is taxable
     *
     * @return The item's data members as a list item
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(name).append(":");

        String formattedPrice = String.format("%.2f", price);
        sb.append(" $").append(formattedPrice).append(" :");

        if (taxable) {
            sb.append(" is taxable");
        } else {
            sb.append(" tax free");
        }
        return sb.toString();
    }

    /**
     * Name of the item
     *
     * @return Item name
     */
    public String getName() {
        return name;
    }

    /**
     * Price of the item
     *
     * @return Item price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Whether item is taxable
     *
     * @return Item taxable
     */
    public boolean getTaxable() {
        return taxable;
    }
}
