public class Savings extends Account{
    private boolean isLoyal;
    double balance;

    private static final double INTEREST_RATE = 0.0025;
    private static final double PROMO_RATE = 0.0035;

    @Override
    public double monthlyInterest() {
        if (isLoyal) {
            return PROMO_RATE;
        } else {
            return INTEREST_RATE;
        }
    }

    @Override
    public double monthlyFee() {
        if (balance >= 300) {
            return 0;
        }
        return 5;
    }

    /**
     * @return String that indicates the account type
     */
    @Override
    public String getType() {
        return "Savings";
    }
}
