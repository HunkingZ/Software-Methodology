import java.util.StringTokenizer;

public class Profile {
    private String fname;
    private String lname;

    public Profile(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }
    public String getfName() { return fname; }
    public String getlName() {
        return lname;
    }

    public boolean equals(Profile profile) {
        return (this.fname.equals(profile.getfName()) && this.lname.equals(profile.getlName()));
    }

}
