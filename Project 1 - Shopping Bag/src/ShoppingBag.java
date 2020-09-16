//This project is made by Hanqing Zhao & Richard Xu

import java.util.*;

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
     */
    private int find(GroceryItem item) {
        for (int i = 0; i < size; i++) {
            if (bag[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Prints every current item in the Shopping Bag
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            GroceryItem itm = bag[i];
            System.out.println("•" + itm.toString());
        }
    }

    /**
     * Doubles capacity of the Shopping Bag
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
     * @param item The item to be removed
     */
    public void remove(GroceryItem item) {
        if (find(item) != -1) {
            int left = find(item);
            int right = left + 1;
            while (right < size) {
                bag[left] = bag[right];
                left++;
                right++;
            }
            bag[right] = null;
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
