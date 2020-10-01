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
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
    }

    private void grow() {
        capacity += 5;
        accounts = Arrays.copyOf(accounts, capacity);
    }

    //return false if account exists
    public boolean add(Account account) {
        if (find(account) != -1) {
            return false;
        }
        accounts[size++] = account;
        if (size >= capacity) {
            grow();
        }
        return true;
    }

    //return false if account doesn't exist
    public boolean remove(Account account) {
        int indexOfAccount = find(account);
        if (indexOfAccount == -1) {
            return false;
        } else {
            accounts[indexOfAccount] = accounts[--size];
            accounts[size] = null;
            return true;
        }

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

    //sort in ascending order
    public void printByDateOpen() {

    }

    public void printByLastName() {

    }

    public void printAccounts() {

    }
}
