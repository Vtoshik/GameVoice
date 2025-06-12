package gui;

import java.util.ArrayList;
import java.util.List;
/**
 * The Event_follower class represents an event follower that receives and processes event notifications.
 */
public class Event_follower extends BaseEventFollower implements Follower {
    /**
     * Informs about an event by printing information about the received vote.
     * @param category The category of the event.
     * @param vote The vote associated with the event.
     */
    @Override
    public void inform(String category, String vote) {
        System.out.println("Vote received for category: " + category + ", Vote: " + vote);
    }
}
