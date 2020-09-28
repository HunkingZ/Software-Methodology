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

    }

    public abstract double monthlyInterest() {

    }

    public abstract double monthlyFee() {

    }

    public Date getDate () {
        return dateopen;
    }

    public Profile getName() {
        return holder;
    }
}
