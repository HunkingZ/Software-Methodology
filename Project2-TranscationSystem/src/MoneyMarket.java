public class MoneyMarket extends Account{
    private int withdrawals;

    private static final double INTEREST_RATE = 0.0065;

    public MoneyMarket(Profile holder, double balance, Date dateopen, int withdrawals) {
        super(holder, balance, dateopen);
        this.withdrawals = withdrawals;
    }

    /**
     * @return Monthly interest
     */
    @Override
    public double monthlyInterest() {
        return INTEREST_RATE;
    }

    /**
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
     * @return String that indicates the account type
     */
    @Override
    public String getType() {
        return "Money Market";
    }
}
