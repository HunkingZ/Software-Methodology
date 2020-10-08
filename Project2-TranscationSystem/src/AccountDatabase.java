import java.util.Arrays;
import java.util.Comparator;

/**
 * Database class that stores information on all active accounts.
 * Concurrent array of abstract object(s), account.
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class AccountDatabase {
    private Account[] database;
    private int size = 0;
    private int capacity = 5;

    /**
     * Constructor for the database of accounts
     */
    public AccountDatabase() {
        database = new Account[capacity];
    }

    /**
     * Looks for the account in the database
     *
     * @param account The account to find
     * @return The index of the account or -1 if not found
     */
    private int find(Account account) {
        for (int i = 0; i < size; i++) {
            if (database[i].equals(account)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Increases the capacity of the database by 5.
     */
    private void grow() {
        capacity += 5;

        Account[] biggerDatabase = new Account[capacity];
        System.arraycopy(database, 0, biggerDatabase, 0, size);
        database = biggerDatabase;
    }

    /**
     * Adds an account to the database
     *
     * @param account The account to add
     * @return True if account is added or False if account already exists
     */
    public boolean add(Account account) {

        if (size != 0 && (find(account) != -1)) { return false; }

        database[size++] = account;
        if (size >= capacity) { grow(); }
        return true;
    }

    /**
     * Removes an account from the database
     *
     * @param account The account to remove
     * @return True if account is removed or False if account does not exist
     */
    public boolean remove(Account account) {
        int accountIndex = find(account);

        //replace target account with last account and remove the last index
        if (size != 0 && accountIndex == -1) { return false; }
        else {
            database[accountIndex] = database[--size];
            database[size] = null;
            return true;
        }
    }

    /**
     * Deposit a specified amount of cash into an account
     *
     * @param account Account to update
     * @param amount Amount to deposit
     * @return True if deposit successful or False if fail
     */
    public boolean deposit(Account account, double amount) {
        int accountIndex = find(account);

        if (accountIndex == -1) { return false; }
        else { database[accountIndex].credit(amount); }
        return true;
    }

    /**
     * Withdraw a specified amount of cash from an account
     *
     * @param account Account to modify
     * @param amount Amount to acquire
     * @return 0 if successful withdrawal, 1 if insufficient funds, -1 if account does not exist
     */
    public int withdrawal(Account account, double amount) {
        int accountIndex = find(account);

        if (accountIndex == -1) { return -1; }
        else if (database[accountIndex].getBalance() - amount < 0) { return 1; }
        database[accountIndex].debit(amount);
        return 0;
    }

    /**
     * Sort accounts based on date opened in ascending order
     */
    private void sortByDateOpen() {
        //bubble sort
        for (int i = 0; i < (size - 1); i++) {
            for (int j = 0; j < (size - 1 - i); j++) {
                Date lAccountDate = database[j].getDate();
                Date rAccountDate = database[j + 1].getDate();

                //if left account was opened later than right account, swap them
                if (lAccountDate.compareTo(rAccountDate) > 0) {
                    Account lAccount = database[j];
                    Account rAccount = database[j + 1];

                    database[j] = rAccount;
                    database[j + 1] = lAccount;
                }
            }
        }
    }

    /**
     * Sort accounts based on last name in ascending order
     */
    private void sortByLastName() {
        //bubble sort
        for (int i = 0; i < (size - 1); i++) {
            for (int j = 0; j < (size - 1 - i); j++) {
                String lAccountSurname = database[j].getlName();
                String rAccountSurname = database[j + 1].getlName();

                //if left account surname is alphabetically before the right account surname, swap them
                if (lAccountSurname.compareTo(rAccountSurname) > 0) {
                    Account lAccount = database[j];
                    Account rAccount = database[j + 1];

                    database[j] = rAccount;
                    database[j + 1] = lAccount;
                }
            }
        }
    }

    /**
     * Print accounts by the date that it was opened in ascending order
     */
    public void printByDateOpen() {
        if (size == 0) {
            System.out.println("Database is empty.");
            return;
        }
        sortByDateOpen();
        System.out.println("--Printing statements by date opened--");
        for (int i = 0; i < size; i++) {
            double interst = database[i].getInterest();
            double fee = database[i].monthlyFee();
            System.out.println(database[i]);
            deposit(database[i], interst);
            withdrawal(database[i], fee);
            System.out.printf("interest : $ %.02f\n", interst);
            System.out.println("fee : $ " + fee);
            System.out.printf("new balance : $ %.02f\n\n", database[i].getBalance());
        }
    }

    /**
     * Prints accounts by the last name of each account in ascending order
     */

    public void printByLastName() {
        if (size == 0) {
            System.out.println("Database is empty.");
            return;
        }
        sortByLastName();
        System.out.println("--Printing statements by last name--");
        for (int i = 0; i < size; i++) {
            double interst = database[i].getInterest();
            double fee = database[i].monthlyFee();
            System.out.println(database[i]);
            deposit(database[i], interst);
            withdrawal(database[i], fee);
            System.out.printf("interest : $ %.02f\n", interst);
            System.out.println("fee : $ " + fee);
            System.out.printf("new balance : $ %.02f\n\n", database[i].getBalance());
        }
    }

    /**
     * Prints accounts
     */

    public void printAccounts() {
        if (size == 0) {
            System.out.println("Database is empty.");
            return;
        }
        System.out.println("--Listing accounts in the database--");
        for (int i = 0; i < size; i++) {
            System.out.println(database[i]);
        }
        System.out.println("--end of listing--");
    }

    public int getSize() {
        return this.size;
    }
}
