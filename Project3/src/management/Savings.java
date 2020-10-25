package management;

/**
 * Sub-class of abstract class Account.
 * Account type of savings.
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Savings extends Account{
    private boolean isLoyal;

    private static final double INTEREST_RATE = 0.0025 / 12;
    private static final double PROMO_RATE = 0.0035 / 12;


    /**
     * Constructor for a savings account
     *
     * @param holder The owner of the account
     * @param balance The balance of the account
     * @param dateopen The date that the account was opened
     * @param isLoyal True if loyalty program on or False if loyalty program off
     */
    public Savings(Profile holder, double balance, Date dateopen, boolean isLoyal) {
        super(holder, balance, dateopen);
        this.isLoyal = isLoyal;
    }

    /**
     * Monthly interest added to the account dependent on account type and specifications
     *
     * @return Monthly interest
     */
    @Override
    public double monthlyInterest() {
        if (isLoyal) {
            return PROMO_RATE;
        } else {
            return INTEREST_RATE;
        }
    }

    /**
     * Monthly fee decreased from the account dependent on account type and specifications
     *
     * @return Monthly fee
     */
    @Override
    public double monthlyFee() {
        final int MONTHLY_FEE = 5;
        final int WAIVE_THRESHOLD = 300;

        double balance = super.getBalance();

        if (balance >= WAIVE_THRESHOLD) { return 0; }
        return MONTHLY_FEE;
    }

    /**
     * Calculates interest based on account type specifications
     *
     * @return Calculated interest
     */
    @Override
    public double getInterest() {
        return getBalance() * monthlyInterest();
    }

    /**
     * Gets special values regarding the account type
     *
     * @return Indication of loyalty program if true or nothing if false
     */
    @Override
    public String getSpecial() {
        if (isLoyal) {
            return "*special Savings account*";
        } else {
            return "";
        }
    }

    @Override
    public String getSpecialValue() {
        return "" + isLoyal;
    }

    /**
     * Indicates the account type
     *
     * @return Account type as a string
     */
    @Override
    public String getType() { return "Savings"; }

    /**
     * Does nothing for this class
     */
    @Override
    public void increase() { }
}
