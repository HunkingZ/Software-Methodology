package management;

/**
 * Sub-class of abstract class Account.
 * Account type of checking.
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Checking extends Account {
    private boolean directDeposit;

    private static final double INTEREST_RATE = 0.0005 / 12;

    /**
     * Constructor for a checking account
     *
     * @param holder The owner of the account
     * @param balance The balance of the account
     * @param dateopen The date that the account was opened
     * @param directDeposit True if direct deposit is enabled or False if otherwise
     */
    public Checking(Profile holder, double balance, Date dateopen, boolean directDeposit) {
        super(holder, balance, dateopen);
        this.directDeposit = directDeposit;
    }

    /**
     * Monthly interest added to the account dependent on account type and specifications
     *
     * @return Monthly interest
     */
    @Override
    public double monthlyInterest() { return INTEREST_RATE; }

    /**
     * Monthly fee decreased from the account dependent on account type and specifications
     *
     * @return Monthly fee
     */
    @Override
    public double monthlyFee() {
        final int MONTHLY_FEE = 25;
        final int WAIVE_THRESHOLD = 1500;

        double balance = super.getBalance();

        if (directDeposit || balance >= WAIVE_THRESHOLD) { return 0; }
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
     * @return Indication of direct deposit if true or nothing if false
     */
    @Override
    public String getSpecial() {
        if (directDeposit) {
            return "*direct deposit account*";
        } else {
            return "";
        }
    }

    /**
     * Indicates the account type
     *
     * @return Account type as a string
     */
    @Override
    public String getType() { return "Checking"; }

    /**
     * Does nothing for this class
     */
    @Override
    public void increase() { }
}
