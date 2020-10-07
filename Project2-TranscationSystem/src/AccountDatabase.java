import java.util.Arrays;
import java.util.Comparator;

/**
 * Database class that stores information on all active accounts
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
        for (int i = 0; i < database.length; i++)
            if (database[i].equals(account)) { return i; }

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
        if (find(account) != -1) { return false; }

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
        if (accountIndex == -1) { return false; }
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
        else if (database[accountIndex].getBalance() - amount >= 0) { database[accountIndex].debit(amount); }
        return 0;
    }

    /**
     * Sort accounts based on date opened in ascending order
     */
    private void sortByDateopen() {
        //bubble sort
        for (int i = 0; i < (size - 1); i++) {
            for (int j = 0; j < (size - 1 - i); j++) {
                Date lAccountDate = database[j].getDate();
                Date rAccountDate = database[j+1].getDate();

                //if left account was opened later than right account, swap them
                if (lAccountDate.compareTo(rAccountDate) > 0) {
                    Account lAccount = database[j];
                    Account rAccount = database[j+1];

                    database[j] = rAccount;
                    database[j+1] = lAccount;
                }
            }
        }


        /*
        Arrays.sort(accounts, new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        */
    }

    /**
     * Sort accounts based on last name in ascending order
     */
    private void sortByLastName() {
        Arrays.sort(database, new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                String lname1 = o1.getlName();
                String lname2 = o2.getlName();
                if (lname1.equals(lname2)) {
                    String fname1 = o1.getfName();
                    String fname2 = o2.getfName();
                    return stringCompare(fname1, fname2);
                } else {
                    return stringCompare(lname1, lname2);
                }
            }
        });
    }

    private int stringCompare(String str1, String str2)
    {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }

        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
    }

    /**
     * Print accounts by the date that it was opened in ascending order
     */
    public void printByDateOpen() {
        sortByDateopen();
        printAccounts();
    }

    /**
     * Prints accounts by the last name of each account in ascending order
     */
    public void printByLastName() {
        sortByLastName();
        printAccounts();
    }

    /**
     * Prints accounts
     */
    public void printAccounts() {
        for (int i = 0; i < size; i++) {
            System.out.println(database[i]);
        }
    }
}
