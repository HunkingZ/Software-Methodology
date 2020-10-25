package management;

/**
 * Account abstract class that defines the type of account that will be opened.
 * Subclasses are: Savings, Checking, and Money Market.
 *
 * @author Hanqing Zhao, Richard Xu
 */
public abstract class Account {
    private final Date dateOpen;
    private Profile holder;
    private double balance;


    /**
     * Super constructor for the baseline of an account
     * This constructor will never be called -- only used as a non-abstract guideline for sub-classes
     *
     * @param holder The owner of the account
     * @param balance The balance of the account
     * @param dateopen The date that the account was opened
     */
    public Account(Profile holder, double balance, Date dateopen) {
        this.holder = holder;
        this.balance = balance;
        this.dateOpen = dateopen;
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

        String balanceString = String.format("%.2f", balance);
        return "*" + getType()
                + "*" + fullName
                + "* $" + balanceString
                + "*" + getDate()
                + getSpecial();
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
        return ((this.holder.equals(account.getHolder()))
                //&& (this.dateopen.compareTo(account.getDate()) == 0)
                && (this.getType().equals(account.getType()))
        );
    }

    /**
     * Checks the account type
     * Savings, Checking, or Money Market
     *
     * @return Account type as a string
     */
    public abstract String getType();

    /**
     * Increases money market withdrawal counter by one
     */
    public abstract void increase();

    /**
     * Date of account opening
     *
     * @return Date linked to account
     */
    public Date getDate () { return dateOpen; }

    /**
     * Last name of account holder
     *
     * @return Last name linked to account
     */
    public String getlName() { return holder.getlName(); }

    /**
     * First name of account holder
     *
     * @return First name linked to account
     */
    public String getfName() { return holder.getfName(); }

    /**
     * Total balance currently in the account
     *
     * @return Account balance
     */
    public double getBalance() { return this.balance; }

    /**
     * Account holder information
     *
     * @return Account holder
     */
    public Profile getHolder() { return this.holder; }

    /**
     * Calculate interest based on account type and data members
     *
     * @return Calculated account specified interest
     */
    public abstract double getInterest();

    /**
     * Gets special values regarding the account type
     *
     * @return String of account type specified unique characteristics
     */
    public abstract String getSpecial();

    public abstract String getSpecialValue();
}
