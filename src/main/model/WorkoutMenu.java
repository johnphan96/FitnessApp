package model;

import exceptions.MissingWorkoutException;
import persistence.Reader;
import persistence.Saveable;

import javax.swing.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Represents a menu of workout programs
public class WorkoutMenu extends JTextArea implements Saveable {
    private List<WorkoutProgram> workoutMenu;

    // EFFECTS: create empty workout menu
    public  WorkoutMenu() {
        workoutMenu = new ArrayList<>();
    }

    public void setWorkoutMenu(List<WorkoutProgram> workoutMenu) {
        this.workoutMenu = workoutMenu;
    }



    // EFFECTS: return total number of workouts in the menu
    public int getTotalWorkoutPrograms() {
        return workoutMenu.size();
    }

    // EFFECTS: return workout program with workoutName
    public WorkoutProgram getWorkoutProgram(String workoutName) throws MissingWorkoutException {
        WorkoutProgram workoutProgram = null;
        if (!listWorkoutNames().contains(workoutName)) {
            throw new MissingWorkoutException();
        }
        int indexOfWorkout = listWorkoutNames().indexOf(workoutName);
        workoutProgram = workoutMenu.get(indexOfWorkout);
        return workoutProgram;
    }

    // MODIFIES: this
    // EFFECTS: add workoutProgram to end of workoutMenu
    public void addWorkoutProgram(WorkoutProgram workoutProgram) {
        workoutMenu.add(workoutProgram);
    }

    // return list of workout programs in workoutMenu
    public List<WorkoutProgram> getWorkoutMenu() {
        return workoutMenu;
    }


    // EFFECTS: returns list of workout names in workoutMenu
    public List<String> listWorkoutNames() {
        List<String> workoutNames = new ArrayList<>();
        for (WorkoutProgram workoutProgram: workoutMenu) {
            workoutNames.add(workoutProgram.getWorkoutName());
        }
        return workoutNames;
    }

    @Override
    public void save(PrintWriter printWriter) {
        for (WorkoutProgram workoutProgram: workoutMenu) {
            printWriter.print(workoutProgram.getWorkoutName());
            printWriter.print(Reader.LINE_DELIMITER);
            printWriter.print(workoutProgram.isDone());
            printWriter.print(Reader.LINE_DELIMITER);
            List<Exercise> exercises = workoutProgram.getExerciseList();
            for (Exercise exercise: exercises) {
                printWriter.print(exercise.getExerciseName() + ":");
                printWriter.print(exercise.getSets() + ":");
                printWriter.print(exercise.getReps());
                if (!(exercises.indexOf(exercise) == exercises.size() - 1)) {
                    printWriter.print(",");
                }
            }
            printWriter.print("\n");
        }
    }

    // EFFECTS: removes workoutProgram from workoutMenu
    public void removeWorkoutProgram(WorkoutProgram workoutProgram) {
        workoutMenu.remove(workoutProgram);
    }
}
