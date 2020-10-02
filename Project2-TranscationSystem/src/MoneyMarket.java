public class MoneyMarket extends Account{
    private int withdrawals;
    int withdrawals_per_statement;
    double balance;

    /**
     * @return Monthly interest
     */
    @Override
    public double monthlyInterest() {
        return 0.0065;
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
}
