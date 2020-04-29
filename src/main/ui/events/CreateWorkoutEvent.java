package ui.events;

import java.util.EventObject;

// Represents an event object raised when buttons in create workout window is clicked
public class CreateWorkoutEvent extends EventObject {
    private String workoutName;
    private String exerciseName;
    private String sets;
    private String reps;

    // EFFECTS: makes an event object
    public CreateWorkoutEvent(Object source) {
        super(source);
    }

    // EFFECTS: makes an event object with workoutName as param
    public CreateWorkoutEvent(Object source, String workoutName) {
        super(source);
        this.workoutName = workoutName;
    }

    // EFFECTS: makes an event object with exerciseName, sets, reps as param
    public CreateWorkoutEvent(Object source, String exerciseName, String sets, String reps) {
        super(source);
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }
}
