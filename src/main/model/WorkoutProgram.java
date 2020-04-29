package model;

import exceptions.MissingExerciseException;
import exceptions.NameLengthException;

import java.util.ArrayList;
import java.util.List;

// Represents a workout program having a name, list of exercises, and status
public class WorkoutProgram {
    private String workoutName;             // Name of workout program
    private List<Exercise> exerciseList;    // List of exercises in the workout
    private boolean done;                   // True if all exercises are performed and workout is finished

    // EFFECTS: name of workout is set to workoutName
    public WorkoutProgram(String workoutName) throws NameLengthException {
        if (workoutName.length() == 0) {
            throw new NameLengthException();
        }
        this.workoutName = workoutName;
        exerciseList = new ArrayList<>();
        done = false;
    }

    // MODIFIES: this
    // EFFECTS: add exercise to exerciseList
    public void addExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }

    // EFFECTS: return total number of exercises in workout program
    public int getTotalExercises() {
        return exerciseList.size();
    }

    // EFFECTS: return workout name
    public String getWorkoutName() {
        return workoutName;
    }

    // EFFECTS: return list of exercises in workout program
    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    // EFFECTS: return boolean value of done
    public boolean isDone() {
        return done;
    }

    // MODIFIES: this
    // EFFECTS: change value of done
    public void setDone(Boolean done) {
        this.done = done;
    }

    // MODIFIES: this
    // EFFECTS: set name of workout
    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    // EFFECTS: returns list of exercise names
    public List<String> getExerciseNames() {
        List<String> exerciseNames = new ArrayList<>();
        for (Exercise exercise: exerciseList) {
            exerciseNames.add(exercise.getExerciseName());
        }
        return exerciseNames;
    }

    // EFFECTS: removes exercise with exerciseName from exerciseList
    public void removeExercise(String exerciseName) throws MissingExerciseException  {
        if (!getExerciseNames().contains(exerciseName)) {
            throw new MissingExerciseException();
        }
        int indexOfExercise = getExerciseNames().indexOf(exerciseName);
        exerciseList.remove(indexOfExercise);
    }

}
