import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void isValid() {
        Date d = new Date(2020, 12, 25);

        System.out.println("The date (" + d + ") is valid.\n\t==> " + d.isValid());
    }
}