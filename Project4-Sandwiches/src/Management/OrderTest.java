package Management;

import org.junit.jupiter.api.Test;

class OrderTest {
    Order order = new Order();

    Sandwich c1 = new Chicken();
    Sandwich c2 = new Chicken();
    Sandwich c3 = new Chicken();
    Sandwich b1 = new Beef();
    Sandwich b2 = new Beef();
    Sandwich b3 = new Beef();
    Sandwich f1 = new Fish();
    Sandwich f2 = new Fish();
    Sandwich f3 = new Fish();

    OrderLine olc1 = new OrderLine(c1);
    OrderLine olb1 = new OrderLine(b1);
    OrderLine olf1 = new OrderLine(f1);

    OrderLine olc2 = new OrderLine(c2);
    OrderLine olb2 = new OrderLine(b2);
    OrderLine olf2 = new OrderLine(f2);

    OrderLine olc3 = new OrderLine(c3);
    OrderLine olb3 = new OrderLine(b3);
    OrderLine olf3 = new OrderLine(f3);

    @Test void add() {
        System.out.println("ADD TEST");

        order.add(olc1);
        order.add(olb1);
        order.add(olf1);

        order.add(olc2);
        order.add(olb2);
        order.add(olf2);

        order.add(olc3);
        order.add(olb3);
        order.add(olf3);

        print();
    }

    @Test void remove() {
        add();
        size();
        get();
        separate();

        System.out.println("REMOVE TEST");
        explainRemove(olc2);
        explainRemove(olb3);
        order.remove(olc2);
        order.remove(olb3);
        print();
        size();
        get();
        separate();

        System.out.println("REMOVE TEST");
        explainRemove(olc1);
        explainRemove(olf3);
        order.remove(olc1);
        order.remove(olf3);
        print();
        size();
        get();
        separate();

        System.out.println("REMOVE TEST");
        explainRemove(olc1);
        order.remove(olc1);
        print();
        size();
        get();
        separate();
    }

    @Test void size() {
        System.out.println("\nSIZE TEST: " + order.size());
    }

    @Test void get() {
        System.out.println("\nGET TEST ");
        System.out.println("Standard cases: (second and second last indices)");
        System.out.println(order.get(1));
        System.out.println(order.get(order.size()-2));

        System.out.println("Edge cases: (first and last indices)");
        System.out.println(order.get(0));
        System.out.println(order.get(order.size()-1));

        System.out.println("Further edge cases: (out of lower/upper bounds)");
        System.out.println(order.get(-1));
        System.out.println(order.get(order.size()));
    }

    void print() {
        for (int i = 0; i < order.size(); i++) {
            System.out.println(order.get(i));
        }
    }
    void separate() {
        System.out.println("\n- - - - -\n");
    }
    void explainRemove(OrderLine ol) {
        System.out.println("Removing serial number: " + ol.getLineNumber());
    }
}