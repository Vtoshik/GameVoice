package gui;
/**
 * The Follower interface defines methods for informing about events.
 */
public interface Follower {
    /**
     * Informs about an event.
     * @param category The category of the event.
     * @param vote The vote associated with the event.
     */
    public void inform(String category, String vote);
}
