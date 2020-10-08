/**
 * Sub-class of abstract class Account.
 * Account type of money market.
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class MoneyMarket extends Account{
    private int withdrawals;

    private static final double INTEREST_RATE = 0.0065;

    /**
     * Constructor for a money market account
     *
     * @param holder The owner of the account
     * @param balance The balance of the account
     * @param dateopen The date that the account was opened
     */
    public MoneyMarket(Profile holder, double balance, Date dateopen) {
        super(holder, balance, dateopen);
        this.withdrawals = 0;
    }

    /**
     * Monthly interest added to the account dependent on account type and specifications
     *
     * @return Monthly interest
     */
    @Override
    public double monthlyInterest() {
        return INTEREST_RATE;
    }

    /**
     * Monthly fee decreased from the account dependent on account type and specifications
     *
     * @return Monthly fee
     */
    @Override
    public double monthlyFee() {
        final int MONTHLY_FEE = 12;
        final int WAIVE_THRESHOLD = 2500;
        final int WITHDRAWAL_THRESHOLD = 6;

        double balance = super.getBalance();

        if (balance >= WAIVE_THRESHOLD && withdrawals <= WITHDRAWAL_THRESHOLD) { return 0; }
        return MONTHLY_FEE;
    }

    /**
     * Updates withdrawal counter for this instance
     */
    public void update() { this.withdrawals++; }

    /**
     * @return String that indicates the account type
     */
    @Override
    public String getType() {
        return "Money Market";
    }
}
