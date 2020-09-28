import java.util.Arrays;
import java.util.Comparator;

public class AccountDatabase {
    private Account[] accounts;
    private int size = 0;
    private int capacity = 5;

    public AccountDatabase() {
        accounts = new Account[capacity];
    }

    private int find(Account account) {
        for (Account acc : accounts) {
            if ()
        }
    }

    private void grow() {
        capacity += 5;
        accounts = Arrays.copyOf(accounts, capacity);
    }

    //return false if account exists
    public boolean add(Account account) {

    }

    //return false if account doesn't exist
    public boolean remove(Account account) {

    }

    public boolean deposit(Account account, double amount) {

    }

    public int withdrawal(Account account, double amoun) {

    }

    private void sortByDateopen() {
        Arrays.sort(accounts, new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    //sort in ascending order
    private void sortByLastName() {
        Arrays.sort(accounts, new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                String name1 = o1.getName().
                String name2 = o2.getName();
            }
        });
    }

    //sort in ascending order
    public void printByDateOpen() {

    }

    public void printByLastName() {

    }

    public void printAccounts() {

    }
}
