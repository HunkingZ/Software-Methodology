import java.util.Arrays;

/**
 * ShoppingBag Class
 * This declares the bag class that holds the items
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
        if (item == null) return;

        bag[size] = item;
        size++;
        if (size >= capacity) {
            grow();
        }
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
    public boolean remove(GroceryItem itemToRemove) {
        if (itemToRemove == null) {
            return false;
        }
        int indexOfItem = find(itemToRemove);

        // If the item exists, replace it by the last item in the bag
        if (indexOfItem != -1) {
            bag[indexOfItem] = bag[--size];
            bag[size] = null;
            return true;
        }
        return false;
    }

    /**
     * Calculates the total price of all the items in the Shopping Bag
     *
     * @return Calculated total
     */
    public double salesPrice() {
        double total_sales = 0;

        for (int i = 0; i < size; i++) {
            total_sales += bag[i].getPrice();
        }

        return total_sales;
    }

    /**
     * Calculates the total tax of all taxable items in the purchase
     *
     * @return Calculated total tax
     */
    public double salesTax() {
        final double TAX_RATE = 0.06625;
        double totalTax = 0;

        for (int i = 0; i < size; i++) {
            if (bag[i].getTaxable()) {
                totalTax += bag[i].getPrice() * TAX_RATE;
            }
        }

        return totalTax;
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

    public static void main(String[] args) {
        ShoppingBag testBag = new ShoppingBag();

        // add() test
        System.out.println("---------Test add() method start-----------");
        System.out.println("Bag capacity: " + testBag.bag.length);
        System.out.println("Items in the bag: ");
        for (GroceryItem item : testBag.bag) {
            if (item != null) {
                System.out.println(item);
            }
        }

        GroceryItem addItem1 = new GroceryItem("beef", 2.0, true);
        GroceryItem addItem2 = new GroceryItem("lamp", 21.0, true);
        GroceryItem addItem3 = new GroceryItem("bead", 303.3, false);
        GroceryItem addItem4 = new GroceryItem("chicken", 44444444, true);
        GroceryItem addItem5 = new GroceryItem("bed", 0.1, false);
        testBag.add(addItem1);
        testBag.add(addItem2);
        testBag.add(addItem3);
        testBag.add(null);
        testBag.add(addItem4);
        testBag.add(addItem5);

        for (GroceryItem item : testBag.bag) {
            if (item != null) {
                System.out.println(">> " + item);
            }
        }

        System.out.println("Bag capacity: " + testBag.bag.length);
        System.out.println("----------Test add() method end------------\n");

        // remove() test
        System.out.println("--------Test remove() method start---------");
        System.out.println("Bag capacity: " + testBag.bag.length);
        System.out.println("Items in the bag: ");
        GroceryItem removeItem1 = new GroceryItem("beef", 2.0, true);
        GroceryItem removeItem2 = new GroceryItem("lamp", 21.0, true);
        GroceryItem removeItem3 = new GroceryItem("duck", 10.0, true);
        GroceryItem removeItem4 = new GroceryItem("chicken", 21.0, true);
        testBag.remove(removeItem1);
        testBag.remove(removeItem2);
        testBag.remove(removeItem3);
        testBag.remove(removeItem4);
        testBag.remove(null);

        for (GroceryItem item : testBag.bag) {
            if (item != null) {
                System.out.println(">> " + item);
            }
        }
        System.out.println("Bag capacity: " + testBag.capacity);
        System.out.println("---------Test remove() method end----------\n");

        // grow() test
        System.out.println("---------Test grow() method start----------");
        System.out.println("Bag capacity: " + testBag.capacity);
        GroceryItem addItem6 = new GroceryItem("turkey", 99.15, false);
        testBag.add(addItem6);
        System.out.println("Bag size after adding over capacity: " + testBag.size);
        System.out.println("Bag capacity after adding over capacity: " + testBag.capacity);
        for (int i = 0; i < 5; i++) {
            testBag.grow();
        }
        System.out.println("Bag capacity after grow: " + testBag.capacity);
        System.out.println("----------Test grow() method end-----------\n");

        // salesTax() test
        System.out.println("-------Test salesTax() method start--------");
        System.out.printf("Total tax needed to pay: $%.2f\n", testBag.salesTax());
        System.out.println("--------Test salesTax() method end---------");
    }
}
