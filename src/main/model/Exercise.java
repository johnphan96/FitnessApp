package model;

// Represents a workout exercise with a name, sets, reps, and status
public class Exercise {
    private String exerciseName;    // Name of exercise
    private int sets;               // Number of sets the user wants to do
    private int reps;               // Number of repetitions per set
    private boolean done;           // True if user performed exercise

    // REQUIRES: exercise name of non-zero length, number of sets and reps is > 0
    // EFFECTS: name of exercise is set to exerciseName, number of sets is set to numSets, and number of reps is set to
    // numReps
    public Exercise(String exerciseName, int numSets, int numReps) {
        this.exerciseName = exerciseName;
        sets = numSets;
        reps = numReps;
        done = false;
    }

    // EFFECTS: return number of reps of exercise
    public int getReps() {
        return reps;
    }

    // EFFECTS: return number of sets of exercise
    public int getSets() {
        return sets;
    }

    // EFFECTS:
    public String getExerciseName() {
        return exerciseName;
    }

    // return boolean value of done
    public boolean isDone() {
        return done;
    }

    // MODIFIES: this
    // EFFECTS: set exercise name
    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    // MODIFIES: this
    // EFFECTS: set number of reps
    public void setReps(int reps) {
        this.reps = reps;
    }

    // MODIFIES: this
    // EFFECTS: set number of sets
    public void setSets(int sets) {
        this.sets = sets;
    }

    // MODIFIES: this
    // EFFECTS: change value of done
    public void setDone(Boolean done) {
        this.done = done;
    }
}
