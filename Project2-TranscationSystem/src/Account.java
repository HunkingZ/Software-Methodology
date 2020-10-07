/**
 * Account abstract class that defines the type of account that will be opened.
 * Subclasses are: Savings, Checking, and Money Market.
 *
 * @author Hanqing Zhao, Richard Xu
 */
public abstract class Account {
    private Profile holder;
    private double balance;
    private Date dateopen;

    public Account(Profile holder, double balance, Date dateopen) {
        this.holder = holder;
        this.balance = balance;
        this.dateopen = dateopen;
    }

    /**
     * Decrease the account balance by a certain amount
     *
     * @param amount The amount to remove from the account
     */
    public void debit(double amount) { balance -= amount; }

    /**
     * Increase the account balance by a certain amount
     *
     * @param amount The amount to add to the account
     */
    public void credit(double amount) {
        balance += amount;
    }

    /**
     * @return String of *Account type*Full name* $balance*account open date*
     */
    @Override
    public String toString() {
        String fullName = holder.getfName() + " " + holder.getlName();

        return "*" + getType()
                + "*" + fullName
                + "* $" + balance
                + "*" + getDate()
                + "*";
    }

    /**
     * Monthly interest added to the account dependent on account type and specifications
     *
     * @return Monthly interest
     */
    public abstract double monthlyInterest();

    /**
     * Monthly fee decreased from the account dependent on account type and specifications
     *
     * @return Monthly fee
     */
    public abstract double monthlyFee();

    /**
     * Checks if two accounts are exactly the same
     *
     * @param account The account to compare with
     * @return True if accounts match | False if accounts are different
     */
    public boolean equals(Account account) {
        return (this.holder.equals(account.holder))
                && (this.getClass() == account.getClass());
    }

    /**
     * Checks the account type
     * Savings, Checking, or Money Market
     *
     * @return Account type as a string
     */
    public abstract String getType();

    public Date getDate () {
        return dateopen;
    }
    public String getlName() {
        return holder.getlName();
    }
    public String getfName() {
        return holder.getfName();
    }

    public double getBalance() {
        return this.balance;
    }

    /*
    public boolean updateBalance(double balance) {
        if (this.balance + balance < 0) { return false; }

        this.balance += balance;
        return true;
    }
     */
}
