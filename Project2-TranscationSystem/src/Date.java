public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    //return 0, 1, or -1
    public int compareTo(Date date) {
        if (year > date.year) {
            return 1;
        }
        if (year < date.year) {
            return -1;
        }
        if (month > date.month) {
            return 1;
        }
        if (month < date.month) {
            return -1;
        }
        if (day > date.day) {
            return 1;
        }
        if (day < date.day) {
            return -1;
        }
        return 0;
    }

    //in the format mm/dd/yyyy
    public String toString() {
        return (month + "/" + day + "/" + year);
    }

    public boolean isValid() {
        if (month > 12) {
            return false;
        }
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0 && year % 3200 != 0) ){

            if (month == 2) {
                return (day - 29 <= 0);
            } else {
                return validDates();
            }
        } else {
            if (month == 2) {
                return (day - 28 <= 0);
            } else {
                return validDates();
            }
        }
    }

    private boolean validDates(){
        if (month == 2 || month == 4 || month ==6 || month == 9 || month == 11) {
            return (day - 30 <= 0);
        } else {
            return (day - 31 <= 0);
        }
    }

}
