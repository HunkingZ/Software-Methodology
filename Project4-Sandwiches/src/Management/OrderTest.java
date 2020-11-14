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

    OrderLine olc1 = new OrderLine(1, c1);
    OrderLine olc2 = new OrderLine(2, c2);
    OrderLine olc3 = new OrderLine(3, c3);
    OrderLine olb1 = new OrderLine(4, b1);
    OrderLine olb2 = new OrderLine(5, b2);
    OrderLine olb3 = new OrderLine(6, b3);
    OrderLine olf1 = new OrderLine(7, f1);
    OrderLine olf2 = new OrderLine(8, f2);
    OrderLine olf3 = new OrderLine(9, f3);

    @Test
    void add() {
        order.add(olc1);
        order.add(olc2);
        order.add(olc3);
        order.add(olb1);
        order.add(olb2);
        order.add(olb3);
        order.add(olf1);
        order.add(olf2);
        order.add(olf3);
    }

    @Test
    void remove() {
    }

    @Test
    void size() {
    }

    @Test
    void get() {
    }
}