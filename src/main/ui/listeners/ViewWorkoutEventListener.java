package ui.listeners;

import ui.events.ViewWorkoutEvent;

import java.util.EventListener;

// Represents an event listener for view workout window
public interface ViewWorkoutEventListener extends EventListener {

    // EFFECTS: shell method for main frame to handle events from view workout window
    public void viewWorkoutEventOccured(ViewWorkoutEvent event);
}
