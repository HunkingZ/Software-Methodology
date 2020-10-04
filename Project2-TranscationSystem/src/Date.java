public class Date {
    private int year;
    private int month;
    private int day;

    /**
     * Constructor for a date
     *
     * @param year The year
     * @param month The month
     * @param day The day
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Compares two dates
     *
     * @param date The other date to compare
     * @return 0 if same, 1 if later, -1 if earlier
     */
    public int compareTo(Date date) {
        //compare year
        if (year > date.year) { return 1; }
        if (year < date.year) { return -1; }

        //compare month
        if (month > date.month) { return 1; }
        if (month < date.month) { return -1; }

        //compare day
        if (day > date.day) { return 1; }
        if (day < date.day) { return -1; }

        //date is the same
        return 0;
    }

    /**
     * Prints the date
     *
     * @return Date in format mm/dd/yyyy
     */
    @Override
    public String toString() {
        return (month + "/" + day + "/" + year);
    }

    /**
     * List of valid dates ( January to December : 1 to 12 )
     *
     * @return True if the month has proper number of days
     */
    private boolean validDates() {
        if (month == Month.APR || month == Month.JUN || month == Month.SEP || month == Month.NOV) {
            return (day - DateSpecial.EVEN_DAYS <= 0);
        } else if (month == Month.JAN || month == Month.MAR || month == Month.MAY || month == Month.JUL
                    || (month == Month.AUG) || month == Month.OCT || month == Month.DEC) {
            return (day - DateSpecial.ODD_DAYS <= 0);
        } else return false;
    }

    /**
     * Checks to see if the date is on a leap year
     *
     * @return True if the year is a leap year
     */
    private boolean isLeapYear() {
        if (year % DateSpecial.LEAP == 0 && year % DateSpecial.CENTURY_NO_LEAP != 0
                || year % DateSpecial.CENTURY_4_LEAP == 0) { return true; }

        return false;
    }

    /**
     * Checks to see if the date is a real date
     *
     * @return True if the date is properly input
     */
    public boolean isValid() {
        if (month > Month.DEC) { return false; }

        if (isLeapYear()) {
            if (month == Month.FEB) { return (day - DateSpecial.FEB_DAYS + 1 <= 0); }
            else { return validDates(); }
        } else {
            if (month == Month.FEB) { return (day - DateSpecial.FEB_DAYS <= 0); }
            else { return validDates(); }
        }
    }
}
