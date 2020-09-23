/**
 * ShoppingBag Class
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class GroceryItem {
    public String name;
    public double price;
    public boolean taxable;

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
     * Compares two items to see if they match exactly
     *
     * @param otherItem The second item to compare
     * @return Whether the items match
     */
    public boolean equals(GroceryItem otherItem) {
        return name.equals(otherItem.name)
                && price == otherItem.price
                && taxable == otherItem.taxable;
    }
}
