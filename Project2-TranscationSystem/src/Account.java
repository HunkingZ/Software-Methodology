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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(holder);
        sb.append(balance);
        //sb.append()
        return sb.toString();
    }

    public abstract double monthlyInterest();

    public abstract double monthlyFee();

    public boolean equals(Account account) {
        return this.holder.equals(account.holder) && this.getClass() == account.getClass();
    }
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
