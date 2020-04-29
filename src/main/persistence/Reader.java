package persistence;

import exceptions.NameLengthException;
import model.Exercise;
import model.WorkoutProgram;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read workout program data from a file
public class Reader {
    public static final String LINE_DELIMITER = "\t";
    public static final String EXERCISE_COMPONENT_DELIMITER = ",";
    public static final String EXERCISE_DELIMITER = ":";
    private static File file;

    // EFFECTS: constructs a reader that takes in a file path
    public Reader(File file) {
        this.file = file;
    }

    // EFFECTS: returns a list of accounts parsed from file;
    // throws IOException if an exception is raised when opening / reading from a file
    public static List<WorkoutProgram> readWorkoutPrograms() throws IOException, NameLengthException {
        List<String> fileContent = Reader.readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string contains data for one workout program
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of workout programs parsed from list of strings that was extracted from fileContent;
    // each string represents a workout program
    private static List<WorkoutProgram> parseContent(List<String> fileContent) throws NameLengthException {
        List<WorkoutProgram> workoutPrograms = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            workoutPrograms.add(parseWorkoutProgram(lineComponents));
        }
        return workoutPrograms;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(LINE_DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 3 where element 0 represents the workout program's name,
    // element 1 represents the completion status of the workout program,
    // element 2 represents the list of exercises in the workout program
    // EFFECTS: returns a workout program constructed from components
    private static WorkoutProgram parseWorkoutProgram(List<String> components) throws NameLengthException {
        String name = components.get(0);
        WorkoutProgram workoutProgram = null;
        workoutProgram = new WorkoutProgram(name);
        Boolean done = Boolean.parseBoolean(components.get(1));
        workoutProgram.setDone(done);
        for (Exercise exercise: parseExerciseComponent(components.get(2))) {
            workoutProgram.addExercise(exercise);
        }
        return workoutProgram;
    }

    // EFFECTS: returns a list of exercises constructed from parsing the given exerciseComponent string
    private static List<Exercise> parseExerciseComponent(String exerciseComponent) {
        List<Exercise> exercises = new ArrayList<>();
        ArrayList<String> exerciseSplits = splitExerciseComponent(exerciseComponent);
        for (String exerciseSplit: exerciseSplits) {
            ArrayList<String> exerciseAttributes = splitExercise(exerciseSplit);
            Exercise exercise = parseExerciseAttributes(exerciseAttributes);
            exercises.add(exercise);
        }
        return exercises;
    }

    // EFFECTS: returns a list of strings obtained by splitting the exerciseComponent on
    private static ArrayList<String> splitExerciseComponent(String exerciseComponent) {
        String[] exerciseSplits = exerciseComponent.split(EXERCISE_COMPONENT_DELIMITER);
        return new ArrayList<>(Arrays.asList(exerciseSplits));
    }


    // EFFECTS: returns a list of strings obtained by splitting the exerciseSplit
    private static ArrayList<String> splitExercise(String exerciseSplit) {
        String[] exerciseAttributes = exerciseSplit.split(EXERCISE_DELIMITER);
        return new ArrayList<>(Arrays.asList(exerciseAttributes));
    }

    // REQUIRES: exerciseAttributes has size 3 where element 0 is the exercise name,
    // element 1 is the number of sets
    // element 2 is the number of reps
    // EFFECTS: returns an exercise constructed from attributes
    private static Exercise parseExerciseAttributes(List<String> exerciseAttributes) {
        String name = exerciseAttributes.get(0);
        int sets = Integer.parseInt(exerciseAttributes.get(1));
        int reps = Integer.parseInt(exerciseAttributes.get(2));
        Exercise exercise = new Exercise(name, sets, reps);
        return exercise;
    }
}
