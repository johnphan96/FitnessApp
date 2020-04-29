package ui.events;

import java.util.EventObject;

// Represents an event object raised when buttons in view workout window is clicked
public class ViewWorkoutEvent extends EventObject {
    private String viewWorkoutName;
    private String deleteWorkoutName;
    private String editWorkoutName;

    // REQUIRES: option is an int [1,3]
    // EFFECTS: makes an event object
    public ViewWorkoutEvent(Object source, String workoutName, int option) {
        super(source);
        if (option == 1) {
            this.viewWorkoutName = workoutName;
        } else if (option == 2) {
            this.deleteWorkoutName = workoutName;
        } else if (option == 3) {
            this.editWorkoutName = workoutName;
        }

    }

    public String getDeleteWorkoutName() {
        return deleteWorkoutName;
    }

    public void setDeleteWorkoutName(String deleteWorkoutName) {
        this.deleteWorkoutName = deleteWorkoutName;
    }

    public String getEditWorkoutName() {
        return editWorkoutName;
    }

    public void setEditWorkoutName(String editWorkoutName) {
        this.editWorkoutName = editWorkoutName;
    }

    public String getViewWorkoutName() {
        return viewWorkoutName;
    }

    public void setViewWorkoutName(String viewWorkoutName) {
        this.viewWorkoutName = viewWorkoutName;
    }
}
