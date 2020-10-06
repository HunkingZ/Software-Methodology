public abstract class Account {
    private Profile holder;
    private double balance;
    private Date dateopen;

    //decrease the balance by amount
    public void debit(double amount) {
        balance -= amount;
    }
    //increase the balance by amount
    public void credit(double amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        String fullName = holder.getfName() + " " + holder.getlName();

        String accountInfo = "*" + getType()
                            + "*" + fullName
                            + "* " + balance
                            + "*" + getDate()
                            + "*";

        return accountInfo;
    }

    public abstract double monthlyInterest();

    public abstract double monthlyFee();

    public boolean equals(Account account) {
        return this.holder.equals(account.holder) && this.getClass() == account.getClass();
    }

    /**
     * @return String that indicates the account type
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

    public boolean updateBalance(double balance) {
        if (this.balance + balance < 0) { return false; }

        this.balance += balance;
        return true;
    }
}
