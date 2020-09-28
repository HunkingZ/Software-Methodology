public class Savings extends Account{
    private boolean isLoyal;
    double balance;

    @Override
    public double monthlyInterest() {
        if (isLoyal) {
            return 0.0035;
        } else {
            return 0.0025;
        }
    }

    @Override
    public double monthlyFee() {
        if (balance >= 300) {
            return 0;
        }
        return 5;
    }
}
