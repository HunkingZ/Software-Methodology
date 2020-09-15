//This project is made by Hanqing Zhao & Richard Xu
import javax.sound.midi.SysexMessage;
import java.util.*;

public class Shopping { // test 2
    public void run() {
        String st = null;
        char c;
        Scanner scan = new Scanner(System.in);
        st = scan.nextLine();
        while (st != null) {
            c = st.charAt(0);
            System.out.println(c);
            //case of input, R == remove, P == print, A == add, C == checkingOut, Q == quit.
            if (c == 'R') {
                Scanner linescanner = new Scanner(st);
                linescanner.next();
                String name = linescanner.next();
                double price = linescanner.nextDouble();
                boolean taxable = linescanner.nextBoolean();
                GroceryItem item = new GroceryItem(name, price, taxable);
                remove(item);
            } else if (c == 'P') {
                display();
            } else if (c == 'A') {
                Scanner linescanner = new Scanner(st);
                linescanner.next();
                String name = linescanner.next();
                double price = linescanner.nextDouble();
                boolean taxable = linescanner.nextBoolean();
                GroceryItem item = new GroceryItem(name, price, taxable);
                add(item);
            } else if (c == 'C') {
                checking_out();
            } else if (c == 'Q') {
                break;
            } else {
                System.out.println("Invalid command!");
            }
            st = scan.nextLine();
        }
        System.out.println("Thanks for shopping with us!");

    }

    public ShoppingBag shopping_bag = new ShoppingBag();
    private int size = 0;
    /*
    public void run(){
        GroceryItem item1 = new GroceryItem("beef", 10.0, true);
        GroceryItem item2 = new GroceryItem("lamp", 20.0, false);
        GroceryItem item3 = new GroceryItem("duck", 25.0, true);
        GroceryItem item4 = new GroceryItem("apple", 2.0, true);
        remove(item1);
        add(item1);
        add(item2);
        add(item3);
        add(item4);
        display();
        remove(item1);
        checking_out();
        display();
    }


     */

    private void add(GroceryItem item) {
        shopping_bag.add(item);
        size++;
        System.out.println(item.name + " added to the bag.");
    }

    private void remove(GroceryItem item) {
            shopping_bag.remove(item);
            if (shopping_bag.getSize() != size) {
                size--;
                System.out.println(item.name + " $" + item.price + " removed.");
            } else {
                System.out.println("Unable to remove, this item is not in the bag.");
            }

    }

    private void display() {
        if (size == 0) {
            System.out.println("The bag is empty!");
        } else {
            System.out.println("**You have " + size + " item(s) in the bag.");

            shopping_bag.print();

            System.out.println("**End of list");
        }
    }
    private void checking_out() {
        System.out.println("**Checking out " + shopping_bag.getSize() + " items.");

        shopping_bag.print();
        System.out.println("*Sales total : $" + shopping_bag.salesPrice());
        System.out.println("*Sales tax : $" + shopping_bag.salesTax());
        System.out.println("*Total amount paid : $" + (shopping_bag.salesPrice() + shopping_bag.salesTax()));

        shopping_bag = new ShoppingBag();
        size = 0;
    }

}
