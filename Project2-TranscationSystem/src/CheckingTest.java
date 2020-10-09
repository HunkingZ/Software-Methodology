import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingTest {
    Profile h1 = new Profile("John", "Doe");
    Profile h2 = new Profile("Jane", "Doe");
    Profile h3 = new Profile("Bob", "Doe");
    Profile h4 = new Profile("Doe", "Doe");

    Date d1 = new Date(1, 1, 2011);
    Date d2 = new Date(2, 2, 2022);
    Date d3 = new Date(3, 3, 2033);
    Date d4 = new Date(4, 4, 2044);

    Account a1 = new Checking(h1, 1245, d1, true);
    Account a2 = new Checking(h2, 41234, d2, false);
    Account a3 = new Checking(h3, 72, d3, false);
    Account a4 = new Checking(h4, 3562536, d4, true);

    AccountDatabase d = new AccountDatabase();

    @Test
    void monthlyInterest() {
        System.out.printf("The monthly interest for "
                + h1.getfName() + " " + h1.getlName()
                + " is: %.6f percent\n", (a1.monthlyInterest() * 100));
        System.out.printf("The monthly interest for "
                + h2.getfName() + " " + h2.getlName()
                + " is: %.6f percent\n", (a2.monthlyInterest() * 100));
        System.out.printf("The monthly interest for "
                + h3.getfName() + " " + h3.getlName()
                + " is: %.6f percent\n", (a3.monthlyInterest() * 100));
        System.out.printf("The monthly interest for "
                + h4.getfName() + " " + h4.getlName()
                + " is: %.6f percent\n", (a4.monthlyInterest() * 100));
    }

    @Test
    void getInterest() {
        monthlyInterest();
        System.out.printf("==> The account interest is: $%.2f\n", a1.getInterest());
        System.out.printf("==> The account interest is: $%.2f\n", a2.getInterest());
        System.out.printf("==> The account interest is: $%.2f\n", a3.getInterest());
        System.out.printf("==> The account interest is: $%.2f\n", a4.getInterest());
    }

    @Test
    void monthlyFee() {
        System.out.printf("The monthly fee of "
                + h1.getfName() + h1.getlName()
                + "'s account is: %.2f\n", a1.monthlyFee());

        d.add(a1);
        for (int i = 0; i < 10; i++) {
            d.withdrawal(a1, 1);
            System.out.println("==> Account balance after withdrawing $1 is now: " + a1.getBalance());
        }

        System.out.printf("The monthly fee of "
                + h1.getfName() + h1.getlName()
                + "'s account is: %.2f\n", a1.monthlyFee());


        System.out.println("\n<--->\n");


        System.out.printf("The monthly fee of "
                + h2.getfName() + h2.getlName()
                + "'s account is: %.2f\n", a2.monthlyFee());

        d.add(a2);
        for (int i = 0; i < 10; i++) {
            d.withdrawal(a2, 1);
            System.out.println("==> Account balance after withdrawing $1 is now: " + a2.getBalance());
        }

        System.out.printf("The monthly fee of "
                + h2.getfName() + h2.getlName()
                + "'s account is: %.2f\n", a2.monthlyFee());


        System.out.println("\n<--->\n");


        System.out.printf("The monthly fee of "
                + h3.getfName() + h3.getlName()
                + "'s account is: %.2f\n", a3.monthlyFee());

        d.add(a3);
        for (int i = 0; i < 10; i++) {
            d.withdrawal(a3, 1);
            System.out.println("==> Account balance after withdrawing $1 is now: " + a3.getBalance());
        }

        System.out.printf("The monthly fee of "
                + h3.getfName() + h3.getlName()
                + "'s account is: %.2f\n", a3.monthlyFee());
    }
}