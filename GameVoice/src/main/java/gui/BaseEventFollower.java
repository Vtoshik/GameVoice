package gui;

import javafx.event.Event;

import java.util.ArrayList;
import java.util.List;
/**
 * The BaseEventFollower class provides basic functionality for managing events.
 */
public class BaseEventFollower {
    /**
     * List of events associated with the follower.
     */
    protected List<Event> events;
    /**
     * Constructs a new BaseEventFollower with an empty list of events.
     */
    public BaseEventFollower() {
        this.events = new ArrayList<>();
    }
    /**
     * Adds an event to the list of events.
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        events.add(event);
    }
    /**
     * Removes an event from the list of events.
     * @param event The event to be removed.
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }
}
