public class Checking extends Account {
    private boolean directDeposit;

    @Override
    public double monthlyInterest() {
        return 0.0005;
    }

    @Override
    public double monthlyFee() {
        if (directDeposit) {
            return 0;
        } else {
            return 25;
        }
    }
}
