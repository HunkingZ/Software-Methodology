import java.util.*;

/**
 * ShoppingBag Class
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class ShoppingBag {
    private GroceryItem[] bag; // List of items
    private int size = 0; // Current number of items in the bag
    private int capacity = 5; // Maximum available number of items in the bag

    /**
     * Constructor for declaring a Shopping Bag
     */
    public ShoppingBag() {
        bag = new GroceryItem[capacity];
    }

    /**
     * Find the index of a certain item in the Shopping Bag
     *
     * @param item The item you want to find
     * @return Index of the target item
     */
    private int find(GroceryItem item) {
        for (int indexOfItem = 0; indexOfItem < size; indexOfItem++) {
            if (bag[indexOfItem].equals(item)) {
                return indexOfItem;
            }
        }
        return -1;
    }

    /**
     * Prints every current item in the Shopping Bag
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            GroceryItem item = bag[i];
            System.out.println("• " + item);
        }
    }

    /**
     * Increases the maximum capacity of the Shopping Bag by 5
     */
    private void grow() {
        capacity += 5;
        bag = Arrays.copyOf(bag, capacity);
    }

    /**
     * Adds an item into the Shopping Bag
     *
     * @param item The item you want to add
     */
    public void add(GroceryItem item) {
        if (size >= capacity) {
            grow();
        }
        bag[size] = item;
        size++;
    }

    /**
     * Returns the number of items in the Shopping Bag
     *
     * @return Size of the Shopping Bag
     */
    public int getSize() {
        return size;
    }

    /**
     * Removes a specific item from the Shopping Bag
     *
     * @param itemToRemove The item to be removed
     */
    public void remove(GroceryItem itemToRemove) {
        if (find(itemToRemove) != -1) {
            int indexOfItem = find(itemToRemove);
            int lastItemInBag = indexOfItem + 1;

            // Replace target item with the last item in the bag
            while (lastItemInBag < size) {
                bag[indexOfItem] = bag[lastItemInBag];
                indexOfItem++;
                lastItemInBag++;
            }

            bag[lastItemInBag] = null;
            size--;
        }
    }

    /**
     * Calculates the total price of all the items in the Shopping Bag
     *
     * @return Calculated total
     */
    public double salesPrice() {
        double total_sales = 0;
        for (int i = 0; i < size; i++) {
            total_sales += bag[i].price;
        }

        return total_sales;
    }

    /**
     * Calculates the total tax of all taxable items in the purchase
     *
     * @return Calculated total tax
     */
    public double salesTax() {
        double tax_rate = 0.06625;
        double total_tax = 0;

        for (int i = 0; i < size; i++) {
            if (bag[i].taxable) {
                total_tax += bag[i].price * tax_rate;
            }
        }

        return total_tax;
    }
}
