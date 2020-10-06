public class MoneyMarket extends Account{
    private int withdrawals;
    int withdrawals_per_statement;
    double balance;

    private static final double INTEREST_RATE = 0.0065;

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
        if (balance >= 2500 && withdrawals_per_statement <= 6) {
            return 0;
        }
        return 12;
    }

    /**
     * @return String that indicates the account type
     */
    @Override
    public String getType() {
        return "Money Market";
    }
}
