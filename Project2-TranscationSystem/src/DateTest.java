import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void isValid() {
        Date a = new Date(12, 31, 2020);
        Date b = new Date(12, 32, 2020);
        Date c = new Date(2, 29, 2000);
        Date d = new Date(2, 29, 2005);
        Date e = new Date(2, 29, 2020);
        Date f = new Date(2, 29, 2500);

        System.out.println("The date (" + a + ") is valid.\n==> " + a.isValid());
        System.out.println("The date (" + b + ") is valid.\n==> " + b.isValid());
        System.out.println("The date (" + c + ") is valid.\n==> " + c.isValid());
        System.out.println("The date (" + d + ") is valid.\n==> " + d.isValid());
        System.out.println("The date (" + e + ") is valid.\n==> " + e.isValid());
        System.out.println("The date (" + f + ") is valid.\n==> " + f.isValid());
    }
}