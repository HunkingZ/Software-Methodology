public class Checking extends Account {
    private boolean directDeposit;

    private static final double INTEREST_RATE = 0.0005;

    @Override
    public double monthlyInterest() {
        return INTEREST_RATE;
    }

    @Override
    public double monthlyFee() {
        if (directDeposit) {
            return 0;
        } else {
            return 25;
        }
    }

    /**
     * @return String that indicates the account type
     */
    @Override
    public String getType() {
        return "Checking";
    }
}
