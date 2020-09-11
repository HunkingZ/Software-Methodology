import java.util.Arrays;

public class ShoppingBag {
    private GroceryItem[] bag;
    private int size = 0;
    private int capacity = 5;

    public ShoppingBag() {
        bag = new GroceryItem[capacity];
    }

    //Find the index of certain item in Shopping Bag
    private int find(GroceryItem item) {
        for (int i = 0; i < size; i++) {
            if (bag[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public void print() {
        // loop every item in the bag
        for (int i = 0; i < size; i++) {
            GroceryItem itm = bag[i];
            System.out.println("•" + itm.toString());
        }
    }

    //Grow the capacity of Shopping Bag
    private void grow() {
        capacity += 5;
        bag = Arrays.copyOf(bag, capacity);
    }

    /**
     * Adds an item into the shopping bag.
     *
     * @param item The item you want to add to your shopping bag.
     */
    public void add(GroceryItem item) {
        if (size >= capacity) {
            grow();
        }
        bag[size] = item;
        size++;
    }

    public int getSize() {
        return size;
    }

    //remove item in shopping bag if it exists

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

    //find the total price of items in the shopping bag
    public double salesPrice() {
        int total_sales = 0;
        for (int i = 0; i < size; i++) {
            total_sales += bag[i].price;
        }
        return total_sales;
    }

    //find the total tax for the items
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
