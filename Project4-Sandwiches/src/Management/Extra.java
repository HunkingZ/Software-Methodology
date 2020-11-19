package Management;

/**
 * Extra ingredient class
 *
 * @author Hanqing Zhao, Richard Xu
 */
public class Extra {
    String name;

    /**
     * Constructor to create an extra ingredient
     *
     * @param name Name of the extra ingredient
     */
    public Extra(String name) {
        this.name = name;
    }

    /**
     * Gets the information of the extra ingredient
     *
     * @return Name of the extra ingredient
     */
    @Override public String toString() {
        return name;
    }
}
