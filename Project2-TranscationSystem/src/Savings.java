/**
 * Sub-class of abstract class Account.
 * Account type of savings.
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Savings extends Account{
    private boolean isLoyal;

    private static final double INTEREST_RATE = 0.0025;
    private static final double PROMO_RATE = 0.0035;

    public Savings(Profile holder, double balance, Date dateopen, boolean isLoyal) {
        super(holder, balance, dateopen);
        this.isLoyal = isLoyal;
    }

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
        final int MONTHLY_FEE = 5;
        final int WAIVE_THRESHOLD = 300;

        double balance = super.getBalance();

        if (balance >= WAIVE_THRESHOLD) { return 0; }
        return MONTHLY_FEE;
    }

    /**
     * @return String that indicates the account type
     */
    @Override
    public String getType() {
        return "Savings";
    }
}
