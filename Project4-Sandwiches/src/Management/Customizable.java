package Management;

/**
 * Interface class to allow adding and removing of items regarding a sandwich
 *
 * @author Hanqing Zhao, Richard Xu
 */
public interface Customizable {
    /**
     * Adds an item to the sandwich
     *
     * @param obj Item to be added
     * @return True if added, False if otherwise
     */
    boolean add(Object obj);

    /**
     * Removes an item from the sandwich
     *
     * @param obj Item to be removed
     * @return True if removed, False if otherwise
     */
    boolean remove(Object obj);
}
