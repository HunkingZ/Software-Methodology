import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void isValid() {
        Date a = new Date(12, 30, 2020);
        Date b = new Date(12, 31, 2020);
        Date c = new Date(2, 29, 2000);
        Date d = new Date(2, 29, 2004);
        Date e = new Date(2, 29, 2020);
        Date f = new Date(2, 29, 400);

        System.out.println("The date (" + a + ") is valid.\n\t==> " + a.isValid());
        System.out.println("The date (" + b + ") is valid.\n\t==> " + b.isValid());
        System.out.println("The date (" + c + ") is valid.\n\t==> " + c.isValid());
        System.out.println("The date (" + d + ") is valid.\n\t==> " + d.isValid());
        System.out.println("The date (" + e + ") is valid.\n\t==> " + e.isValid());
        System.out.println("The date (" + f + ") is valid.\n\t==> " + f.isValid());
    }
}