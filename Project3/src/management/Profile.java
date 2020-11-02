package management;

public class Profile {
    private String fname;
    private String lname;

    /**
     * Constructor for profiles
     * @param fname first name
     * @param lname last name
     */
    public Profile(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    /**
     * first name of holder
     * @return first name
     */
    public String getfName() { return fname; }

    /**
     * last name of holder
     * @return last name
     */
    public String getlName() { return lname; }

    /**
     * compares two profiles
     * @param profile
     * @return true if the same, false if otherwise
     */
    public boolean equals(Profile profile) {
        return (this.fname.equals(profile.getfName()) && this.lname.equals(profile.getlName()));
    }

}
