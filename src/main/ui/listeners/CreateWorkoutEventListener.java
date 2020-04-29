package ui.listeners;

import ui.events.CreateWorkoutEvent;

import java.util.EventListener;

// Represents an event listener for create workout
public interface CreateWorkoutEventListener extends EventListener {

    // EFFECTS: shell method for main frame to handle events from workout window
    public void createWorkoutEventOccured(CreateWorkoutEvent event);
}
