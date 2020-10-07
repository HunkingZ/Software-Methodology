import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionManagerTest {
    @Test
    void parseBalance() {
        String input = "1.0";
        double output = TransactionManager.parseBalance(input);
        System.out.println(output);
        System.out.println(TransactionManager.parseBalance("123jr"));
    }
}