import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDatabaseTest {
    AccountDatabase accs = new AccountDatabase();

    Profile holder1 = new Profile("To", "Mato");
    Profile holder2 = new Profile("Po", "Tato");

    Date date1 = new Date(2020, 10, 30);
    Date date2 = new Date(2020, 12, 25);
    Date date3 = new Date(2019, 02, 28);
    Date date4 = new Date(2016, 04, 29);

    Account one = new Savings(holder1, 0, date1, true);
    Account two = new Checking(holder1, 0, date1, true);
    Account three = new MoneyMarket(holder1, 0, date1);
    Account four = new Savings(holder2, 0, date2, false);
    Account five = new Checking(holder2, 0, date3, false);
    Account six = new MoneyMarket(holder2, 0, date4);

    @Test
    void sortByDateOpen() {
        accs.add(one);
        accs.add(four);
        sortByDateOpen();
    }

    @Test
    void printByDateOpen() {
        printByDateOpen();
    }

    @Test
    void printByLastName() {
    }

    @Test
    void printAccounts() {
        printAccounts();
    }
}