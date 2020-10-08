/**
 * Sub-class of abstract class Account.
 * Account type of checking.
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Checking extends Account {
    private boolean directDeposit;

    private static final double INTEREST_RATE = 0.0005;

    public Checking(Profile holder, double balance, Date dateopen, boolean directDeposit, Type accountType) {
        super(holder, balance, dateopen, accountType);
        this.directDeposit = directDeposit;
    }

    @Override
    public double monthlyInterest() {
        return INTEREST_RATE;
    }

    @Override
    public double monthlyFee() {
        final int MONTHLY_FEE = 25;
        final int WAIVE_THRESHOLD = 1500;

        double balance = super.getBalance();

        if (directDeposit || balance >= WAIVE_THRESHOLD) { return 0; }
        return MONTHLY_FEE;
    }

    /**
     * @return String that indicates the account type
     */
    @Override
    public String getType() {
        return "Checking";
    }
}
